package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate publishDate;
    private LocalDate unpublishDate;
    private LocalDate manageDate;
    private String issueStatus;

    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    public LocalDate getUnpublishDate() { return unpublishDate; }
    public void setUnpublishDate(LocalDate unpublishDate) { this.unpublishDate = unpublishDate; }
    public LocalDate getManageDate() { return manageDate; }
    public void setManageDate(LocalDate manageDate) { this.manageDate = manageDate; }
    public String getIssueStatus() { return issueStatus; }
    public void setIssueStatus(String issueStatus) { this.issueStatus = issueStatus; }
    public Librarian getLibrarian() { return librarian; }
    public void setLibrarian(Librarian librarian) { this.librarian = librarian; }
    public Publication getPublication() { return publication; }
    public void setPublication(Publication publication) { this.publication = publication; }
} 