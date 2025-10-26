package hust.soict.dsai.aims.test;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

/*
public class AimsTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Movie 1");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Movie 2");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Movie 3");

        // Thêm 1 DVD
        cart.addDigitalVideoDisc(dvd1);

        // Thêm 2 DVD
        cart.addDigitalVideoDisc(dvd1, dvd2);

        // Thêm nhiều DVD (varargs)
        cart.addDigitalVideoDisc(dvd1, dvd2, dvd3);

        // Tính tổng
        System.out.println("Total cost: " + cart.totalCost());
    }
}
 */

public class AimsTest {
    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Cinderella");
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Lion King");

        dvd1.printInfo();
        dvd2.printInfo();
        dvd3.printInfo();

        System.out.println("Total DVDs created: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
    }
}

