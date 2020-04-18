package ro.unibuc.fmi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Main {



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

            // afisez bartile din raftul ales
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
        return bS;
    }

    static Owner completeInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("Salutare si bine ai venit!");
        System.out.println("-----------------------------");
        System.out.println("");
        System.out.println("Numele tau: ");
        String answerS = scan.nextLine();
        System.out.println("Din ce tara esti? ");
        String answerN = scan.nextLine();
        System.out.println("Cate carti vrei sa citesti in acest an?");
        int answerB = Integer.parseInt(scan.nextLine());
        Owner owner = new Owner(answerS, answerN, answerB);
        return owner;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ReadWriteService readWriteService = ReadWriteService.getInstance();

        // owner
        Owner owner;
        owner = readWriteService.registerOwner("owner.txt");

        // service
        OwnerService ownerService = new OwnerService();
        ownerService.registerOwner(owner);

        // lista cu rafturi(domenii)
        List<Bookshelf> bookShelfList = new ArrayList<>();
        bookShelfList = readWriteService.registerBookshelfList("rafturi.txt");
        bookShelfList = readWriteService.registerBooks(bookShelfList, "books.txt");
        bookShelfList = readWriteService.registerNotes(bookShelfList,"notes.txt");
        do {

            // ..................  meniu ............................
            System.out.println("--------------- progres ----------------");
            ownerService.progress();
            System.out.println("--------------- meniu ----------------");
            System.out.println("1. Pune o carte intr-un raft");
            System.out.println("2. Scrie o nota");
            System.out.println("3. Aminteste-ti");
            System.out.println("4. Ce se citeste?");
            System.out.println("--------------------------------------");   
            // ......................................................

            switch (Integer.parseInt(scan.nextLine())) {
                case 1:
                    addNewBook(bookShelfList);
                    ownerService.increaseNumberOfBooksRead();
                    break;
                case 2:
                    addNote(bookShelfList);
                    break;
                case 3:
                    view(bookShelfList);
                    break;
                case 4:
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
                            break;
                        case 2:
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

            System.out.println("Cotinui? (1/0)");
        } while (Integer.parseInt(scan.nextLine()) == 1);

    }

}
