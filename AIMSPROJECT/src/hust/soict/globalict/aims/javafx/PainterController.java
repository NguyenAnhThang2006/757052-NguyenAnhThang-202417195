package hust.soict.globalict.aims.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadio;

    @FXML
    private RadioButton eraserRadio;

    @FXML
    private ToggleGroup toolToggleGroup;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color drawingColor;
        double radius;

        if (penRadio.isSelected()) {
            drawingColor = Color.BLACK;
            radius = 4;
        } else {
            drawingColor = Color.WHITE;
            radius = 8;
        }

        double x = event.getX();
        double y = event.getY();

        Circle newCircle = new Circle(x, y, radius, drawingColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}
