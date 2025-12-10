package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.exception.PlayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    private JFrame parentScreen;

    public MediaStore(Media media, Cart cart, JFrame parentScreen) {
        this.media = media;
        this.cart = cart;
        this.parentScreen = parentScreen;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setPreferredSize(new Dimension(300, 150));

        JLabel titleLabel = new JLabel(media.getTitle());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel costLabel = new JLabel(String.format("%.2f $", media.getCost()));
        costLabel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton cartButton = new JButton("Add to Cart");
        cartButton.addActionListener(this::handleAddToCart);
        buttonPanel.add(cartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(this::handlePlay);
            buttonPanel.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(costLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }

    private void handleAddToCart(ActionEvent e) {
        try {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added '" + media.getTitle() + "' to cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (LimitExceededException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cart Full Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handlePlay(ActionEvent e) {
        if (media instanceof Playable playable) {
            try {
                playable.play();
                JOptionPane.showMessageDialog(this,
                        "Playing: " + media.getTitle() + "\nCheck console for details.",
                        "Playing Media",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (PlayerException ex) {
                JOptionPane.showMessageDialog(this,
                        "Cannot play '" + media.getTitle() + "': " + ex.getMessage(),
                        "Playback Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
