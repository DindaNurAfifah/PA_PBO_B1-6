/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package papbo;

/**
 *
 * @author ASUS-GK
 */
public class ObatCair extends Obat{
    private String kode;

    public ObatCair(String kode, String namaObat, String dosisObatAnak, String dosisObatDewasa, int hargaObat, int stokObat) {
        super(namaObat, dosisObatAnak, dosisObatDewasa, hargaObat, stokObat);
        this.kode = "C" + kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = "C" + kode;
    }
    
    
}
