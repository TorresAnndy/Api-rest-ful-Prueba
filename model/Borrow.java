package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;
    private LocalDate toDate;
    private String borrowStatus;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
    public String getBorrowStatus() { return borrowStatus; }
    public void setBorrowStatus(String borrowStatus) { this.borrowStatus = borrowStatus; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public Publication getPublication() { return publication; }
    public void setPublication(Publication publication) { this.publication = publication; }
} 