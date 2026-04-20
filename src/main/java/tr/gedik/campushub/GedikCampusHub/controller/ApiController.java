package tr.gedik.campushub.GedikCampusHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.gedik.campushub.GedikCampusHub.dto.ApiResponse;
import tr.gedik.campushub.GedikCampusHub.dto.CourseRequest;
import tr.gedik.campushub.GedikCampusHub.dto.EnrollRequest;
import tr.gedik.campushub.GedikCampusHub.model.Course;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.model.StudyBasketItem;
import tr.gedik.campushub.GedikCampusHub.repository.CourseRepository;
import tr.gedik.campushub.GedikCampusHub.repository.StudentRepository;
import tr.gedik.campushub.GedikCampusHub.repository.StudyBasketItemRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudyBasketItemRepository basketRepository;

    // ✅ GET all courses → 200 OK with list
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    // ✅ GET course by ID → 200 OK or 404 Not Found
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Error: Course ID [" + id + "] does not exist.");
        }
        return ResponseEntity.ok(course.get());
    }

    // ✅ POST create course → 201 Created
    @PostMapping("/courses")
    public ResponseEntity<ApiResponse> createCourse(@RequestBody CourseRequest req) {
        Course course = new Course();
        course.setName(req.getCourseName());
        course.setMaxCapacity(req.getMaxCapacity() != null ? req.getMaxCapacity() : 30);
        course.setCurrentEnrollment(0);

        // ADD THESE - set defaults for required fields
        course.setCode("COURSE-" + System.currentTimeMillis()); // auto-generate code
        course.setDepartment("General");                         // default department

        courseRepository.save(course);
        return ResponseEntity.status(201)
                .body(new ApiResponse("Course created successfully", course));
    }

    // ✅ PUT update course description → 200 OK or 404
    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody CourseRequest req) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Error: Course ID [" + id + "] does not exist.");
        }
        Course course = optional.get();
        if (req.getCourseName() != null) course.setName(req.getCourseName());
        courseRepository.save(course);
        return ResponseEntity.ok(new ApiResponse("Course updated", course));
    }

    // ✅ POST enroll student → 201 Created or 400 Full or 404 Not Found
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestBody EnrollRequest req) {

        Optional<Student> studentOpt = studentRepository.findById(req.getStudentId());
        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Error: Student ID [" + req.getStudentId() + "] does not exist.");
        }

        Optional<Course> courseOpt = courseRepository.findById(req.getCourseId());
        if (courseOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("Error: Course ID [" + req.getCourseId() + "] does not exist.");
        }

        Course course = courseOpt.get();

        // ✅ Capacity check → 400 Bad Request
        if (course.getCurrentEnrollment() >= course.getMaxCapacity()) {
            return ResponseEntity.status(400)
                    .body("Error: Course capacity has been reached.");
        }

        // Add to basket as enrollment
        StudyBasketItem item = new StudyBasketItem();
        item.setStudent(studentOpt.get());
        item.setAddedAt(LocalDateTime.now());
        basketRepository.save(item);

        // Increment enrollment count
        course.setCurrentEnrollment(course.getCurrentEnrollment() + 1);
        courseRepository.save(course);

        return ResponseEntity.status(201)
                .body(new ApiResponse("Enrollment successful! Enrollment ID: " + item.getId(), item.getId()));
    }

    // ✅ GET all students → 200 OK
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
}