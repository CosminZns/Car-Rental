package ro.jademy.cars.bmw;


import ro.jademy.cars.Car;
import ro.jademy.information.Make;
import ro.jademy.information.Model;

public class BMW extends Car {
    private boolean isM;

    public BMW(Make make, Model model, String color, int price, boolean isM,boolean isRented,int id) {
        super(make, model, color, price,isRented,id);
        this.isM=isM;
    }

    public boolean isM() {
        return isM;
    }
}
