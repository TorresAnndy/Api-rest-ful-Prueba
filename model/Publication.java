package model;

import jakarta.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int year;
    private String author;
    private String statusPublication;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getStatusPublication() { return statusPublication; }
    public void setStatusPublication(String statusPublication) { this.statusPublication = statusPublication; }
} 