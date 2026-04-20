package tr.gedik.campushub.GedikCampusHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.gedik.campushub.GedikCampusHub.model.Note;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.service.NoteService;
import tr.gedik.campushub.GedikCampusHub.service.StudentService;
import tr.gedik.campushub.GedikCampusHub.service.StudyBasketService;
import tr.gedik.campushub.GedikCampusHub.model.StudyStatus;

@Controller
@RequestMapping("/basket")
public class StudyBasketController {

    // TODO: later replace with real logged-in student
    private static final Long CURRENT_STUDENT_ID = 3L;

    private final StudyBasketService basketService;
    private final StudentService studentService;
    private final NoteService noteService;

    public StudyBasketController(StudyBasketService basketService,
                                 StudentService studentService,
                                 NoteService noteService) {
        this.basketService = basketService;
        this.studentService = studentService;
        this.noteService = noteService;
    }

    @GetMapping
    public String viewBasket(Model model) {
        Student student = studentService.getStudentById(CURRENT_STUDENT_ID);
        model.addAttribute("student", student);
        model.addAttribute("items", basketService.getBasketForStudent(student));
        return "basket/list";
    }

    @GetMapping("/add")
    public String addToBasket(@RequestParam Long noteId) {
        Student student = studentService.getStudentById(CURRENT_STUDENT_ID);
        Note note = noteService.getNoteById(noteId);
        basketService.addToBasket(student, note);
        return "redirect:/basket";
    }

    @GetMapping("/{itemId}/remove")
    public String removeFromBasket(@PathVariable Long itemId) {
        basketService.removeFromBasket(itemId);
        return "redirect:/basket";
    }
    @GetMapping("/{itemId}/start")
    public String markInProgress(@PathVariable Long itemId) {
        basketService.updateStatus(itemId, StudyStatus.IN_PROGRESS);
        return "redirect:/basket";
    }

    @GetMapping("/{itemId}/complete")
    public String markCompleted(@PathVariable Long itemId) {
        basketService.updateStatus(itemId, StudyStatus.COMPLETED);
        return "redirect:/basket";
    }

}
