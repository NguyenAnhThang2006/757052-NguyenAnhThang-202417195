package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException; // Cần import PlayerException
import hust.soict.dsai.aims.exception.LimitExceededException; // Cần import LimitExceededException

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Thêm imports cho ChangeListener và ObservableList
import javafx.collections.ListChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CartScreenController {

    private Cart cart;
    private CartScreen cartScreen;
    private StoreScreen storeScreen;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label totalCostLabel;

    // (Bổ sung cho Bước 8 Optional)
    // @FXML private TextField tfFilter;
    // @FXML private RadioButton radioBtnFilterId;
    // @FXML private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart, StoreScreen storeScreen, CartScreen cartScreen) {
        this.cart = cart;
        this.storeScreen = storeScreen;
        this.cartScreen = cartScreen;
    }

    @FXML
    private void initialize() {
        // 1. Cài đặt các cột cho TableView
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        // 2. Liên kết dữ liệu
        tblMedia.setItems(this.cart.getItemsOrdered());

        // 3. Ẩn nút ban đầu
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // 4. Thêm ChangeListener cho sự kiện chọn dòng (đã có)
        tblMedia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateButtonBar(newValue);
            } else {
                // Xử lý khi không có gì được chọn (ví dụ sau khi xóa item)
                btnPlay.setVisible(false);
                btnRemove.setVisible(false);
            }
        });

        // 5. Cập nhật Total Cost Label (Bước 9)
        updateTotalCostLabel();

        // Cần lắng nghe thay đổi của Cart (thêm/xóa item) để update Total Cost
        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
            updateTotalCostLabel();
        });

        // (Bước 8 Optional) Thêm ChangeListener cho TextField và RadioButtons ở đây
    }

    // Cập nhật trạng thái nút Play/Remove
    public void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    // --- Xử lý sự kiện nút Play (Bước 9) ---
    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable playable) {
            try {
                // Gọi play() và bắt PlayerException (đã sửa trong các bước trước)
                playable.play();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Play Media");
                alert.setHeaderText(selectedMedia.getTitle());
                alert.setContentText("Media is playing successfully!");
                alert.showAndWait();
            } catch (PlayerException e) {
                // Hiển thị dialog lỗi khi độ dài non-positive
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Illegal Media Length");
                alert.setHeaderText("Playback Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unexpected Error");
                alert.setContentText("An unexpected error occurred during playback.");
                alert.showAndWait();
            }
        }
    }

    // --- Xử lý sự kiện nút Remove (Bước 7) ---
    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            // Không cần gọi tblMedia.refresh() vì ObservableList tự động cập nhật TableView.
        }
    }

    // --- Xử lý sự kiện nút Place Order (Bước 9) ---
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getQtyOrdered() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Order Warning");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Cannot place an order.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Total Cost: " + String.format("%.2f $", cart.totalCost()));
        alert.setContentText("Your order has been created successfully! The cart is now empty.");
        alert.showAndWait();

        cart.clear();
    }

    // --- Cập nhật Total Cost Label ---
    private void updateTotalCostLabel() {
        totalCostLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    // (Bổ sung cho Bước 8 Optional)
    /*
    private void showFilteredMedia(String newValue) {
        // Implement logic FilteredList/Predicate ở đây
    }
    */

    // Phương thức để quay lại StoreScreen (cho các Menu Item)
    @FXML
    void handleViewStoreMenu(ActionEvent event) {
        cartScreen.setVisible(false);
        storeScreen.setVisible(true);
        // Có thể gọi storeScreen.toFront() nếu cần
    }

    // Bạn cũng cần thêm các hàm xử lý cho MenuBar (Add Book, Add CD, Add DVD) ở đây
    // để mở các màn hình AddItemToStoreScreen tương ứng (Bước 9).
    // ...
}