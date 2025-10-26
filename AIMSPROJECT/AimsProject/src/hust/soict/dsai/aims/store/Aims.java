package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {

        Cart anOrder = new Cart();
        Scanner sc = new Scanner(System.in);

        //Direct command code

        /*
        //Create new dvd objects add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion king",
                "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        //Remove the dvd selected from the cart
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.removeDigitalVideoDisc(dvd4);

        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Nonexistent",
                "Drama", "Unknown", 120, 15.00f);
        anOrder.removeDigitalVideoDisc(dvd5);


        //print total cost of the items in the cart
        System.out.println("\n--- Cart Total ---");
        System.out.println("Total cost is: " + anOrder.totalCost());

         */


        //Test case for user menu
/*

1
4
The Lion King
Animation
Roger Allers
87
19.95
1
4
Star Wars
Science Fiction
George Lucas
87
24.95
1
2
Animation
Aladdin
18.99
2
4
Star Wars
Science Fiction
George Lucas
87
24.95
3
4
(Enter at the end if copy paste the whole test case)

*/

        //Menu for user
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

                //Choose way to add DVD
                case 1: {
                    System.out.println("\n--- Add a new DVD ---");
                    System.out.println("Choose how to create DVD:");
                    System.out.println("1. By title only");
                    System.out.println("2. By category, title, and cost");
                    System.out.println("3. By director, category, title, and cost");
                    System.out.println("4. By all attributes");
                    System.out.print("Your choice: ");
                    int opt = sc.nextInt();
                    sc.nextLine();

                    DigitalVideoDisc dvd = null;

                    if (opt == 1) {
                        System.out.print("\nEnter title: ");
                        String title = sc.nextLine();
                        dvd = new DigitalVideoDisc(title);
                    }
                    else if (opt == 2) {
                        System.out.print("\nEnter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(category, title, cost);
                    }
                    else if (opt == 3) {
                        System.out.print("\nEnter director: ");
                        String director = sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(director, category, title, cost);
                    }
                    else if (opt == 4) {
                        System.out.print("\nEnter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter director: ");
                        String director = sc.nextLine();
                        System.out.print("Enter length: ");
                        int length = sc.nextInt();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(title, category, director, length, cost);
                    }

                    if (dvd != null) {
                        anOrder.addDigitalVideoDisc(dvd);
                    } else {
                        System.out.println("Invalid choice! Try again later. ");
                    }
                    break;
                }

                //Choose way to remove DVD
                case 2: {
                    System.out.println("\n--- Remove a DVD ---");
                    System.out.println("Choose how to identify DVD:");
                    System.out.println("1. By title");
                    System.out.println("2. By category, title, and cost");
                    System.out.println("3. By director, category, title, and cost");
                    System.out.println("4. By all attributes");
                    System.out.print("Your choice: ");
                    int opt = sc.nextInt();
                    sc.nextLine();

                    DigitalVideoDisc dvd = null;

                    if (opt == 1) {
                        System.out.print("\nEnter title: ");
                        String title = sc.nextLine();
                        dvd = new DigitalVideoDisc(title);
                    }
                    else if (opt == 2) {
                        System.out.print("\nEnter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(category, title, cost);
                    }
                    else if (opt == 3) {
                        System.out.print("\nEnter director: ");
                        String director = sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(director, category, title, cost);
                    }
                    else if (opt == 4) {
                        System.out.print("\nEnter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter director: ");
                        String director = sc.nextLine();
                        System.out.print("Enter length: ");
                        int length = sc.nextInt();
                        System.out.print("Enter cost: ");
                        float cost = sc.nextFloat();
                        dvd = new DigitalVideoDisc(title, category, director, length, cost);
                    }

                    if (dvd != null) {
                        anOrder.removeDigitalVideoDisc(dvd);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                }


                case 3: {
                    System.out.println("\n--- Cart Total ---");
                    System.out.println("Total cost is: " + anOrder.totalCost());
                    break;
                }

                case 4: {
                    System.out.println("\nExiting... Thank you for visiting the store!");
                    break;
                }

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();


    }


}

