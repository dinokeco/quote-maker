package ba.unsa.etf.rpr.p5;

/***
 * Class for representing fractions in java
 * @author Dino Keco
 * @version 1.0
 */
public class Fraction {

    private int numerator;
    private int denominator;

    /**
     * Default constructor sets fraction to 1. 1 nom and 1 denom
     */
    public Fraction() {
        this(1,1);
    }

    /**
     * Construct the fraction
     * @param numerator numerator value
     * @param denominator denumerator value
     * @throws IllegalArgumentException in case of division by zero
     */
    public Fraction(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) throw new IllegalArgumentException("Division by zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Calculate the greatest common divisor of two integer numbers
     * @param a - first number
     * @param b - second number
     * @return greatest common divisor
     */
    private int gcd(int a, int b) {
        if (b > a) return gcd(b,a);
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    /***
     * shortens the fraction by reducing numerator and denominator for the gcd of them
     */
    public Fraction reduce() {
        int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
        if (gcd > 1) {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
        return this;
    }

    /**
     * checks if it is possible to shorten the fraction
     * @return true if possible otherwise false
     */
    public boolean isReducable() {
        int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
        return gcd > 1;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (this.denominator == 0){
            throw new IllegalArgumentException("Division by zero");
        }
        this.denominator = denominator;
    }

    /**
     * Get double value of fraction
     * @return double value of fraction
     */
    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    @Override
    public String toString() {
        return String.valueOf(numerator) + "/" + String.valueOf(denominator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fraction){
            // there are other ways to do this. this is simple solution
            return this.toDouble() == ((Fraction) obj).toDouble();
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // we don't need to implement this now but keep it here as reminder
    }
}
