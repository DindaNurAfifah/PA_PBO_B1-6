package ProjectAkhir;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static String userAktif;
    static ArrayList<Transaksi> dataTransaksi = new ArrayList<>();
    
    static ArrayList<User> dataUser = new ArrayList<>() {
        {
            add(new User("admin", "admin", "null", "null", 1));
            add(new User("dinda", "061", "Jl. Jalan", "dinda@gmail.com", 1));
            add(new User("mahsa", "065", "Jl. Jalan", "mahsa@gmail.com", 1));
            add(new User("ikhwan", "066", "Jl. Jalan", "ikhwan@gmail.com", 1));
            add(new User("muchlis", "069", "Jl. Jalan", "muchlis@gmail.com", 1));
        }
    };

    static ArrayList<ObatCair> cair = new ArrayList<>() {
        {
            add(new ObatCair("0", "Paracetamol Cair", "10-15 mg/kgBB/setiap 4-6 jam", "500-1000 mg/setiap 4-6 jam", 30000, 10));
            add(new ObatCair("1", "Ibuprofen Cair", "5-10 mg/kgBB/setiap 6-8 jam", "200-400 mg/setiap 4-6 jam", 60000, 10));
            add(new ObatCair("2", "Cetirizine Cair", "2,5-5 mg/setiap 12 jam", "5-10 mg/setiap 12-24 jam", 20000, 10));
        }
    };
    
    static ArrayList<ObatKapsul> kapsul = new ArrayList<>() {
        {
            add(new ObatKapsul("0", "Amoxicillin", "20-50 mg/kgBB/hari", "250-500 mg setiap 8 jam atau 500-875 mg setiap 12 jam", 250000, 10));
            add(new ObatKapsul("1", "Cephalexin", "25-50 mg/kgBB/hari", "250-500 mg setiap 6 jam atau 1-4 gram setiap 12 jam", 18000, 10));
            add(new ObatKapsul("2", "Omeprazole", "0,7-3 mg/kgBB/hari", "20-40 mg setiap hari", 6000, 10));
        }
    };

    static ArrayList<ObatPil> pil = new ArrayList<>() {
        {
            add(new ObatPil("0", "Tramadol", "Tidak disarankan untuk anak-anak di bawah usia 12 tahun", "50-100 mg setiap 4-6 jam", 50000, 10));
            add(new ObatPil("1", "Naproxen", "Tidak disarankan untuk anak-anak di bawah usia 12 tahun", "250-500 mg setiap 12 jam", 17500, 10));
            add(new ObatPil("2", "Furosemide", "1-2 mg/kg berat badan per hari", "20-80 mg per hari", 430000, 10));
        }
    };

    public static void main(String[] args) throws IOException {

        String pilih = "a";
        while (!"E".equals(pilih)) {
            System.out.println("""
                               O========================================O
                               |     Aplikasi Apotek Chemical Farma     |
                               O========================================O
                               | 1. Registration                        |
                               | 2. Login                               |
                               | 0. Exit                                |
                               O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            if (null == pilih) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilih) {

                    case "1", "Registration", "registration"->
                        regis();

                    case "2", "Login", "login" -> {
                        if (login() == true) {
                            System.out.println("");
                        } else {
                            System.out.println("""
                                               O========================================O
                                               |             Account not found          | 
                                               O========================================O
                                               """);
                        }
                        break;
                    }

                    case "0", "Exit", "exit" -> {
                        System.out.println("O========================================O");
                        System.out.println("|         Thank you for visiting         |");
                        System.out.println("O========================================O");
                        System.exit(0);
                    }

                    default -> {
                        System.out.println("O========================================O");
                        System.out.println("|             Invalid choice             |");
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
            }
        }
    }

    public static int kodeObatCair() {
        int x = 0;
        if (cair.size() < 1) {
            x = 0;
        } else {
            for (int i = 0; i < cair.size(); i++) {
                x++;
            }
        }
        return x;
    }

    public static int kodeObatKapsul() {
        int x = 0;
        if (kapsul.size() < 1) {
            x = 0;
        } else {
            for (int i = 0; i < kapsul.size(); i++) {
                x++;
            }
        }
        return x;
    }

    public static int kodeObatPil() {
        int x = 0;
        if (pil.size() < 1) {
            x = 0;
        } else {
            for (int i = 0; i < pil.size(); i++) {
                x++;
            }
        }
        return x;
    }

    public static boolean login() throws IOException {
        String user, pass;
        System.out.println("""
                                O========================================O
                                |     Aplikasi Apotek Chemical Farma     |
                                |                 Login!                 |
                                O========================================O""");
        System.out.print("  Username : ");
        user = input.readLine();
        System.out.print("  Password : ");
        pass = input.readLine();
        for (User cekUser : dataUser) {
            if (cekUser.getUsername().equals(user) && cekUser.getPassword().equals(pass)) {
                if (dataUser.get(0).getUsername().equals(user) && dataUser.get(0).getPassword().equals(pass)) {
                    System.out.println("O========================================O");
                    System.out.println("Welcome " + user + "!");
                    userAktif = user;
                    menuAdmin();
                } else {
                    System.out.println("O========================================O");
                    System.out.println("Welcome " + user + "!");
                    userAktif = user;
                    menuUser();
                    return true;
                }
            }
        }
        return false;
    }

    public static void regis() throws IOException {
        String user, pass, alamat, email;
        int age;

        System.out.println("""
                               O========================================O
                               |              Registration              |
                               O========================================O""");
        System.out.print("  Username : ");
        user = input.readLine();
        System.out.print("  Password : ");
        pass = input.readLine();
        System.out.print("  Address  : ");
        alamat = input.readLine();
        System.out.print("  Email    : ");
        email = input.readLine();
        boolean isValidInput;
        do {
            try {
              System.out.print("  Age      : ");
              age = Integer.parseInt(input.readLine());
              isValidInput = true;
              System.out.println("""
                                          O========================================O
                                          |         Successful Registration        |
                                          O========================================O""");
              User addUser = new User(user, pass, alamat, email, age);
              dataUser.add(addUser);
            } catch (NumberFormatException e) {
              System.out.println("Invalid input! Enter with number!");
              isValidInput = false;
            }
          } while (!isValidInput);
        }

    public static void menuAdmin() throws IOException {
        String pilih = "a";
        while (!"0".equals(pilih)) {
            System.out.println("""
                               O========================================O
                               |     Aplikasi Apotek Chemical Farma     |
                               O========================================O
                               | 1. Add Data                            |
                               | 2. Read Data                           |
                               | 3. Update Data                         |
                               | 4. Delete Data                         |
                               | 0. Log Out                             |
                               O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            if (null == pilih) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilih) {

                    case "1", "Add Data", "add data" ->
                        createDataObat();

                    case "2", "Read Data", "read data" ->
                        readData();

                    case "3", "Update Data", "update data" ->
                        updateData();

                    case "4", "Delete Data", "delete data" ->
                        deleteData();

                    case "0", "Log Out", "log out" -> {
                        System.out.println("O========================================O");
                        System.out.println("|         Thank you for visiting         |");
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }

                    default -> {
                        System.out.println("""
                                          O========================================O
                                          |              Menu Not Exist!!          |
                                          O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
            }
        }
    }

    public static void createDataObat() throws IOException {
        String pilihtipe = "a", nama, kode, dosisAnak, dosisDewasa;
        int stok, harga;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                                O========================================O
                                |                 Add Obat               |
                                O========================================O
                                | 1. Obat Cair                           |
                                | 2. Obat Kapsul                         |
                                | 3. Obat Pil                            |
                                | 0. Exit                                |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");

            } else {
                switch (pilihtipe) {
                    case "1", "Obat Cair", "obat cair" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodeCair = kodeObatCair();
                        kode = "" + kodeCair;

                        System.out.print("  Nama Obat          : ");
                        nama = input.readLine();
                        System.out.print("  Dosis Anak         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Dosis Dewasa       : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                              System.out.print("  Harga Obat         : Rp.");
                              harga = Integer.parseInt(input.readLine());
                              isValidInput = true;
                              do {
                                try {
                                  System.out.print("  Stok Obat          : ");
                                  stok = Integer.parseInt(input.readLine());
                                  isValidInput = true;
                                  ObatCair addCair = new ObatCair(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                  cair.add(addCair);
                                  System.out.println("""
                                          O========================================O
                                          |         Data successfully added        |
                                          O========================================O""");
                                  break;
                                } catch (NumberFormatException e) {
                                    System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                  isValidInput = false;
                                }
                              } while (!isValidInput); 
                            } catch (NumberFormatException e) {
                              System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                              isValidInput = false;
                            }
                          } while (!isValidInput);

                        
                    }

                    case "2", "Obat Kapsul", "obat kapsul" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodeKapsul = kodeObatKapsul();
                        kode = "" + kodeKapsul;

                        System.out.print("  Nama Obat          : ");
                        nama = input.readLine();
                        System.out.print("  Dosis Anak         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Dosis Dewasa       : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                              System.out.print("  Harga Obat         : Rp.");
                              harga = Integer.parseInt(input.readLine());
                              isValidInput = true;
                              do {
                                try {
                                  System.out.print("  Stok Obat          : ");
                                  stok = Integer.parseInt(input.readLine());
                                  isValidInput = true;
                                  ObatKapsul addKapsul = new ObatKapsul(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                  kapsul.add(addKapsul);
                                  System.out.println("""
                                          O========================================O
                                          |         Data successfully added        |
                                          O========================================O""");
                                  break;
                                } catch (NumberFormatException e) {
                                  System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                  isValidInput = false;
                                }
                              } while (!isValidInput); 
                            } catch (NumberFormatException e) {
                              System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                              isValidInput = false;
                            }
                          } while (!isValidInput);
                        
                    }

                    case "3", "Obat Pil", "obat pil" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodePil = kodeObatPil();
                        kode = "" + kodePil;

                        System.out.print("  Nama Obat          : ");
                        nama = input.readLine();
                        System.out.print("  Dosis Anak         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Dosis Dewasa       : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                              System.out.print("  Harga Obat         : Rp.");
                              harga = Integer.parseInt(input.readLine());
                              isValidInput = true;
                              do {
                                try {
                                  System.out.print("  Stok Obat          : ");
                                  stok = Integer.parseInt(input.readLine());
                                  isValidInput = true;
                                  ObatPil addPil = new ObatPil(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                  pil.add(addPil);
                                  System.out.println("""
                                          O========================================O
                                          |         Data successfully added        |
                                          O========================================O""");
                                  break;
                                } catch (NumberFormatException e) {
                                  System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                  isValidInput = false;
                                }
                              } while (!isValidInput); 
                            } catch (NumberFormatException e) {
                              System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                              isValidInput = false;
                            }
                          } while (!isValidInput);
                    }

                    case "0", "Exit", "exit" -> {
                        System.out.println("");
                        break;
                    }

                    default -> {
                        System.out.println("""
                                          O========================================O
                                          |              Menu Not Exist!!          |
                                          O========================================O""");
                        break;
                    }
                }
            }
        }
    }

    public static void readData() throws IOException {
        String pilihtipe = "a";

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                                O========================================O
                                |                Read Data               |
                                O========================================O
                                | 1. Read Data User                      |
                                | 2. Read Data Obat                      |
                                | 0. Exit                                |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");

            } else {
                switch (pilihtipe) {
                    case "1", "Read Data User", "read data user" ->
                        readDataUser();

                    case "2", "Read Data Obat", "read data obat" ->
                        readDataObat();

                    case "0", "Exit", "exit" -> {
                        System.out.println("");
                        break;
                    }

                    default -> {
                        System.out.println("""
                                          O========================================O
                                          |              Menu Not Exist!!          |
                                          O========================================O""");
                        break;
                    }
                }
            }
        }
    }

    public static void readDataUser() {
        if (cair.size() < 1) {
            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
            System.out.print("Press [enter] to continue...");
            new java.util.Scanner(System.in).nextLine();
        } else {
            System.out.println("""
                            O========================================O
                            |                Data User               |
                            O========================================O""");
            for (int i = 1; i < dataUser.size(); i++) {
                System.out.println("  Username           : " + dataUser.get(i).getUsername());
                System.out.println("  Password           : " + dataUser.get(i).getPassword());
                System.out.println("  Address            : " + dataUser.get(i).getAddress());
                System.out.println("  Email              : " + dataUser.get(i).getEmail());
                System.out.println("-------------------------------=----------");
            }
            System.out.println("O========================================O");
            System.out.print("Press [enter] to continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }

    public static void readDataObat() throws IOException {

        String pilihtipe = "a";
        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                                O========================================O
                                |              Read Data Obat            |
                                O========================================O
                                | 1. Obat Cair                           |
                                | 2. Obat Kapsul                         |
                                | 3. Obat Pil                            |
                                | 0. Exit                                |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");
            } else switch (pilihtipe) {
                case "1", "Obat Cair", "obat cair" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                                        O========================================O
                                        |                Obat Cair               |
                                        O========================================O""");
                        for (int i = 0; i < cair.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            cair.get(i).tampil();
                        }

                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                case "2", "Obat Kapsul" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                                        O========================================O
                                        |               Obat Kapsul              |
                                        O========================================O""");
                        for (int i = 0; i < kapsul.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            kapsul.get(i).tampil();
                        }
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                case "3", "Obat Pil", "obat pil" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                                        O========================================O
                                        |                 Obat Pil               |
                                        O========================================O""");
                        for (int i = 0; i < pil.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            pil.get(i).tampil();
                        }
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                case "0", "Exit", "exit" -> {
                    System.out.println("");
                    break;
                }
                default -> {
                    System.out.println("""
                                          O========================================O
                                          |              Menu Not Exist!!          |
                                          O========================================O""");
                    break;
                }
            }
        }
    }
    
    public static void updateData() throws IOException {
        String pilihtipe = "a", kode;
        boolean ditemukan = false;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                                O========================================O
                                |               Update Obat              |
                                O========================================O
                                | 1. Obat Cair                           |
                                | 2. Obat Kapsul                         |
                                | 3. Obat Pil                            |
                                | 0. Exit                                |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");
            } else switch (pilihtipe) {
                case "1", "Obat Cair", "obat cair" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                        O========================================O
                        |               Data Is Empty            |
                        O========================================O""");
                    } else {
                        System.out.println("""
                        O========================================O
                        |                 Obat Cair              |
                        O========================================O""");
                        for (int i = 0; i < cair.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            cair.get(i).tampil();
                        }
                        System.out.println("O========================================O");
                        System.out.print(" Input Code : ");
                        String no = input.readLine();
                        for (ObatCair obatCair : cair) {
                            if (obatCair.getKode().equals(no)) {
                                int kodeCair = kodeObatCair();
                                kode = "C" + kodeCair;
                                obatCair.setKode(kode);

                                System.out.print("  Nama Obat          : ");
                                obatCair.setNamaObat(input.readLine());
                                System.out.print("  Dosis Anak         : ");
                                obatCair.setDosisObatAnak(input.readLine());
                                System.out.print("  Dosis Dewasa       : ");
                                obatCair.setDosisObatDewasa(input.readLine());
                                boolean isValidInput;
                                do {
                                    try {
                                      System.out.print("  Harga Obat         : Rp.");
                                      obatCair.setHargaObat(Integer.parseInt(input.readLine()));
                                      isValidInput = true;
                                      do {
                                        try {
                                          System.out.print("  Stok Obat          : ");
                                          obatCair.setStokObat(Integer.parseInt(input.readLine()));
                                          isValidInput = true;
                                          System.out.println("""
                                          O========================================O
                                          |        Data Changed Successfully       |
                                          O========================================O""");
                                          System.out.print("Press [enter] to continue...");
                                          new java.util.Scanner(System.in).nextLine();
                                          pilihtipe = "0";
                                        } catch (NumberFormatException e) {
                                          System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                          isValidInput = false;
                                        }
                                      } while (!isValidInput); 
                                    } catch (NumberFormatException e) {
                                      System.out.println("""
                                        O========================================O
                                        |    Invalid input! Enter with number    |
                                        O========================================O""");
                                      isValidInput = false;
                                    }
                                } while (!isValidInput);
                                ditemukan = true;
                            } 
                        }
                        if (ditemukan == false){
                            System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    break;
                }

                case "2", "Obat Kapsul", "obat kapsul" -> {
                    if (kapsul.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                    } else {
                        System.out.println("""
                                        O========================================O
                                        |               Obat Kapsul              |
                                        O========================================O""");
                        for (int i = 0; i < kapsul.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            kapsul.get(i).tampil();
                        }
                        System.out.println("O========================================O");
                        System.out.print(" Input Code : ");
                        String no = input.readLine();
                        for (ObatKapsul obatKapsul : kapsul) {
                            if (obatKapsul.getKode().equals(no)) {
                                int kodeKapsul = kodeObatKapsul();
                                kode = "K" + kodeKapsul;
                                obatKapsul.setKode(kode);

                                System.out.print("  Nama Obat          : ");
                                obatKapsul.setNamaObat(input.readLine());
                                System.out.print("  Dosis Anak         : ");
                                obatKapsul.setDosisObatAnak(input.readLine());
                                System.out.print("  Dosis Dewasa       : ");
                                obatKapsul.setDosisObatDewasa(input.readLine());
                                boolean isValidInput;
                                do {
                                    try {
                                      System.out.print("  Harga Obat         : Rp.");
                                      obatKapsul.setHargaObat(Integer.parseInt(input.readLine()));
                                      isValidInput = true;
                                      do {
                                        try {
                                          System.out.print("  Stok Obat          : ");
                                          obatKapsul.setStokObat(Integer.parseInt(input.readLine()));
                                          isValidInput = true;
                                          System.out.println("""
                                          O========================================O
                                          |        Data Changed Successfully       |
                                          O========================================O""");
                                          System.out.print("Press [enter] to continue...");
                                          new java.util.Scanner(System.in).nextLine();
                                          pilihtipe = "0";
                                        } catch (NumberFormatException e) {
                                          System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                          isValidInput = false;
                                        }
                                      } while (!isValidInput); 
                                    } catch (NumberFormatException e) {
                                      System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                      isValidInput = false;
                                    }
                                } while (!isValidInput);
                                ditemukan = true;
                            }
                        }
                        if (ditemukan == false){
                            System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    break;
                }

                case "3", "Obat Pil", "obat pil" -> {
                    if (pil.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                    } else {
                        System.out.println("""
                        O========================================O
                        |                 Obat Pil               |
                        O========================================O""");
                        for (int i = 0; i < pil.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            pil.get(i).tampil();
                        }
                        System.out.println("O========================================O");
                        System.out.print(" Input Code : ");
                        String no = input.readLine();
                        for (ObatPil obatPil : pil) {
                            if (obatPil.getKode().equals(no)) {
                                int kodePil = kodeObatPil();
                                kode = "P" + kodePil;
                                obatPil.setKode(kode);

                                System.out.print("  Nama Obat          : ");
                                obatPil.setNamaObat(input.readLine());
                                System.out.print("  Dosis Anak         : ");
                                obatPil.setDosisObatAnak(input.readLine());
                                System.out.print("  Dosis Dewasa       : ");
                                obatPil.setDosisObatDewasa(input.readLine());
                                boolean isValidInput;
                                do {
                                    try {
                                      System.out.print("  Harga Obat         : Rp.");
                                      obatPil.setHargaObat(Integer.parseInt(input.readLine()));
                                      isValidInput = true;
                                      do {
                                        try {
                                          System.out.print("  Stok Obat          : ");
                                          obatPil.setStokObat(Integer.parseInt(input.readLine()));
                                          isValidInput = true;
                                          System.out.println("""
                                          O========================================O
                                          |        Data Changed Successfully       |
                                          O========================================O""");
                                          System.out.print("Press [enter] to continue...");
                                          new java.util.Scanner(System.in).nextLine();
                                          pilihtipe = "0";
                                        } catch (NumberFormatException e) {
                                          System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter with number    |
                                          O========================================O""");
                                          isValidInput = false;
                                        }
                                      } while (!isValidInput); 
                                    } catch (NumberFormatException e) {
                                      System.out.println("""
                                        O========================================O
                                        |    Invalid input! Enter with number    |
                                        O========================================O""");
                                      isValidInput = false;
                                    }
                                } while (!isValidInput);
                                ditemukan = true;
                            }
                        }
                        if (ditemukan == false){
                            System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    break;
                }

                case "0", "Exit", "exit" -> {
                    System.out.println("");
                    break;
                }

                default -> {
                    System.out.println("""
                    O========================================O
                    |              Menu Not Exist!!          |
                    O========================================O""");
                    break;
                }
            }
        }
    }

    public static void deleteData() throws IOException {
        String pilihtipe = "a";
        boolean ditemukan = false;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
            O========================================O
            |               Delete Obat              |
            O========================================O
            | 1. Obat Cair                           |
            | 2. Obat Kapsul                         |
            | 3. Obat Pil                            |
            | 0. Exit                                |
            O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");
            } else {
                switch (pilihtipe) {
                    case "1", "Obat Cair", "obat cair" -> {
                        if (cair.size() < 1) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        } else {
                            System.out.println("""
                            O========================================O
                            |                 Obat Cair              |
                            O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil();
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();
                            for (int i = 0; i < cair.size(); i++) {
                                if (cair.get(i).getKode().equals(no)) {
                                    cair.remove(i);
                                    System.out.println("""
                                    O========================================O
                                    |         Data Deleted Successfully      |
                                    O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                    ditemukan = true;
                                }
                            }
                            
                            if (ditemukan == false){
                                System.out.println("""
                                O========================================O
                                |             Data Not Exist             |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            }
                        }
                        break;
                    }

                    case "2", "Obat Kapsul", "obat kapsul" -> {
                        if (kapsul.size() < 1) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                            O========================================O
                            |                Obat Kapsul             |
                            O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil();
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();
                            for (int i = 0; i < kapsul.size(); i++) {
                                if (kapsul.get(i).getKode().equals(no)) {
                                    kapsul.remove(i);
                                    System.out.println("""
                                    O========================================O
                                    |         Data Deleted Successfully      |
                                    O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                    ditemukan = true;
                                }
                            }
                            
                            if (ditemukan == false){
                                System.out.println("""
                                O========================================O
                                |             Data Not Exist             |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            }
                        }
                        break;
                    }

                    case "3", "Obat Pil" -> {
                        if (pil.size() < 1) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                            O========================================O
                            |                 Obat Pil               |
                            O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil();
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();
                            for (int i = 0; i < pil.size(); i++) {
                                if (pil.get(i).getKode().equals(no)) {
                                    pil.remove(i);
                                    System.out.println("""
                                    O========================================O
                                    |         Data Deleted Successfully      |
                                    O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                    ditemukan = true;
                                }
                            }
                            
                            if (ditemukan == false){
                                System.out.println("""
                                O========================================O
                                |             Data Not Exist             |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            }
                        }
                        break;
                    }

                    case "0", "Exit", "exit" -> {
                        System.out.println("");
                        break;
                    }

                    default -> {
                        System.out.println("""
                        O========================================O
                        |              Menu Not Exist!!          |
                        O========================================O""");
                    break;
                    }
                }
            }
        }
    }

    public static void pembelian() throws IOException{
        String pilihtipe = "a";
        while (!"0".equals(pilihtipe)) {
            System.out.println("""
            O========================================O
            |                Beli Obat               |
            O========================================O
            | 1. Obat Cair                           |
            | 2. Obat Kapsul                         |
            | 3. Obat Pil                            |
            | 0. Exit                                |
            O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            if (null == pilihtipe) {
                System.out.println(" Wrong Input! ");
            } else switch (pilihtipe) {
                case "1", "Obat Cair" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                        O========================================O
                        |               Data Is Empty            |
                        O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                        O========================================O
                        |                 Obat Cair              |
                        O========================================O""");
                        for (int i = 0; i < cair.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            cair.get(i).tampil();
                        }
                        boolean isValidInput;
                        do{
                            try{
                                System.out.print("  Input Number : ");
                                int beli = Integer.parseInt(input.readLine());
                                if (beli < cair.size() || beli > 0){
                                    String nama = userAktif, kode = cair.get(beli-1).getKode(),
                                            Obat = cair.get(beli-1).getNamaObat(),
                                            DosisA = cair.get(beli-1).getDosisObatAnak(),
                                            DosisD = cair.get(beli-1).getDosisObatDewasa();
                                    int harga = cair.get(beli-1).getHargaObat();

                                    String ulang = "a";
                                    while(!"0".equals(ulang)){
                                        boolean isValidInput1;
                                        do{
                                            try{
                                                System.out.print("  Jumlah pembelian   : ");
                                                int jumlah = Integer.parseInt(input.readLine());
                                                if (jumlah > cair.get(beli-1).stokObat || jumlah <= 0){
                                                    System.out.println("""
                                                    O========================================O
                                                    |               Stok Kurang              |
                                                    O========================================O""");
                                                    ulang = "a";

                                                } else {
                                                    Transaksi buy = new Transaksi(kode, nama, 
                                                            Obat, DosisA, DosisD, 
                                                            "Proses", jumlah, harga);

                                                    dataTransaksi.add(buy);
                                                    ulang = "E";
                                                }
                                                isValidInput1 = true;
                                            } catch (NumberFormatException e) {
                                                System.out.println("""
                                                O========================================O
                                                |    Invalid input! Enter with number    |
                                                O========================================O""");
                                                isValidInput1 = false;
                                            }
                                        } while (!isValidInput1);
                                    }
                                }
                                isValidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                O========================================O
                                |    Invalid input! Enter with number    |
                                O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);
                        
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                case "2", "Obat Kapsul" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                        O========================================O
                        |               Data Is Empty            |
                        O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                        O========================================O
                        |               Obat Kapsul              |
                        O========================================O""");
                        for (int i = 0; i < kapsul.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            kapsul.get(i).tampil();
                        }
                        boolean isValidInput;
                        do{
                            try{
                                System.out.print("  Input Number : ");
                                int beli = Integer.parseInt(input.readLine());
                                if (beli < kapsul.size() || beli > 0){
                                    String nama = userAktif, kode = kapsul.get(beli-1).getKode(),
                                        Obat = kapsul.get(beli-1).getNamaObat(),
                                        DosisA = kapsul.get(beli-1).getDosisObatAnak(),
                                        DosisD = kapsul.get(beli-1).getDosisObatDewasa();
                                    int harga = kapsul.get(beli-1).getHargaObat();

                                    String ulang = "a";
                                    while(!"0".equals(ulang)){
                                        boolean isValidInput1;
                                        do{
                                            try{
                                                System.out.print("  Jumlah pembelian   : ");
                                                int jumlah = Integer.parseInt(input.readLine());
                                                if (jumlah > kapsul.get(beli-1).stokObat || jumlah <= 0){
                                                    System.out.println("""
                                                    O========================================O
                                                    |               Stok Kurang              |
                                                    O========================================O""");
                                                    ulang = "a";

                                                } else {
                                                    Transaksi buy = new Transaksi(kode, nama, 
                                                            Obat, DosisA, DosisD, 
                                                            "Proses", jumlah, harga);

                                                    dataTransaksi.add(buy);
                                                    ulang = "E";
                                                }
                                                isValidInput1 = true;
                                            } catch (NumberFormatException e) {
                                                System.out.println("""
                                                O========================================O
                                                |    Invalid input! Enter with number    |
                                                O========================================O""");
                                                isValidInput1 = false;
                                            }
                                        } while (!isValidInput1);
                                    }
                                }
                                isValidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                O========================================O
                                |    Invalid input! Enter with number    |
                                O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                
                case "3", "Obat Pil" -> {
                    if (cair.size() < 1) {
                        System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    } else {
                        System.out.println("""
                        O========================================O
                        |                 Obat Pil               |
                        O========================================O""");
                        for (int i = 0; i < pil.size(); i++) {
                            System.out.print("[" + (i + 1) + "] ");
                            pil.get(i).tampil();
                        }
                        boolean isValidInput;
                        do{
                            try{
                                System.out.print("  Input Number : ");
                                int beli = Integer.parseInt(input.readLine());
                                if (beli < pil.size() || beli > 0){
                                String nama = userAktif, kode = pil.get(beli-1).getKode(),
                                        Obat = pil.get(beli-1).getNamaObat(),
                                        DosisA = pil.get(beli-1).getDosisObatAnak(),
                                        DosisD = pil.get(beli-1).getDosisObatDewasa();
                                int harga = pil.get(beli-1).getHargaObat();

                                String ulang = "a";
                                while(!"0".equals(ulang)){

                                    boolean isValidInput1;
                                    do{
                                        try{
                                            System.out.print("  Jumlah pembelian   : ");
                                            int jumlah = Integer.parseInt(input.readLine());
                                            if (jumlah > pil.get(beli-1).stokObat || jumlah <= 0){
                                                System.out.println("""
                                                        O========================================O
                                                        |               Stok Kurang              |
                                                        O========================================O""");
                                                ulang = "a";
                                            } else {
                                                Transaksi buy = new Transaksi(kode, nama, 
                                                        Obat, DosisA, DosisD, 
                                                        "Proses", jumlah, harga);

                                                dataTransaksi.add(buy);
                                                ulang = "E";
                                            }
                                            isValidInput1 = true;
                                    }catch (NumberFormatException e) {
                                            System.out.println("""
                                              O========================================O
                                              |    Invalid input! Enter with number    |
                                              O========================================O""");
                                            isValidInput1 = false;
                                        }
                                    } while (!isValidInput1);
                                }
                            }
                            isValidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                  O========================================O
                                  |    Invalid input! Enter with number    |
                                  O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);        
                        System.out.println("O========================================O");
                        System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                    }
                }
                
                case "0", "Log Out", "log out"  -> {
                    System.out.println("");
                    break;
                }
                default -> {
                    System.out.println("""
                    O========================================O
                    |              Menu Not Exist!!          |
                    O========================================O""");
                    break;
                }
            }
        }
    }
    
    public static void pembayaran() throws IOException{
        int total = 0, cekKeranjang = 0;
        
        for (int i = 0; i < dataTransaksi.size(); i++){
            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                cekKeranjang++;
            }
        }
        
        if(cekKeranjang != 0){
            for(int i = 0; i < dataTransaksi.size();i++){
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                    if (dataTransaksi.get(i).getStatus() == "Proses"){
                        System.out.print("[" + (i+1) + "]");
                        dataTransaksi.get(i).tampil();
                        total += (dataTransaksi.get(i).getJumlahObat()*
                                dataTransaksi.get(i).getHargaObat());
                    }
                }
            }
            System.out.println("    Total Harga   : Rp." + total);
            int bayar = 1;
            while(bayar < total){
                System.out.println("    Input         : Rp.");
                bayar = Integer.parseInt(input.readLine());
                if (bayar < total){
                    System.out.println("""
                    O========================================O
                    |             Uang Anda Kurang!          |
                    O========================================O""");
                }
            }
            for (int i = 0; i < dataTransaksi.size(); i++){
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                    for (ObatCair obatC : cair) {
                        if (obatC.getKode().equals(dataTransaksi.get(i).getKodeObat())){
                            dataTransaksi.get(i).setStatus("Sukses");
                        }
                    }
                }
            }
            for (int i = 0; i < dataTransaksi.size(); i++){
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                    for (ObatKapsul obatK : kapsul) {
                        if (obatK.getKode().equals(dataTransaksi.get(i).getKodeObat())){
                            dataTransaksi.get(i).setStatus("Sukses");
                        }
                    }
                }
            }
            for (int i = 0; i < dataTransaksi.size(); i++){
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                    for (ObatPil obatP : pil) {
                        if (obatP.getKode().equals(dataTransaksi.get(i).getKodeObat())){
                            dataTransaksi.get(i).setStatus("Sukses");
                        }
                    }
                }
            }
            
            int kembali = bayar - total;
            System.out.println("O========================================O");
            System.out.println("    Kembalian     : Rp. " + kembali);
        } else {
            System.out.println("""
            O========================================O
            |        Pembelian Belum dilakukan       |
            O========================================O""");
        }
        
        System.out.print("Press [enter] to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    
    public static void keranjang(){
        int total = 0, cekKeranjang = 0;
        
        for (int i = 0; i < dataTransaksi.size(); i++){
            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                if (dataTransaksi.get(i).getStatus().equals("Proses")){
                    cekKeranjang++;
                }
            }
        }
        
        if(cekKeranjang != 0){
            for(int i = 0; i < dataTransaksi.size();i++){
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)){
                    if (dataTransaksi.get(i).getStatus() == "Proses"){
                        System.out.print("[" + (i+1) + "]");
                        dataTransaksi.get(i).tampil();
                        total += (dataTransaksi.get(i).getJumlahObat()*
                                dataTransaksi.get(i).getHargaObat());
                    }
                }
            }
            System.out.println("    Total Harga   : Rp." + total);
        } else {
            System.out.println("""
            O========================================O
            |        Pembelian Belum dilakukan       |
            O========================================O""");
        }
        
        System.out.print("Press [enter] to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    
    public static void menuUser() throws IOException {
        String pilih = "a";
        while (!"0".equals(pilih)) {
            System.out.println("""
                               O========================================O
                               |     Aplikasi Apotek Chemical Farma     |
                               O========================================O
                               | 1. Beli Obat                           |
                               | 2. Keranjang Belanja                   |
                               | 3. Pembayaran                          |
                               | 0. Log Out                             |
                               O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            if (null == pilih) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");

            } else switch (pilih) {
                case "1", "Beli Obat", "beli obat" -> {
                    pembelian();
                }
                case "2", "Keranjang Belanja", "keranjang belanja" -> {
                    keranjang();
                }
                case "3", "Pembayaran", "pembayaran"-> {
                    pembayaran();
                }
                case "0", "Log Out", "log out" -> {
                    System.out.println("O========================================O");
                    System.out.println("|         Thank you for visiting         |");
                    System.out.println("O========================================O");
                    System.out.print("Press [enter] to continue...");
                        new java.util.Scanner(System.in).nextLine();
                }

                default -> {
                    System.out.println("""
                    O========================================O
                    |              Menu Not Exist!!          |
                    O========================================O""");
                    System.out.print("Press [enter] to continue...");
                    new java.util.Scanner(System.in).nextLine();
                }
            }
        }
    }
}
