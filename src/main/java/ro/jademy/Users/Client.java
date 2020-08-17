package ro.jademy.Users;

import ro.jademy.cars.RentedCarHistory;

import java.util.List;


public class Client extends User {

    private List<RentedCarHistory> rentedCarHistories;

    public Client(String name, String email, String telephoneNumber, List<RentedCarHistory> rentedCarHistories, int id) {
        super(name, email, telephoneNumber, id);
        this.rentedCarHistories = rentedCarHistories;
    }

    public RentedCarHistory getCurrentRentedCar() {
        for (RentedCarHistory item : rentedCarHistories) {
            if (item.isCurentlyRented()) {
                return item;
            }

        }
        return null;
    }

    public List<RentedCarHistory> getRentedCarHistories() {
        return rentedCarHistories;
    }

    public void setRentedCarHistories(List<RentedCarHistory> rentedCarHistories) {
        this.rentedCarHistories = rentedCarHistories;
    }

    @Override
    public String toString() {
        return "Client: " + "Name:" + name + ", Email:" + email + ", Phone:" + telephoneNumber + ", Rented Car History: " + rentedCarHistories;
    }
}
