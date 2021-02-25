package gui;

import lombok.SneakyThrows;
import model.IngredientEnum;
import model.Sandwich;
import model.SandwichShop;
import model.SandwichType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class SandwichStoreGUI extends JFrame{
    private JTextArea priceTextArea;
    private JTabbedPane tabbedPane1;
    private JComboBox<String> sandwichTypeCombo;
    private JComboBox<String> orderSandwichType;
    private JButton createSandwichButton;
    private JButton addSandwichToOrderButton;
    private JTextField totalAmount;
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

    private ButtonGroup sauceGroup;
    private ButtonGroup fillingGroup;
    private ButtonGroup cheeseGroup;


    private SandwichShop sandwichShop;

    public SandwichStoreGUI() {
        initilizeVailables();

        Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3"},
                { "Row2-Column1", "Row2-Column2", "Row2-Column3"} };
        Object columnNames[] = { "Column One", "Column Two", "Column Three"};
        selectedSandwiches.setModel(new DefaultTableModel(rowData, columnNames));

        add(contentPane);
    }

    private void initilizeVailables() {
        sandwichShop = new SandwichShop();

        initNewSandwich();
        initSandwichType(orderSandwichType);
        availableSandwiches.setModel(new DefaultTableModel(sandwichShop.getSandwichesOnType(SandwichType.DINNER), Sandwich.sandwichData()));
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
