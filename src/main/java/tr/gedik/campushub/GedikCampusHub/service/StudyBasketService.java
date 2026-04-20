package tr.gedik.campushub.GedikCampusHub.service;

import tr.gedik.campushub.GedikCampusHub.model.StudyBasketItem;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.model.Note;
import tr.gedik.campushub.GedikCampusHub.model.StudyStatus;

import java.util.List;

public interface StudyBasketService {

    List<StudyBasketItem> getBasketForStudent(Student student);

    StudyBasketItem addToBasket(Student student, Note note);

    void removeFromBasket(Long itemId);

    void updateStatus(Long itemId, StudyStatus status);

}
