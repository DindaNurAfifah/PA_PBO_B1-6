/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package papbo;

/**
 *
 * @author ASUS-GK
 */
public class ObatPil extends Obat{
    private String kode;

    public ObatPil(String kode, String namaObat, String dosisObatAnak, String dosisObatDewasa, int hargaObat, int stokObat) {
        super(namaObat, dosisObatAnak, dosisObatDewasa, hargaObat, stokObat);
        this.kode = "P" + kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = "P" + kode;
    }
    
    
}
