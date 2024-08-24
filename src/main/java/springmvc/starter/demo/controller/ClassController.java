package springmvc.starter.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.service.ClassService;
import springmvc.starter.demo.vo.ClassVO;

import java.util.Optional;

/**
 * The ClassController class is a controller that handles HTTP requests related to classes.
 * It provides methods for listing classes, creating a new class, updating an existing class,
 * and deleting a class.
 */
@Controller
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * Lists all classes.
     * @param model Model for view rendering.
     * @return The class list view.
     */
    @GetMapping
    public String listClasses(Model model) {
        model.addAttribute("classes", classService.findAll());
        model.addAttribute("title", "Classes");
        return "page/classes/list";
    }

    /**
     * Shows the form for creating a new class.
     * @param model Model for view rendering.
     * @return The create class form view.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("classVO", new ClassVO());
        return "page/classes/create-form";
    }

    /**
     * Saves a new class.
     * @param classVO The class data from the form.
     * @param bindingResult Binding result for validation.
     * @return Redirects to the class list view on success, or back to the create form on validation failure.
     */
    @PostMapping
    public String saveClass(@Valid @ModelAttribute("classVO") ClassVO classVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/classes/create-form";
        }
        ClassDTO classDTO = new ClassDTO(null, classVO.getName(), classVO.getDescription());
        classService.save(classDTO);
        return "redirect:/classes";
    }

    /**
     * Shows the form for editing an existing class.
     * @param id The ID of the class to edit.
     * @param model Model for view rendering.
     * @return The edit class form view if the class exists, or redirects to the class list view if not.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<ClassDTO> classDTO = classService.findById(id);
        if (classDTO.isPresent()) {
            model.addAttribute("classVO", new ClassVO(classDTO.get().getName(), classDTO.get().getDescription()));
            model.addAttribute("classId", id); 
            return "page/classes/update-form";
        } else {
            return "redirect:/classes";
        }
    }

    /**
     * Updates an existing class.
     * @param id The ID of the class to update.
     * @param classVO The updated class data from the form.
     * @param bindingResult Binding result for validation.
     * @param model Model for view rendering.
     * @return Redirects to the class list view on success, or back to the update form on validation failure.
     */
    @PostMapping("/update/{id}")
    public String updateClass(@PathVariable("id") Long id, @Valid @ModelAttribute("classVO") ClassVO classVO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("classId", id); 
            return "page/classes/update-form"; 
        }
        ClassDTO classDTO = new ClassDTO(id, classVO.getName(), classVO.getDescription());
        classService.update(classDTO);
        return "redirect:/classes";
    }

    /**
     * Deletes a class.
     * @param id The ID of the class to delete.
     * @return Redirects to the class list view.
     */
    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable("id") Long id) {
        classService.deleteById(id);
        return "redirect:/classes";
    }
}