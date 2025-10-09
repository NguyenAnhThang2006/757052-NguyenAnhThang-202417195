public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;

    private DigitalVideoDisc itemsOrdered[] =
            new DigitalVideoDisc[MAX_NUMBERS_ORDERED];

    private int qtyOrdered = 0;

    public Cart() {
        qtyOrdered = 0;
    }

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

    public void removeDigitalVideoDisc(DigitalVideoDisc dvd) {
        int found = 0;
        String name = dvd.getTitle();
        float money = dvd.getCost();

        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(dvd)) {
                found = 1;

                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;

                System.out.println("The disc has been removed: " + name + " (" + money + ")");
                break;
            }
        }
        if (found == 0) {
            System.out.println("The disc is not found in cart: " + name + " (" + money + ")");
        }
    }

    public float totalCost() {
        float total = 0.0f;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

}
