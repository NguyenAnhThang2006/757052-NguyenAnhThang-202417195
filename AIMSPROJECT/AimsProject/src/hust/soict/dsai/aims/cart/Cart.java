package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

import java.util.ArrayList;
import java.util.Comparator;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full");
            return;
        }
        itemsOrdered.add(media);
        System.out.println("Added: " + media.getTitle() + " ($" + media.getCost() + ")");
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed: " + media.getTitle());
        } else {
            System.out.println("Media not found: " + media.getTitle());
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
        int index = 1;
        for (Media m : itemsOrdered) {
            System.out.println(index + ". " + m.toString());
            index++;
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("****************************************************");
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found: " + m.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No media found with title: " + title);
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                System.out.println("Found: " + m.toString());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No media found with ID: " + id);
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

    public void sort(int option) {
        switch (option) {
            case 1 -> itemsOrdered.sort(Comparator.comparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER));
            case 2 -> itemsOrdered.sort(Comparator.comparingDouble(Media::getCost));
            default -> System.out.println("Invalid sort option!");
        }
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("Cart is now empty.");
    }
}
