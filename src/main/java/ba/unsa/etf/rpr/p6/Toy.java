package ba.unsa.etf.rpr.p6;

import java.io.Serializable;
import java.util.Objects;

public class Toy implements Serializable {

    private Integer id;
    private String name;
    private String color;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Toy(Integer id, String name, String color, String image) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.image = image;
    }

    public Toy(){
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return Objects.equals(id, toy.id) && Objects.equals(name, toy.name) && Objects.equals(color, toy.color) && Objects.equals(image, toy.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, image);
    }
}
