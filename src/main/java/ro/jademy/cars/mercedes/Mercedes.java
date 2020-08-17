package ro.jademy.cars.mercedes;

import ro.jademy.cars.Car;
import ro.jademy.information.Make;
import ro.jademy.information.Model;

public class Mercedes extends Car {
    private boolean isAMG;


    public Mercedes(Make make, Model model, String color, int price, boolean isAMG, boolean isRented, int id) {
        super(make, model, color, price, isRented, id);
        this.isAMG = isAMG;
    }

    public boolean isAMG() {
        return isAMG;
    }


}
