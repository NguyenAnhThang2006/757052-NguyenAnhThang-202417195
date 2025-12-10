package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import javax.swing.*;
import java.awt.*;

// Lớp cơ sở cho các màn hình thêm Media
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JFrame storeScreen; // Tham chiếu để quay lại

    public AddItemToStoreScreen(Store store, JFrame storeScreen, String title) {
        this.store = store;
        this.storeScreen = storeScreen;

        setTitle(title);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Thêm Menu và Header (giống StoreScreen)
        setJMenuBar(createMenuBar());
        add(createHeader(title), BorderLayout.NORTH);

        // Thêm Form chính (được override bởi các lớp con)
        add(createInputForm(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Abstract method: Các lớp con phải implement để tạo form nhập liệu
    protected abstract JPanel createInputForm();

    // --- Các phương thức MenuBar và Header (Tương tự StoreScreen) ---

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            showStoreScreen(); // Quay lại Store
        });
        menu.add(viewStore);

        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createHeader(String title) {
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 30));
        header.add(titleLabel);
        return header;
    }

    protected void showStoreScreen() {
        // Đóng cửa sổ hiện tại và hiện lại StoreScreen
        this.dispose();
        if (storeScreen != null) {
            storeScreen.setVisible(true);
        }
    }
}