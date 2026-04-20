package tr.gedik.campushub.GedikCampusHub.service;

import tr.gedik.campushub.GedikCampusHub.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note saveNote(Note note);
    void deleteNote(Long id);
}
