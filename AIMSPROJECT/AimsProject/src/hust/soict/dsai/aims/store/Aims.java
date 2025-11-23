package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import java.util.Scanner;

public class Aims {

    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> viewStore();
                case 2 -> updateStore();
                case 3 -> seeCurrentCart();
                case 0 -> System.out.println("Exiting AIMS. Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void viewStore() {
        store.printStore();
        int choice;
        do {
            storeMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> seeMediaDetails();
                case 2 -> addMediaToCart();
                case 3 -> playMedia();
                case 4 -> seeCurrentCart();
                case 0 -> {}
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);
    }

    public static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = sc.nextLine();
        Media media = findMediaInStore(title);
        if (media != null) {
            System.out.println(media.toString());
            int choice;
            do {
                mediaDetailsMenu();
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> {
                        cart.addMedia(media);
                        System.out.println("Added to cart. Current cart size: " + cart.getQtyOrdered());
                    }
                    case 2 -> {
                        if (media instanceof DigitalVideoDisc dvd) {
                            System.out.println("Playing DVD: " + dvd.getTitle());
                        } else {
                            System.out.println("This media cannot be played.");
                        }
                    }
                    case 0 -> {}
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 0);
        } else {
            System.out.println("Media not found in store!");
        }
    }

    public static void addMediaToCart() {
        System.out.print("Enter media title to add to cart: ");
        String title = sc.nextLine();
        Media media = findMediaInStore(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Added to cart. Current cart size: " + cart.getQtyOrdered());
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void playMedia() {
        System.out.print("Enter media title to play: ");
        String title = sc.nextLine();
        Media media = findMediaInStore(title);
        if (media != null) {
            if (media instanceof DigitalVideoDisc dvd) {
                System.out.println("Playing DVD: " + dvd.getTitle());
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void updateStore() {
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 1) {
            System.out.print("Enter media title: ");
            String title = sc.nextLine();
            DigitalVideoDisc dvd = new DigitalVideoDisc(title);
            store.addMedia(dvd);
        } else if (choice == 2) {
            System.out.print("Enter media title to remove: ");
            String title = sc.nextLine();
            Media media = findMediaInStore(title);
            if (media != null) store.removeMedia(media);
            else System.out.println("Media not found in store!");
        }
    }

    public static void seeCurrentCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> filterCart();
                case 2 -> sortCart();
                case 3 -> removeMediaFromCart();
                case 4 -> playMediaInCart();
                case 5 -> placeOrder();
                case 0 -> {}
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static Media findMediaInStore(String title) {
        for (int i = 0; i < Store.MAX_ITEMS_IN_STORE; i++) {
            try {
                Media media = store.getItemsInStore()[i];
                if (media != null && media.getTitle().equalsIgnoreCase(title)) {
                    return media;
                }
            } catch (Exception e) {}
        }
        return null;
    }

    private static void filterCart() {
        System.out.println("1. By ID");
        System.out.println("2. By title");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 1) {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());
            cart.searchById(id);
        } else if (choice == 2) {
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            cart.searchByTitle(title);
        }
    }

    private static void sortCart() {
        System.out.println("1. By title");
        System.out.println("2. By cost");
        System.out.print("Choose: ");
        int choice = Integer.parseInt(sc.nextLine());
        cart.sort(choice);
    }

    private static void removeMediaFromCart() {
        System.out.print("Enter media title to remove: ");
        String title = sc.nextLine();
        Media media = cart.findMediaByTitle(title);
        if (media != null) cart.removeMedia(media);
        else System.out.println("Media not found in cart!");
    }

    private static void playMediaInCart() {
        System.out.print("Enter media title to play: ");
        String title = sc.nextLine();
        Media media = cart.findMediaByTitle(title);
        if (media != null) {
            if (media instanceof DigitalVideoDisc dvd) {
                System.out.println("Playing DVD: " + dvd.getTitle());
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found in cart!");
        }
    }

    private static void placeOrder() {
        System.out.println("Order created. Cart is now empty!");
        cart.clear();
    }
}
