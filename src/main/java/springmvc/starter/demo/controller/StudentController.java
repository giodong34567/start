package springmvc.starter.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.dto.StudentDTO;
import springmvc.starter.demo.service.ClassService;
import springmvc.starter.demo.service.StudentService;
import springmvc.starter.demo.vo.StudentVO;

import java.util.Optional;

/**
 * Controller for managing student-related operations.
 * This controller handles listing, creating, editing, updating, and deleting students.
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    /**
     * Lists all students.
     * @param model Model for view rendering.
     * @return The student list view.
     */
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("title", "Students");
        return "page/students/list";
    }

    /**
     * Shows the form for creating a new student.
     * @param model Model for view rendering.
     * @return The create student form view.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("studentVO", new StudentVO());
        model.addAttribute("classes", classService.findAll()); 
        return "page/students/create-form";
    }

    /**
     * Saves a new student.
     * @param studentVO The student data from the form.
     * @param bindingResult Binding result for validation.
     * @param model Model for view rendering.
     * @return Redirects to the student list view on success, or back to the create form on validation failure.
     */
    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("studentVO") StudentVO studentVO, BindingResult bindingResult, Model model) {
        Optional<ClassDTO> classDTO = classService.findById(studentVO.getClassId());
        if (classDTO.isEmpty()) {
            bindingResult.rejectValue("classId", "error.classId", "Lớp không tồn tại");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("classes", classService.findAll());
            return "page/students/create-form";
        }
        StudentDTO studentDTO = new StudentDTO(null, studentVO.getName(), studentVO.getEmail(), studentVO.getAge(), new ClassDTO(studentVO.getClassId(), null, null));
        studentService.save(studentDTO);
        return "redirect:/students";
    }

    /**
     * Shows the form for editing an existing student.
     * @param id The ID of the student to edit.
     * @param model Model for view rendering.
     * @return The edit student form view if the student exists, or redirects to the student list view if not.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<StudentDTO> studentDTO = studentService.findById(id);
        if (studentDTO.isPresent()) {
            model.addAttribute("studentVO", new StudentVO(studentDTO.get().getName(), studentDTO.get().getEmail(), studentDTO.get().getAge(), studentDTO.get().getStudentClass().getId()));
            model.addAttribute("studentId", id);
            model.addAttribute("classes", classService.findAll()); 
            return "page/students/update-form";
        } else {
            return "redirect:/students";
        }
    }

    /**
     * Updates an existing student.
     * @param id The ID of the student to update.
     * @param studentVO The updated student data from the form.
     * @param bindingResult Binding result for validation.
     * @param model Model for view rendering.
     * @return Redirects to the student list view on success, or back to the update form on validation failure.
     */
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @Valid @ModelAttribute("studentVO") StudentVO studentVO, BindingResult bindingResult, Model model) {

        Optional<ClassDTO> classDTO = classService.findById(studentVO.getClassId());
        if (classDTO.isEmpty()) {
            bindingResult.rejectValue("classId", "error.classId", "Lớp không tồn tại");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentId", id); // Ensure the ID is passed back to the form
            model.addAttribute("classes", classService.findAll()); // Pass classes back to the form
            return "page/students/update-form";  // Return to the update form view
        }
        StudentDTO studentDTO = new StudentDTO(id, studentVO.getName(), studentVO.getEmail(), studentVO.getAge(), new ClassDTO(studentVO.getClassId(), null, null));
        studentService.update(studentDTO, id);
        return "redirect:/students";
    }

    /**
     * Deletes a student.
     * @param id The ID of the student to delete.
     * @return Redirects to the student list view.
     */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}