import javax.swing.JOptionPane;

public class FirstDegreeEquation {
    public static void main(String[] args) {
        String strNum1, strNum2;
        String strNotification = "You've just entered: ";

        JOptionPane.showMessageDialog(null, "The equation ax + b = 0",
                    "The format of the quation" , JOptionPane.INFORMATION_MESSAGE);

        strNum1 = JOptionPane.showInputDialog(null,
                    "Please input number a: ", "Input the number a",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + "x + ";

        strNum2 = JOptionPane.showInputDialog(null,
                    "Please input number b: ", "Input the number b",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2 + " = 0\n";

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        if(num1 != 0) {
            strNotification += "The equation has an unique solution: x = " + (0 - num2 / num1) + "\n";
        } else {
            strNotification += "The equation has infinite solution of x.";
        }

        JOptionPane.showMessageDialog(null, strNotification,
                    "Solution:", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);


    }
}
