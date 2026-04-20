package tr.gedik.campushub.GedikCampusHub.dto;

import java.time.LocalDate;

public class EnrollRequest {
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;

    // Getters & Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
}