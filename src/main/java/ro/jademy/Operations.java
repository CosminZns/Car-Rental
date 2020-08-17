package ro.jademy;

import ro.jademy.Users.Client;
import ro.jademy.Users.User;
import ro.jademy.cars.Car;
import ro.jademy.cars.RentedCarHistory;

import java.util.*;

public class Operations {
    private List<Car> cars;
    private List<User> users;


    public Operations(List<Car> cars, List<User> users) {
        this.cars = cars;
        this.users = users;
    }

    //Sort cars by make, model, color -- nu merge
    public void sort() {
        List<Car> cars = new ArrayList<>(this.cars);
        System.out.println("Before:" + cars);
        //Sort by Make
        cars.sort((car1, car2) -> {
            int comp = 0;
            comp = car1.getMake().getMake().compareTo(car2.getMake().getMake());
            //If Make is the same, sort by Model
            if (comp == 0) {
                cars.sort((car11, car21) -> {
                    int comp1 = 0;
                    comp1 = car11.getModel().getModel().compareTo(car21.getModel().getModel());
                    //if Model is the same, sort by color
                    if (comp1 == 0) {
                        cars.sort(Comparator.comparing(Car::getColor));
                    }
                    return comp1;
                });
            }
            return comp;
        });
        System.out.println("After:");
        System.out.println(cars);
    }

    //How many times was a car rented
    public void rentedCarTimes(Car car) {
        int count = 0;
        Client client = null;
        for (User user : users) {
            if (user instanceof Client) {
                client = (Client) user;
                for (int i = 0; i < client.getRentedCarHistories().size(); i++) {
                    if (car.getId() == client.getRentedCarHistories().get(i).getCar().getId()) {
                        count++;
                    }
                }

            }
        }

        System.out.println("This car was rented " + count + " times");
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
}
