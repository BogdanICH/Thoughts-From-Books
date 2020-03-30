package ro.unibuc.fmi.entities;

import ro.unibuc.fmi.PublishingHouse;
import java.util.HashSet;
import java.util.Set;


public class Bookshelf {
    private String name;
    private Integer noOfBooks;

    //lista de edituri
    Set<PublishingHouse> publishingHousesSet = new HashSet<>();

    //constructor
    public Bookshelf(String name) {
        this.name = name;
    }


    //afisez edituri
    public void showPublishingHouse() {
        for (PublishingHouse publishingHouse : publishingHousesSet) {
            System.out.println(publishingHouse.getName());
        }
    }

    //numar de carti pe acest raft
    public Integer noOfBooksInBookshelf() {
        noOfBooks = 0;
        for (PublishingHouse pH : publishingHousesSet) {
            noOfBooks += pH.noOfBooksInThis();
        }
        return noOfBooks;
    }

    //adauga editura
    public void addPublishingHouse(String name) {
        PublishingHouse pH = new PublishingHouse(name);
        publishingHousesSet.add(pH);
    }

    //adauga o carte
    public void addBook(String nameOfPH, String authorName, String nationality, String name, Integer numberOfPages, Integer rating) {
        for (PublishingHouse pH : publishingHousesSet) {
            if (pH.getName().equals(nameOfPH)) {
                pH.addBook(authorName, nationality, name, numberOfPages, rating);
                return;
            }
        }
        addPublishingHouse(nameOfPH);
        for (PublishingHouse pH : publishingHousesSet) {
            if (pH.getName().equals(nameOfPH)) {
                pH.addBook(authorName, nationality, name, numberOfPages, rating);
            }
        }

    }
    //adauga o nota
    public void addNote(String bookName, String note, Integer page) {
        for (PublishingHouse pH : publishingHousesSet) {
            if (pH.addNote(bookName, note, page)) {
                return;
            }
        }
        System.out.println("The book was not found");
    }
    //afiseaza carti
    public void showBooksFrom(String pHName) {
        if (pHName.equals("all")) {
            for (PublishingHouse pH : publishingHousesSet) {
                    pH.showBooks();
                }
        } else {
            for (PublishingHouse pH : publishingHousesSet) {
                if (pH.getName().equals(pHName)) {
                    pH.showBooks();
                }
            }
        }
    }

    public void showBook(String bookName) {
        for (PublishingHouse pH : publishingHousesSet) {
            pH.showBook(bookName);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
