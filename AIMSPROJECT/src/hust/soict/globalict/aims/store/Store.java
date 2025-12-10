package hust.soict.globalict.aims.store;

import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.exception.LimitExceededException;

public class Store {

    public static final int MAX_ITEMS_IN_STORE = 100;
    private final Media[] itemsInStore = new Media[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addMedia(Media media) throws LimitExceededException {
        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            throw new LimitExceededException("ERROR: The store is full. Cannot add more items. Limit is " + MAX_ITEMS_IN_STORE);
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