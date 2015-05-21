package be.howest.nmct.projectmad.admin;

/**
 * Created by Nathan on 21/05/15.
 */
public class KSA {

    public String naam, adres, gemeente;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }



    public KSA(String naam, String adres, String gemeente) {
        this.naam = naam;
        this.adres = adres;
        this.gemeente = gemeente;
    }
}
