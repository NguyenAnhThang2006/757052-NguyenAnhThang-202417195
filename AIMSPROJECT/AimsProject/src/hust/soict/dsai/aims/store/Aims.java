package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.screen.StoreScreen;
import java.util.ArrayList;
import java.util.List;

public class Aims {

    private static Store store = new Store();
    private static Cart cart = new Cart();

    static {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 121, 24.95f);

        Book book1 = new Book("The Lord of the Rings", "Fantasy", 20.5f, new ArrayList<String>(List.of("J.R.R. Tolkien")));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);

        store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f));
        store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 29.99f));
        store.addMedia(new DigitalVideoDisc("Avatar", "Sci-Fi", "James Cameron", 162, 35.00f));
        store.addMedia(new DigitalVideoDisc("Titanic", "Romance", "James Cameron", 195, 15.00f));
        store.addMedia(new DigitalVideoDisc("Home Alone", "Comedy", "Chris Columbus", 103, 12.50f));
    }


    public static void main(String[] args) {
        new StoreScreen(store, cart);
    }
}