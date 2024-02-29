package org.example.studentmanagement.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.studentmanagement.dto.StudentDto;
import org.example.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    @GetMapping("students")
    public String listStudents(Model model){
        List<StudentDto> studentDtos = studentService.getAllStudents();
        model.addAttribute("students", studentDtos);
        return "students";
    }

    @GetMapping("students/new")
    public String newStudent(Model model){
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "create_student";
    }

    @PostMapping("students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult bindingResult,
                              Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("student", studentDto);
            return "create_student";
        }
        studentService.saveStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("students/{studentId}/edit")
    public String editStudent(Model model, @PathVariable("studentId") Long studentId){
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "edit_student";
    }

    @PostMapping("students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model){
        model.addAttribute("student", studentService.findStudentById(studentId));
        return "view_student";
    }

}
