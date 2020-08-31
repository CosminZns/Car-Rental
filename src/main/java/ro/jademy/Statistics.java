package ro.jademy;

import ro.jademy.Users.Client;
import ro.jademy.Users.User;
import ro.jademy.cars.Car;
import ro.jademy.cars.RentedCarHistory;

import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Statistics {
    private List<Car> cars;
    private List<User> users;


    public Statistics(List<Car> cars, List<User> users) {
        this.cars = cars;
        this.users = users;
    }

    //Sort cars by make, model, color
    public void sort() {
        List<Car> cars = new ArrayList<>(this.cars);
        System.out.println("Before:" + cars);
        //Sort by Make
        cars.sort((car1, car2) -> {
            int comp = car1.getMake().getName().compareTo(car2.getMake().getName());
            //If Make is the same, sort by Model
            if (comp == 0) {
                comp = car1.getModel().getName().compareTo(car2.getMake().getName());
                if (comp == 0) {
                    comp = car1.getColor().compareTo(car2.getColor());
                }
            }
            return comp;
        });
        System.out.println("After:");
        System.out.println(cars);
    }

    //How many times was a car rented
    public int getRentedCarTimes() {
        System.out.println("Chose a car by the number");
        Scanner sc = new Scanner(System.in);
        int decision = sc.nextInt() - 1;
        Car car = cars.get(decision);
        int count = 0;
        for (User user : users) {
            if (user instanceof Client) {
                Client client = (Client) user;
                for (RentedCarHistory rentedCarHistory : client.getRentedCarHistories()) {
                    if (car.equals(rentedCarHistory.getCar())) {
                        count++;
                    }
                }
            }
        }
        System.out.println("This car was rented " + count + " times");
        return count;
    }

    //How many times each cars was rented
    public HashMap<Car, Integer> numberOfRents() {
        HashMap<Car, Integer> rentedTimes = new HashMap<>();
        int count = 0;
        Client client = null;
        for (Car car : cars) {
            for (User user : users) {
                if (user instanceof Client) {
                    client = (Client) user;
                    for (RentedCarHistory rented : client.getRentedCarHistories()) {
                        if (car.getId() == rented.getCar().getId()) {
                            count++;
                            rentedTimes.put(car, count);
                        }
                    }
                }
            }
        }
        for (Map.Entry<Car, Integer> entry : rentedTimes.entrySet()) {
            System.out.println(entry.getKey() + " was rented " + entry.getValue().toString() + " Times");
        }
        return rentedTimes;
    }

    //The most rented Car
    public void bestRentedCar(HashMap<Car, Integer> rentedTimes) {
        int maxValue = 0;
        for (Map.Entry<Car, Integer> entry : rentedTimes.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }
        Set<Car> keySet = rentedTimes.keySet();
        int finalMaxValue = maxValue;
        keySet.stream().filter(x -> rentedTimes.get(x) == finalMaxValue).forEach(System.out::println);

    }

    //Averege time a car is rented
    public void displayAveregeRentedTime() {
        List<Long> times = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Client) {
                Client client = (Client) user;
                for (RentedCarHistory item : client.getRentedCarHistories()) {
                    long daysBetween = DAYS.between(item.getPickUp(), item.getReturnDate());
                    times.add(daysBetween);
                }
            }
        }

        int sum = 0;
        for (Long time : times) {
            sum = (int) (sum + time);
        }
        int averegeTime = sum / times.size();
        System.out.println("Averege time a car is rented is : " + averegeTime + " days");
    }


}
