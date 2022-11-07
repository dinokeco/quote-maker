package ba.unsa.etf.rpr.p6;

import java.io.Serializable;
import java.util.Objects;

public class Pet implements Serializable {

    private Integer id;
    private String name;
    private String breed;
    private Integer age;
    private Double weight;

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(breed, pet.breed) && Objects.equals(age, pet.age) && Objects.equals(weight, pet.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, age, weight);
    }

    public Pet(Integer id, String name, String breed, Integer age, Double weight) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
