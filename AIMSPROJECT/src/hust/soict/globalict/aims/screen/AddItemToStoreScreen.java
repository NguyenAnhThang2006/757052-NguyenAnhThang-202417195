package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.store.Store;
import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JFrame storeScreen;

    public AddItemToStoreScreen(Store store, JFrame storeScreen, String title) {
        this.store = store;
        this.storeScreen = storeScreen;

        setTitle(title);
        setSize(800, 600);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());
        add(createHeader(title), BorderLayout.NORTH);

        add(createInputForm(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    protected abstract JPanel createInputForm();


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            showStoreScreen();
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
        this.dispose();
        if (storeScreen != null) {
            storeScreen.setVisible(true);
        }
    }
}