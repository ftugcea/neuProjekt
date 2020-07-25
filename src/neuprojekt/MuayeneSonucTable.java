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
public class MuayeneSonucTable {
    
    String sirano , kaynakno , kontrol , kaynakyon , kalinlik , cap , hatatipi , hatayeri , sonuc ;

    public MuayeneSonucTable(String sirano, String kaynakno, String kontrol, String kaynakyon, String kalinlik, String cap, String hatatipi, String hatayeri, String sonuc) {
        this.sirano = sirano;
        this.kaynakno = kaynakno;
        this.kontrol = kontrol;
        this.kaynakyon = kaynakyon;
        this.kalinlik = kalinlik;
        this.cap = cap;
        this.hatatipi = hatatipi;
        this.hatayeri = hatayeri;
        this.sonuc = sonuc;
    }

    public String getSirano() {
        return sirano;
    }
    
    public String getKaynakno() {
        return kaynakno;
    }

    public String getKontrol() {
        return kontrol;
    }

    public String getKaynakyon() {
        return kaynakyon;
    }

    public String getKalinlik() {
        return kalinlik;
    }

    public String getCap() {
        return cap;
    }

    public String getHatatipi() {
        return hatatipi;
    }

    public String getHatayeri() {
        return hatayeri;
    }

    public String getSonuc() {
        return sonuc;
    }

    public void setSirano(String sirano) {
        this.sirano = sirano;
    }
    
    public void setKaynakno(String kaynakno) {
        this.kaynakno = kaynakno;
    }

    public void setKontrol(String kontrol) {
        this.kontrol = kontrol;
    }

    public void setKaynakyon(String kaynakyon) {
        this.kaynakyon = kaynakyon;
    }

    public void setKalinlik(String kalinlik) {
        this.kalinlik = kalinlik;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setHatatipi(String hatatipi) {
        this.hatatipi = hatatipi;
    }

    public void setHatayeri(String hatayeri) {
        this.hatayeri = hatayeri;
    }

    public void setSonuc(String sonuc) {
        this.sonuc = sonuc;
    }
    
    
    
}
