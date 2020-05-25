package domain;

public class Notes {
    private String note;
    private Integer page;

    //constructor
    public Notes(String note, Integer page) {
        this.note = note;
        this.page = page;
    }

    public String getNote() {
        return note;
    }

    public Integer getPage() {
        return page;
    }
}