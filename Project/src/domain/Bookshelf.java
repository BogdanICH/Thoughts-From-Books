package domain;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.util.*;

public class Bookshelf implements Comparable<Bookshelf> {
    private String name;
    private Integer noOfBooks;
    protected static String bestBookshelf = "";
    protected static Integer ibestBookshelf = 0;

    //lista de carti
    public List<Book> bookList = new ArrayList<>();
    //lista de autori
    static List<Author> authorList = new ArrayList<>();
    //lista de autori
    static Set<PublishingHouse> publishingHouse = new HashSet<>();

    //constructor
    public Bookshelf(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookshelf bookshelf = (Bookshelf) o;
        return Objects.equals(name, bookshelf.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Bookshelf o) {
        return name.compareTo(o.getName());
    }

    //adauga carte
    public void addBook(String publishingName, String authorName, String nationality, String name, int numberOfPages, int rating) {
        Book book = new Book(authorName, name, numberOfPages, rating);
        bookList.add(book);
        Collections.sort(bookList);

        noOfBooks = bookList.size();
        if (noOfBooks > ibestBookshelf) {
            ibestBookshelf = this.noOfBooks;
            bestBookshelf = this.name;
        }

        // atribui cartea unui autor
        int ok = 0;
        for (Author author : authorList) {
            if (author.getName().equals(authorName)) {
                author.addBook(book);
                ok = 1;
                addInPH(author, publishingName);
            }
        }
        if (ok == 0) {
            Author author = new Author(authorName);
            addAuthor(author, book);
            addInPH(author, publishingName);
        }
    }

    // atribui cartea unei edituri
    public void addInPH(Author author, String publishingName) {
        int ok = 0;
        for (PublishingHouse pH : publishingHouse) {
            if (pH.getName().equals(publishingName)) {
                pH.addAuthor(author);
                ok = 1;
            }
        }
        if (ok == 0) {
            PublishingHouse pH = new PublishingHouse(publishingName);
            addPH(pH, author);
        }
    }

    // adaug autor
    public static void addAuthor(Author author, Book book) {
        authorList.add(author);
        author.addBook(book);
    }
    public static void addPH(PublishingHouse pH, Author author) {
        pH.addAuthor(author);
        publishingHouse.add(pH);
    }

    // returnez autorul cu cele mai multe carti
    public String bestAuthor() {
        String name = "";
        Integer max = -1;
        for (Author author : authorList) {
            if (author.numberOfBooks() > max) {
                name = author.getName();
                max = author.numberOfBooks();
            }
        }
        if (max == -1) {
            System.out.println("Niciunul");
        } else {
            return name;
        }
        return "";
    }

    // returnez editura cu cei mai multi actori
    public String bestPublishingHouse() {
        String name = "";
        Integer max = -1;
        for (PublishingHouse pH : publishingHouse) {
            if (pH.getNumberOfBooks() > max) {
                name = pH.getName();
                max = pH.getNumberOfBooks();
            }
        }
        if (max == -1) {
            System.out.println("Niciuna");
        } else {
            return name;
        }
        return "";
    }

    //afiseaza cartile din acest raft
    public void showBooks() {
        for (Book book : bookList) {
            System.out.println(" - " + book.getName());
        }
    }

    //afisez o singura carte
    public void showBook(String bookName) {
        for (Book book : bookList) {
            if (book.getName().equals(bookName)) {
                System.out.println("Title: " + book.getName() + " | Author: " + book.getAuthor() + " | pages: " + book.getNumberOfPages() + " | Rating: " + book.getRating());
                book.getNotes();
            }
        }
    }

    //adaugare nota
    public void addNote(String bookName, String note, Integer page) {
        for (Book book : bookList) {
            if (book.getName().equals(bookName)) {
                book.addNote(note, page);
            }
        }
    }

    public Integer getNoOfBooks() {
        return noOfBooks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getBestBookshelf() {
        return bestBookshelf;
    }
}