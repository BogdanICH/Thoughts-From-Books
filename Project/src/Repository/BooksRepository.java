package Repository;
import Connection.DatabaseConnection;
import domain.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksRepository {

    private static BooksRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO books (name, author, pages, rate) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM books WHERE name = ?";
    private static final String UPDATE_STATEMENT = "UPDATE books SET rate = ? WHERE name = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM books WHERE name =?";

    private BooksRepository() {
    }

    public static BooksRepository getInstance() {
        if (instance == null) {
            instance = new BooksRepository();
        }

        return instance;
    }

    public Book saveBook(Book book) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("bookTable.sql").getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getNumberOfPages());
            statement.setInt(4, book.getRating());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new book: " + e.getMessage());
            return new Book();
        }
        return book;
    }

    public Book findBook(String name) {
        Book book = new Book();
        try (PreparedStatement statement = DatabaseConnection.getInstance("bookTable.sql").getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find Book: Book was not found!");
                    return book;
                }

                System.out.println("Book was found!");
                book.setName(result.getString("name"));
                book.setAuthor(result.getString("author"));
                book.setNumberOfPages(result.getInt("pages"));
                book.setRating(result.getInt("rate"));


            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find book: " + e.getMessage());
        }
        return book;
    }

    public Book updateBook(Book book) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("bookTable.sql").getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(1, book.getRating());
            statement.setString(2, book.getName());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book was updated successfully!");
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update book: " + e.getMessage());
            return new Book();
        }

        System.out.println("Something went wrong when trying to update book: Book was not found!");
        return new Book();
    }

    public boolean deleteBook(String name) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("bookTable.sql").getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete book: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete book: Book was not found!");
        return false;
    }

}