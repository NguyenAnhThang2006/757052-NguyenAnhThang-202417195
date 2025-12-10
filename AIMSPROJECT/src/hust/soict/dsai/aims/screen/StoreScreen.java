package hust.soict.dsai.aims.screen;

import java.awt.*;
import javax.swing.*;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        setTitle("Store");
        setSize(1024, 768);
        setLayout(new BorderLayout());

        add(createNorth(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");

        // Lời gọi constructor: new Add...ToStoreScreen(store, this)
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            this.setVisible(false);
            new AddBookToStoreScreen(store, this);
        });
        smUpdateStore.add(addBook);

        JMenuItem addCd = new JMenuItem("Add CD");
        addCd.addActionListener(e -> {
            this.setVisible(false);
            new AddCompactDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addCd);

        JMenuItem addDvd = new JMenuItem("Add DVD");
        addDvd.addActionListener(e -> {
            this.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addDvd);

        menu.add(smUpdateStore);

        JMenuItem viewStoreMenuItem = new JMenuItem("View store");
        menu.add(viewStoreMenuItem);

        JMenuItem viewCartMenuItem = new JMenuItem("View cart");
        viewCartMenuItem.addActionListener(e -> {
            showCartScreen();
        });
        menu.add(viewCartMenuItem);

        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View cart");
        cartButton.setPreferredSize(new Dimension(100, 50));
        cartButton.setMaximumSize(new Dimension(100, 50));

        cartButton.addActionListener(e -> {
            showCartScreen();
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    private JPanel createCenter() {
        JPanel center = new JPanel();

        center.setLayout(new GridLayout(0, 3, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Media[] mediaArray = store.getItemsInStore();
        ArrayList<Media> mediaInStore = new ArrayList<>(Arrays.asList(mediaArray));

        for (int i = 0; i < Math.min(mediaInStore.size(), 9); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }
        return center;
    }

    private void showCartScreen() {
        this.setVisible(false);
        new CartScreen(cart, this);
    }
}