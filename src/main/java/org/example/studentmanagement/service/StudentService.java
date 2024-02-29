package org.example.studentmanagement.service;

import org.example.studentmanagement.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    void saveStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);

    StudentDto findStudentById(Long studentId);
}
