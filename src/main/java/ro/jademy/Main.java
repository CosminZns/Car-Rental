package ro.jademy;

import ro.jademy.Users.User;
import ro.jademy.cars.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        //facut loggein pt seller?
        //ceva statistici
        //niste sortari ceva

        //Best rented car? by model and make?
        //Averenge time a car is rented?
        //Best days of the month?
        //Show rented cars?
        //Record for the seller how many rented cars he has?, best seller this week

        List<User> users = new ArrayList<>();
        users.addAll(DataSource.createSeller());
        users.addAll(DataSource.createClients());
        ArrayList<Car> cars = new ArrayList<>(DataSource.createCars());
        Shop shop = new Shop(cars, users);
        System.out.println("Welcome to Rental Shop");
        System.out.println("------------------------");
        // shop.printLog();
        //shop.start();

        Operations operations = new Operations(cars, users);
        HashMap<Car, Integer> da = operations.numberOfRents();
        operations.bestRentedCar(da);

    }
}
