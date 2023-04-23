package ProjectAkhir;

/**
 *
 * @author USERD
 */
public class Transaksi {
    private String kodeObat, namaCustomer, namaObat, dosisAnak, dosisDewasa, status;
    private int jumlahObat, hargaObat;

    public Transaksi(String kodeObat, String namaCustomer, String namaObat, String dosisAnak, String dosisDewasa, String status, int jumlahObat, int hargaObat) {
        this.kodeObat = kodeObat;
        this.namaCustomer = namaCustomer;
        this.namaObat = namaObat;
        this.dosisAnak = dosisAnak;
        this.dosisDewasa = dosisDewasa;
        this.status = status;
        this.jumlahObat = jumlahObat;
        this.hargaObat = hargaObat;
    }

    public String getKodeObat() {
        return kodeObat;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getDosisAnak() {
        return dosisAnak;
    }

    public String getDosisDewasa() {
        return dosisDewasa;
    }

    public String getStatus() {
        return status;
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public int getHargaObat() {
        return hargaObat;
    }

    public void setKodeObat(String kodeObat) {
        this.kodeObat = kodeObat;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setDosisAnak(String dosisAnak) {
        this.dosisAnak = dosisAnak;
    }

    public void setDosisDewasa(String dosisDewasa) {
        this.dosisDewasa = dosisDewasa;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJumlahObat(int jumlahObat) {
        this.jumlahObat = jumlahObat;
    }

    public void setHargaObat(int hargaObat) {
        this.hargaObat = hargaObat;
    }

    public void tampil(){
        System.out.println(" Nama Obat     : " + this.namaObat);
        System.out.println("    Dosis Anak    : " + this.dosisAnak);
        System.out.println("    Dosis Dewasa  : " + this.dosisDewasa);
        System.out.println("    Harga Obat    : Rp." + this.hargaObat);
        System.out.println("    Jumlah        : " + this.jumlahObat);
        System.out.println("---------------------------------");
    }
}
