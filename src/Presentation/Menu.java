package Presentation;

import java.util.Arrays;
import java.util.List;
import BusinessObjects.RegistrationManagement;
import Core.Interfaces.IRegistration;
import Utilities.DataInput;

public class Menu {
    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }
        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public static void manageStudent(IRegistration service) {
        RegistrationManagement studentMenu = new RegistrationManagement(service);
        studentMenu.processMenuForStudent();
    }
}