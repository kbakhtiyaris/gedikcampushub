package tr.gedik.campushub.GedikCampusHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gedik.campushub.GedikCampusHub.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // later we could add: Optional<Student> findByEmail(String email);
}
