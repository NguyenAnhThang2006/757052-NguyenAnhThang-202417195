package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;

public class Store {

    public static final int MAX_ITEMS_IN_STORE = 100;
    private Media[] itemsInStore = new Media[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addMedia(Media media) {
        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full. Cannot add more items.");
            return;
        }
        itemsInStore[qtyInStore++] = media;
        System.out.println("Added to store: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] != null && itemsInStore[i].equals(media)) {
                found = true;
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[--qtyInStore] = null;
                System.out.println("Removed from store: " + media.getTitle());
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found in store: " + media.getTitle());
        }
    }

    public void printStore() {
        System.out.println("\n***********************STORE***********************");
        if (qtyInStore == 0) {
            System.out.println("The store is empty.");
        } else {
            for (int i = 0; i < qtyInStore; i++) {
                System.out.println((i + 1) + ". " + itemsInStore[i].toString());
            }
        }
        System.out.println("***************************************************");
    }

    public Media[] getItemsInStore() {
        Media[] copy = new Media[qtyInStore];
        System.arraycopy(itemsInStore, 0, copy, 0, qtyInStore);
        return copy;
    }

    public Media findMediaByTitle(String title) {
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] != null && itemsInStore[i].getTitle().equalsIgnoreCase(title)) {
                return itemsInStore[i];
            }
        }
        return null;
    }
}
