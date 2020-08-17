package ro.jademy;

import ro.jademy.Users.Client;
import ro.jademy.Users.User;
import ro.jademy.cars.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        //facut loggein pt seller
        //ceva statistici
        //niste sortari ceva

        List<User> users = new ArrayList<>();
        users.addAll(DataSource.createSeller());
        users.addAll(DataSource.createClients());
        ArrayList<Car> cars = new ArrayList<>(DataSource.createCars());
        Shop shop = new Shop(cars, users);
        System.out.println("Welcome to Rental Shop");
        System.out.println("------------------------");
        shop.printLog();
        shop.start();

        Operations op= new Operations(cars);
        op.sort();
    }
}
