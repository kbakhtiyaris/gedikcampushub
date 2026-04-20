package tr.gedik.campushub.GedikCampusHub.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;  // for throws IOException

import tr.gedik.campushub.GedikCampusHub.model.Note;
import tr.gedik.campushub.GedikCampusHub.repository.NoteRepository;
import tr.gedik.campushub.GedikCampusHub.service.NoteService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;






import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/notes")
public class NoteController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    private final NoteService noteService;
    private final NoteRepository noteRepository;  // ADD THIS

    public NoteController(NoteService noteService, NoteRepository noteRepository) {  // ADD noteRepository HERE
        this.noteService = noteService;
        this.noteRepository = noteRepository;
    }

    // rest of your code stays the same...

    @GetMapping
    public String listNotes(Model model,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Note> notePage;

        if (keyword == null || keyword.isBlank()) {
            notePage = noteRepository.findAll(pageable);
        } else {
            notePage = noteRepository.findByTitleContainingIgnoreCase(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("notes", notePage.getContent());
        model.addAttribute("currentPage", notePage.getNumber());
        model.addAttribute("totalPages", notePage.getTotalPages());
        return "notes/list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        return "notes/form";
    }

    @PostMapping
    public String saveNote(@ModelAttribute Note note,
                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadDir = Paths.get("uploads");
            Files.createDirectories(uploadDir);
            Path path = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            note.setFileName(fileName);
            note.setFileType(file.getContentType());
            note.setFilePath(path.toString());
        }
        noteService.saveNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
        Note note = noteService.getNoteById(id);
        if (note.getFilePath() == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(note.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + note.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(note.getFileType()))
                .body(resource);
    }


    @GetMapping("/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}
