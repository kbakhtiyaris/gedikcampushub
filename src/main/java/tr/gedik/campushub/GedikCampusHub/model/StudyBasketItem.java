package tr.gedik.campushub.GedikCampusHub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class StudyBasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who owns this basket item
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // which note/resource is in the basket
    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    private LocalDateTime addedAt;

    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }


    public StudyBasketItem() {
    }

    public StudyBasketItem(Student student, Note note) {
        this.student = student;
        this.note = note;
        this.addedAt = LocalDateTime.now();
        this.status = StudyStatus.PLANNED;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}


