package ro.unibuc.fmi;

public abstract class Author {
    private String authorName;
    private String nationality;

    //constructor gol pentru a putea instantia un obiect de tip Book si a vedea la inceput ca am 0 carti
    public Author() {}

    //constructor plin
    public Author(String authorName, String nationality) {
        this.authorName = authorName;
        this.nationality = nationality;
    }

    public String getAuthorName() {
        return authorName;
    }
    public String getNationality() {
        return nationality;
    }
}
