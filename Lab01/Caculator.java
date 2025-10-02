import javax.swing.JOptionPane;

public class Caculator {
    public static void main(String[] args) {
        String strNum1, strNum2;
        String strNotification = "You've just entered: ";

        strNum1 = JOptionPane.showInputDialog(null,
                    "Please input the first number: ", "Input the first number",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + " and ";

        strNum2 = JOptionPane.showInputDialog(null,
                    "Please input the second number: ", "Input the second number",
                    JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2 + "\n";

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        strNotification += "Sum of two numbers: " + (num1 + num2) + "\n";
        strNotification += "Difference of two numbers: " + (num1 - num2) + "\n";
        strNotification += "Product of two numbers: " + (num1 * num2) + "\n";
        
        if(num2 != 0) {
            strNotification += "Quotient of two numbers: " + (num1 / num2) + "\n";
        } else {
            strNotification += "Can't make this division";
        }

        JOptionPane.showMessageDialog(null, strNotification, "Caculation", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
