package org.example.studentmanagement.service.impl;

import lombok.AllArgsConstructor;
import org.example.studentmanagement.dto.StudentDto;
import org.example.studentmanagement.entity.Student;
import org.example.studentmanagement.repository.StudentRepository;
import org.example.studentmanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private ModelMapper modelMapper;
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        students.forEach((p) -> {
            studentDtos.add(modelMapper.map(p, StudentDto.class));
        });
        return studentDtos;
    }

    @Override
    public void saveStudent(StudentDto studentDto) {
        studentRepository.save(modelMapper.map(studentDto, Student.class));
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return modelMapper.map(studentRepository.findById(studentId).get(), StudentDto.class);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(modelMapper.map(studentDto, Student.class));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentDto findStudentById(Long studentId) {
        return modelMapper.map(studentRepository.findById(studentId).get(), StudentDto.class);
    }

}
