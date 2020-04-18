package ro.unibuc.fmi;

import com.sun.java.accessibility.util.EventID;

import java.util.HashSet;
import java.util.Set;


public class Author extends Human {

    Set<Book> booksSet = new HashSet<>();

    public Author(String name, String nationality) {
        super(name, nationality);
    }

    public void addBook(Book book) {
        booksSet.add(book);
    }

    public Integer numberOfBooks() {
        return booksSet.size();
    }

    public String getName() {
        return name;
    }

}
