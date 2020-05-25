package Main;
import Service.BooksService;
import Service.OwnerService;
import Service.ReadWriteService;
import domain.Book;
import domain.Bookshelf;
import domain.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Main {

    private static final OwnerService ownerService = OwnerService.getInstance();
    private static final BooksService bookService = BooksService.getInstance();

    static void view(List<Bookshelf> bookShelfList) {
        Scanner scan = new Scanner(System.in);
        int answer;
        do {
            // afisez rafturile
            for (Bookshelf bookshelf : bookShelfList) {
                System.out.println("- " + bookshelf.getName() + " (" + bookshelf.getNoOfBooks() + " books)");
            }

            // aleg raftul pe care vreau sa ma uit
            System.out.println("Alege raft(prin nume): ");
            String answerS = scan.nextLine();

            // afisez cartile din raftul ales
            for (Bookshelf bookshelf : bookShelfList) {
                if (bookshelf.getName().equals(answerS)) {
                    do {
                        System.out.println(".......................");
                        bookshelf.showBooks();
                        // aleg cartea
                        System.out.println("Alege carte(prin nume): ");
                        answerS = scan.nextLine();
                        bookshelf.showBook(answerS);
                        System.out.println("Vrei sa alegi alta carte din acest raft? (1/0)");
                        answer = Integer.parseInt(scan.nextLine());

                    } while (answer == 1);
                }
            }

            System.out.println("Te uiti pe alt raft? (1/0)");
            answer = Integer.parseInt(scan.nextLine());

        } while (answer == 1);
    }

    // adaug nota
    static void addNote(List<Bookshelf> bookShelfList) {
        Scanner scan = new Scanner(System.in);
        // afisez rafturile
        for (Bookshelf bookshelf : bookShelfList) {
            System.out.println("- " + bookshelf.getName() + " (" + bookshelf.getNoOfBooks() + " books)" );
        }
        // aleg raft
        System.out.println("Alege raft(prin nume): ");
        String answerS = scan.nextLine();
        System.out.println(".....................");

        // afisez cartile din raftul ales
        for (Bookshelf bookshelf : bookShelfList) {
            if (bookshelf.getName().equals(answerS)) {
                bookshelf.showBooks();

                // aleg cartea caruia vreau sa ii adaug nota
                System.out.println("Nume carte: ");
                answerS = scan.nextLine();

                // adaug nota
                System.out.println("Nota: ");
                String answerN = scan.nextLine();

                // adaug pagina la care am gasit nota
                System.out.println("Pagina: ");
                Integer answerP = Integer.parseInt(scan.nextLine());

                // adaug informatiile primite
                bookshelf.addNote(answerS, answerN, answerP);
            }
        }
    }

    static void addNewBook(List<Bookshelf> bookShelfList) {
        Scanner scan = new Scanner(System.in);

        // vad daca am vreun raft
        if (bookShelfList.size() == 0) {
            // daca nu creez unul
            addBook(bookShelfList);
        } else {
            // afisez rafturile existente
            for (Bookshelf bookshelf : bookShelfList) {
                System.out.println("- " + bookshelf.getName() + " (" + bookshelf.getNoOfBooks() + " books)" );
            }
            System.out.println("Alege raft(prin nume) sau scrie 'nou' pentru a adauga un raft nou: ");
            String answerS = scan.nextLine();

            // creez un raft nou daca utilizatorul vrea
            if (answerS.equals("nou")) {
                // adaug raft nou
                addBook(bookShelfList);
            } else {
                // adaug carte noua
                for (Bookshelf bookshelf : bookShelfList) {
                    if (bookshelf.getName().equals(answerS)) {
                        addBook(bookshelf);
                    }
                }
            }
        }
    }

    //functie pentru a adauga o carte
    static void addBook(List<Bookshelf> myList) {
        Scanner scan = new Scanner(System.in);

        //verific daca am vreun raft, implicit vreo carte
        if (myList.size() == 0) {
            System.out.println("Rafturile sunt goale, asa ca trebuie sa adaugi o carte.");
        }

        // aleg nume pentru noul raft
        System.out.println("Alegeti nume pentru raft: ");
        String bSName = scan.nextLine();

        // il creez
        Bookshelf bS = new Bookshelf(bSName);

        // adaug cartea in el
        bS = addBook(bS);

        // adaug raftul cu cartea in lista mea de rafturi
        myList.add(bS);
        Collections.sort(myList);
    }

    // adaug o carte pornind de un raft dat
    static Bookshelf addBook(Bookshelf bS) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Completeaza detaliile cartii:");
        System.out.println("Editura: ");
        String answerE = scan.nextLine();
        System.out.println("Autor: ");
        String answerA = scan.nextLine();
        System.out.println("Nationalitate: ");
        String answerN = scan.nextLine();
        System.out.println("Nume carte: ");
        String answerB = scan.nextLine();
        System.out.println("Numar de pagini: ");
        int answerP = Integer.parseInt(scan.nextLine());
        System.out.println("Evaluare carte printr-o nota: ");
        int answerR = Integer.parseInt(scan.nextLine());
        bS.addBook(answerE, answerA, answerN, answerB, answerP, answerR);

        // add to database
        Book bookToSave = bookService.saveBook(answerB, answerA, answerP, answerR);
        return bS;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ReadWriteService readWriteService = ReadWriteService.getInstance();

        // instantiez un owner fals pentru inceput(va fi actualizat)
        Owner owner = new Owner("name", 0);

        // lista cu rafturi(domenii)
        List<Bookshelf> bookShelfList = new ArrayList<>();
        bookShelfList = readWriteService.registerBookshelfList("rafturi.txt");
        bookShelfList = readWriteService.registerBooks(bookShelfList, "books.txt");
        bookShelfList = readWriteService.registerNotes(bookShelfList,"notes.txt");

        do {
            // ..................  meniu ............................
            System.out.println("--------------- progres ----------------");
            ownerService.progress(owner);
            System.out.println("--------------- meniu ----------------");
            System.out.println("1. Pune o carte intr-un raft");
            System.out.println("2. Scrie o nota");
            System.out.println("3. Aminteste-ti");
            System.out.println("4. Ce se citeste?");
            System.out.println("5. Setari");
            System.out.println("6. Gestioneaza carti");
            System.out.println("--------------------------------------");
            // ......................................................

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    addNewBook(bookShelfList);
                    ownerService.increaseNumberOfBooksRead(owner);
                    break;
                case 2:
                    addNote(bookShelfList);
                    break;
                case 3:
                    view(bookShelfList);
                    break;
                case 4: {
                    // ..................  meniu ............................
                    System.out.println("1. Cel mai citit autor");
                    System.out.println("2. Cea mai citita editura");
                    System.out.println("3. Cel mai citit domeniu");
                    // ......................................................
                    switch (Integer.parseInt(scan.nextLine())) {
                        case 1:
                            for (Bookshelf bookshelf : bookShelfList) {
                                System.out.println("Cel mai citit autor: " + bookshelf.bestAuthor());
                                break;
                            }
                            break;                        case 2:
                            for (Bookshelf bookshelf : bookShelfList) {
                                System.out.println("Cea mai citita editura: " + bookshelf.bestPublishingHouse());
                                break;
                            }
                            break;
                        case 3:
                            for (Bookshelf bookshelf : bookShelfList) {
                                System.out.println("Cel mai citit domeniu: " + bookshelf.getBestBookshelf());
                                break;
                            }
                            break;
                    }
                    break;
                }
                case 5: {
                    // ..................  Setari ............................
                    System.out.println("1. Inregistreaza owner");
                    System.out.println("2. Informatii");
                    System.out.println("3. Schimba numarul cartilor pe care ti-ai propus sa le citesti");
                    System.out.println("4. Sterge Informatiile");
                    // ......................................................
                    switch (Integer.parseInt(scan.nextLine())) {
                        case 1: {
                            System.out.println("Numele tau:");
                            String ownerName = scan.nextLine();
                            System.out.println("Cate carti ti-ai propus sa citesti?:");
                            Integer goalBooksRead = Integer.parseInt(scan.nextLine());
                            Owner ownerToSave = ownerService.saveOwner(ownerName, goalBooksRead);
                            owner.setName(ownerName);
                            owner.setGoalBooksRead(goalBooksRead);

                            break;
                        }
                        case 2: {
                            Owner ownerToFind = ownerService.findOwner("bogdan");
                            System.out.println(ownerToFind);
                            break;
                        }
                        case 3: {
                            System.out.println("Numele:");
                            String newName = scan.nextLine();
                            System.out.println("Noul 'goal' de carti?:");
                            Integer goalBooksRead = Integer.parseInt(scan.nextLine());
                            System.out.println(newName);
                            Owner ownerToUpdate = new Owner(newName, goalBooksRead);
                            ownerToUpdate = ownerService.updateOwner(ownerToUpdate);
                            System.out.println(ownerToUpdate);
                            break;
                        }
                        case 4:
                            System.out.println("Dati numele utilizatorului pe care vreti sa il stergeti:");
                            String newName = scan.nextLine();
                            boolean result = ownerService.deleteOwner(newName);
                            System.out.println(result);
                            break;
                    }
                    break;
                }
                case 6: {
                    // ..................  Setari ............................
                    System.out.println("1. Gaseste o carte");
                    System.out.println("2. Schimba rate-ul unei carti");
                    System.out.println("3. Sterge o carte");
                    // ......................................................
                    switch (Integer.parseInt(scan.nextLine())) {
                        case 1: {
                            System.out.println("Numele cartii:");
                            String thisBook = scan.nextLine();
                            Book bookToFind = bookService.findBook(thisBook);
                            System.out.println(bookToFind);
                            break;
                        }
                        case 2: {
                            System.out.println("Numele cartii:");
                            String thisBook = scan.nextLine();
                            System.out.println("Noul rate al cartii?:");
                            int rate = Integer.parseInt(scan.nextLine());
                            Book bookToUpdate = new Book("", thisBook, 0, rate);
                            bookToUpdate = bookService.updateBook(bookToUpdate);
                            System.out.println(bookToUpdate);
                            break;
                        }
                        case 3:
                            System.out.println("Dati numele cartii pe care vreti sa o stergeti:");
                            String thisBook = scan.nextLine();
                            boolean result = bookService.deleteBook(thisBook);
                            System.out.println(result);
                            break;
                    }
                    break;
                }
            }

            System.out.println("Cotinui? (1/0)");
        } while (Integer.parseInt(scan.nextLine()) == 1);

    }

}