import java.util.Scanner;

public class TriangleDrawing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for(int k = 0; k < 2*(i + 1) - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\nDrawing completed!");
        sc.close();

    }

}