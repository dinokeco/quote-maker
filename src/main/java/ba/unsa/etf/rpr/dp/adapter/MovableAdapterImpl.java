package ba.unsa.etf.rpr.dp.adapter;

public class MovableAdapterImpl implements MovableAdapter{

    private static final double MPH2KMPH = 1.60934;

    private Movable luxuryCar;

    public MovableAdapterImpl(Movable movable){
        this.luxuryCar = movable;
    }
    @Override
    public double speed() {
        return this.luxuryCar.speed() * MPH2KMPH;
    }
}
