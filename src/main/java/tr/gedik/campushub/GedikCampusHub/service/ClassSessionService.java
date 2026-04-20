package tr.gedik.campushub.GedikCampusHub.service;

import tr.gedik.campushub.GedikCampusHub.model.ClassSession;
import tr.gedik.campushub.GedikCampusHub.model.Course;

import java.util.List;

public interface ClassSessionService {

    List<ClassSession> getAllSessions();

    ClassSession getSessionById(Long id);

    ClassSession saveSession(ClassSession session);

    void deleteSession(Long id);

    List<ClassSession> getSessionsByCourse(Course course);
}
