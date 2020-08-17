package ro.jademy.Users;

public class Seller extends User {


    public Seller(String name, String email, String telephoneNumber,int id) {
        super(name, email, telephoneNumber,id);
    }
    @Override
    public String toString() {
        return "Seller: " + "Name:"+ getName() + " ,Email:,"+ getEmail() + " ,Phone:,"+ getTelephoneNumber() ;

    }

}
