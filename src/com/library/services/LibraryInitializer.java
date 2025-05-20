package com.library.services;

import com.library.models.*;

import java.util.*;

public class LibraryInitializer {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Patron> patrons = new HashMap<>();
    private final Map<String, Queue<Patron>> reservations = new HashMap<>();
    private final Map<String, List<Book>> branches = new HashMap<>();

    public void run() {
        System.out.println("Library Management System is up and running.");

        branches.put("Downtown", new ArrayList<>());
        branches.put("Uptown", new ArrayList<>());

        Book b1 = new Book("ISBN123", "1984", "George Orwell", 1949, "Downtown");
        Book b2 = new Book("ISBN124", "Brave New World", "Aldous Huxley", 1932, "Uptown");
        books.put(b1.getIsbn(), b1);
        books.put(b2.getIsbn(), b2);
        branches.get("Downtown").add(b1);
        branches.get("Uptown").add(b2);

        Patron p1 = new Patron("P001", "Alice", "alice@example.com");
        patrons.put(p1.getId(), p1);

        reserveBook(p1.getId(), b2.getIsbn());
        returnBook(b2.getIsbn());

        recommendBooks(p1);
        transferBook(b1.getIsbn(), "Downtown", "Uptown");

        // Add a new book
        Book newBook = new Book("ISBN125", "To Kill a Mockingbird", "Harper Lee", 1960, "Downtown");
        books.put(newBook.getIsbn(), newBook);
        branches.get("Downtown").add(newBook);
        System.out.println("Added book: " + newBook.getTitle());

        // Search Book by Title
        String titleToSearch = "1984";
        books.values().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(titleToSearch))
                .forEach(b -> System.out.println("Found book by title: " + b.getTitle()));

        // Add a new patron
        Patron newPatron = new Patron("P002", "Bob", "bob@example.com");
        patrons.put(newPatron.getId(), newPatron);
        System.out.println("Added patron: " + newPatron.getName());

        // Simulate book checkout
        Book bookToBorrow = books.get("ISBN125");
        if (!bookToBorrow.isBorrowed()) {
            bookToBorrow.setBorrowed(true);
            newPatron.addToHistory(bookToBorrow.getIsbn());
            System.out.println(newPatron.getName() + " checked out: " + bookToBorrow.getTitle());
        }

        // Simulate book return and notify if reserved
        returnBook("ISBN124"); // This ISBN is reserved by Alice in initializer

        // Recommend books for new patron
        recommendBooks(newPatron);

        // Transfer book
        transferBook("ISBN125", "Downtown", "Uptown");
    }

    private void reserveBook(String patronId, String bookId) {
        reservations.putIfAbsent(bookId, new LinkedList<>());
        reservations.get(bookId).offer(patrons.get(patronId));
        patrons.get(patronId).reserveBook(bookId);
        System.out.println(patrons.get(patronId).getName() + " reserved book: " + bookId);
    }

    void returnBook(String bookId) {
        Book book = books.get(bookId);
        book.setBorrowed(false);
        Queue<Patron> queue = reservations.get(bookId);
        if (queue != null && !queue.isEmpty()) {
            Patron next = queue.poll();
            next.notifyAvailable(bookId);
        }
    }

    void recommendBooks(Patron patron) {
        System.out.println("Recommendations for " + patron.getName() + ":");
        for (Book book : books.values()) {
            if (!patron.getBorrowingHistory().contains(book.getIsbn())) {
                System.out.println("- " + book.getTitle());
            }
        }
    }

    public void transferBook(String bookId, String fromBranch, String toBranch) {
        Book book = books.get(bookId);
        if (book != null && fromBranch != null && toBranch != null && branches.containsKey(fromBranch) && branches.containsKey(toBranch)) {
            branches.get(fromBranch).remove(book);
            branches.get(toBranch).add(book);
            book.setCurrentBranch(toBranch);
            System.out.println("Transferred book " + bookId + " from " + fromBranch + " to " + toBranch);
        } else {
            System.out.println("Transfer failed. Please check the input values.");
        }
    }
}