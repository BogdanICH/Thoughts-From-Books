package Service;
import domain.Book;

public class BooksService {

    private static BooksService instance;
    private final Repository.BooksRepository bookRepository = Repository.BooksRepository.getInstance();

    private BooksService() {
    }

    public static BooksService getInstance() {
        if (instance == null) {
            instance = new BooksService();
        }

        return instance;
    }

    public Book saveBook(String name, String author, int pages, int rate) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setNumberOfPages(pages);
        book.setRating(rate);

        return bookRepository.saveBook(book);
    }

    public Book findBook(String name) {
        return bookRepository.findBook(name);
    }

    public Book updateBook(Book book) {return bookRepository.updateBook(book);}

    public boolean deleteBook(String name) {return bookRepository.deleteBook(name);}
}