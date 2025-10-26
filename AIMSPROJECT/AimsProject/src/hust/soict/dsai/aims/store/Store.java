package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class Store {

    public static final int MAX_ITEMS_IN_STORE = 100;
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    // Thêm DVD vào Store
    public void addDVD(DigitalVideoDisc dvd) {
        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full. Cannot add more DVDs.");
            return;
        }
        itemsInStore[qtyInStore++] = dvd;
        System.out.println("Added to store: " + dvd.getTitle());
    }

    // Xóa DVD khỏi Store
    public void removeDVD(DigitalVideoDisc dvd) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] != null && itemsInStore[i].equals(dvd)) {
                found = true;
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[--qtyInStore] = null;
                System.out.println("Removed from store: " + dvd.getTitle());
                break;
            }
        }
        if (!found) {
            System.out.println("DVD not found in store: " + dvd.getTitle());
        }
    }

    // In danh sách DVD hiện có trong Store
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
}
