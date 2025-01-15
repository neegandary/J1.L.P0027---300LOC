package BusinessObjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Core.Entities.Student;
import Core.Interfaces.IRegistration;
import Presentation.Menu;
import Utilities.Algorithm;
import Utilities.Constant;
import Utilities.DataInput;

public class RegistrationManagement {
    IRegistration studentDAO;

    public RegistrationManagement(IRegistration studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void processMenuForStudent() {
        try {
            do {
                Menu.print("******Registration Management******|1.New Registration"
                        + "|2.Update Registration Information |3.Display Registered List"
                        + "|4.Delete Registration Information |5.Search Participants by Name"
                        + "|6.Filter Data by Campus |7.Statistics of Registration Numbers by Location"
                        + "|8. Save Data to File |9.Exit the Program |Select: ");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1: {
                        addNewStudent();
                    }
                    case 2: {
                        updateStudent();
                    }
                    case 3: {
                        System.out.println(">>Register List:");
                        printList(studentDAO.getStudents());
                    }
                    case 4: {
                        deleteStudent();
                    }
                    case 5: {
                        searchStudentByName();
                    }
                    case 6: {
                        filterStudentByCampus();
                    }
                    case 7: {
                        statisticsByLocation();
                    }
                    case 8: {
                        saveDataToFile();
                        System.out.println("Data saved to file.");
                    }
                    case 9: {
                        String confirm = DataInput.getString("\"Do you want to save before exiting? (Y/N): ")
                                .toUpperCase();
                        if (confirm.equals("Y")) {
                            System.out.println("Data saved to file.");
                            saveDataToFile();
                        }
                        System.exit(0);
                    }
                    default: {
                        System.out.println(">>Choice invalid");
                    }
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Student inputStudent() throws Exception {
        String id = DataInput.getString("Enter the id:", Constant.ID_PATTERN);
        String name = DataInput.getString("Enter the name:", Constant.NAME_PATTERN);
        String phone = DataInput.getString("Enter the phone:", Constant.PHONE_PATTERN);
        String email = DataInput.getString("Enter the email:", Constant.EMAIL_PATTERN);
        String mountainCode = DataInput.getString("Enter the mountain code:", Constant.validMountainCode);
        Student student = new Student(id, name, phone, email, mountainCode);
        return student;
    }

    public void printList(List<Student> studentList) throws Exception {
        System.out.println(String.join("", Collections.nCopies(95, "-")));
        System.out.format("%-15s | %-20s | %-20s | %-20s | %s%n", "Student ID", "Name", "Phone", "Peak Code", "Fee");
        System.out.println(String.join("", Collections.nCopies(95, "-")));
        for (Student student : studentList) {
            String code = student.getMountainCode();
            if (code.length() == 1) {
                code = "MT0" + code;
            } else {
                code = "MT" + code;
            }
            System.out.format("%-15s | %-20s | %-20s | %-20s | %d%n", student.getId(), student.getName(),
                    student.getPhoneNumber(), code, student.getTuitionFee());
        }
        System.out.println(String.join("", Collections.nCopies(95, "-")));
    }

    public void printStudent(Student student) throws Exception {
        System.out.println(String.join("", Collections.nCopies(95, "-")));
        System.out.format("%-11s:%s%n", "Student Id", student.getId());
        System.out.format("%-11s:%s%n", "Name", student.getName());
        System.out.format("%-11s:%s%n", "Phone", student.getPhoneNumber());
        System.out.format("%-11s:%s%n", "Mountain", student.getMountainCode());
        System.out.format("%-11s:%d%n", "Fee", student.getTuitionFee());
        System.out.println(String.join("", Collections.nCopies(95, "-")));
    }

    public void printStatistics(Map<String, int[]> list) throws Exception {
        System.out.println(String.join("", Collections.nCopies(95, "-")));
        System.out.format("%-15s | %-20s | %s%n", "Peak Name", "Number of Participants", "Total Cost");
        System.out.println(String.join("", Collections.nCopies(95, "-")));
        for (Map.Entry<String, int[]> entry : list.entrySet()) {
            String code = entry.getKey();
            if (code.length() == 1) {
                code = "MT0" + code;
            } else {
                code = "MT" + code;
            }
            System.out.format("%-15s | %-20d | %d%n", code, entry.getValue()[0], entry.getValue()[1]);
        }
        System.out.println(String.join("", Collections.nCopies(95, "-")));
    }

    public void addNewStudent() throws Exception {
        try {
            Student student = inputStudent();
            if (studentDAO.getStudentById(student.getId()) == null) {
                System.out.println(student.toString());
                studentDAO.addStudent(student);
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Student ID already exists.");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent() throws Exception {
        try {
            String id = DataInput.getString("Enter the id:", Constant.ID_PATTERN);
            Student student = studentDAO.getStudentById(id);
            if (student == null) {
                System.out.println("Student ID does not exist.");
                return;
            }
            String name = DataInput.getString("Enter the name:", Constant.NAME_PATTERN);
            String phone = DataInput.getString("Enter the phone:", Constant.PHONE_PATTERN);
            String email = DataInput.getString("Enter the email:", Constant.EMAIL_PATTERN);
            String mountainCode = DataInput.getString("Enter the mountain code:", Constant.MOUNTAIN_PATTERN);
            student.setName(name);
            student.setPhoneNumber(phone);
            student.setEmail(email);
            student.setMountainCode(mountainCode);
            studentDAO.updateStudent(student);
            System.out.println("Student updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent() throws Exception {
        try {
            String id = DataInput.getString("Enter the id:", Constant.ID_PATTERN);
            Student student = studentDAO.getStudentById(id);
            if (student != null) {
                printStudent(student);
                String confirm = DataInput.getString("Do you want to delete this student? (Y/N): ").toUpperCase();
                if (confirm.equals("Y")) {
                    studentDAO.removeStudent(student);
                    System.out.println("Student deleted successfully.");
                }
            } else {
                System.out.println("Student ID does not exist.");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchStudentByName() {
        try {
            String name = DataInput.getString("Enter the name:", Constant.NAME_PATTERN);
            List<Student> studentList = studentDAO.getStudents();
            studentList = studentList.stream().filter(s -> s.getName().equalsIgnoreCase(name)).toList();
            if (studentList.isEmpty()) {
                System.out.println("Student not found.");
                return;
            }
            printList(studentList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void filterStudentByCampus() {
        try {
            String campus = DataInput.getString("Enter the campus:", Constant.CAMPUS_PATTERN);
            List<Student> studentList = studentDAO.getStudents();
            studentList = studentList.stream().filter(s -> s.getMountainCode().equalsIgnoreCase(campus)).toList();
            if (studentList.isEmpty()) {
                System.out.println("Student not found.");
                return;
            }
            printList(studentList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void statisticsByLocation() {
        try {
            List<Student> studentList = studentDAO.getStudents();
            if (studentList.isEmpty()) {
                System.out.println("No student found.");
                return;
            }
            Map<String, int[]> list = new HashMap<>();
            studentList.forEach(e -> {
                list.putIfAbsent(e.getMountainCode(), new int[2]);
                list.get(e.getMountainCode())[0]++;
                list.get(e.getMountainCode())[1] += e.getTuitionFee();
            });
            printStatistics(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveDataToFile() throws Exception {
        studentDAO.saveStudentListToFile();
    }
}
