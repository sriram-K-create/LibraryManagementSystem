package com.library.models;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;
    private String currentBranch;

    public Book(String isbn, String title, String author, int publicationYear, String currentBranch) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.currentBranch = currentBranch;
        this.isBorrowed = false;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isBorrowed() { return isBorrowed; }
    public String getCurrentBranch() { return currentBranch; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }
    public void setCurrentBranch(String branch) { this.currentBranch = branch; }
}