/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package papbo;

/**
 *
 * @author ASUS-GK
 */
public class Obat {
    protected String namaObat, dosisObatAnak, dosisObatDewasa;
    protected int hargaObat, stokObat;

    public Obat(String namaObat, String dosisObatAnak, String dosisObatDewasa, int hargaObat, int stokObat) {
        this.namaObat = namaObat;
        this.dosisObatAnak = dosisObatAnak;
        this.dosisObatDewasa = dosisObatDewasa;
        this.hargaObat = hargaObat;
        this.stokObat = stokObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getDosisObatAnak() {
        return dosisObatAnak;
    }

    public String getDosisObatDewasa() {
        return dosisObatDewasa;
    }

    public int getHargaObat() {
        return hargaObat;
    }

    public int getStokObat() {
        return stokObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setDosisObatAnak(String dosisObatAnak) {
        this.dosisObatAnak = dosisObatAnak;
    }

    public void setDosisObatDewasa(String dosisObatDewasa) {
        this.dosisObatDewasa = dosisObatDewasa;
    }

    public void setHargaObat(int hargaObat) {
        this.hargaObat = hargaObat;
    }

    public void setStokObat(int stokObat) {
        this.stokObat = stokObat;
    }
   
}
