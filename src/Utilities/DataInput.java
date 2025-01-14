package Utilities;

import java.util.List;
import java.util.Scanner;

public class DataInput {

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        System.out.print(displayMessage);
        number = getIntegerNumber();
        return number;
    }

    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String strInput;
        strInput = getString();
        if (!DataValidatation.checkStringWithFormat(strInput, "\\d{1,10}")) {

            throw new Exception("Data invalid.");
        } else {
            number = Integer.parseInt(strInput);
        }
        return number;
    }

    public static double getDoubleNumber(String displayMessage) throws Exception {
        double number = 0;
        String strInput = getString(displayMessage);
        if (!DataValidatation.checkStringEmpty(strInput)) {
            if (!DataValidatation.checkStringWithFormat(strInput, "[0-9]+[.]?[0-9]+")) {
                throw new Exception("Data invalid.");
            } else {
                number = Double.parseDouble(strInput);
            }
        }
        return number;
    }

    public static String getString() {
        String strInput;
        Scanner sc = new Scanner(System.in);
        strInput = sc.nextLine();
        return strInput;
    }

    public static String getString(String displayMessage) {
        String strInput;
        System.out.print(displayMessage);
        strInput = getString();
        return strInput;
    }

    public static String getString(String displayMessage, String pattern) {
        String strInput;
        boolean stop = true;
        do {
            strInput = getString(displayMessage).toUpperCase();
            stop = !DataValidatation.checkStringWithFormat(strInput, pattern);
            if (stop) {
                System.out.println("Please re-enter:");
            }
        } while (stop);
        return strInput;
    }

    public static String getString(String displayMessage, List<String> pattern) {
        String strInput;
        boolean stop = true;
        do {
            strInput = getString(displayMessage).trim();
            strInput = strInput.replace("^0+", "");
            stop = !DataValidatation.checkMatchCode(strInput, pattern);
            if (stop) {
                System.out.println("Please re-enter:");
            }
        } while (stop);
        return strInput;
    }

}
