package com.example.assesmentjava2.Models;

public class Book {
    private int issn;
    private String title;
    private int publishYear;
    private String authors;
    private String genre;
    private boolean isAvailable;

    public Book(int issn, String title, int publishYear, String authors, String genre, boolean available) {
        this.issn = issn;
        this.title = title;
        this.publishYear = publishYear;
        this.authors = authors;
        this.genre = genre;
        this.isAvailable = available;
    }

    // Getters and setters
    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
