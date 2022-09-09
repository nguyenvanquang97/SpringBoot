package vn.techmaster.day2.day2.model;

public class Car {
    private String brand;
    private String name;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car(String brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public Car() {
    }
}
