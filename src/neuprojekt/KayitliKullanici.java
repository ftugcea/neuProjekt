/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuprojekt;

/**
 *
 * @author tugce
 */
public class KayitliKullanici {
    String musteri , adres ;

    public KayitliKullanici(String musteri, String adres) {
        this.musteri = musteri;
        this.adres = adres;
    }

    public String getMusteri() {
        return musteri;
    }

    public String getAdres() {
        return adres;
    }

    public void setMusteri(String musteri) {
        this.musteri = musteri;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}
