package ro.jademy.information;

public class Make {
    private String make;
    private String telephoneNr;
    private String location;


    public Make(String make, String telephoneNr, String location) {
        this.make = make;
        this.telephoneNr = telephoneNr;
        this.location = location;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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
                " Make='" + make + '\'' +
                ", telephoneNr='" + telephoneNr + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
