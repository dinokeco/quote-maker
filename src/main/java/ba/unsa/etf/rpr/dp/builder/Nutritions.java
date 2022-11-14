package ba.unsa.etf.rpr.dp.builder;

/**
 * Java Bean for holding nutrition facts
 * @author Dino Keco
 * @version 1.0
 */
public class Nutritions {
    private int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int proteins;
    private int carbohydrate;
    private int fiber;
    private int vitamins;

    public Nutritions(int servingSize, int servings, int calories, int fat, int proteins, int carbohydrate, int fiber, int vitamins) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.proteins = proteins;
        this.carbohydrate = carbohydrate;
        this.fiber = fiber;
        this.vitamins = vitamins;
    }
    public Nutritions(int servingSize, int servings, int calories, int fat, int proteins, int carbohydrate, int fiber) {
       this(servingSize, servings, calories, fat, proteins, carbohydrate, fiber, 0);
    }
    public Nutritions(int servingSize, int servings, int calories, int fat, int proteins, int carbohydrate) {
        this(servingSize, servings, calories, fat, proteins, carbohydrate, 0, 0);
    }
    public Nutritions() {
    }
    // ... and so on many constructors

    public Nutritions servingSize(int servingSize){
        this.servingSize = servingSize;
        return this;
    }
    public Nutritions servings(int servings){
        this.servings = servings;
        return this;
    }
    public Nutritions calories(int calories){
        this.calories = calories;
        return this;
    }
    public Nutritions fat(int fat){
        this.fat = fat;
        return this;
    }
    public Nutritions proteins(int proteins){
        this.proteins = proteins;
        return this;
    }
    public Nutritions carbohydrate(int carbohydrate){
        this.carbohydrate = carbohydrate;
        return this;
    }
    public Nutritions fiber(int fiber){
        this.fiber = fiber;
        return this;
    }
    public Nutritions vitamins(int vitamins){
        this.vitamins = vitamins;
        return this;
    }

    @Override
    public String toString() {
        return "Nutritions{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", proteins=" + proteins +
                ", carbohydrate=" + carbohydrate +
                ", fiber=" + fiber +
                ", vitamins=" + vitamins +
                '}';
    }

    public static void main(String[] args) {
        Nutritions n = new Nutritions().calories(200).fat(30);
        System.out.println(n);
    }
}
