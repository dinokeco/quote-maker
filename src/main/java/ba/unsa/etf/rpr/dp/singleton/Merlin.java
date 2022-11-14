package ba.unsa.etf.rpr.dp.singleton;

/**
 * Singleton design pattern using enum;
 * @author Dino Keco
 * @version 1.0
 */
public enum Merlin {
    INSTANCE;

    public void sing(){
        System.out.println("Pala magla do pola Sarajeva...");
    }

    public static void main(String[] args) {
        Merlin.INSTANCE.sing();
    }
}
