package tr.gedik.campushub.GedikCampusHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gedik.campushub.GedikCampusHub.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // we can add custom query methods later if needed
}
