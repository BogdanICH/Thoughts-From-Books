package ro.unibuc.fmi;

public class OwnerService {
    private Owner owner;

    public void registerOwner(Owner owner) {
        this.owner = owner;
    }

    public void increaseNumberOfBooksRead() {
        owner.increaseNumberOfBooksRead();
    }

    public void progress() {
        System.out.println("Ai citit " + owner.getNumberOfBooksRead() + " din " + owner.getGoalBooksRead() + ".");
    }
}
