package tr.gedik.campushub.GedikCampusHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gedik.campushub.GedikCampusHub.model.ClassSession;
import tr.gedik.campushub.GedikCampusHub.model.Course;

import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {

    // Optional helper method: find sessions by course
    List<ClassSession> findByCourse(Course course);
}
