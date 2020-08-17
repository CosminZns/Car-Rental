package ro.jademy.Users;

public class User {
    protected String name;
    protected String email;
    protected String telephoneNumber;
    protected int id;

    public User(String name, String email, String telephoneNumber, int id) {
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}
