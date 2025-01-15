package DataObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Core.Entities.Student;
import Core.Interfaces.IRegistration;

public class StudentDAO implements IRegistration {
    private final List<Student> studentList = new ArrayList<>();
    private final FileManager fileManager;

    public StudentDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
        loadData();
    }

    public void loadData() throws Exception {
        String id, name, phone, email, mountainCode;
        try {
            studentList.clear();
            List<String> stuData = fileManager.readDataFromFile();
            for (String e : stuData) {
                List<String> stu = Arrays.asList(e.split(","));
                id = stu.get(0).trim();
                name = stu.get(1).trim();
                email = stu.get(2).trim();
                phone = stu.get(3).trim();
                mountainCode = stu.get(4).trim();
                Student student = new Student(id, name, phone, email, mountainCode);
                studentList.add(student);
                if (studentList.isEmpty()) {
                    throw new Exception("Student list is empty.");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Student> getStudents() throws Exception {
        studentList.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        return studentList;
    }

    public void addStudent(Student student) throws Exception {
        studentList.add(student);
    }

    public Student getStudentById(String id) throws Exception {
        if (studentList.isEmpty())
            getStudents();
        Student student = studentList.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        return student;
    }

    public void updateStudent(Student student) throws Exception {
        Student studentUpdate = getStudentById(student.getId());
        if (studentUpdate != null) {
            studentUpdate.setEmail(student.getEmail());
            studentUpdate.setName(student.getName());
            studentUpdate.setPhoneNumber(student.getPhoneNumber());
            studentUpdate.setMountainCode(student.getMountainCode());
            studentUpdate.setTuitionFee(student.getPhoneNumber());
        }
    }

    public void removeStudent(Student student) throws Exception {
        if (studentList.isEmpty())
            getStudents();
        Student stu = getStudentById(student.getId());
        if (stu != null)
            studentList.remove(stu);
    }

    public void saveStudentListToFile() throws Exception {
        List<String> stringObject = studentList.stream().map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObject);
        fileManager.saveDataToFile(data);
    }

}