public class Cart {

    //Create a array queue for disc
    public static final int MAX_NUMBERS_ORDERED = 20;

    private DigitalVideoDisc itemsOrdered[] =
            new DigitalVideoDisc[MAX_NUMBERS_ORDERED];

    private int qtyOrdered = 0;

    public Cart() {
        qtyOrdered = 0;
    }

    //Add a DVD to the Store
    public void addDigitalVideoDisc(DigitalVideoDisc dvd) {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full");
            return;
        }
        itemsOrdered[qtyOrdered] = dvd;
        qtyOrdered++;

        String name = dvd.getTitle();
        float money = dvd.getCost();
        System.out.println("The disc has been added: " + name + " (" + money + ")");
    }

    //Remove a DVD from the Store
    public void removeDigitalVideoDisc(DigitalVideoDisc dvd) {
        boolean found = false;
        String name = dvd.getTitle();
        float money = dvd.getCost();

        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] != null && itemsOrdered[i].equals(dvd)) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;

                System.out.println("The disc has been removed: " + name + " ($" + money + ")");
                break;
            }
        }

        if (!found) {
            System.out.println("The disc is not found in cart (or misinformation as originally added): " + name + " ($" + money + ")");
        }
    }


    //Caculate the total cost
    public float totalCost() {
        float total = 0.0f;

        System.out.println("\n===== CART CONTENT =====");
        if (qtyOrdered == 0) {
            System.out.println("The cart is empty.");
        } else {
            for (int i = 0; i < qtyOrdered; i++) {
                DigitalVideoDisc disc = itemsOrdered[i];
                System.out.println((i + 1) + ". "
                        + disc.getTitle() + " - "
                        + disc.getCategory() + " - "
                        + disc.getDirector() + " - "
                        + disc.getLength() + " mins - $"
                        + disc.getCost());
                total += disc.getCost();
            }
        }
        System.out.println("=========================");
        return total;
    }

}