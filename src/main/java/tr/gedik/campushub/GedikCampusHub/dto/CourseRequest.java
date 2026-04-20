package tr.gedik.campushub.GedikCampusHub.dto;

public class CourseRequest {
    private String courseName;
    private Integer credits;
    private Integer maxCapacity;

    // Getters & Setters
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }
}