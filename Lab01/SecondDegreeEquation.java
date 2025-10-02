import javax.swing.JOptionPane;

public class SecondDegreeEquation {
    public static void main(String[] args) {
        String strA, strB, strC;
        String strNotification = "You've just entered the equation:\n";

        JOptionPane.showMessageDialog(null,
            "The quadratic equation format: ax² + bx + c = 0",
            "Format of the equation", JOptionPane.INFORMATION_MESSAGE);

        strA = JOptionPane.showInputDialog(null,
                "Please input coefficient a (a != 0):", "Input a",
                JOptionPane.INFORMATION_MESSAGE);
        strNotification += strA + "x² + ";

        strB = JOptionPane.showInputDialog(null,
                "Please input coefficient b:", "Input b",
                JOptionPane.INFORMATION_MESSAGE);
        strNotification += strB + "x + ";

        strC = JOptionPane.showInputDialog(null,
                "Please input coefficient c:", "Input c",
                JOptionPane.INFORMATION_MESSAGE);
        strNotification += strC + " = 0\n";

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        if (a == 0) {
            JOptionPane.showMessageDialog(null,
                "Coefficient 'a' must not be zero for a quadratic equation.",
                "Invalid input", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        double delta = b*b - 4*a*c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2*a);
            double x2 = (-b - Math.sqrt(delta)) / (2*a);
            strNotification += "The equation has two distinct real roots:\n";
            strNotification += "x1 = " + x1 + "\n";
            strNotification += "x2 = " + x2 + "\n";
        } else if (delta == 0) {
            double x = -b / (2*a);
            strNotification += "The equation has one real root:\n";
            strNotification += "x = " + x + "\n";
        } else {
            strNotification += "The equation has no real roots.\n";
        }

        JOptionPane.showMessageDialog(null, strNotification,
                "Solution", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}
