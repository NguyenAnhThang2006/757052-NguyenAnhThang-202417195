package hust.soict.dsai.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Painter extends Application {

    // Phương thức start() phải được override
    @Override
    public void start(Stage stage) throws Exception {
        // Tải Scene Graph từ FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Painter.fxml"));

        // Tạo Scene từ root node
        Scene scene = new Scene(root);

        // Thiết lập Stage
        stage.setTitle("Painter");
        stage.setScene(scene);
        stage.show(); // Hiển thị cửa sổ
    }

    // Phương thức main() gọi launch(args)
    public static void main(String[] args) {
        launch(args);
    }
}