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

    public void print() {
        System.out.println("*********************** CART ***********************");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            int index = 1;
            for (Media m : itemsOrdered) {
                System.out.println(index + ". " + m.toString());
                index++;
            }
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("****************************************************");
    }

    public void searchByTitle(String title) {
        boolean found = false;
        System.out.println("\n--- Search Results for '" + title + "' ---");
        for (Media m : itemsOrdered) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found: " + m.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No media found with title: " + title);
        System.out.println("------------------------------------------");
    }

    public void searchById(int id) {
        boolean found = false;
        System.out.println("\n--- Search Results for ID: " + id + " ---");
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                System.out.println("Found: " + m.toString());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No media found with ID: " + id);
        System.out.println("------------------------------------------");
    }

    public Media findMediaByTitle(String title) {
        for (Media m : itemsOrdered) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    public int getQtyOrdered() {
        return itemsOrdered.size();
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void sort(int option) {
        switch (option) {
            case 1 -> {
                itemsOrdered.sort(Comparator
                        .comparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Media::getCost, Comparator.reverseOrder())
                );
                System.out.println("Cart sorted by Title (A-Z), then Cost (Highest first).");
            }
            case 2 -> {
                itemsOrdered.sort(Comparator
                        .comparing(Media::getCost, Comparator.reverseOrder())
                        .thenComparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER)
                );
                System.out.println("Cart sorted by Cost (Highest first), then Title (A-Z).");
            }
            default -> System.out.println("Invalid sort option!");
        }
        this.print();
    }

    public Media getLuckyItem() {
        if (itemsOrdered.isEmpty()) return null;

        int randomIndex = (int) (Math.random() * itemsOrdered.size());
        return itemsOrdered.get(randomIndex);
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("Cart is now empty.");
    }
}