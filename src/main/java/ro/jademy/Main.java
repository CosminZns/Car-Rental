package ro.jademy;

import ro.jademy.Users.User;
import ro.jademy.cars.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        //Folosit undeva metoda de sort
        //Record for the seller how many cars he rented?, best employee of the month

        List<User> users = new ArrayList<>();
        users.addAll(DataSource.createSeller());
        users.addAll(DataSource.createClients());
        ArrayList<Car> cars = new ArrayList<>(DataSource.createCars());
        Statistics statistics = new Statistics(cars, users);
        Shop shop = new Shop(cars, users, statistics);
        System.out.println("Welcome to Rental Shop");
        System.out.println("------------------------");
        shop.printLog();
        shop.start();

    }
}
