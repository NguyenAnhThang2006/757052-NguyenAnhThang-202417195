package hust.soict.globalict.aims.screen;

import javax.swing.*;
import hust.soict.globalict.aims.cart.Cart;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;
    private JFrame storeScreen;

    public CartScreen(Cart cart, JFrame storeScreen) {
        super();
        this.cart = cart;
        this.storeScreen = storeScreen;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/globalict/aims/screen/cart.fxml"));

                loader.setControllerFactory(param -> {
                    if (param.equals(CartScreenController.class)) {
                        return new CartScreenController(cart, CartScreen.this, storeScreen);
                    }
                    return null;
                });

                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading cart.fxml: " + e.getMessage(), "FXML Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.setVisible(true);
    }
}
