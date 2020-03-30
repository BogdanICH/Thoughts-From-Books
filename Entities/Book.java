package ro.unibuc.fmi;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

public class Book extends Author {
    private String name;
    private Integer numberOfPages;
    private Integer rating;
    private static Integer numberOfBooks = 0;
    private Integer numberOfNotes;

    List<Notes> notesList = new ArrayList<>();

    //constructor gol ca sa pot instantia si vedea ca la inceput am 0 carti
    public Book() {}

    //constructor plin
    public Book(String authorName, String nationality, String name, Integer numberOfPages, Integer rating) {
        super(authorName, nationality);
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
            System.out.println("    - " + note.getNote() + "(page: " + note.getPage() + ")" );
        }
    }

    public String getAuthor() {
        return getAuthorName();
    }

    //return numar de carti
    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getNumberOfNotes() {
        return numberOfNotes;
    }

    public Integer getRating() {
        return rating;
    }
}
