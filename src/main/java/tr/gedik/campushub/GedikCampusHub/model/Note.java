package tr.gedik.campushub.GedikCampusHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    // "NOTE", "PAST_PAPER", "ANNOUNCEMENT" (simple for now)
    private String type;

    private LocalDateTime createdAt;

    // File metadata
    private String fileName;
    private String fileType;
    private String filePath;

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }


    public Note() {
    }

    public Note(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    // getters and setters

}
