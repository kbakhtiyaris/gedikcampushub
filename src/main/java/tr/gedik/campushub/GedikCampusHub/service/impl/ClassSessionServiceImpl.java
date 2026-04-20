package tr.gedik.campushub.GedikCampusHub.service.impl;

import org.springframework.stereotype.Service;
import tr.gedik.campushub.GedikCampusHub.model.ClassSession;
import tr.gedik.campushub.GedikCampusHub.model.Course;
import tr.gedik.campushub.GedikCampusHub.repository.ClassSessionRepository;
import tr.gedik.campushub.GedikCampusHub.service.ClassSessionService;

import java.util.List;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository classSessionRepository;

    public ClassSessionServiceImpl(ClassSessionRepository classSessionRepository) {
        this.classSessionRepository = classSessionRepository;
    }

    @Override
    public List<ClassSession> getAllSessions() {
        return classSessionRepository.findAll();
    }

    @Override
    public ClassSession getSessionById(Long id) {
        return classSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ClassSession not found with id: " + id));
    }

    @Override
    public ClassSession saveSession(ClassSession session) {
        return classSessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long id) {
        classSessionRepository.deleteById(id);
    }

    @Override
    public List<ClassSession> getSessionsByCourse(Course course) {
        return classSessionRepository.findByCourse(course);
    }
}
