package ro.unibuc.fmi;

public class Owner extends Human {
    private int numberOfBooksRead;
    private int goalBooksRead;

    //constructor
    public Owner(String name, String nationality, int goalBooksRead) {
        super(name, nationality);
        this.goalBooksRead = goalBooksRead;

    }

    public void increaseNumberOfBooksRead() {
        this.numberOfBooksRead += 1;
    }

    public int getNumberOfBooksRead() {
        return numberOfBooksRead;
    }

    public int getGoalBooksRead() {
        return goalBooksRead;
    }
}
