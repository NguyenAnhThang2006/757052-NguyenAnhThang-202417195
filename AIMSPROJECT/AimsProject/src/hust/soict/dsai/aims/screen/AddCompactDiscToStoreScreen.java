package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField, categoryField, costField, directorField, artistField;

    public AddCompactDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen, "Add CD to Store");
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

        center.add(new JLabel("Artist:"));
        artistField = new JTextField();
        center.add(artistField);

        JButton submitButton = new JButton("Add CD");
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
            String artist = artistField.getText();

            CompactDisc newCD = new CompactDisc(title, category, cost, director, artist);

            store.addMedia(newCD);
            JOptionPane.showMessageDialog(this, "CD added successfully!");

            this.dispose();
            storeScreen.setVisible(true);
            storeScreen.revalidate();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}