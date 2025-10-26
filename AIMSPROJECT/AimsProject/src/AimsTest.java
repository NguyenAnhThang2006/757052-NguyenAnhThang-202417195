
public class AimsTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Movie 1");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Movie 2");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Movie 3");

        cart.addDigitalVideoDisc(dvd1);

        cart.addDigitalVideoDisc(dvd1, dvd2);

        cart.addDigitalVideoDisc(dvd1, dvd2, dvd3);

        System.out.println("Total cost: " + cart.totalCost());
    }
}
