package tr.gedik.campushub.GedikCampusHub.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // List all students
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "students/form";
    }

    // Handle create / update
    @PostMapping
    public String saveStudent(@Valid @ModelAttribute Student student,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "students/form";  // return form with errors
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }



    // Delete
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
