package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.playable.Playable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        // Thiết lập Layout (Quan trọng cho lưới 3x3)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // --- 1. Tạo Label Title và Cost ---
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 16));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()));
        cost.setAlignmentX(CENTER_ALIGNMENT);
        cost.setFont(new Font(cost.getFont().getName(), Font.PLAIN, 14));


        // --- 2. Tạo Button Panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");

        // GẮN LISTENER CHO ADD TO CART (ĐÃ SỬA LỖI EXCEPTION)
        addToCartButton.addActionListener(e -> {
            try {
                // Thêm Media vào Cart (có thể ném LimitExceededException)
                cart.addMedia(media);

                // Thành công
                JOptionPane.showMessageDialog(this,
                        media.getTitle() + " đã được thêm vào giỏ hàng.\nSố lượng hiện tại: " + cart.getQtyOrdered(),
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (LimitExceededException ex) {
                // Xử lý ngoại lệ, không ném lại Runtime Exception
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(), // Hiển thị thông báo lỗi (Cart đầy)
                        "Lỗi Giỏ Hàng",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(addToCartButton);

        // --- 3. Xử lý nút Play ---
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");

            playButton.addActionListener(e -> {
                // Logic kiểm tra Playable và Length (nếu là DVD)
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

        // --- Thêm các thành phần vào MediaStore ---
        this.add(Box.createVerticalGlue()); // Đẩy nội dung ra giữa
        this.add(title);
        this.add(cost);
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
    }

    // --- Phương thức để hiển thị JDialog Play ---
    private void playMediaDialog(Playable media) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Playing Media");
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));

        // Tạo nội dung hiển thị
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Cần truyền chi tiết thông báo vào đây (dùng System.out.println hoặc một JTextArea)
        JTextArea detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setFont(new Font(detailArea.getFont().getName(), Font.PLAIN, 14));

        // Giả định phương thức play() trong Model sẽ in ra console
        detailArea.setText("Media: " + ((Media)media).getTitle() + "\n" +
                "Cost: " + ((Media)media).getCost() + " $\n" +
                "Status: Playing...");

        if (media instanceof DigitalVideoDisc dvd) {
            detailArea.append("\nThời lượng: " + dvd.getLength() + " phút");
        }

        contentPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

        dialog.setContentPane(contentPanel);
        dialog.setVisible(true);

        // Gọi play() trong Model (nếu bạn muốn thấy output trong console/log)
        // media.play();
    }
}