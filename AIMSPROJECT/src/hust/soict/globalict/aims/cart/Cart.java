package hust.soict.globalict.aims.cart;

import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.exception.LimitExceededException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;

    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) throws LimitExceededException {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException("ERROR: The number of media has reached its limit (" + MAX_NUMBERS_ORDERED + ")");
        }
        itemsOrdered.add(media);
        System.out.println("The media '" + media.getTitle() + "' has been added to the cart.");
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media '" + media.getTitle() + "' has been removed from the cart.");
        } else {
            System.out.println("ERROR: The media '" + media.getTitle() + "' was not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("The cart is now empty.");
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public int getQtyOrdered() {
        return itemsOrdered.size();
    }

    public void sort(int option) {
        switch (option) {
            case 1 -> {
                itemsOrdered.sort(Comparator
                        .comparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Media::getCost, Comparator.reverseOrder())
                );
                System.out.println("Cart sorted by Title (A-Z), then by Cost (Highest first).");
            }
            case 2 -> {
                itemsOrdered.sort(Comparator
                        .comparing(Media::getCost, Comparator.reverseOrder())
                        .thenComparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER)
                );
                System.out.println("Cart sorted by Cost (Highest first), then by Title (A-Z).");
            }
            default -> System.out.println("Invalid sort option!");
        }
    }
}
