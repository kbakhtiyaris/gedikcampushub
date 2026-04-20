package tr.gedik.campushub.GedikCampusHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gedik.campushub.GedikCampusHub.model.StudyBasketItem;
import tr.gedik.campushub.GedikCampusHub.model.Student;

import java.util.List;

public interface StudyBasketItemRepository extends JpaRepository<StudyBasketItem, Long> {

    List<StudyBasketItem> findByStudent(Student student);
}
