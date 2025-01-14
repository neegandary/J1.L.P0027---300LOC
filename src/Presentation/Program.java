package Presentation;

import Core.Interfaces.IRegistration;
import DataObject.MountainDAO;
import DataObject.StudentDAO;
import Utilities.Constant;

public class Program {
    public static void main(String[] args) {
        try {
            do {
                MountainDAO mountainService = new MountainDAO("MountainList.csv");
                IRegistration studentService = new StudentDAO(Constant.DATA_FILE);
                Menu.manageStudent(studentService);
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
