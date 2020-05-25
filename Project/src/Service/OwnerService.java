package Service;
import domain.Owner;

public class OwnerService {

    private static OwnerService instance;
    private final Repository.OwnerRepository ownerRepository = Repository.OwnerRepository.getInstance();

    private OwnerService() {
    }

    public static OwnerService getInstance() {
        if (instance == null) {
            instance = new OwnerService();
        }

        return instance;
    }

    public Owner saveOwner(String name, int goalBooksRead) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setGoalBooksRead(goalBooksRead);

        return ownerRepository.saveOwner(owner);
    }

    public Owner findOwner(String name) {
        return ownerRepository.findOwner(name);
    }

    public Owner updateOwner(Owner owner) {return ownerRepository.updateOwner(owner);}

    public boolean deleteOwner(String name) {return ownerRepository.deleteOwner(name);}

    public void increaseNumberOfBooksRead(Owner owner) {
        owner.increaseNumberOfBooksRead();
    }

    public void progress(Owner owner) {
        System.out.println("Ai citit " + owner.getNumberOfBooksRead() + " cărți din " + owner.getGoalBooksRead() + ".");
    }
}