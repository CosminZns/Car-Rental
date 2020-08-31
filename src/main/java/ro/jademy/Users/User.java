package ro.jademy.Users;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephoneNumber, user.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, telephoneNumber, id);
    }
}
