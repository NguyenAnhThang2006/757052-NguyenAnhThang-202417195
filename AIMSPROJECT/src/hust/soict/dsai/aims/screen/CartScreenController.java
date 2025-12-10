package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.playable.Playable;
import hust.soict.dsai.aims.exception.PlayerException;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.util.Optional;

public class CartScreenController {

    private Cart cart;
    private FilteredList<Media> filteredItems;

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediacategory;
    @FXML private TableColumn<Media, Float> colMediaCost;

    @FXML private Label totalCostLabel;
    @FXML private Button btnRemove;
    @FXML private Button btnPlay;

    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private ToggleGroup filterCategory;
    @FXML private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private void initialize() {

        // Data-Binding
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Khởi tạo FilteredList và gán vào TableView
        // Lưu ý: cart.getItemsOrdered() phải là ObservableList
        filteredItems = new FilteredList<>(cart.getItemsOrdered());
        tblMedia.setItems(filteredItems);

        // Đảm bảo ban đầu không có filter nào được áp dụng
        filteredItems.setPredicate(null);

        // Vô hiệu hóa nút ban đầu
        btnPlay.setDisable(true);
        btnRemove.setDisable(true);

        // Thiết lập Selection Listener (ChangeListener)
        tblMedia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateButtonState(newSelection);
        });

        // Thiết lập Listener cho Filter
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> filterMedia());
        radioBtnFilterId.selectedProperty().addListener((obs, oldValue, newValue) -> filterMedia());

        // Cập nhật TotalCost mỗi khi danh sách trong Cart thay đổi
        cart.getItemsOrdered().addListener((javafx.collections.ListChangeListener<Media>) change -> updateTotalCost());

        updateTotalCost();
    }

    private void updateButtonState(Media selectedMedia) {
        if (selectedMedia == null) {
            btnRemove.setDisable(true);
            btnPlay.setDisable(true);
            return;
        }
        btnRemove.setDisable(false);
        btnPlay.setDisable(!(selectedMedia instanceof Playable));
    }

    private void updateTotalCost() {
        totalCostLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            updateTotalCost();
            updateButtonState(tblMedia.getSelectionModel().getSelectedItem());

            // SỬA LỖI: Gọi filterMedia() để refresh FilteredList (đảm bảo không trắng xóa)
            filterMedia();

            new Alert(Alert.AlertType.INFORMATION, "Đã xóa " + selectedMedia.getTitle() + " khỏi giỏ hàng.").showAndWait();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable playable) {
            try {
                // SỬA LỖI: Gọi phương thức playable.play() (ném PlayerException)
                playable.play();

                // Hiển thị thông báo Play thành công sau khi gọi hàm model
                new Alert(Alert.AlertType.INFORMATION, "Đang Play: " + selectedMedia.getTitle() +
                        "\n(Output Playable Model được in ra console)").showAndWait();

            } catch (PlayerException ex) {
                // Bắt PlayerException (Checked Exception)
                new Alert(Alert.AlertType.ERROR, "Lỗi Play: " + ex.getMessage()).showAndWait();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getQtyOrdered() == 0) {
            new Alert(Alert.AlertType.WARNING, "Giỏ hàng trống. Không thể đặt hàng.").showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Tổng chi phí: " + String.format("%.2f $", cart.totalCost()));
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cart.clear();
            updateTotalCost();

            // SỬA LỖI: Gọi filterMedia() để reset bộ lọc, đảm bảo TableView hiển thị rỗng
            filterMedia();

            new Alert(Alert.AlertType.INFORMATION, "Đã tạo đơn hàng thành công!").showAndWait();
        }
    }

    private void filterMedia() {
        String filterText = tfFilter.getText().toLowerCase().trim();
        boolean filterById = radioBtnFilterId.isSelected();

        if (filterText.isEmpty()) {
            filteredItems.setPredicate(null);
            return;
        }

        filteredItems.setPredicate(media -> {
            if (filterById) {
                try {
                    // Cần đảm bảo Media có phương thức getId() trả về ID
                    int id = Integer.parseInt(filterText);
                    return media.getId() == id;
                } catch (NumberFormatException ignored) {
                    return false;
                }
            } else {
                return media.getTitle().toLowerCase().contains(filterText);
            }
        });
    }

    @FXML
    void handleViewStoreMenu(ActionEvent event) {
        // Đóng cửa sổ Cart (sẽ kích hoạt WindowListener trong CartScreen để hiện lại StoreScreen)
        ((MenuItem)event.getSource()).getParentPopup().getOwnerWindow().hide();
    }
}