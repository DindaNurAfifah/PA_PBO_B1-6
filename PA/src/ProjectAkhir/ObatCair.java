package ProjectAkhir;

import static ProjectAkhir.Main.dataUser;
import static ProjectAkhir.Main.userAktif;

/**
 *
 * @author ASUS-GK
 */
public class ObatCair extends Obat implements Dosis {

    private String kode;

    public ObatCair(String kode, String namaObat, String dosisObatAnak, String dosisObatDewasa, int hargaObat, int stokObat) {
        super(namaObat, dosisObatAnak, dosisObatDewasa, hargaObat, stokObat);
        this.kode = "C" + kode;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    @Override
    public void tampil() {
        System.out.println("Kode Obat     : " + this.kode);
        System.out.println("    Nama Obat     : " + this.namaObat);
    }

    public void tampil(boolean showDosis) {
        if (showDosis) {
            tampil();
            System.out.println("    Dosis Anak    : " + this.dosisObatAnak);
            System.out.println("    Dosis Dewasa  : " + this.dosisObatDewasa);
            System.out.println("    Harga Obat    : Rp." + this.hargaObat);
            System.out.println("    Stok Obat     : " + this.stokObat);
            System.out.println("---------------------------------");
        } else {
            tampil();
            tampilDosis();
            System.out.println("    Harga Obat    : Rp." + this.hargaObat);
            System.out.println("    Stok Obat     : " + this.stokObat);
            System.out.println("---------------------------------");
        }
    }

    @Override
    public void tampilDosis() {
        for (User cekUser : dataUser) {
            if (cekUser.getUsername().equals(userAktif)) {
                if (cekUser.getAge() < 17) {
                    System.out.println("    Dosis         : " + this.dosisObatAnak);
                } else {
                    System.out.println("    Dosis         : " + this.dosisObatDewasa);
                }
            }
        }
    }
}
