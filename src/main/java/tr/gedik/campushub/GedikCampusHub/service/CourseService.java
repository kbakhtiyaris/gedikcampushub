package tr.gedik.campushub.GedikCampusHub.service;

import tr.gedik.campushub.GedikCampusHub.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Long id);
}
