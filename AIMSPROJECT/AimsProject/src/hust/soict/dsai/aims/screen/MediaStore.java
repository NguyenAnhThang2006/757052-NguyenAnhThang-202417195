package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.exception.LimitExceededException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private StoreScreen storeScreen;

    public MediaStore(Media media, StoreScreen storeScreen) {
        this.media = media;
        this.storeScreen = storeScreen;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    storeScreen.getCart().addMedia(media);
                    JOptionPane.showMessageDialog(null, "Added " + media.getTitle() + " to cart!");
                } catch (LimitExceededException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Cart Full Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        container.add(btnAddToCart);

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (media instanceof Playable playable) {
                        try {
                            playable.play();
                            JOptionPane.showMessageDialog(null, "Playing: " + media.getTitle(), "Play Media", JOptionPane.INFORMATION_MESSAGE);
                        } catch (PlayerException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Illegal Media Operation", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            container.add(btnPlay);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.add(Box.createVerticalGlue());

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}