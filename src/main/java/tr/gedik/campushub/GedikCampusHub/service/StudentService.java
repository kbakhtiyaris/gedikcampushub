package tr.gedik.campushub.GedikCampusHub.service;

import tr.gedik.campushub.GedikCampusHub.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);
}
