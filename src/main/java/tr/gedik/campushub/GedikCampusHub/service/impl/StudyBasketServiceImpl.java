package tr.gedik.campushub.GedikCampusHub.service.impl;

import org.springframework.stereotype.Service;
import tr.gedik.campushub.GedikCampusHub.model.Note;
import tr.gedik.campushub.GedikCampusHub.model.Student;
import tr.gedik.campushub.GedikCampusHub.model.StudyBasketItem;
import tr.gedik.campushub.GedikCampusHub.repository.StudyBasketItemRepository;
import tr.gedik.campushub.GedikCampusHub.service.StudyBasketService;
import tr.gedik.campushub.GedikCampusHub.model.StudyStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudyBasketServiceImpl implements StudyBasketService {

    private final StudyBasketItemRepository basketRepo;

    public StudyBasketServiceImpl(StudyBasketItemRepository basketRepo) {
        this.basketRepo = basketRepo;
    }

    @Override
    public List<StudyBasketItem> getBasketForStudent(Student student) {
        return basketRepo.findByStudent(student);
    }

    @Override
    public StudyBasketItem addToBasket(Student student, Note note) {
        StudyBasketItem item = new StudyBasketItem();
        item.setStudent(student);
        item.setNote(note);
        item.setAddedAt(LocalDateTime.now());
        item.setStatus(StudyStatus.PLANNED);
        return basketRepo.save(item);
    }

    @Override
    public void updateStatus(Long itemId, StudyStatus status) {
        StudyBasketItem item = basketRepo.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Basket item not found: " + itemId));
        item.setStatus(status);
        basketRepo.save(item);
    }


    @Override
    public void removeFromBasket(Long itemId) {
        basketRepo.deleteById(itemId);
    }
}
