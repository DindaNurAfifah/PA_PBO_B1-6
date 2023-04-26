package ProjectAkhir;

/**
 *
 * @author ASUS-GK
 */
public abstract class Obat {
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

    public final void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public final void setDosisObatAnak(String dosisObatAnak) {
        this.dosisObatAnak = dosisObatAnak;
    }

    public final void setDosisObatDewasa(String dosisObatDewasa) {
        this.dosisObatDewasa = dosisObatDewasa;
    }

    public final void setHargaObat(int hargaObat) {
        this.hargaObat = hargaObat;
    }

    public final void setStokObat(int stokObat) {
        this.stokObat = stokObat;
    }
    
    public abstract void tampil();
}
