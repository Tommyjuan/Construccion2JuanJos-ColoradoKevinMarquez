package app.dto;

public class PersonDto {

    private long id;
    private long document;
    private String name;
    private long celPhone;

    public PersonDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(long celPhone) {
        this.celPhone = celPhone;
    }
}
