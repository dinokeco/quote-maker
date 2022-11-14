package ba.unsa.etf.rpr.dp.adapter;

public class App {

    public static void main(String[] args) {

        final double speed = 60;
        // some dummy vehicle
        Movable m = new Movable() {
            @Override
            public double speed() {
                return speed;
            }
        };
        MovableAdapter adapter = new MovableAdapterImpl(m);
        System.out.println("MPH ["+ speed+"] = KMPH ["+ adapter.speed() +"]");
    }
}
