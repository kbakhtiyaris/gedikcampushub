package tr.gedik.campushub.GedikCampusHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.gedik.campushub.GedikCampusHub.model.ClassSession;
import tr.gedik.campushub.GedikCampusHub.model.Course;
import tr.gedik.campushub.GedikCampusHub.service.ClassSessionService;
import tr.gedik.campushub.GedikCampusHub.service.CourseService;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class ClassSessionController {

    private final ClassSessionService classSessionService;
    private final CourseService courseService;

    public ClassSessionController(ClassSessionService classSessionService, CourseService courseService) {
        this.classSessionService = classSessionService;
        this.courseService = courseService;
    }

    // List all sessions (timetable overview)
    @GetMapping
    public String listSessions(Model model) {
        model.addAttribute("sessions", classSessionService.getAllSessions());
        return "sessions/list";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ClassSession classSession = new ClassSession();
        model.addAttribute("classSession", classSession);
        model.addAttribute("courses", courseService.getAllCourses());
        List<String> days = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");
        model.addAttribute("daysOfWeek", days);
        return "sessions/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ClassSession classSession = classSessionService.getSessionById(id);
        model.addAttribute("classSession", classSession);
        model.addAttribute("courses", courseService.getAllCourses());
        List<String> days = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");
        model.addAttribute("daysOfWeek", days);
        return "sessions/form";
    }


    // Handle form submit
    @PostMapping
    public String saveSession(@RequestParam Long courseId,
                              @RequestParam String dayOfWeek,
                              @RequestParam String startTime,
                              @RequestParam String endTime,
                              @RequestParam(required = false) Long id,
                              @RequestParam String classroom) {

        Course course = courseService.getCourseById(courseId);

        ClassSession session;
        if (id != null) {
            session = classSessionService.getSessionById(id);
        } else {
            session = new ClassSession();
        }

        session.setCourse(course);
        session.setDayOfWeek(dayOfWeek);
        session.setClassroom(classroom);
        session.setStartTime(LocalTime.parse(startTime));
        session.setEndTime(LocalTime.parse(endTime));

        classSessionService.saveSession(session);
        return "redirect:/sessions";
    }

    // Delete
    @GetMapping("/{id}/delete")
    public String deleteSession(@PathVariable Long id) {
        classSessionService.deleteSession(id);
        return "redirect:/sessions";
    }
}
