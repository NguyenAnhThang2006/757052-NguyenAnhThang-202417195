package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.Track;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField artistField;
    private JTextField directorField;
    private JTextField lengthField;

    public AddCompactDiscToStoreScreen(Store store, JFrame storeScreen) {
        super(store, storeScreen, "Add CD to Store");
    }

    @Override
    protected JPanel createInputForm() {
        titleField = new JTextField(20);
        categoryField = new JTextField(20);
        costField = new JTextField(20);
        artistField = new JTextField(20);
        directorField = new JTextField(20);
        lengthField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Cost:"));
        formPanel.add(costField);
        formPanel.add(new JLabel("Artist:"));
        formPanel.add(artistField);
        formPanel.add(new JLabel("Director (optional):"));
        formPanel.add(directorField);
        formPanel.add(new JLabel("Initial Length (Total):"));
        formPanel.add(lengthField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveCd);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        return formPanel;
    }

    private void saveCd(ActionEvent event) {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();

            if (title.isEmpty() || costField.getText().trim().isEmpty() || lengthField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền Title, Length và Cost.", "Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float cost = Float.parseFloat(costField.getText().trim());
            String artist = artistField.getText().trim();
            String director = directorField.getText().trim();
            int length = Integer.parseInt(lengthField.getText().trim());

            CompactDisc cd = new CompactDisc(title, category, cost, director, length, artist);
            cd.addTrack(new Track("Sample Track 1", 5));

            store.addMedia(cd);

            JOptionPane.showMessageDialog(this, "CD '" + title + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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