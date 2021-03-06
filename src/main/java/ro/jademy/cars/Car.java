package ro.jademy.cars;

import ro.jademy.information.Make;
import ro.jademy.information.Model;

import java.util.Objects;

public abstract class Car implements Comparable<Car> {
    private Make make;
    private Model model;
    private String color;
    private int basePrice;
    private boolean isRented;
    private int id;

    public Car(Make make, Model model, String color, int basePrice, boolean isRented, int id) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.basePrice = basePrice;
        this.isRented = isRented;
        this.id = id;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public Model getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        this.isRented = rented;
    }

    @Override
    public String toString() {
        return
                make +
                        ", model=" + model +
                        ", color='" + color + '\'' +
                        ", basePrice=" + basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return basePrice == car.basePrice &&
                isRented == car.isRented &&
                id == car.id &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, color, basePrice, isRented, id);
    }

    @Override
    public int compareTo(Car o) {
        int comp = 0;
        comp = o.getMake().getName().compareTo(this.getMake().getName());
        if (comp == 0) {
            comp = o.getModel().getName().compareTo(this.getModel().getName());
//            if (comp == 0) {
//                return (Integer) o.getBasePrice().compareTo((Integer)this.getBasePrice());
//            }
        }
        return comp;
    }

}
