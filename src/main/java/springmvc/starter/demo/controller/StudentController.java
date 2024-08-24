package springmvc.starter.demo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.starter.demo.dto.StudentDTO;
import springmvc.starter.demo.servicre.StudentService;
import springmvc.starter.demo.vo.StudentVO;

import java.util.Optional;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("title", "Students");
        return "page/students/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("studentVO", new StudentVO());
        return "page/students/create-form";
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("studentVO") StudentVO studentVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/students/create-form";
        }
        StudentDTO studentDTO = new StudentDTO(null, studentVO.getName(), studentVO.getEmail(), studentVO.getAge());
        studentService.save(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<StudentDTO> studentDTO = studentService.findById(id);
        if (studentDTO.isPresent()) {
            model.addAttribute("studentVO", new StudentVO(studentDTO.get().getName(), studentDTO.get().getEmail(), studentDTO.get().getAge()));
            model.addAttribute("studentId", id); // Pass the ID to the form
            return "page/students/update-form";
        } else {
            return "redirect:/students";
        }
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid @ModelAttribute("studentVO") StudentVO studentVO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentId", id); // Ensure the ID is passed back to the form
            return "page/students/update-form";  // Return to the update form view
        }
        StudentDTO studentDTO = new StudentDTO(id, studentVO.getName(), studentVO.getEmail(), studentVO.getAge());
        studentService.update(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}