package model;

import jakarta.persistence.*;

@Entity
@Table(name = "librarian")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idLibrarian;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdLibrarian() { return idLibrarian; }
    public void setIdLibrarian(String idLibrarian) { this.idLibrarian = idLibrarian; }
} 