package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.screen.StoreScreen;
import hust.soict.dsai.aims.exception.LimitExceededException;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        try {
            store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
            store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.99f));
            store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f));
            store.addMedia(new DigitalVideoDisc("Inception", "Science Fiction", "C. Nolan", 148, 22.50f));
            store.addMedia(new DigitalVideoDisc("Titanic", "Romance", "James Cameron", 195, 15.00f));
            store.addMedia(new DigitalVideoDisc("Avatar", "Science Fiction", "James Cameron", 162, 28.99f));
            store.addMedia(new DigitalVideoDisc("Braveheart", "Historical", "Mel Gibson", 178, 12.99f));
            store.addMedia(new DigitalVideoDisc("The Matrix", "Science Fiction", "Wachowskis", 136, 17.50f));
            store.addMedia(new DigitalVideoDisc("Pulp Fiction", "Crime", "Quentin Tarantino", 154, 11.99f));

        } catch (IllegalArgumentException ex) {
            System.err.println("Data Initialization Error: " + ex.getMessage());
            return;
        } catch (LimitExceededException e) {
            System.err.println("Store Limit Exceeded During Initialization: " + e.getMessage());
            throw new RuntimeException(e);
        }

        new StoreScreen(store, cart);
    }
}