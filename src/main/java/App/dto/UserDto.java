package app.dto;

public class UserDto {

    private long Id;
    private PersonDto personId;
    private String password;
    private String userName;
    private String Rol;

    public UserDto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public PersonDto getPersonId() {
        return personId;
    }

    public void setPersonId(PersonDto personId) {
        this.personId = personId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

}
