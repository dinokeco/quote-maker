package ba.unsa.etf.rpr.t2.z1;

/**
 * Entry point to our math program
 */
public class App {

    public static void main( String[] args ){
        if (args.length != 2){
            System.err.println("Nedovoljan broj argumenata java -jar rpr-t2-z1.jar [funkcija] [argument]");
        } else if (!args[0].equals("sinus") && !args[0].equals("factoriel")) {
            System.err.println("Nevalidna funkcija, sinus i factoriel su podrzane funkcije");
        }else{
            try {
                String funkcija = args[0];
                double input = Double.parseDouble(args[1]);
                switch (funkcija){
                    case "sinus":
                        System.out.println("sinus("+input+")="+Math.sin(input));
                        break;
                    case "factoriel":
                        System.out.println("factoriel("+input+")="+Math.factoriel((int)input));
                        break;
                }
            }catch (Exception e){
                System.err.println("Nevalidan drugi argument, mora biti broj");
            }
        }
    }
}
