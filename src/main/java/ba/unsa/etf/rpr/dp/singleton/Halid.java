package ba.unsa.etf.rpr.dp.singleton;

/**
 * Singleton design pattern using factory method;
 * @author Dino Keco
 * @version 1.0
 */
public class Halid {
    // constructor is available here because its inside of class
    private static final Halid INSTANCE = new Halid();

    private Halid(){
        // initialize anything you want
    }
    public static Halid getInstance(){
        return INSTANCE;
    }

    public void sing(){
        System.out.println("Miljacka ... ");
    }
    public static void main(String[] args) {
        Halid.getInstance().sing();
    }
}
