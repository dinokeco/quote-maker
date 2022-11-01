package ba.unsa.etf.rpr.p6;

import java.io.Serializable;
import java.util.Objects;

public class Laptop implements Serializable {
    private String model;
    private String brand;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Laptop(String model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    public Laptop(){

    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return model.equals(laptop.model) && brand.equals(laptop.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, brand);
    }
}
