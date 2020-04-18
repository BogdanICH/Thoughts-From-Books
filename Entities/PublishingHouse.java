package ro.unibuc.fmi;

import javax.print.DocFlavor;
import java.security.PrivateKey;
import java.util.HashSet;
import java.util.Set;

public class PublishingHouse {
    private String name;

    Set<Author> authorList = new HashSet<>();

    public PublishingHouse(String name) {
        this.name = name;
    }

    public Integer getNumberOfBooks() {
        int noOfBooks = 0;
        for (Author author : authorList) {
            noOfBooks += author.numberOfBooks();
        }
        return noOfBooks;
    }

    public void addAuthor(Author author) {
        authorList.add(author);
    }

    public String getName() {
        return name;
    }
}
