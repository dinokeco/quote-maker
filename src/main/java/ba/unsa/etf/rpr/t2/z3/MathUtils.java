package ba.unsa.etf.rpr.t2.z3;

import java.util.List;

/**
 * Util class
 *
 * @author Dino Keco
 */
public class MathUtils {

    /**
     * get minimal element from list
     * @param numbers
     * @return
     */
    public static Double min(List<Double> numbers){
        double min = Double.MAX_VALUE;
        for(Double num : numbers){
            if (num.doubleValue() < min){
                min = num.doubleValue();
            }
        }
        return min;
    }

    /**
     * get maximal element from the list
     * @param numbers
     * @return
     */
    public static Double max(List<Double> numbers){
        double max = Double.MIN_VALUE;
        for(Double num : numbers){
            if (num.doubleValue() > max){
                max = num.floatValue();
            }
        }
        return max;
    }

    /**
     * Calculate mean value of elements in the list
     * @param numbers
     * @return
     */
    public static Double mean(List<Double> numbers){
        double sum = 0;
        for(Double num : numbers){
            sum += num.doubleValue();
        }
        return sum/numbers.size();
    }

    /**
     * calculate standard deviation using this formula: sqrt((∑(Xi - ų)^2) / N)
     * @param numbers
     * @return
     */
    public static Double stdev(List<Double> numbers){
        Double mean = MathUtils.mean(numbers);
        float standardDeviation = 0;
        for(Double num: numbers) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/numbers.size());
    }
}
