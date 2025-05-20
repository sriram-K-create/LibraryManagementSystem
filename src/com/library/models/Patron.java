package com.library.models;

import java.util.*;

public class Patron {
    private final String id;
    private String name;
    private String email;
    private final List<String> borrowingHistory;
    private final Set<String> reservedBooks;

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
        this.reservedBooks = new HashSet<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getBorrowingHistory() { return borrowingHistory; }
    public Set<String> getReservedBooks() { return reservedBooks; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public void addToHistory(String bookId) {
        borrowingHistory.add(bookId);
    }

    public void reserveBook(String bookId) {
        reservedBooks.add(bookId);
    }

    public void notifyAvailable(String bookId) {
        System.out.println("Notification to " + name + ": Book " + bookId + " is now available.");
        reservedBooks.remove(bookId);
    }
}