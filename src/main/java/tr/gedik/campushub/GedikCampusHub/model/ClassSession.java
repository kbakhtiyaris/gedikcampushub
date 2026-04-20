package tr.gedik.campushub.GedikCampusHub.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. MONDAY, TUESDAY (we'll store as String)
    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String classroom;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public ClassSession() {
    }

    public ClassSession(String dayOfWeek, LocalTime startTime, LocalTime endTime,
                        String classroom, Course course) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.course = course;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
