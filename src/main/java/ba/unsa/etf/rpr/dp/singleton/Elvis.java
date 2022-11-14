package ba.unsa.etf.rpr.dp.singleton;

import java.io.Serializable;

/**
 * Singleton design pattern using public constant attribute;
 * @author Dino Keco
 * @version 1.0
 */
public class Elvis implements Serializable {
    // constructor is available here because its inside of class
    transient public static final Elvis INSTANCE = new Elvis();

    private Elvis(){
        // initialize anything you want
    }
    public void sing(){
        System.out.println("Viva las Vegas");
    }
    private Object readResolve(){
        // fake elvis will end up in garbage.
        return INSTANCE;
    }
    public static void main(String[] args) {
        Elvis.INSTANCE.sing();
    }
}
