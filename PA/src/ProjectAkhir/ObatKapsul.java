package ProjectAkhir;

/**
 *
 * @author ASUS-GK
 */
public class ObatKapsul extends Obat{
    private String kode;

    public ObatKapsul(String kode, String namaObat, String dosisObatAnak, String dosisObatDewasa, int hargaObat, int stokObat) {
        super(namaObat, dosisObatAnak, dosisObatDewasa, hargaObat, stokObat);
        this.kode = "K" + kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public void tampil(){
        System.out.println("Kode Obat     : " + this.kode);
        System.out.println("    Nama Obat     : " + this.namaObat);
        System.out.println("    Dosis Anak    : " + this.dosisObatAnak);
        System.out.println("    Dosis Dewasa  : " + this.dosisObatDewasa);
        System.out.println("    Harga Obat    : Rp." + this.hargaObat);
        System.out.println("    Stok Obat     : " + this.stokObat);
        System.out.println("---------------------------------");
    }
}
