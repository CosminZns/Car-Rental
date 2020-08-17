package ro.jademy.cars.audi;

import ro.jademy.cars.Car;
import ro.jademy.information.Make;
import ro.jademy.information.Model;

public class Audi extends Car {
    private boolean isSport;

    public Audi(Make make, Model model, String color, int price, boolean isSport, boolean isRented, int id) {
        super(make, model, color, price, isRented,id);
        this.isSport = isSport;
    }

    public boolean isSport() {
        return isSport;
    }

}
