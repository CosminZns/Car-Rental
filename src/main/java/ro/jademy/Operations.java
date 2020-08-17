package ro.jademy;

import ro.jademy.Users.User;
import ro.jademy.cars.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Operations {
    private List<Car> allCars;

    public Operations(List<Car> allCars) {
        this.allCars = allCars;
    }

    public void sort() {
        List<Car> cars = new ArrayList<>(allCars);
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

}
