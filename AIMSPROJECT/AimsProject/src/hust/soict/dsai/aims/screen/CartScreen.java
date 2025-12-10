package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import javax.swing.JFrame;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;
    private StoreScreen storeScreen;

    public CartScreen(Cart cart, StoreScreen storeScreen) {
        super();
        this.cart = cart;
        this.storeScreen = storeScreen;

        setTitle("Cart");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        Platform.runLater(() -> {
            try {
                // Sửa thành đường dẫn tương đối (từ package hiện tại)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/cart.fxml"));
                CartScreenController controller = new CartScreenController(cart, storeScreen, this);
                loader.setController(controller);

                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setVisible(true);
    }
}