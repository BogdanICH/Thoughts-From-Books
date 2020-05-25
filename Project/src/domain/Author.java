package domain;
import java.util.HashSet;
import java.util.Set;

public class Author extends Human {

    Set<Book> booksSet = new HashSet<>();

    public Author(String name) {
        super(name);
    }

    public void addBook(Book book) {
        booksSet.add(book);
    }

    public Integer numberOfBooks() {
        return booksSet.size();
    }

    public String getName() { return name; }
}