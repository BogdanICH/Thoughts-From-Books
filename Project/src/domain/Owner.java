package domain;

public class Owner extends Human {
    private int numberOfBooksRead;
    private int goalBooksRead;

    //constructor
    public Owner() {}
    public Owner(String name, int goalBooksRead) {
        super(name);
        this.goalBooksRead = goalBooksRead;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String ownName) {
        this.name = ownName;
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
    public void setGoalBooksRead(int number) {
        this.goalBooksRead = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", number of books read='" + numberOfBooksRead + '\'' +
                ", goal books read=" + goalBooksRead +
                '}';
    }
}