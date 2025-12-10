package hust.soict.globalict.aims.screen;

import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.playable.Playable;
import hust.soict.globalict.aims.exception.PlayerException;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Optional;

public class CartScreenController {

    private Cart cart;
    private FilteredList<Media> filteredItems;

    private JFrame currentScreen;
    private JFrame storeScreen;

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

    public CartScreenController(Cart cart, JFrame currentScreen, JFrame storeScreen) {
        this.cart = cart;
        this.currentScreen = currentScreen;
        this.storeScreen = storeScreen;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredItems = new FilteredList<>(cart.getItemsOrdered());
        tblMedia.setItems(filteredItems);

        tfFilter.setText("");
        radioBtnFilterId.setSelected(true);
        filteredItems.setPredicate(null);

        btnPlay.setDisable(true);
        btnRemove.setDisable(true);

        tblMedia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateButtonState(newSelection);
        });

        tfFilter.textProperty().addListener((obs, oldValue, newValue) -> filterMedia());
        radioBtnFilterId.selectedProperty().addListener((obs, oldValue, newValue) -> filterMedia());
        radioBtnFilterTitle.selectedProperty().addListener((obs, oldValue, newValue) -> filterMedia());

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
            filterMedia();
            new Alert(Alert.AlertType.INFORMATION, "Removed " + selectedMedia.getTitle() + " from cart.").showAndWait();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable playable) {
            try {
                playable.play();
                new Alert(Alert.AlertType.INFORMATION, "Playing: " + selectedMedia.getTitle() +
                        "\n(Console output should follow)").showAndWait();
            } catch (PlayerException ex) {
                new Alert(Alert.AlertType.ERROR, "Play Error: " + ex.getMessage()).showAndWait();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getQtyOrdered() == 0) {
            new Alert(Alert.AlertType.WARNING, "Cart is empty. Cannot place order.").showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText("Total Cost: " + String.format("%.2f $", cart.totalCost()));
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cart.clear();
            updateTotalCost();
            filterMedia();
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").showAndWait();
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
        SwingUtilities.invokeLater(() -> {
            currentScreen.dispose();
            storeScreen.setVisible(true);
        });
    }
}
