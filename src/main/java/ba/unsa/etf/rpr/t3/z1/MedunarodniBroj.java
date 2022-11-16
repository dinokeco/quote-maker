package ba.unsa.etf.rpr.t3.z1;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj{

    private String drzava;
    private String broj;

    public MedunarodniBroj(String drzava, String broj){
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        if (drzava != null && broj != null){
            return drzava+broj; //maybe different formatting of number
        }else {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(drzava, broj);
    }
}
