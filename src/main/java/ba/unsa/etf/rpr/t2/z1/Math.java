package ba.unsa.etf.rpr.t2.z1;

/**
 * Class for basic math operations
 */
public class Math {
    /**
     * Approximate value of constant PI
     */
    public final static double PI = 3.14159265358979323846;

    /**
     * Calculate sinus of input number using formula:
     * Sine = SUM (-1.0^n)(x^(2n+1)) / (2n+1)!
     * @param input float number
     * @return sinus of input number
     */
    public static double sin(double input){
        double sum = 0;
        for (int i = 0; i <= 5; i++) {
            sum += power(-1, i) * (power(input, 2 * i + 1) / factoriel(2 * i + 1));
        }
        return sum;
    }

    /**
     * Calculate factoriel of input integer
     * @param input integer number
     * @return factoriel of input number
     */
    public static int factoriel(int input){
        int fact = 1;
        for (int i = 2; i <= input; i++) {
            fact = fact * i;
        }
        return fact;
    }

    /**
     * Calculate power of the input
     * @param input - number for base
     * @param pow - number for powering
     * @return input^pow
     */
    public static double power(double input, int pow){
        double product = 1;
        for(int i=0; i<pow; i++){
            product *= input;
        }
        return product;
    }
}
