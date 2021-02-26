package gui;

import lombok.SneakyThrows;
import model.IngredientEnum;
import model.Sandwich;
import model.SandwichShop;
import model.SandwichType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class SandwichStoreGUI extends JFrame{
    private JTextArea priceTextArea;
    private JTabbedPane tabbedPane1;
    private JComboBox<String> sandwichTypeCombo;
    private JComboBox<String> orderSandwichType;
    private JButton createSandwichButton;
    private JButton addSandwichToOrderButton;
    private JTable availableSandwiches;
    private JTable selectedSandwiches;
    private JButton orderButton;
    private JPanel contentPane;
    private JButton cancelButton;
    private JRadioButton hamBtn;
    private JRadioButton salamiBtn;
    private JRadioButton sottillettaBtn;
    private JRadioButton mozzarellaBtn;
    private JRadioButton mayoBtn;
    private JRadioButton mustardBtn;
    private JTextArea nameSandwich;
    private JLabel totalAmount;

    private ButtonGroup sauceGroup;
    private ButtonGroup fillingGroup;
    private ButtonGroup cheeseGroup;


    private SandwichShop sandwichShop;

    public SandwichStoreGUI() {
        initilizeVailables();
        add(contentPane);
    }

    private void initilizeVailables() {
        sandwichShop = new SandwichShop();
        initNewSandwich();
        initSandwichType(orderSandwichType);
        loadSandwiches();
        loadOrdersInUi();
        addSandwichToOrderButton.addActionListener(this::addSelectedSandwichesToOrder);
    }

    private void loadSandwiches() {
        availableSandwiches.setModel(new DefaultTableModel(sandwichShop.getSandwichesOnType(SandwichType.DINNER), Sandwich.sandwichData()));
    }

    private void loadOrdersInUi() {
        selectedSandwiches.setModel(new DefaultTableModel(sandwichShop.getOrderSandwiches(), Sandwich.sandwichData()));
        totalAmount.setText(sandwichShop.buildOrder().getTotalAmount().toString());
    }

    private void addSelectedSandwichesToOrder(ActionEvent actionEvent) {
        int[] selectedRows = availableSandwiches.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            Sandwich sandwich = new Sandwich();
            sandwich.setId(Long.parseLong((String) availableSandwiches.getModel().getValueAt(selectedRows[i], 0)));
            sandwich.setName((String) availableSandwiches.getModel().getValueAt(selectedRows[i], 1));
            sandwich.setType(SandwichType.valueOf(((String) availableSandwiches.getModel().getValueAt(selectedRows[i], 2)).toUpperCase()));
            sandwich.setPrice(Double.parseDouble((String) availableSandwiches.getModel().getValueAt(selectedRows[i], 3)));
            sandwichShop.addSandwichTOOrder(sandwich);
        }
        loadOrdersInUi();
    }

    private void initNewSandwich() {
        initIngredients();
        initSandwichType(sandwichTypeCombo);
        createSandwichButton.addActionListener(this::createSandwich);
    }

    private void initSandwichType(JComboBox<String> sandwichTypeCombo) {
        sandwichTypeCombo.addItem(SandwichType.DINNER.getName());
        sandwichTypeCombo.addItem(SandwichType.LUNCH.getName());

        orderSandwichType.addActionListener(this::changeSandwichType);

    }

    private void changeSandwichType(ActionEvent actionEvent) {
        SandwichType sandwichType = SandwichType.valueOf(orderSandwichType.getSelectedItem().toString().toUpperCase());
        System.out.println("Change Type");
        availableSandwiches.setModel(new DefaultTableModel(sandwichShop.getSandwichesOnType(sandwichType), Sandwich.sandwichData()));
    }

    private void initIngredients() {
        sauceGroup = new ButtonGroup();
        fillingGroup = new ButtonGroup();
        cheeseGroup = new ButtonGroup();

        hamBtn.setActionCommand(IngredientEnum.HAM.toString());
        salamiBtn.setActionCommand(IngredientEnum.SALAMI.toString());
        sottillettaBtn.setActionCommand(IngredientEnum.SOTTILETTA.toString());
        mozzarellaBtn.setActionCommand(IngredientEnum.MOZZARELLA.toString());
        mayoBtn.setActionCommand(IngredientEnum.MAYONNAISE.toString());
        mustardBtn.setActionCommand(IngredientEnum.MUSTARD.toString());

        bindIngredients();
    }

    @SneakyThrows
    private void createSandwich(ActionEvent e) {

        Double price = Double.parseDouble(priceTextArea.getText());
        SandwichType type = SandwichType.valueOf(((String) sandwichTypeCombo.getSelectedItem()).toUpperCase());
        String name = nameSandwich.getText();
        IngredientEnum sauce = IngredientEnum.valueOf(sauceGroup.getSelection().getActionCommand());
        IngredientEnum filling = IngredientEnum.valueOf(fillingGroup.getSelection().getActionCommand());
        IngredientEnum cheese = IngredientEnum.valueOf(cheeseGroup.getSelection().getActionCommand());

        Sandwich sandwich = new Sandwich();
        sandwich.setName(name);
        sandwich.setType(type);
        sandwich.setPrice(price);
        sandwich.addIngredient(sauce);
        sandwich.addIngredient(filling);
        sandwich.addIngredient(cheese);
        System.out.println(sandwich.toString());
        sandwich.saveSandwich();
        loadSandwiches();
        JOptionPane.showMessageDialog(this, "Created successfully");
    }

    private void bindIngredients() {

        sauceGroup.add(mayoBtn);
        sauceGroup.add(mustardBtn);

        fillingGroup.add(salamiBtn);
        fillingGroup.add(hamBtn);

        cheeseGroup.add(mozzarellaBtn);
        cheeseGroup.add(sottillettaBtn);
    }
}
