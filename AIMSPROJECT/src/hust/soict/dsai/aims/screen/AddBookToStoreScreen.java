package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Author;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    // KHÔNG KHỞI TẠO Ở ĐÂY, CHỈ KHAI BÁO
    private JTextField titleField, categoryField, costField, authorsField;

    // Constructor chuẩn hóa (Store, JFrame)
    public AddBookToStoreScreen(Store store, JFrame storeScreen) {
        super(store, storeScreen, "Add Book to Store");
    }

    @Override
    protected JPanel createInputForm() {
        // KHỞI TẠO TẤT CẢ CÁC THÀNH PHẦN TẠI ĐÂY (CỤC BỘ)
        titleField = new JTextField(20);
        categoryField = new JTextField(20);
        costField = new JTextField(20);
        authorsField = new JTextField(20);

        // Đảm bảo GridLayout là (5, 2)
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // 1. Title
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField); // Dòng này không thể lỗi vì titleField vừa được khởi tạo

        // 2. Category
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);

        // 3. Cost
        formPanel.add(new JLabel("Cost:"));
        formPanel.add(costField);

        // 4. Authors
        formPanel.add(new JLabel("Authors (comma separated):"));
        formPanel.add(authorsField);

        // 5. Nút Submit
        JButton saveButton = new JButton("Save"); // Đây là thành phần được khởi tạo cục bộ
        saveButton.addActionListener(this::saveBook);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        return formPanel;
    }

    private void saveBook(ActionEvent event) {
        // ... (Logic saveBook giữ nguyên, không cần thay đổi)
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String authorsStr = authorsField.getText().trim();

            if (title.isEmpty() || costField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền Title và Cost.", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi Lưu trữ", JOptionPane.ERROR_MESSAGE);
        }
    }
}