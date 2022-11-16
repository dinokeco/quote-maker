package ba.unsa.etf.rpr.t3.z1;

import java.util.*;

/**
 * Class for phonebook
 *
 * @author Dino Keco
 */
public class Imenik {

    private Map<String, TelefonskiBroj> brojevi;

    public Imenik() {
        this.brojevi = new HashMap<String, TelefonskiBroj>();
    }

    public Map<String, TelefonskiBroj> getBrojevi() {
        return brojevi;
    }

    public void setBrojevi(Map<String, TelefonskiBroj> brojevi) {
        this.brojevi = brojevi;
    }

    /**
     * Dodaje broj u imenik
     * @param ime - vlasnik broja
     * @param broj - broj telefona mob, fix, inter
     */
    public void dodaj(String ime, TelefonskiBroj broj) {
        this.brojevi.put(ime, broj);
    }

    /**
     * Vraća telefonski broj osobe pod imenom ime u formi stringa pozivajući metodu ispisi() klase TelefonskiBroj
     *
     * @param ime
     * @return
     */
    public String dajBroj(String ime) {
        TelefonskiBroj broj = this.brojevi.get(ime);
        if (broj != null) {
            return broj.ispisi();
        } else {
            return null;
        }
    }

    /**
     * vraća ime osobe čiji telefonski broj je broj - i ova operacija treba koristiti HashMapu
     *
     * @return
     */
    public String dajIme(TelefonskiBroj broj) {
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (entry.getValue().ispisi().equals(broj.ispisi())) { // use ispisi to compare numbers
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * vraća sve brojeve u telefonskom imeniku za osobe čije ime počinje na slovo s u formatu:
     * <p>
     * 1. Ime Prezime - broj
     * 2. Ime Prezime - broj
     * ...
     *
     * @param c
     * @return
     */
    public String naSlovo(char c) {
        StringBuilder builder = new StringBuilder();

        int counter = 1;
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (entry.getKey().startsWith(String.valueOf(c))) {
                builder.append(counter) // use string builder to avoid memory overload
                        .append(". ")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue().ispisi())
                        .append(System.lineSeparator());
            }
            counter++;
        }
        return builder.toString();
    }

    /**
     * vraća skup Stringova koji sadrži imena i prezimena svih osoba koje žive u gradu g (istog pobrojanog tipa koji je dat u klasi FiksniBroj), što se može saznati ako osoba ima fiksni broj telefona u tom gradu. Pri tome skup treba biti sortiran abecedno.
     *
     * @param g
     * @return
     */
    public Set<String> izGrada(Grad g) {
        Set<String> results = new TreeSet<String>(); // tree set impl should sort automatically
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (jelIzGrada(entry.getValue(), g)) {
                results.add(entry.getKey());
            }
        }
        return results;
    }

    /**
     * vraća brojeve za osobe iz grada g. Ovaj skup treba biti sortiran po stringu koji se dobije metodom ispisi().
     * @param g
     * @return
     */
    public Set<TelefonskiBroj> izGradaBrojevi(Grad g){
        Set<TelefonskiBroj> results = new TreeSet<TelefonskiBroj>(new Comparator<TelefonskiBroj>() {
            @Override
            public int compare(TelefonskiBroj o1, TelefonskiBroj o2) {
                return o1.ispisi().compareTo(o2.ispisi());
            }
        });

        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            if (jelIzGrada(entry.getValue(), g)) {
                results.add(entry.getValue());
            }
        }

        return results;
    }

    private boolean jelIzGrada(TelefonskiBroj broj, Grad g) {
        if (broj instanceof FiksniBroj) {
            return g.equals(((FiksniBroj) broj).getGrad());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Map.Entry<String, TelefonskiBroj> entry : this.brojevi.entrySet()) {
            builder.append(counter) // use string builder to avoid memory overload
                    .append(". ")
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue().ispisi())
                    .append(System.lineSeparator());
            counter++;
        }
        return builder.toString();
    }
}
