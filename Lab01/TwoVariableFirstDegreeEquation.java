import javax.swing.JOptionPane;

public class TwoVariableFirstDegreeEquation {
    public static void main(String[] args) {
        String strNum1, strNum2, strNum3, strNum4, strNum5, strNum6;
        String strNotification = "You've just entered: \n";

        JOptionPane.showMessageDialog(null, "The first equation a1x + b1y = c1",
                    "The format of the equation", JOptionPane.INFORMATION_MESSAGE);

        strNum1 = JOptionPane.showInputDialog(null,
                    "Please input number a1: ", "Input the number a1",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + "x + ";

        strNum2 = JOptionPane.showInputDialog(null,
                    "Please input number b1: ", "Input the number b1",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2 + "y = ";

        strNum5 = JOptionPane.showInputDialog(null,
                    "Please input number c1: ", "Input the number c1",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum5 + "\n";

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
        double num5 = Double.parseDouble(strNum5);

        JOptionPane.showMessageDialog(null, "The second equation a2x + b2y = c2",
                    "The format of the equation", JOptionPane.INFORMATION_MESSAGE);

        strNum3 = JOptionPane.showInputDialog(null,
                    "Please input number a2: ", "Input the number a2",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum3 + "x + ";

        strNum4 = JOptionPane.showInputDialog(null,
                    "Please input number b2: ", "Input the number b2",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum4 + "y = ";

        strNum6 = JOptionPane.showInputDialog(null,
                    "Please input number c2: ", "Input the number c2",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum6 + "\n";

        double num3 = Double.parseDouble(strNum3);
        double num4 = Double.parseDouble(strNum4);
        double num6 = Double.parseDouble(strNum6);

        double determinant = (num1 * num4) - (num2 * num3);

        if (determinant != 0) {
            double x = (num5 * num4 - num2 * num6) / determinant;
            double y = (num1 * num6 - num3 * num5) / determinant;

            strNotification += "The equation has a unique solution: \n";
            strNotification += "x = " + x + "\n";
            strNotification += "y = " + y + "\n";
        } else {
            strNotification += "The system has no unique solution.\n";

            if ((num1 * num4 - num2 * num3) == 0 && (num1 * num6 - num2 * num5) == 0) {
                strNotification += "The system has infinite solutions.\n";
            } else {
                strNotification += "The system has no solution.\n";
            }
        }

        JOptionPane.showMessageDialog(null, strNotification,
                    "Solution:", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
