package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

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
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }
}
