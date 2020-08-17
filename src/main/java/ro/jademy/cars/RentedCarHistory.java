package ro.jademy.cars;

import java.time.LocalDate;

public class RentedCarHistory {

    private Car car;
    private LocalDate pickUp;
    private LocalDate returnDate;
    private boolean isCurentlyRented;
    private int rentalPricel;


    public RentedCarHistory(Car car, LocalDate pickUp, LocalDate returnDate, boolean isCurentlyRented, int rentalPricel) {
        this.car = car;
        this.pickUp = pickUp;
        this.returnDate = returnDate;
        this.isCurentlyRented = isCurentlyRented;
        this.rentalPricel = rentalPricel;
    }



    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getPickUp() {
        return pickUp;
    }

    public void setPickUp(LocalDate pickUp) {
        this.pickUp = pickUp;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isCurentlyRented() {
        return isCurentlyRented;
    }

    public void setCurentlyRented(boolean curentlyRented) {
        isCurentlyRented = curentlyRented;
    }

    public int getRentalPricel() {
        return rentalPricel;
    }

    public void setRentalPricel(int rentalPricel) {
        this.rentalPricel = rentalPricel;
    }

    @Override
    public String toString() {
        return   "car=" + car +
                ", pickUp=" + pickUp +
                ", returnDate=" + returnDate +
                ", isCurentlyRented=" + isCurentlyRented +
                ", rentalPricel=" + rentalPricel ;
    }
}
