package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField, categoryField, costField, directorField, lengthField;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen, "Add DVD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(6, 2, 10, 10));

        center.add(new JLabel("Title:"));
        titleField = new JTextField();
        center.add(titleField);

        center.add(new JLabel("Category:"));
        categoryField = new JTextField();
        center.add(categoryField);

        center.add(new JLabel("Cost:"));
        costField = new JTextField();
        center.add(costField);

        center.add(new JLabel("Director:"));
        directorField = new JTextField();
        center.add(directorField);

        center.add(new JLabel("Length:"));
        lengthField = new JTextField();
        center.add(lengthField);

        JButton submitButton = new JButton("Add DVD");
        submitButton.addActionListener(e -> processAddingItem());
        center.add(new JPanel());
        center.add(submitButton);

        return center;
    }

    @Override
    protected void processAddingItem() {
        try {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());
            String director = directorField.getText();
            int length = Integer.parseInt(lengthField.getText());

            DigitalVideoDisc newDVD = new DigitalVideoDisc(title, category, director, length, cost);

            store.addMedia(newDVD);
            JOptionPane.showMessageDialog(this, "DVD added successfully!");

            this.dispose();
            storeScreen.setVisible(true);
            storeScreen.revalidate();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format (Cost/Length).", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}