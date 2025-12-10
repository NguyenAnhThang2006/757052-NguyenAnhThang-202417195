package hust.soict.globalict.aims.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color; // Cần thiết cho màu sắc
import javafx.scene.shape.Circle; // Cần thiết để vẽ chấm tròn

public class PainterController {

    // Ánh xạ fx:id từ FXML cho khu vực vẽ
    @FXML
    private Pane drawingAreaPane;

    // Ánh xạ fx:id cho RadioButton Pen
    @FXML
    private RadioButton penRadio;

    // Ánh xạ fx:id cho RadioButton Eraser (không nhất thiết phải khai báo, nhưng tốt cho việc kiểm tra)
    @FXML
    private RadioButton eraserRadio;

    // Ánh xạ ToggleGroup (không bắt buộc nếu bạn dùng RadioButton để kiểm tra)
    @FXML
    private ToggleGroup toolToggleGroup;

    /**
     * Xử lý sự kiện khi nhấn nút "Clear".
     * Xóa tất cả các hình vẽ (Circle) khỏi Pane.
     */
    @FXML
    void clearButtonPressed(ActionEvent event) {
        // Xóa tất cả các đối tượng (Node) con trong Pane
        drawingAreaPane.getChildren().clear();
    }

    /**
     * Xử lý sự kiện kéo chuột trên khu vực vẽ.
     * Tạo một Circle tại vị trí con trỏ chuột với màu sắc tương ứng với công cụ được chọn.
     */
    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {

        // 1. Xác định màu sắc và kích thước dựa trên công cụ
        Color drawingColor;
        double radius;

        if (penRadio.isSelected()) {
            // Chế độ Pen: Màu đen, kích thước nhỏ
            drawingColor = Color.BLACK;
            radius = 4;
        } else {
            // Chế độ Eraser: Màu trắng (màu nền canvas), kích thước lớn hơn
            drawingColor = Color.WHITE;
            radius = 8; // Bán kính lớn hơn để xóa dễ dàng hơn
        }

        // 2. Lấy tọa độ chuột
        double x = event.getX();
        double y = event.getY();

        // 3. Tạo một hình tròn (dot)
        Circle newCircle = new Circle(x, y, radius, drawingColor);

        // 4. Thêm hình tròn vào Pane
        drawingAreaPane.getChildren().add(newCircle);
    }
}