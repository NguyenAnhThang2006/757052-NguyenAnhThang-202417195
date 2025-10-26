package hust.soict.dsai.aims.test;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Aladdin");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Star Wars");

        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);

        store.printStore();

        store.removeDVD(dvd2);
        store.printStore();

        store.removeDVD(new DigitalVideoDisc("Nonexistent"));
    }
}
