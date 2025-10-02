import java.util.Scanner;

public class ShowNumberOfDaysInMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month = 0;
        int year = -1;
        String m = "lam";

        while (month == 0) {
            System.out.print("Input month: ");
            m = sc.next();

            if (m.equals("1") || m.equals("January") || m.equals("Jan") || m.equals("Jan.")) {
                month = 1;
            } else if (m.equals("2") || m.equals("February") || m.equals("Feb") || m.equals("Feb.")) {
                month = 2;
            } else if (m.equals("3") || m.equals("March") || m.equals("Mar") || m.equals("Mar.")) {
                month = 3;
            } else if (m.equals("4") || m.equals("April") || m.equals("Apr") || m.equals("Apr.")) {
                month = 4;
            } else if (m.equals("5") || m.equals("May")) {
                month = 5;
            } else if (m.equals("6") || m.equals("June") || m.equals("Jun") || m.equals("Jun.")) {
                month = 6;
            } else if (m.equals("7") || m.equals("July") || m.equals("Jul") || m.equals("Jul.")) {
                month = 7;
            } else if (m.equals("8") || m.equals("August") || m.equals("Aug") || m.equals("Aug.")) {
                month = 8;
            } else if (m.equals("9") || m.equals("September") || m.equals("Sep") || m.equals("Sep.")) {
                month = 9;
            } else if (m.equals("10") || m.equals("October") || m.equals("Oct") || m.equals("Oct.")) {
                month = 10;
            } else if (m.equals("11") || m.equals("November") || m.equals("Nov") || m.equals("Nov.")) {
                month = 11;
            } else if (m.equals("12") || m.equals("December") || m.equals("Dec") || m.equals("Dec.")) {
                month = 12;
            } else {
                System.out.println("Invalid Month. Please try again.");
            }
        }

        while (year < 0) {
            System.out.print("Input year: ");
            String y = sc.next();
            try {
                year = Integer.parseInt(y);
                if (year < 0) {
                    System.out.println("Invalid Year. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Year. Please try again.");
            }
        }

        int days = 0;

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                if (year % 400 == 0) {
                    days = 29;
                }
                else if (year % 100 == 0) {
                    days = 28;
                }
                else if (year % 4 == 0) {
                    days = 29;
                }
                else {
                    days = 28;
                }
                break;
        }

        System.out.println("There are " + days + " days in " + m + " " + year + ".");
    }
}