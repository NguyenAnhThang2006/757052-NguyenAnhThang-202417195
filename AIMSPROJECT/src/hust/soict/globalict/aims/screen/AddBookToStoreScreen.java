package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.Author;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField, categoryField, costField, authorsField;

    public AddBookToStoreScreen(Store store, JFrame storeScreen) {
        super(store, storeScreen, "Add Book to Store");
    }

    @Override
    protected JPanel createInputForm() {
        titleField = new JTextField(20);
        categoryField = new JTextField(20);
        costField = new JTextField(20);
        authorsField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Cost:"));
        formPanel.add(costField);

        formPanel.add(new JLabel("Authors (comma separated):"));
        formPanel.add(authorsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveBook);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        return formPanel;
    }

    private void saveBook(ActionEvent event) {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String authorsStr = authorsField.getText().trim();

            if (title.isEmpty() || costField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Title and Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float cost = Float.parseFloat(costField.getText().trim());
            ArrayList<Author> authors = new ArrayList<>();

            if (!authorsStr.isEmpty()) {
                String[] authorNames = authorsStr.split(",");
                for (String name : authorNames) {
                    authors.add(new Author(name.trim()));
                }
            }

            Book newBook = new Book(title, category, cost, authors);
            store.addMedia(newBook);

            JOptionPane.showMessageDialog(this, "Book '" + title + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showStoreScreen();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (LimitExceededException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Storage Limit Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
