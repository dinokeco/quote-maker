package ba.unsa.etf.rpr.p5;

public class Main {
    public static void main(String[] args) {
        Fraction f = new Fraction(4, 6);
        System.out.println(f);

        if (f.isReducable()){
            System.out.println("Reduced "+ f + " is "+ f.reduce());
        }else {
            System.out.println("Not possible to reduce");
        }

        Fraction f2 = new Fraction(8, 12);
        if (f == f2){
            System.out.println("exactly same");
        }else{
            System.out.println("not exactly same");
        }

        if (f.equals(f2)){
            System.out.println("equals");
        }else{
            System.out.println("not equals");
        }
    }
}
