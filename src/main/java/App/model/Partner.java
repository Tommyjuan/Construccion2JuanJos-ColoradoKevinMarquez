package app.model;

import java.sql.Timestamp;

public class Partner {

    private long id;
    private User User_id;
    private double money;
    private String Type;
    private Timestamp dateCreated;

    public Partner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser_id() {
        return User_id;
    }

    public void setUser_id(User User_id) {
        this.User_id = User_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

}
