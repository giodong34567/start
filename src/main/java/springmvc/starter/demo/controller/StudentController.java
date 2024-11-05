package springmvc.starter.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.starter.demo.dto.request.StudentDTO;
import springmvc.starter.demo.dto.response.StudentResponseDTO;
import springmvc.starter.demo.service.GraduationService;
import springmvc.starter.demo.service.MajorService;
import springmvc.starter.demo.service.SchoolService;
import springmvc.starter.demo.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private GraduationService graduationService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("title", "List of students");

        return "page/students/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute("majors", majorService.findAll());
        model.addAttribute("schools", schoolService.findAll());
        return "page/students/create-form";
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("studentDTO") StudentDTO studentDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentDTO", studentDTO);
            model.addAttribute("majors", majorService.findAll());
            model.addAttribute("schools", schoolService.findAll());
            return "page/students/create-form";
        }
        studentService.saveStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/searchings")
    public String showSearchingForm(Model model, @RequestParam(name = "name", required = false) String name) {
        model.addAttribute("students", studentService.findStudentByName(name));
        return "page/students/search-students";
    }

    @GetMapping("/details")
    public String showStudentDetails(Model model, @RequestParam Map<String, String> params) {
        List<StudentResponseDTO> studentResponseDTOs = studentService.findAllDetails(params);
        model.addAttribute("students", studentResponseDTOs);
        return "page/students/details";
    }
}
