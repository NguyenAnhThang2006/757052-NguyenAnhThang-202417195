public class Aims {
    public static void main(String[] args) {

        //Create a new cart
        Cart anOrder = new Cart();

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
        anOrder.removeDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Nonexistent",
                "Drama", "Unknown", 120, 15.00f);
        anOrder.removeDigitalVideoDisc(dvd4);


        //print total cost of the items in the cart
        System.out.print("\nTotal cost is: ");
        System.out.println(anOrder.totalCost());

    }
}
