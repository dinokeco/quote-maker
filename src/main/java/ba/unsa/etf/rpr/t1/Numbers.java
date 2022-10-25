package ba.unsa.etf.rpr.t1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Dino Keco
 * Accepts one number from keyboard input and print out all numbers
 * that are dividable by sum of its digits
 * */
public class Numbers {

    /**
     * entry point method for numbers program
     * */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter integer number: ");
        int number = scanner.nextInt();
        for (int i = 1; i < number; i++){
            if (isDividableBySumOfDigits(i)){
                System.out.println(i);
            }
        }
    }

    /**
     * check if number is dividable by its digits
     * @param number just an integer number
     * */
    private static boolean isDividableBySumOfDigits(int number){
        int sum = sumDigits(number);
        return number%sum == 0;
    }

    /**
     * calculate the sum of all digits
     * @param number just an integer number
     * */
    private static int sumDigits(int number){
        int sum = 0;
        while (number > 0) {
            sum += number%10;
            number = number / 10;
        }
        return sum;
    }
    /**
     * get digits of given number
     * @param number just an integer number
     * */
    private static int[] getDigits(int number){
        int numberOfDigits = String.valueOf(number).length();
        int[] digits = new int[numberOfDigits];
        int i = 0;
        while (number > 0) {
            digits[i++] = number%10;
            number = number / 10;
        }
        return digits;
    }
}
