package ba.unsa.etf.rpr.t3.z1;

import java.util.Objects;

/**
 * Class for mobile phone numbers
 *
 * @author Dino Keco
 */
public class MobilniBroj extends TelefonskiBroj{

    private int mobilnaMreza;
    private String broj;

    public MobilniBroj(int mobilnaMreza, String broj){
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        if (broj != null){
            return "0"+mobilnaMreza+"/"+broj;
        }else{
            return null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobilnaMreza, broj);
    }
}
