package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField directorField;
    private JTextField lengthField;

    public AddDigitalVideoDiscToStoreScreen(Store store, JFrame storeScreen) {
        super(store, storeScreen, "Add DVD to Store");
    }

    @Override
    protected JPanel createInputForm() {
        titleField = new JTextField(20);
        categoryField = new JTextField(20);
        costField = new JTextField(20);
        directorField = new JTextField(20);
        lengthField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Director:"));
        formPanel.add(directorField);
        formPanel.add(new JLabel("Length (min):"));
        formPanel.add(lengthField);
        formPanel.add(new JLabel("Cost:"));
        formPanel.add(costField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveDvd);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        return formPanel;
    }

    private void saveDvd(ActionEvent event) {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String director = directorField.getText().trim();

            if (title.isEmpty() || costField.getText().trim().isEmpty() || lengthField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền Title, Length và Cost.", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int length = Integer.parseInt(lengthField.getText().trim());
            float cost = Float.parseFloat(costField.getText().trim());

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);

            JOptionPane.showMessageDialog(this, "DVD '" + title + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showStoreScreen();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Length and Cost must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (LimitExceededException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi Lưu trữ", JOptionPane.ERROR_MESSAGE);
        }
    }
}