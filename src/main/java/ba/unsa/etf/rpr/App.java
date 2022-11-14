package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Hello world!
 * comment by dino
 * saso mange
 */
public class App 
{
    public static void main( String[] args )
    {
        Stack<String> stack1 = new Stack<String>();
        stack1.push("a");
        stack1.push("b");

        System.out.println(stack1.pop());
        System.out.println(stack1.toString());
        System.out.println( "Dobro dosli u RPR" );
    }
}
