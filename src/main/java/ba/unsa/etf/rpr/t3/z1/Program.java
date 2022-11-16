package ba.unsa.etf.rpr.t3.z1;

import java.util.Scanner;
import java.util.Set;

public class Program {
    public static Scanner scanner = new Scanner(System.in);
    public static Imenik imenik = new Imenik();

    public static void main(String[] args) {
        popuniPodatke();
        while(true){
            System.out.println("Unesite komandu [dodaj, dajBroj, dajIme, naSlovo, izGrada, izGradaBrojevi, imenik, izlaz]");
            String command = scanner.nextLine();
            switch (command){
                case "dodaj":
                    dodajBroj();
                break;
                case "dajBroj":
                    dajBroj();
                break;
                case "dajIme":
                    dajIme();
                    break;
                case "naSlovo":
                    naSlovo();
                    break;
                case "izGrada":
                    izGrada();
                    break;
                case "izGradaBrojevi":
                    izGradaBrojevi();
                    break;
                case "imenik":
                    ispisiImenik();
                    break;
                case "izlaz":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pogresna komanda.");
            }
        }
    }
    private static void izGradaBrojevi(){
        System.out.println("Unesite ime grada:");
        String grad = scanner.nextLine();
        try{
            Grad g = Grad.valueOf(grad);
            Set<TelefonskiBroj> response = imenik.izGradaBrojevi(g);
            for (TelefonskiBroj b : response){
                System.out.println(b.ispisi());
            }
        }catch (Exception e){
            System.out.println("Pogresan grad");
            return;
        }
    }
    private static void izGrada(){
        System.out.println("Unesite ime grada:");
        String grad = scanner.nextLine();
        try{
            Grad g = Grad.valueOf(grad);
            Set<String> response = imenik.izGrada(g);
            System.out.println(response);
        }catch (Exception e){
            System.out.println("Pogresan grad");
            return;
        }
    }
    private static void naSlovo(){
        System.out.println("Unesite prvo slovo imena: ");
        String c = scanner.nextLine();
        String response = imenik.naSlovo(c.toCharArray()[0]);
        System.out.println(response);
    }
    private static void dajIme() {
        TelefonskiBroj br = unesiBrojTelefona();
        String ime = imenik.dajIme(br);
        if(ime == null){
            System.out.println("Ne postoji u imeniku");
        }else{
            System.out.println("Vlasnik broja "+ br.ispisi()+" je "+ ime);
        }
    }
    private static TelefonskiBroj unesiBrojTelefona(){
        System.out.println("Unesite tip broja[fiksni, mobilni, medunarodni]:");
        String type = scanner.nextLine();
        switch (type){
            case "fiksni":
                System.out.println("Unesite pozivni: ");
                String pozivni = scanner.nextLine();
                System.out.println("Unesite broj: ");
                String broj = scanner.nextLine();
                return new FiksniBroj(Grad.izPozivnog(pozivni), broj);
            case "mobilni":
                System.out.println("Unesite mrezu: ");
                int mreza = scanner.nextInt();
                System.out.println("Unesi broj: ");
                String mobilniBroj = scanner.nextLine();
                return new MobilniBroj(mreza, mobilniBroj);
            case "medunarodni":
                System.out.println("Unesite kod drzave [+387]: ");
                String kod = scanner.nextLine();
                System.out.println("Unesi broj: ");
                String medBroj = scanner.nextLine();
                return new MedunarodniBroj(kod, medBroj);
        }
        return null;
    }
    private static void dodajBroj(){
        System.out.println("Unesite ime:");
        String name = scanner.nextLine();
        TelefonskiBroj br = unesiBrojTelefona();
        imenik.dodaj(name, br);
    }

    private static void ispisiImenik(){
        System.out.println(imenik.toString());
    }

    private static void dajBroj(){
        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();
        String rezultat = imenik.dajBroj(ime);
        System.out.println(rezultat == null ? "nema u imeniku" : rezultat);
    }

    private static void popuniPodatke(){
        imenik.dodaj("Eldar", new FiksniBroj(Grad.SARAJEVO, "225-883"));
        imenik.dodaj("Dino", new FiksniBroj(Grad.ZENICA, "225-884"));
        imenik.dodaj("Amir", new MobilniBroj(61, "225-885"));
        imenik.dodaj("Chris", new MedunarodniBroj("+44", "7768878794"));
    }
}
