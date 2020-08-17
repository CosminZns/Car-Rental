package ro.jademy;

import ro.jademy.Users.Client;
import ro.jademy.Users.Seller;
import ro.jademy.Users.User;
import ro.jademy.cars.Car;
import ro.jademy.cars.RentedCarHistory;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Shop {

    private List<Car> cars;
    private List<User> users;
    private Scanner sc = new Scanner(System.in);
    private User loggedIn;
    private List<RentedCarHistory> rentedCarHistories = new ArrayList<>();


    public Shop(List<Car> cars, List<User> users) {
        this.cars = cars;
        this.users = users;
    }

    public void displayCars(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(i + 1 + ".  " + cars.get(i));
        }
    }


    public void start() throws SQLException {
        showMenu();
        int decison = sc.nextInt();
        switch (decison) {
            case 1:
                displayCars(cars);
                start();
                break;
            case 2:
                filterCars();
                start();
                break;
            case 3:
                rentCar();
                start();
                break;
            case 4:
                displayIncome();
                start();
                break;
            case 5:
                displayCars(getAvailableCars());
                start();
                break;
            case 6:
                printLog();
                start();
                break;
            case 7:
                returnCar();
                start();
                break;
            case 8:
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }

    }

    private void showMenu() {
        System.out.println("1.Display cars");
        System.out.println("2.Filter cars");
        System.out.println("3.Rent a car");
        System.out.println("4.Display Income");
        System.out.println("5.Display Available Cars");
        System.out.println("6.Log out");
        System.out.println("7.Return car");
        System.out.println("8.Exit");
    }

    public void filterCars() {
        System.out.println("Filter by :1.make");
        System.out.println("Filter by :2.model");
        System.out.println("Filter by :3.color");
        System.out.println("Filter by :4.Price");
        int decision = sc.nextInt();
        switch (decision) {
            case 1:
                filterbyMake();
                break;
            case 2:
                filterbyModel();
                break;
            case 3:
                filterbyColor();
                break;
            case 4:
                filterbyPrice();
                break;
        }
    }

    private void filterbyColor() {
        System.out.println("Type the color ");
        String inputColor = sc.next();
        System.out.println(cars.stream().filter(color -> color.getColor().equals(inputColor)).collect(Collectors.toList()));
    }

    private void filterbyPrice() {
        System.out.println("Type the Price ");
        int inputPrice = sc.nextInt();
        System.out.println(cars.stream().filter(price -> price.getBasePrice() == (inputPrice)).collect(Collectors.toList()));
    }

    private void filterbyModel() {
        System.out.println("Type the model ");
        String model = sc.next();
        for (Car car : cars) {
            if (car.getModel().getModel().equals(model)) {
                System.out.println(car);
            }
        }
    }

    private void filterbyMake() {
        System.out.println("Type the make ");
        String make = sc.next();
        for (Car car : cars) {
            if (car.getMake().getMake().equals(make)) {
                System.out.println(car);
            }
        }
    }

    public void rentCar() throws SQLException {
        if (loggedIn instanceof Client) {
            Client client = (Client) loggedIn;
            if (client.getCurrentRentedCar() == null) {
                System.out.println("Chose a car to rent");
                List<Car> availableCars = getAvailableCars();
                System.out.println("Available Cars are : " + availableCars);
                //decision should be the index of the car
                int decision = sc.nextInt() - 1;
                if (decision <= availableCars.size()) {
                    boolean correctDates = false;
                    while (!correctDates) {
                        System.out.println("Please enter the date u want to pick up the car");
                        System.out.println("Year:");
                        int year = sc.nextInt();
                        System.out.println("Month:");
                        int month = sc.nextInt();
                        System.out.println("Day:");
                        int day = sc.nextInt();
                        LocalDate localDate = LocalDate.now();
                        LocalDate pickUp = LocalDate.of(Integer.parseInt(String.valueOf(year)),
                                Integer.parseInt(String.valueOf(month)),
                                Integer.parseInt(String.valueOf(day)));
                        if (pickUp.isAfter(localDate)) {
                            System.out.println("Please enter the date u want to return the car");
                            System.out.println("Year:");
                            year = sc.nextInt();
                            System.out.println("Month:");
                            month = sc.nextInt();
                            System.out.println("Day:");
                            day = sc.nextInt();
                            LocalDate returnDate = LocalDate.of(Integer.parseInt(String.valueOf(year)),
                                    Integer.parseInt(String.valueOf(month)),
                                    Integer.parseInt(String.valueOf(day)));
                            if (returnDate.isAfter(pickUp)) {
                                correctDates = true;
                                long days = pickUp.until(returnDate, ChronoUnit.DAYS);
                                int rentalPrice = (int) (cars.get(decision).getBasePrice() * days);
                                System.out.println("You will pay " + rentalPrice + "$");
                                RentedCarHistory carHistory = new RentedCarHistory(cars.get(decision), pickUp, returnDate, true, rentalPrice);
                                cars.get(decision).setRented(true);
                                rentedCarHistories.add(carHistory);
                                client.setRentedCarHistories(rentedCarHistories);
                                DataSource.saveRentedCar(carHistory, client);
                                System.out.println("You rented" + client.getCurrentRentedCar());
                            }

                        } else {
                            System.out.println("Please enter a valid date");
                        }
                    }
                    System.out.println("Invalid option");
                }
            } else {
                System.out.println("You already rented a car");
                System.out.println("You rented" + client.getCurrentRentedCar());
            }
        } else {
            System.out.println("You are logged in as a seller");
        }

    }

    public void displayIncome() {
        if (loggedIn instanceof Seller) {
            //sum all the rentalprice of curent rented cars
            int sum = 0;
            for (RentedCarHistory item : rentedCarHistories) {
                sum = sum + item.getRentalPricel();
            }
            System.out.println("The income from the rented cars is: " + sum);
        } else {
            System.out.println("You don't have permission for this");
        }

    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isRented()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }


    private void returnCar() throws SQLException {
        if (loggedIn instanceof Client) {
            Client client = (Client) loggedIn;
            for (RentedCarHistory item : client.getRentedCarHistories()) {
                if (item.isCurentlyRented()) {
                    item.getCar().setRented(false);
                    DataSource.returnCar(item, client);
                    System.out.println("You returned : " + item.getCar());
                }

            }
        } else {
            System.out.println("You are logged in as a seller");
        }
    }

    private User logIn() {
        System.out.println("Type username");
        String userName = sc.next();
        System.out.println("Type email");
        String email = sc.next();
        for (User user : users) {
            if (user.getName().equals(userName) && user.getEmail().equals(email)) {
                return loggedIn = user;
            }
        }

        return loggedIn = null;
    }

    public void printLog() {
        logIn();
        int count = 0;
        do {
            if (loggedIn != null) {
                System.out.println(loggedIn.getName() + " logged in ");
            } else {
                count++;
                System.out.println("Wrong username or password, please try again");


            }
        } while (loggedIn == null && count < 2);
    }


}



