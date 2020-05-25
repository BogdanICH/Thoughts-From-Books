package domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private String name;
    private Integer numberOfPages;
    private Integer rating;
    protected static Integer numberOfBooks = 0;
    protected static Integer numberOfNotes = 0;
    private String Author;

    // lista de note
    List<Notes> notesList = new ArrayList<>();

    //constructor gol
    public Book() {}

    //constructor
    public Book(String authorName, String name, Integer numberOfPages, Integer rating) {
        this.Author = authorName;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
        numberOfNotes = 0;
        numberOfBooks++;
    }

    //adauga o nota
    public void addNote(String note, Integer page) {
        Notes newNote = new Notes(note, page);
        notesList.add(newNote);
        numberOfNotes++;
    }

    //afiseaza note
    public void getNotes() {
        for (Notes note : notesList) {
            System.out.println("    - " + note.getNote() + " (page: " + note.getPage() + ")" );
        }
    }

    public void setName(String name) { this.name = name; }
    public void setNumberOfPages(Integer numberOfPages) { this.numberOfPages = numberOfPages; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setAuthor(String name) {this.Author = name;}
    public String getAuthor() {return this.Author;}
    public String getName() {return name;}
    public Integer getNumberOfPages() {
        return numberOfPages;
    }
    public Integer getRating() { return rating; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Book o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Book{" +
                "name: '" + name + '\'' +
                ", author: '" + Author + '\'' +
                ", rate: " + rating +
                '}';
    }
}