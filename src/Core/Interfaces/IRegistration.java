package Core.Interfaces;

import java.util.List;
import Core.Entities.Student;

public interface IRegistration {
    List<Student> getStudents() throws Exception;

    Student getStudentById(String id) throws Exception;

    void addStudent(Student student) throws Exception;

    void updateStudent(Student student) throws Exception;

    void removeStudent(Student student) throws Exception;

    void saveStudentListToFile() throws Exception;
}