package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n===== AIMS MENU =====");
            System.out.println("1. Add a disc to the cart");
            System.out.println("2. Remove a disc from the cart");
            System.out.println("3. View total cost");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1–4): ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter cost: ");
                    float cost = sc.nextFloat();
                    DigitalVideoDisc dvd = new DigitalVideoDisc(category, title, cost);
                    anOrder.addDigitalVideoDisc(dvd);
                }
                case 2 -> {
                    System.out.print("Enter title to remove: ");
                    String title = sc.nextLine();
                    DigitalVideoDisc dvd = new DigitalVideoDisc(title);
                    anOrder.removeDigitalVideoDisc(dvd);
                }
                case 3 -> anOrder.print();
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
