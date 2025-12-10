package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField, categoryField, costField, authorsField;

    public AddBookToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super(store, cart, storeScreen, "Add Book to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(6, 2, 10, 10));

        // 1. Title
        center.add(new JLabel("Title:"));
        titleField = new JTextField();
        center.add(titleField);

        // 2. Category
        center.add(new JLabel("Category:"));
        categoryField = new JTextField();
        center.add(categoryField);

        // 3. Cost
        center.add(new JLabel("Cost:"));
        costField = new JTextField();
        center.add(costField);

        // 4. Authors (Dùng dấu phẩy để phân tách)
        center.add(new JLabel("Authors (comma separated):"));
        authorsField = new JTextField();
        center.add(authorsField);

        // 5. Nút Submit
        JButton submitButton = new JButton("Add Book");
        submitButton.addActionListener(e -> processAddingItem());
        center.add(new JPanel()); // Ô trống
        center.add(submitButton);

        return center;
    }

    @Override
    protected void processAddingItem() {
        try {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());
            String authorsText = authorsField.getText();

            // Chuyển chuỗi tác giả thành List<String>
            List<String> authors = Arrays.asList(authorsText.split("\\s*,\\s*"));

            Book newBook = new Book(title, category, cost, authors);

            store.addMedia(newBook);
            JOptionPane.showMessageDialog(this, "Book added successfully!");

            // Đóng cửa sổ và refresh StoreScreen
            this.dispose();
            storeScreen.revalidate(); // Yêu cầu vẽ lại StoreScreen

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}