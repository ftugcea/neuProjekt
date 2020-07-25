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
public class RaporBilgileri {
    String yuzeydurumu , teklifno , isemrino;

    public RaporBilgileri(String yuzeydurumu, String teklifno, String isemrino) {
        this.yuzeydurumu = yuzeydurumu;
        this.teklifno = teklifno;
        this.isemrino = isemrino;
    }

    public String getYuzeydurumu() {
        return yuzeydurumu;
    }

    public void setYuzeydurumu(String yuzeydurumu) {
        this.yuzeydurumu = yuzeydurumu;
    }

    public String getTeklifno() {
        return teklifno;
    }

    public void setTeklifno(String teklifno) {
        this.teklifno = teklifno;
    }

    public String getIsemrino() {
        return isemrino;
    }

    public void setIsemrino(String isemrino) {
        this.isemrino = isemrino;
    }
    
}
