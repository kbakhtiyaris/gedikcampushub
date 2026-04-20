package tr.gedik.campushub.GedikCampusHub.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.gedik.campushub.GedikCampusHub.model.Course;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.service.CourseService;
import tr.gedik.campushub.GedikCampusHub.service.StudentService;



@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;  // ADD THIS

    public CourseController(CourseService courseService, StudentService studentService) {  // ADD studentService HERE
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // List all courses
    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/list";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/form";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courses/form";
    }

    // Handle create/edit submit
    @PostMapping
    public String saveCourse(@ModelAttribute Course course,
                             BindingResult result,
                             Model model) {
        // Set defaults for new fields we added
        if (course.getMaxCapacity() == null) {
            course.setMaxCapacity(30);
        }
        if (course.getCurrentEnrollment() == null) {
            course.setCurrentEnrollment(0);
        }

        courseService.saveCourse(course);
        return "redirect:/courses";
    
    }





    // Delete
    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
