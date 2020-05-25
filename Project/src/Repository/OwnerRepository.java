package Repository;
import Connection.DatabaseConnection;
import domain.Owner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRepository {

    private static OwnerRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO ownerDB (name, goalBooksRead) VALUES (?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM ownerDB WHERE name = ?";
    private static final String UPDATE_STATEMENT = "UPDATE ownerDB SET goalBooksRead = ? WHERE name = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM ownerDB WHERE name =?";

    private OwnerRepository() {
    }

    public static OwnerRepository getInstance() {
        if (instance == null) {
            instance = new OwnerRepository();
        }

        return instance;
    }

    public Owner saveOwner(Owner owner) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("tables.sql").getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, owner.getName());
            statement.setInt(2, owner.getGoalBooksRead());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Owner();
        }
        return owner;
    }

    public Owner findOwner(String name) {
        Owner owner = new Owner();
        try (PreparedStatement statement = DatabaseConnection.getInstance("tables.sql").getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return owner;
                }

                System.out.println("User was found!");
                owner.setName(result.getString("name"));
                owner.setGoalBooksRead(result.getInt("goalBooksRead"));

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return owner;
    }

    public Owner updateOwner(Owner owner) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("tables.sql").getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(1, owner.getGoalBooksRead());
            statement.setString(2, owner.getName());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
                return owner;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return new Owner();
        }

        System.out.println("Something went wrong when trying to update user: User was not found!");
        return new Owner();
    }

    public boolean deleteOwner(String name) {
        try (PreparedStatement statement = DatabaseConnection.getInstance("tables.sql").getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete user: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete user: User was not found!");
        return false;
    }

}