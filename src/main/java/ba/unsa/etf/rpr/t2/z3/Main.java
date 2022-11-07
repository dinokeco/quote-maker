package ba.unsa.etf.rpr.t2.z3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = new ArrayList<Double>();

        while(true){
            System.out.print("Unesite broj ili rijec stop: ");
            String line = scanner.nextLine();
            if ("stop".equalsIgnoreCase(line.trim())){
                break;
            }
            try{
                Double number = Double.parseDouble(line);
                numbers.add(number);
            }catch (Exception e){
                System.out.println("Uneseni string nije broj!");
                continue; // ignore exception and continue with the loop
            }
        }

        System.out.println("Rezultati: ");
        System.out.println("MIN: " + MathUtils.min(numbers));
        System.out.println("MAX: " + MathUtils.max(numbers));
        System.out.println("MEAN: " + MathUtils.mean(numbers));
        System.out.println("STDEV: " + MathUtils.stdev(numbers));
    }
}
