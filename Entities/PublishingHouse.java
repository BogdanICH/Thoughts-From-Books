package ro.unibuc.fmi;

import java.util.HashSet;
import java.util.Set;

public class PublishingHouse {

    private String Name;

    //lista de carti
    Set<Book> booksSet = new HashSet<>();

    //constructor
    public PublishingHouse(String Name) {
        this.Name = Name;
    }

    //adauga carte
    public void addBook(String authorName, String nationality, String name, Integer numberOfPages, Integer rating) {
        Book book = new Book(authorName, nationality, name, numberOfPages, rating);
        booksSet.add(book);
    }

    //returneaza numarul de pagini a unei carti
    public Integer getBookPages(String name) {
        for (Book book : booksSet) {
            if (book.getName().equals(name)) {
                return book.getNumberOfPages();
            }
        }
        return 0;
    }
    //returneaza ratingul unei carti
    public Integer getBookRating(String name) {
        for (Book book : booksSet) {
            if (book.getName().equals(name)) {
                return book.getRating();
            }
        }
        return 0;
    }

    //afiseaza cartile din editura asta
    public void showBooks() {
        for (Book book : booksSet) {
            System.out.println(book.getName());
        }
    }

    //afisez o singura carte
    public void showBook(String bookName) {
        for (Book book : booksSet) {
            if (book.getName().equals(bookName)) {
                System.out.println("Title: " + book.getName() + " | Author: " + book.getAuthor() + " | pages: " + book.getNumberOfPages() + " | Rating: " + book.getRating());
                book.getNotes();
            }
        }
    }

    //adaugare nota
    public Boolean addNote(String bookName, String note, Integer page) {
        for (Book book : booksSet) {
            if (book.getName().equals(bookName)) {
                book.addNote(note, page);
                return true;
            }
        }
        return false;
    }

    //numarul de carti din editura asta
    public Integer noOfBooksInThis() {
        return booksSet.size();
    }

    public String getName() {
        return Name;
    }

}
