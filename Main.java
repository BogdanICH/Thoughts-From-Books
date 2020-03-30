package ro.unibuc.fmi;
import ro.unibuc.fmi.entities.Bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //functie pentru a adauga o carte
    static Bookshelf addBook(Bookshelf bS) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Completeaza detaliile cartii:");
        System.out.println("Editura: ");
        String answerPH = scan.nextLine();
        System.out.println("Autor: ");
        String answerA = scan.nextLine();
        System.out.println("Nationalitate: ");
        String answerN = scan.nextLine();
        System.out.println("Nume carte: ");
        String answerB = scan.nextLine();
        System.out.println("Numar de pagini: ");
        Integer answerP = scan.nextInt();
        System.out.println("Evaluare carte printr-o nota: ");
        Integer answerR = scan.nextInt();
        bS.addBook(answerPH, answerA, answerN, answerB, answerP, answerR);
        return bS;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Book book = new Book();
        //lista cu rafturi(domenii)
        List<Bookshelf> bookShelfList = new ArrayList<>();
        int answer;
        String answerS;

        //programul mare
        do {
            //daca nu am nici o carte, adaug un raft si o carte
            if (book.getNumberOfBooks() == 0) {
                System.out.println("Nu exista nici o carte in librarie. Doriti sa adaugati? (1/0)");
                answer = scan.nextInt();
                if (answer == 1) {
                    System.out.println("Alegeti nume pentru raft: ");
                    scan.nextLine();
                    answerS = scan.nextLine();
                    Bookshelf bS = new Bookshelf(answerS);
                    bS = addBook(bS);
                    bookShelfList.add(bS);
                }
            }

            //meniu
            System.out.println("1. Adaug o carte intr-un raft");
            System.out.println("2. Adaug o nota unei carti");
            System.out.println("3. Vizualizez");
            int menu = scan.nextInt();
            switch (menu) {
                case 1:
                    for (Bookshelf bookshelf : bookShelfList) { System.out.println("- " + bookshelf.getName());}
                    System.out.println("Alege raft(prin nume) sau scrie 'nou' pentru a adauga un raft nou: ");
                    scan.nextLine();
                    answerS = scan.nextLine();
                    if (answerS.equals("nou")) {
                        System.out.println("------------------");
                        System.out.println("Alegeti nume pentru raft: ");
                        answerS = scan.nextLine();
                        Bookshelf bS = new Bookshelf(answerS);
                        bS = addBook(bS);
                        bookShelfList.add(bS);
                    } else {
                        for (Bookshelf bookshelf : bookShelfList) {
                            if (bookshelf.getName().equals(answerS)) {
                                addBook(bookshelf);
                            }
                        }

                    }
                    break;
                case 2:
                    for (Bookshelf bookshelf : bookShelfList) { System.out.println("- " + bookshelf.getName());}
                    System.out.println("------------------");
                    System.out.println("Alege raft(prin nume): ");
                    scan.nextLine();
                    answerS = scan.nextLine();
                    System.out.println("------------------");
                    for (Bookshelf bookshelf : bookShelfList) {
                        if (bookshelf.getName().equals(answerS)) {
                            bookshelf.showBooksFrom("all");
                            System.out.println("------------------");
                            System.out.println("Nume carte: ");
                            answerS = scan.nextLine();
                            System.out.println("------------------");
                            System.out.println("Nota: ");
                            String answerN = scan.nextLine();
                            System.out.println("------------------");
                            System.out.println("Pagina: ");
                            Integer answerP = scan.nextInt();
                            System.out.println("------------------");
                            bookshelf.addNote(answerS, answerN, answerP);
                        }
                    }
                    break;
                case 3:
                    do {
                        for (Bookshelf bookshelf : bookShelfList) { System.out.println("- " + bookshelf.getName());}
                        System.out.println("------------------");
                        System.out.println("Alege raft(prin nume): ");
                        scan.nextLine();
                        answerS = scan.nextLine();
                        System.out.println("------------------");
                        for (Bookshelf bookshelf : bookShelfList) {
                            if (bookshelf.getName().equals(answerS)) {
                                do {
                                    bookshelf.showBooksFrom("all");
                                    System.out.println("------------------");
                                    System.out.println("Alege carte(prin nume): ");
                                    answerS = scan.nextLine();
                                    bookshelf.showBook(answerS);
                                    System.out.println("------------------");

                                    System.out.println("Vrei sa alegi alta carte din acest raft? (1/0)");
                                    answer = scan.nextInt();
                                    System.out.println("------------------");
                                }while (answer == 1);

                            }
                        }
                        System.out.println("------------------");
                        System.out.println("Te uiti pe alt raft? (1/0)");
                        answer = scan.nextInt();
                        System.out.println("------------------");
                    } while (answer == 1);
                    break;
            }



            System.out.println("------------------");
            System.out.println("Cotinui? (1/0)");
            answer = scan.nextInt();
            System.out.println("------------------");
        } while (answer == 1);

    }

}