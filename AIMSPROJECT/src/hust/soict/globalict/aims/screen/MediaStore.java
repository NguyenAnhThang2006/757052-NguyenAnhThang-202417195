package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.exception.LimitExceededException;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.playable.Playable;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 16));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()));
        cost.setAlignmentX(CENTER_ALIGNMENT);
        cost.setFont(new Font(cost.getFont().getName(), Font.PLAIN, 14));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");

        addToCartButton.addActionListener(e -> {
            try {
                cart.addMedia(media);

                JOptionPane.showMessageDialog(this,
                        media.getTitle() + " đã được thêm vào giỏ hàng.\nSố lượng hiện tại: " + cart.getQtyOrdered(),
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (LimitExceededException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Lỗi Giỏ Hàng",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");

            playButton.addActionListener(e -> {
                if (media instanceof DigitalVideoDisc dvd && dvd.getLength() <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "ERROR: DVD " + media.getTitle() + " không thể Play. Length <= 0.",
                            "Lỗi Play",
                            JOptionPane.ERROR_MESSAGE);
                } else if (media instanceof Playable) {
                    playMediaDialog((Playable) media);
                }
            });
            buttonPanel.add(playButton);
        }

        this.add(Box.createVerticalGlue()); // Đẩy nội dung ra giữa
        this.add(title);
        this.add(cost);
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }

    private void playMediaDialog(Playable media) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Playing Media");
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));

        JPanel contentPanel = new JPanel(new BorderLayout());

        JTextArea detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setFont(new Font(detailArea.getFont().getName(), Font.PLAIN, 14));

        detailArea.setText("Media: " + ((Media)media).getTitle() + "\n" +
                "Cost: " + ((Media)media).getCost() + " $\n" +
                "Status: Playing...");

        if (media instanceof DigitalVideoDisc dvd) {
            detailArea.append("\nThời lượng: " + dvd.getLength() + " phút");
        }

        contentPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

        dialog.setContentPane(contentPanel);
        dialog.setVisible(true);

    }
}