package ro.jademy;


import ro.jademy.Users.Client;
import ro.jademy.Users.Seller;
import ro.jademy.Users.User;
import ro.jademy.cars.Car;
import ro.jademy.cars.RentedCarHistory;
import ro.jademy.cars.audi.Audi;
import ro.jademy.cars.bmw.BMW;
import ro.jademy.cars.mercedes.Mercedes;
import ro.jademy.information.Make;
import ro.jademy.information.Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DataSource {


    private static final String HOST = "jdbc:mysql://localhost:3306/car_rental";
    private static final String U_NAME = "cosmin";
    private static final String U_PASS = "";
    private static Connection con;


    static {
        try {
            con = DriverManager.getConnection(HOST, U_NAME, U_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> createCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT model.production_year,model.model,make.make,make.location,make.telephone_number,car.make,car.color,car.base_price,car.is_m,car.is_amg,car.is_sport,car.id_car\n" +
                "FROM make\n" +
                "INNER JOIN model\n" +
                "ON model.id_model = make.id_model inner join car on car.id_make=make.id_make ";
        Statement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            cars.add(getCar(rs));
        }
        return cars;
    }


    public static List<User> createClients() throws SQLException {
        List<RentedCarHistory> rentedCarHistories = new ArrayList<>();
        Statement statement = con.createStatement();
        List<User> clients = new ArrayList<>();
        String sql = "select user.name,user.email,user.phone,rented_car.is_rented,rented_car.pickup_date,rented_car.rental_price,rented_car.return_date,car.make,car.model,car.color,car.base_price,make.location,make.make,make.telephone_number,model.model,model.production_year,car.is_amg,car.is_m,car.is_sport,user.id_user,car.id_car\n" +
                "from user left  join\n" +
                "rented_car on user.id_user=rented_car.id_user left join car on rented_car.id_car=car.id_car left join make on make.id_make=car.id_make left join model on model.id_model=make.id_model";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            if (rs.getDate("pickup_date") != null) {
                LocalDate pickupDate = rs.getDate("pickup_date").toLocalDate();
                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
                RentedCarHistory rentedCarHistory = new RentedCarHistory(getCar(rs), pickupDate, returnDate,
                        rs.getBoolean("is_rented"), rs.getInt("rental_price"));
                rentedCarHistories.add(rentedCarHistory);
                User user = new Client(rs.getString("name"), rs.getString("email"), rs.getString("phone"), rentedCarHistories, rs.getInt("id_user"));
                clients.add(user);
            } else {
                User user = new Client(rs.getString("name"), rs.getString("email"), rs.getString("phone"), rentedCarHistories, rs.getInt("id_user"));
                clients.add(user);
            }

        }
        return clients;
    }

    public static List<User> createSeller() throws SQLException {
        Statement statement = con.createStatement();
        List<User> sellers = new ArrayList<>();
        String sql = "select * from user where user_type = 'seller' ";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            User user = new Seller(rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getInt("id_user"));
            sellers.add(user);

        }
        return sellers;

    }

    public static Car getCar(ResultSet rs) throws SQLException {
        String carType = rs.getString("make");
        Make make;
        Model model;
        switch (carType) {
            case "bmw":
                make = new Make(rs.getString("make"), rs.getString
                        ("telephone_number"), rs.getString("location"));
                model = new Model(rs.getString("model"), rs.getInt("production_year"));
                return new BMW(make, model, rs.getString("color"),
                        rs.getInt("base_price"), rs.getBoolean("is_m"), false, rs.getInt("id_car"));

            case "audi":
                make = new Make(rs.getString("make"), rs.getString
                        ("telephone_number"), rs.getString("location"));
                model = new Model(rs.getString("model"), rs.getInt("production_year"));
                return new Audi(make, model, rs.getString("color"),
                        rs.getInt("base_price"), rs.getBoolean("is_sport"), false, rs.getInt("id_car"));

            case "mercedes":
                make = new Make(rs.getString("make"), rs.getString
                        ("telephone_number"), rs.getString("location"));
                model = new Model(rs.getString("model"), rs.getInt("production_year"));
                return new Mercedes(make, model, rs.getString("color"),
                        rs.getInt("base_price"), rs.getBoolean("is_amg"), false, rs.getInt("id_car"));
            default:
                throw new IllegalStateException("Unexpected value: " + carType);

        }
    }

    //de completat
    public static void saveRentedCar(RentedCarHistory carHistory, Client loggedIn) throws SQLException {
        String sql = "insert into rented_car (is_rented,rental_price,pickup_date,return_date,id_car,id_user) values (?,?,?,?,?,?);";
        PreparedStatement statement = con.prepareStatement(sql);
        boolean isRented = carHistory.isCurentlyRented();
        int rentalPrice = carHistory.getRentalPricel();
        LocalDate date = carHistory.getPickUp();
        Date pickUp = java.sql.Date.valueOf(date);
        LocalDate date1 = carHistory.getReturnDate();
        Date returnDate = java.sql.Date.valueOf(date1);
        int idCar = carHistory.getCar().getId();
        int idUser = loggedIn.getId();
        statement.setBoolean(1, isRented);
        statement.setInt(2, rentalPrice);
        statement.setDate(3, pickUp);
        statement.setDate(4, returnDate);
        statement.setInt(5, idCar);
        statement.setInt(6, idUser);
        int row = statement.executeUpdate();
    }

    public static void returnCar(RentedCarHistory carHistory, Client loggedIn) throws SQLException {
        int idUser = loggedIn.getId();
        String sql = "UPDATE rented_car SET is_rented = 0 where id_user = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, idUser);
        int row = statement.executeUpdate();
        statement.close();
    }

}
