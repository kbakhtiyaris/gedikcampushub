package tr.gedik.campushub.GedikCampusHub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.gedik.campushub.GedikCampusHub.model.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // your existing methods...
    // List<Note> findByCourse(Course course);
    // List<Note> findByType(NoteType type);

    // NEW: pagination + search
    Page<Note> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
