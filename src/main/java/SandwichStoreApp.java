import gui.SandwichStoreGUI;

import javax.swing.*;

public class SandwichStoreApp {
    public static void main(String[] args) {
        SandwichStoreGUI sandwichStoreGUI = new SandwichStoreGUI();
        sandwichStoreGUI.setTitle("client.Sandwich store");
        sandwichStoreGUI.pack();
        sandwichStoreGUI.setVisible(true);
        sandwichStoreGUI.setLocationRelativeTo(null);
        sandwichStoreGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
