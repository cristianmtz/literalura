package com.aluracursos.literalura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;


@Entity
public class

Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Author> authors;

    private List<String> subjects;

    private List<String> bookshelves;


    private List<String> languages;

    private Boolean copyright;

    private Double downloadCount;


    //Constructor

    public Book() {
    }

    public Book(Long id, String title, List<Author> authors, List<String> subjects, List<String> bookshelves, List<String> languages, Boolean copyright, Double downloadCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.subjects = subjects;
        this.bookshelves = bookshelves;
        this.languages = languages;
        this.copyright = copyright;
        this.downloadCount = downloadCount;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }


    //


    @Override
    public String toString() {
        return "----------Name Book--------" +
                "Title='" + title+
                "Authors=" + authors +
                "Subjects=" + subjects +
                "Bookshelves=" + bookshelves +
                "Languages=" + languages +
                "Copyright=" + copyright +
                "Download Count=" + downloadCount;
    }
}
