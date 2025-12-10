package hust.soict.dsai.aims.screen;

import java.awt.*;
import javax.swing.*;
import hust.soict.dsai.aims.cart.Cart;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (storeScreen != null) {
                    // Đảm bảo hiển thị lại StoreScreen trên Swing EDT (Khắc phục lỗi treo)
                    SwingUtilities.invokeLater(() -> {
                        storeScreen.setVisible(true);
                    });
                }
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml"));

                    loader.setControllerFactory(param -> {
                        if (param.equals(CartScreenController.class)) {
                            return new CartScreenController(cart);
                        }
                        return null;
                    });

                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi tải cart.fxml: " + e.getMessage(), "Lỗi FXML", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setVisible(true);
    }
}