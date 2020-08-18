package ro.jademy.information;

import java.util.Objects;

public class Make {
    private String name;
    private String telephoneNr;
    private String location;


    public Make(String name, String telephoneNr, String location) {
        this.name = name;
        this.telephoneNr = telephoneNr;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return
                " Make='" + name + '\'' +
                        ", telephoneNr='" + telephoneNr + '\'' +
                        ", location='" + location + '\'' +
                        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Make make = (Make) o;
        return Objects.equals(name, make.name) &&
                Objects.equals(telephoneNr, make.telephoneNr) &&
                Objects.equals(location, make.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, telephoneNr, location);
    }
}
