package ba.unsa.etf.rpr.t2.z2;

/**
 * Class for Bank account
 * @author Dino Keco
 */
public class Racun {

    private Long brojRacuna;
    private Osoba korisnikRacuna;
    private boolean odobrenjePrekoracenja;
    private Double stanjeRacuna;
    private Double prekoracenje;

    public Racun(Long brojRacuna, Osoba korisnikRacuna){
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
    }

    public boolean provjeriOdobrenjaPrekoracenja(Double iznos){
        return this.prekoracenje > iznos;
    }

    public boolean izvrsiUplatu(Double iznos){
        this.stanjeRacuna += iznos;
        return true;
    }

    public boolean izvrsiIsplatu(Double iznos){
        if (this.stanjeRacuna + this.prekoracenje < iznos){
            return false;
        }else{
            this.stanjeRacuna -= iznos;
            return true;
        }
    }

    public void odobriPrekoracenje(Double iznos){
        this.prekoracenje = iznos;
    }

    public Long getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Osoba getKorisnikRacuna() {
        return korisnikRacuna;
    }

    public void setKorisnikRacuna(Osoba korisnikRacuna) {
        this.korisnikRacuna = korisnikRacuna;
    }

    public boolean isOdobrenjePrekoracenja() {
        return odobrenjePrekoracenja;
    }

    public void setOdobrenjePrekoracenja(boolean odobrenjePrekoracenja) {
        this.odobrenjePrekoracenja = odobrenjePrekoracenja;
    }

    public Double getStanjeRacuna() {
        return stanjeRacuna;
    }

    public void setStanjeRacuna(Double stanjeRacuna) {
        this.stanjeRacuna = stanjeRacuna;
    }

    public Double getPrekoracenje() {
        return prekoracenje;
    }

    public void setPrekoracenje(Double prekoracenje) {
        this.prekoracenje = prekoracenje;
    }
}
