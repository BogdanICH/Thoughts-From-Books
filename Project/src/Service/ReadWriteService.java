package Service;
import domain.Bookshelf;
import domain.Owner;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class ReadWriteService {
    static ReadWriteService obj = new ReadWriteService();
    private Owner owner;
    private List<Bookshelf> bookshelfList = new ArrayList<>();

    private ReadWriteService() {
    }

    public static ReadWriteService getInstance() {
        return obj;
    }

    public List<String[]> readPersonsFromFile(String file) {
        String[] dataFields;
        List<String[]> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                dataFields = currentLine.split(",");
                data.add(dataFields);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return data;
        }
        System.out.println("Successfully read!");
        return data;
    }

    public void writeToFile(String action) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            File file = new File("audit.csv");

            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(action + "," + timestamp);
            bufferedWriter.newLine();


        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        } finally {

            try {

                if (bufferedWriter != null)
                    bufferedWriter.close();

                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException e) {

                e.getMessage();

            }
        }
        System.out.println("Successfully wrote.");
    }

    // not in use anymore
    public Owner registerOwner(String file) {
        List<String[]> data = readPersonsFromFile(file);
        String[] dataFields = data.get(0);
        Owner registredOwner = new Owner(dataFields[0], Integer.parseInt(dataFields[1]));
        this.owner = registredOwner;
        writeToFile("register_owner");
        return this.owner;
    }

    public List<Bookshelf> registerBookshelfList(String file) {
        List<String[]> data = readPersonsFromFile(file);
        String[] dataFields = data.get(0);
        for (int i = 0; i < dataFields.length; i++) {
            Bookshelf bookshelf = new Bookshelf(dataFields[i]);
            bookshelfList.add(bookshelf);
        }
        Collections.sort(bookshelfList);
        writeToFile("register_bookshelf");
        return bookshelfList;
    }

    public List<Bookshelf> registerBooks(List<Bookshelf> bookShelfList, String file) {
        List<String[]> data = readPersonsFromFile(file);
        String[] dataFields = data.get(0);
        int i = 0;
        for (Bookshelf bookshelf : bookShelfList) {
            for (int j = 0; j < 2; j++) {
                bookshelf.addBook(dataFields[0], dataFields[1], dataFields[2], dataFields[3], Integer.parseInt(dataFields[4]), Integer.parseInt(dataFields[5]));
                i++;
                dataFields = data.get(i);
            }
        }

        writeToFile("register_books");
        return bookShelfList;
    }

    public List<Bookshelf> registerNotes(List<Bookshelf> bookShelfList, String file) {
        List<String[]> data = readPersonsFromFile(file);
        String[] dataFields = data.get(0);
        for (Bookshelf bookshelf : bookShelfList) {
            bookshelf.addNote(bookshelf.bookList.get(0).getName(), dataFields[0], Integer.parseInt(dataFields[1]));
            break;
        }
        writeToFile("register_notes");
        return bookShelfList;
    }
}