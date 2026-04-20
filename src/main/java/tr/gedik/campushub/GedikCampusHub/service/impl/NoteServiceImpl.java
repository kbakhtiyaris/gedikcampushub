package tr.gedik.campushub.GedikCampusHub.service.impl;



import org.springframework.stereotype.Service;
import tr.gedik.campushub.GedikCampusHub.model.Note;
import tr.gedik.campushub.GedikCampusHub.repository.NoteRepository;
import tr.gedik.campushub.GedikCampusHub.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found for id: " + id));
    }

    @Override
    public Note saveNote(Note note) {
        if (note.getCreatedAt() == null) {
            note.setCreatedAt(LocalDateTime.now());
        }
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
