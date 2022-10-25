package ba.unsa.etf.rpr.t1;

import java.util.Scanner;

/**
 * Class that accepts two inputs from keyboard and calculate their sum
 * @author Dino Keco
 * @version 1.0
 * */
public class Addition {

    /**
     * entry method for console application for addition
     * @param args input arguments from console <i>asdas</i>
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite prvi broj: ");
        int first = scanner.nextInt();
        System.out.println("Unesite drugi broj: ");
        int second = scanner.nextInt();

        System.out.println("Suma brojeva je : "+ (first + second));
    }
}
