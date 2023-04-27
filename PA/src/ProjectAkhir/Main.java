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
            add(new User("muchlis", "069", "Jl. Jalan", "muchlis@gmail.com", 18));
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
        while (!"0".equals(pilih)) {
            System.out.println("""
                O========================================O
                |     Aplikasi Apotek Chemical Farma     |
                O========================================O
                | [1] Registration                       |
                | [2] Login                              |
                | [0] Exit                               |
                O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            String pilihan = pilih.toUpperCase();
            if (null == pilihan) {
                System.out.println("""
                    O========================================O
                    |              Invalid choice            | 
                    O========================================O
                    """);
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {

                    case "1", "REGISTRATION" -> {
                        regis();
                        break;
                    }
                    case "2", "LOGIN" -> {
                        if (login() == true) {
                            System.out.println("");
                        } else {
                            System.out.println("""
                                O========================================O
                                |             Account Not Found          | 
                                O========================================O
                                """);
                        }
                        break;
                    }

                    case "0" -> {
                        System.out.println("");
                        System.out.println("O========================================O");
                        System.out.println("|         Thank You For Visiting         |");
                        System.out.println("O========================================O");
                        System.exit(0);
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
            O========================================O
            """);
        System.out.print("  Username : ");
        user = input.readLine();
        System.out.print("  Password : ");
        pass = input.readLine();
        for (User cekUser : dataUser) {
            if (cekUser.getUsername().equals(user) && cekUser.getPassword().equals(pass)) {
                if (dataUser.get(0).getUsername().equals(user) && dataUser.get(0).getPassword().equals(pass)) {
                    System.out.println("\nO========================================O");
                    System.out.println("Welcome " + user + "!");
                    userAktif = user;
                    menuAdmin();
                    return true;
                } else {
                    System.out.println("\nO========================================O");
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
                if (age < 1) {
                    throw new NumberFormatException();
                } else {
                    isValidInput = true;
                    String ditemukan = "tidak";
                    for (int i = 0; i < dataUser.size(); i++) {

                        // jika username sudah digunakan maka tidak bisa regis
                        if (dataUser.get(i).getUsername().equals(user)) {
                            ditemukan = "ditemukan";
                        }
                    }

                    if (ditemukan.equals("ditemukan")) {
                        System.out.println("O========================================O");
                        System.out.println("Username has been used!");
                    } else {
                        System.out.println("""
                      O========================================O
                      |         Successful Registration        |
                      O========================================O
                      """);
                        User addUser = new User(user, pass, alamat, email, age);
                        dataUser.add(addUser);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("""
                O========================================O
                |    Invalid input! Enter with number    |
                O========================================O""");
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
                | [1] Add Data                           |
                | [2] Read Data                          |
                | [3] Update Data                        |
                | [4] Delete Data                        |
                | [0] Log Out                            |
                O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            String pilihan = pilih.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {

                    case "1", "ADD DATA" ->
                        createDataObat();

                    case "2", "READ DATA" ->
                        readData();

                    case "3", "UPDATE DATA" ->
                        updateData();

                    case "4", "DELETE DATA" ->
                        deleteData();

                    case "0" -> {
                        System.out.println("");
                        System.out.println("O========================================O");
                        System.out.println("|         Thank You For Visiting         |");
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
                                | [1] Liquid                             |
                                | [2] Capsule                            |
                                | [3] Pill                               |
                                | [0] Exit                               |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodeCair = kodeObatCair();
                        kode = "" + kodeCair;

                        System.out.print("  Name               : ");
                        nama = input.readLine();
                        System.out.print("  Child Dose         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Adult Dose         : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                                System.out.print("  Price              : Rp.");
                                harga = Integer.parseInt(input.readLine());
                                if (harga < 1) {
                                    throw new NumberFormatException();
                                } else {
                                    isValidInput = true;
                                }
                                do {
                                    try {
                                        System.out.print("  Stock               : ");
                                        stok = Integer.parseInt(input.readLine());
                                        if (stok < 1) {
                                            throw new NumberFormatException();
                                        } else {
                                            isValidInput = true;
                                        }
                                        ObatCair addCair = new ObatCair(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                        cair.add(addCair);
                                        System.out.println("""
                                          O========================================O
                                          |         Data Successfully Added        |""");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("""
                                          O========================================O
                                          |    Invalid Input! Enter With Number    |""");
                                        isValidInput = false;
                                    }
                                } while (!isValidInput);
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter With Number    |
                                          O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);

                    }

                    case "2", "CAPSULE" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodeKapsul = kodeObatKapsul();
                        kode = "" + kodeKapsul;

                        System.out.print("  Name               : ");
                        nama = input.readLine();
                        System.out.print("  Child Dose         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Adult Dose         : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                                System.out.print("  Price              : Rp.");
                                harga = Integer.parseInt(input.readLine());
                                if (harga < 1) {
                                    throw new NumberFormatException();
                                } else {
                                    isValidInput = true;
                                }
                                do {
                                    try {
                                    System.out.print("  Stock              : ");
                                        stok = Integer.parseInt(input.readLine());
                                        if (stok < 1) {
                                            throw new NumberFormatException();
                                        } else {
                                            isValidInput = true;
                                        }
                                        ObatKapsul addKapsul = new ObatKapsul(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                        kapsul.add(addKapsul);
                                        System.out.println("""
                                          O========================================O
                                          |         Data Successfully Added        |""");
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter With Number    |
                                          O========================================O""");
                                        isValidInput = false;
                                    }
                                } while (!isValidInput);
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                          O========================================O
                                          |    Invalid input! Enter With Number    |
                                          O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);

                    }

                    case "3", "PILL" -> {
                        System.out.println("""
                               O========================================O
                               |                 Add Obat               |
                               O========================================O""");
                        int kodePil = kodeObatPil();
                        kode = "" + kodePil;

                        System.out.print("  Name               : ");
                        nama = input.readLine();
                        System.out.print("  Child Dose         : ");
                        dosisAnak = input.readLine();
                        System.out.print("  Adult Dose         : ");
                        dosisDewasa = input.readLine();
                        boolean isValidInput;
                        do {
                            try {
                                System.out.print("  Price              : Rp.");
                                harga = Integer.parseInt(input.readLine());
                                if (harga < 1) {
                                    throw new NumberFormatException();
                                } else {
                                    isValidInput = true;
                                }
                                do {
                                    try {
                                        System.out.print("  Stock              : ");
                                        stok = Integer.parseInt(input.readLine());
                                        if (stok < 1) {
                                            throw new NumberFormatException();
                                        } else {
                                            isValidInput = true;
                                        }
                                        ObatPil addPil = new ObatPil(kode, nama, dosisAnak, dosisDewasa, harga, stok);
                                        pil.add(addPil);
                                        System.out.println("""
                                          O========================================O
                                          |         Data Successfully Added        |""");
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

                    case "0" -> {
                        System.out.println("");
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

    public static void readData() throws IOException {
        String pilihtipe = "a";

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                                O========================================O
                                |                Read Data               |
                                O========================================O
                                | [1] Read Data User                     |
                                | [2] Read Data Obat                     |
                                | [3] Read Data Transaksi                |
                                | [0] Exit                               |
                                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "READ DATA USER" ->
                        readDataUser();

                    case "2", "READ DATA OBAT" ->
                        readDataObat();

                    case "3", "READ DATA TRANSAKSI" ->
                        readDataTransaksi();

                    case "0" -> {
                        System.out.println("");
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
                System.out.println("  Age                : " + dataUser.get(i).getAge());
                System.out.println("------------------------------------------");
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
                | 1. Liquid                              |
                | 2. Capsule                             |
                | 3. Pill                                |
                | 0. Exit                                |
                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
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
                                |                  Liquid                |
                                O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil(true);
                            }

                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "2", "CAPSULE" -> {
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
                                |                 Capsule                |
                                O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "3", "PILL" -> {
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
                                |                  Pill                  |
                                O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "0" -> {
                        System.out.println("");
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

        public static void readDataObatUser() throws IOException {

        String pilihtipe = "a";
        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                O========================================O
                |              Read Data Obat            |
                O========================================O
                | 1. Liquid                              |
                | 2. Capsule                             |
                | 3. Pill                                |
                | 0. Exit                                |
                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
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
                                |                  Liquid                |
                                O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil(false);
                            }

                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "2", "CAPSULE" -> {
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
                                |                 Capsule                |
                                O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil(false);
                            }
                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "3", "PILL" -> {
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
                                |                  Pill                  |
                                O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil(false);
                            }
                            System.out.println("O========================================O");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }
                    case "0" -> {
                        System.out.println("");
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
    
    public static void readDataTransaksi() {
        if (dataTransaksi.size() < 1) {
            System.out.println("""
                O========================================O
                |               Data Is Empty            |
                O========================================O""");
            System.out.print("Press [enter] to continue...");
            new java.util.Scanner(System.in).nextLine();
        } else {
            for (int i = 0; i < dataTransaksi.size(); i++) {
                if (dataTransaksi.get(i).getStatus().equals("Sukses")) {
                    dataTransaksi.get(i).tampil();
                }
            }
        }
    }
    
    

    public static void updateData() throws IOException {
        String pilihtipe = "a", kode;
        int stok, harga;
        boolean ditemukan = false;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
                O========================================O
                |               Update Obat              |
                O========================================O
                | [1] Liquid                             |
                | [2] Capsule                            |
                | [3] Pill                               |
                | [0] Exit                               |
                O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
                        if (cair.isEmpty()) {
                            System.out.println("""
                                O========================================O
                                |               Data Is Empty            |
                                O========================================O""");
                        } else {
                            System.out.println("""
                                O========================================O
                                |                  Liquid                |
                                O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
                                for (ObatCair obatCair : cair) {
                                    if (obatCair.getKode().equals(no)) {
                                        int kodeCair = kodeObatCair();
                                        kode = "C" + kodeCair;
                                        obatCair.setKode(kode);

                                        System.out.print("  Name               : ");
                                        obatCair.setNamaObat(input.readLine());
                                        System.out.print("  Child Dose         : ");
                                        obatCair.setDosisObatAnak(input.readLine());
                                        System.out.print("  Adult Dose       : ");
                                        obatCair.setDosisObatDewasa(input.readLine());
                                        boolean isValidInput;
                                        do {
                                            try {
                                                System.out.print("  Price              : Rp.");
                                                harga = Integer.parseInt(input.readLine());
                                                if (harga < 1) {
                                                    throw new NumberFormatException();
                                                } else {
                                                    isValidInput = true;
                                                }
                                                do {
                                                    try {
                                                        System.out.print("  Stock              : ");
                                                        stok = Integer.parseInt(input.readLine());
                                                        if (stok < 1) {
                                                            throw new NumberFormatException();
                                                        } else {
                                                            isValidInput = true;
                                                        }

                                                        obatCair.setHargaObat(harga);
                                                        obatCair.setStokObat(stok);

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
                                if (ditemukan == false) {
                                    System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "2", "CAPSULE" -> {
                        if (kapsul.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                                        O========================================O
                                        |                 Capsule                |
                                        O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
                                for (ObatKapsul obatKapsul : kapsul) {
                                    if (obatKapsul.getKode().equals(no)) {
                                        int kodeKapsul = kodeObatKapsul();
                                        kode = "P" + kodeKapsul;
                                        obatKapsul.setKode(kode);

                                        System.out.print("  Name               : ");
                                        obatKapsul.setNamaObat(input.readLine());
                                        System.out.print("  Child Dose         : ");
                                        obatKapsul.setDosisObatAnak(input.readLine());
                                        System.out.print("  Adult Dose         : ");
                                        obatKapsul.setDosisObatDewasa(input.readLine());
                                        boolean isValidInput;
                                        do {
                                            try {
                                                System.out.print("  Price              : Rp.");
                                                harga = Integer.parseInt(input.readLine());
                                                if (harga < 1) {
                                                    throw new NumberFormatException();
                                                } else {
                                                    isValidInput = true;
                                                }
                                                do {
                                                    try {
                                                        System.out.print("  Stock              : ");
                                                        stok = Integer.parseInt(input.readLine());
                                                        if (stok < 1) {
                                                            throw new NumberFormatException();
                                                        } else {
                                                            isValidInput = true;
                                                        }

                                                        obatKapsul.setHargaObat(harga);
                                                        obatKapsul.setStokObat(stok);
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
                                if (ditemukan == false) {
                                    System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "3", "PILL" -> {
                        if (pil.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                        O========================================O
                        |                  Pill                  |
                        O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print(" Input Code : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
                                for (ObatPil obatPil : pil) {
                                    if (obatPil.getKode().equals(no)) {
                                        int kodePil = kodeObatPil();
                                        kode = "P" + kodePil;
                                        obatPil.setKode(kode);

                                        System.out.print("  Name               : ");
                                        obatPil.setNamaObat(input.readLine());
                                        System.out.print("  Child Dose         : ");
                                        obatPil.setDosisObatAnak(input.readLine());
                                        System.out.print("  Adult Dose       : ");
                                        obatPil.setDosisObatDewasa(input.readLine());
                                        boolean isValidInput;
                                        do {
                                            try {
                                                System.out.print("  Price              : Rp.");
                                                harga = Integer.parseInt(input.readLine());
                                                if (harga < 1) {
                                                    throw new NumberFormatException();
                                                } else {
                                                    isValidInput = true;
                                                }
                                                do {
                                                    try {
                                                        System.out.print("  Stock              : ");
                                                        stok = Integer.parseInt(input.readLine());
                                                        if (stok < 1) {
                                                            throw new NumberFormatException();
                                                        } else {
                                                            isValidInput = true;
                                                        }

                                                        obatPil.setHargaObat(harga);
                                                        obatPil.setStokObat(stok);

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
                                if (ditemukan == false) {
                                    System.out.println("""
                            O========================================O
                            |             Data Not Exist             |
                            O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "0" -> {
                        System.out.println("");
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

    public static void deleteData() throws IOException {
        String pilihtipe = "a";
        boolean ditemukan = false;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
            O========================================O
            |               Delete Obat              |
            O========================================O
            | [1] Liquid                             |
            | [2] Capsule                            |
            | [3] Pill                               |
            | [0] Exit                               |
            O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
                        if (cair.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        } else {
                            System.out.println("""
                            O========================================O
                            |                  Liquid                |
                            O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print("    Input Code    : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
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

                                if (ditemukan == false) {
                                    System.out.println("""
                                    O========================================O
                                    |             Data Not Exist             |
                                    O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "2", "CAPSULE" -> {
                        if (kapsul.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                            O========================================O
                            |                 Capsule                |
                            O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print("    Input Code    : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
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

                                if (ditemukan == false) {
                                    System.out.println("""
                                    O========================================O
                                    |             Data Not Exist             |
                                    O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "3", "PILL" -> {
                        if (pil.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                        } else {
                            System.out.println("""
                            O========================================O
                            |                  Pill                  |
                            O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil(true);
                            }
                            System.out.println("O========================================O");
                            System.out.print("    Input Code    : ");
                            String no = input.readLine();

                            boolean dataDipesan = false;
                            for (int i = 0; i < dataTransaksi.size(); i++) {
                                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                                    if (dataTransaksi.get(i).getKodeObat().equals(no)) {
                                        dataDipesan = true;
                                    }
                                }
                            }

                            if (dataDipesan) {
                                System.out.println("""
                                O========================================O
                                |            Data Is On Order!           |
                                |            Unable To Change            |
                                O========================================O""");
                                System.out.print("Press [enter] to continue...");
                                new java.util.Scanner(System.in).nextLine();
                            } else {
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

                                if (ditemukan == false) {
                                    System.out.println("""
                                O========================================O
                                |             Data Not Exist             |
                                O========================================O""");
                                    System.out.print("Press [enter] to continue...");
                                    new java.util.Scanner(System.in).nextLine();
                                }
                            }
                        }
                        break;
                    }

                    case "0" -> {
                        System.out.println("");
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

    public static void pembelian() throws IOException {
        String pilihtipe = "a";
        int jumlah_data = 0;

        while (!"0".equals(pilihtipe)) {
            System.out.println("""
            O========================================O
            |                   Buy                  |
            O========================================O
            | [1] Liquid                              |
            | [2] Capsule                             |
            | [3] Pill                                |
            | [0] Exit                                |
            O========================================O""");
            System.out.print("  Input choice : ");
            pilihtipe = input.readLine();
            String pilihan = pilihtipe.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid choice             |");
                System.out.println("O========================================O");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            } else {
                switch (pilihan) {
                    case "1", "LIQUID" -> {
                        if (cair.isEmpty()) {
                            System.out.println("""
                        O========================================O
                        |               Data Is Empty            |
                        O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        } else {
                            System.out.println("""
                        O========================================O
                        |                  Liquid                |
                        O========================================O""");
                            for (int i = 0; i < cair.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                cair.get(i).tampil(false);
                            }
                            boolean isValidInput;
                            do {
                                try {
                                    System.out.print("    Input Number  : ");
                                    int beli = Integer.parseInt(input.readLine());
                                    System.out.println("O========================================O");
                                    if ((beli > cair.size()) || beli <= 0) {
                                        System.out.println("""
                                    |             Data Not Exist             |
                                    O========================================O""");
                                    } else if (cair.get(beli - 1).getStokObat() == 0) {
                                        System.out.println("""
                                        |               Out Of Stock             |
                                        O========================================O""");
                                    } else if (beli <= cair.size()) {
                                        String nama = userAktif, kode = cair.get(beli - 1).getKode(),
                                                Obat = cair.get(beli - 1).getNamaObat(),
                                                DosisA = cair.get(beli - 1).getDosisObatAnak(),
                                                DosisD = cair.get(beli - 1).getDosisObatDewasa();
                                        int harga = cair.get(beli - 1).getHargaObat();

                                        String ulang = "a";
                                        while (!"0".equals(ulang)) {
                                            boolean isValidInput1;
                                            do {
                                                try {
                                                    System.out.print("  Total Buy : ");
                                                    int jumlah = Integer.parseInt(input.readLine());
                                                    System.out.println("O========================================O");
                                                    if (jumlah > cair.get(beli - 1).getStokObat()) {
                                                        System.out.println("""
                                                    |              Out Of Stock              |
                                                    O========================================O""");
                                                        ulang = "a";
                                                    } else if (jumlah <= 0) {
                                                        System.out.println("""
                                                  O========================================O
                                                  |             Invalid number             |
                                                  O========================================O""");
                                                        ulang = "a";
                                                    } else {
                                                        System.out.println("""
                                                  |             Added To Cart!             |
                                                  O========================================O""");
                                                        int stok = cair.get(beli - 1).getStokObat() - jumlah;
                                                        cair.get(beli - 1).setStokObat(stok);
                                                        Transaksi buy = new Transaksi(kode, nama,
                                                                Obat, DosisA, DosisD,
                                                                "Proses", jumlah, harga);

                                                        dataTransaksi.add(buy);
                                                        ulang = "0";
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
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        }
                    }

                    case "2", "CAPSULE" -> {
                        if (cair.isEmpty()) {
                            System.out.println("""
                        O========================================O
                        |               Data Is Empty            |
                        O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        } else {
                            System.out.println("""
                        O========================================O
                        |                 Capsule                |
                        O========================================O""");
                            for (int i = 0; i < kapsul.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                kapsul.get(i).tampil(false);
                                jumlah_data++;
                            }
                            boolean isValidInput;
                            do {
                                try {
                                    System.out.print("    Input Number  : ");
                                    int beli = Integer.parseInt(input.readLine());
                                    System.out.println("O========================================O");
                                    if ((beli > jumlah_data) || (beli <= 0)) {
                                        System.out.println("""
                                    |             Data Not Exist             |
                                    O========================================O""");
                                    } else if (kapsul.get(beli - 1).getStokObat() == 0) {
                                        System.out.println("""
                                        |                Out Of Stock              |
                                        O========================================O""");
                                    } else if (beli <= kapsul.size()) {
                                        String nama = userAktif, kode = kapsul.get(beli - 1).getKode(),
                                                Obat = kapsul.get(beli - 1).getNamaObat(),
                                                DosisA = kapsul.get(beli - 1).getDosisObatAnak(),
                                                DosisD = kapsul.get(beli - 1).getDosisObatDewasa();
                                        int harga = kapsul.get(beli - 1).getHargaObat();

                                        String ulang = "a";
                                        while (!"0".equals(ulang)) {
                                            boolean isValidInput1;
                                            do {
                                                try {
                                                    System.out.print("  Total Buy : ");
                                                    int jumlah = Integer.parseInt(input.readLine());
                                                    System.out.println("O========================================O");
                                                    if (jumlah > kapsul.get(beli - 1).getStokObat()) {
                                                        System.out.println("""
                                                    |               Out Of Stock             |
                                                    O========================================O""");
                                                        ulang = "a";
                                                    } else if (jumlah <= 0) {
                                                        System.out.println("""
                                                  O========================================O
                                                  |             Invalid number             |
                                                  O========================================O""");
                                                        ulang = "a";
                                                    } else {
                                                        System.out.println("""
                                                  |              Added To Cart!            |
                                                  O========================================O""");
                                                        int stok = kapsul.get(beli - 1).getStokObat() - jumlah;
                                                        kapsul.get(beli - 1).setStokObat(stok);
                                                        Transaksi buy = new Transaksi(kode, nama,
                                                                Obat, DosisA, DosisD,
                                                                "Proses", jumlah, harga);

                                                        dataTransaksi.add(buy);
                                                        ulang = "0";
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
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                            jumlah_data = 0;
                        }
                    }

                    case "3", "PILL" -> {
                        if (cair.isEmpty()) {
                            System.out.println("""
                            O========================================O
                            |               Data Is Empty            |
                            O========================================O""");
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                        } else {
                            System.out.println("""
                        O========================================O
                        |                  Pill                  |
                        O========================================O""");
                            for (int i = 0; i < pil.size(); i++) {
                                System.out.print("[" + (i + 1) + "] ");
                                pil.get(i).tampil(false);
                                jumlah_data++;
                            }
                            boolean isValidInput;
                            do {
                                try {
                                    System.out.print("    Input Number  : ");
                                    int beli = Integer.parseInt(input.readLine());
                                    System.out.println("O========================================O");
                                    if ((beli > jumlah_data) || (beli <= 0)) {
                                        System.out.println("""
                                    |             Data Not Exist             |
                                    O========================================O""");
                                    } else if (pil.get(beli - 1).getStokObat() == 0) {
                                        System.out.println("""
                                        |               Out Of Stock             |
                                        O========================================O""");
                                    } else if (beli <= pil.size()) {
                                        String nama = userAktif, kode = pil.get(beli - 1).getKode(),
                                                Obat = pil.get(beli - 1).getNamaObat(),
                                                DosisA = pil.get(beli - 1).getDosisObatAnak(),
                                                DosisD = pil.get(beli - 1).getDosisObatDewasa();
                                        int harga = pil.get(beli - 1).getHargaObat();

                                        String ulang = "a";
                                        while (!"0".equals(ulang)) {

                                            boolean isValidInput1;
                                            do {
                                                try {
                                                    System.out.print("  Total Buy : ");
                                                    int jumlah = Integer.parseInt(input.readLine());
                                                    System.out.println("O========================================O");
                                                    if (jumlah > pil.get(beli - 1).stokObat) {
                                                        System.out.println("""
                                                            |               Out Of Stock             |
                                                            O========================================O""");
                                                        ulang = "a";
                                                    } else if (jumlah <= 0) {
                                                        System.out.println("""
                                                  O========================================O
                                                  |             Invalid number             |
                                                  O========================================O""");
                                                        ulang = "a";
                                                    } else {
                                                        System.out.println("""
                                                  |              Added To Cart!            |
                                                  O========================================O""");
                                                        int stok = pil.get(beli - 1).getStokObat() - jumlah;
                                                        pil.get(beli - 1).setStokObat(stok);
                                                        Transaksi buy = new Transaksi(kode, nama,
                                                                Obat, DosisA, DosisD,
                                                                "Proses", jumlah, harga);

                                                        dataTransaksi.add(buy);
                                                        ulang = "0";
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
                            System.out.print("Press [enter] to continue...");
                            new java.util.Scanner(System.in).nextLine();
                            jumlah_data = 0;
                        }
                    }

                    case "0" -> {
                        System.out.println("");
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

    public static void keranjang() throws IOException {
        int total = 0, cekKeranjang = 0;
        System.out.println("O========================================O");

        for (int i = 0; i < dataTransaksi.size(); i++) {
            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)) {
                if (dataTransaksi.get(i).getStatus().equals("Proses")) {
                    cekKeranjang++;
                }
            }
        }

        if (cekKeranjang != 0) {
            for (int i = 0; i < dataTransaksi.size(); i++) {
                if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)) {
                    if (dataTransaksi.get(i).getStatus() == "Proses") {
                        System.out.print("[" + (i + 1) + "]");
                        dataTransaksi.get(i).tampil();
                        total += (dataTransaksi.get(i).getJumlahObat()
                                * dataTransaksi.get(i).getHargaObat());
                    }
                }
            }

            System.out.println("    Total Price   : Rp." + total);
            System.out.println("O========================================O");
            System.out.println("| [1] Payment                            |");                         
            System.out.println("| [2] Clear Cart                         |");
            System.out.println("| [0] Exit                               |");
            System.out.println("O========================================O");
            System.out.print("    Input         : ");
            String pilihan = input.readLine();
            String pilih = pilihan.toUpperCase();
            System.out.println("O========================================O");
            switch (pilih) {
                case "1", "PAYMENT" -> {
                    int bayar = 1;
                    while (bayar < total) {
                        boolean isValidInput;
                        do {
                            try {
                                System.out.println("    Total Price   : Rp." + total);
                                System.out.print("    Input         : Rp.");
                                bayar = Integer.parseInt(input.readLine());
                                isValidInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("""
                                O========================================O
                                |    Invalid input! Enter with number    |
                                O========================================O""");
                                isValidInput = false;
                            }
                        } while (!isValidInput);
                        if (bayar < total) {
                            System.out.println("""
                            O========================================O
                            |             Not Enough Money!          |
                            O========================================O""");
                        }
                    }

                    if (bayar >= total) {
                        // mengubah status transaksi menjadi sukses
                        for (int i = 0; i < dataTransaksi.size(); i++) {
                            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)
                                    && dataTransaksi.get(i).getStatus().equals("Proses")) {
                                dataTransaksi.get(i).setStatus("Sukses");
                            }
                        }

                        int kembali = bayar - total;
                        System.out.println("O========================================O");
                        System.out.println("  |     Change     : Rp. " + kembali);
                        System.out.println("O========================================O");
                    }
                }

                case "2", "CLEAR CART" -> {

                    // mengembalikan stok obat cair
                    for (int i = 0; i < dataTransaksi.size(); i++) {
                        for (int j = 0; j < cair.size(); j++) {
                            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)
                                    && dataTransaksi.get(i).getStatus().equals("Proses")) {
                                if (dataTransaksi.get(i).getKodeObat().equals(cair.get(j).getKode())) {
                                    int stok = cair.get(j).getStokObat() + dataTransaksi.get(i).getJumlahObat();
                                    cair.get(j).setStokObat(stok);
                                }
                            }
                        }
                    }

                    // mengembalikan stok obat kapsul
                    for (int i = 0; i < dataTransaksi.size(); i++) {
                        for (int j = 0; j < kapsul.size(); j++) {
                            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)
                                    && dataTransaksi.get(i).getStatus().equals("Proses")) {
                                if (dataTransaksi.get(i).getKodeObat().equals(kapsul.get(j).getKode())) {
                                    int stok = kapsul.get(j).getStokObat() + dataTransaksi.get(i).getJumlahObat();
                                    kapsul.get(j).setStokObat(stok);
                                }
                            }
                        }
                    }

                    // mengembalikan stok obat pil
                    for (int i = 0; i < dataTransaksi.size(); i++) {
                        for (int j = 0; j < pil.size(); j++) {
                            if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)
                                    && dataTransaksi.get(i).getStatus().equals("Proses")) {
                                if (dataTransaksi.get(i).getKodeObat().equals(pil.get(j).getKode())) {
                                    int stok = pil.get(j).getStokObat() + dataTransaksi.get(i).getJumlahObat();
                                    pil.get(j).setStokObat(stok);
                                }
                            }
                        }
                    }

                    // menghapus isi keranjang belanja
                    for (int i = 0; i < dataTransaksi.size(); i++) {
                        if (dataTransaksi.get(i).getNamaCustomer().equals(userAktif)
                                && dataTransaksi.get(i).getStatus().equals("Proses")) {
                            dataTransaksi.remove(i);
                            i--;
                        }
                    }

                    System.out.println("|          Cart Has Been Cleared!        |");
                    System.out.println("O========================================O");
                }

                case "0" -> {
                    System.out.println("");
                }

                default -> {
                    System.out.println("""
                    |              Menu Not Exist!!          |
                    O========================================O""");
                    System.out.print("Press [enter] to continue...");
                    new java.util.Scanner(System.in).nextLine();
                }
            }

        } else {
            System.out.println("""
            |              No Purchased              |
            O========================================O""");
        }

        System.out.print("Press [enter] to continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static void updateProfil() throws IOException {
        System.out.println("------------------------------------------");
        for (int i = 1; i < dataUser.size(); i++) {
            if (dataUser.get(i).getUsername().equals(userAktif)) {
                System.out.println("  Username           : " + dataUser.get(i).getUsername());
                System.out.println("  Password           : " + dataUser.get(i).getPassword());
                System.out.println("  Address            : " + dataUser.get(i).getAddress());
                System.out.println("  Email              : " + dataUser.get(i).getEmail());
                System.out.println("  Age                : " + dataUser.get(i).getAge());
            }
        }

        System.out.println("------------------------------------------");
        System.out.println("""
                           | [1] Update Profile
                           | [0] Exit""");
        System.out.println("------------------------------------------");
        System.out.print("    Input : ");

        String pilih = input.readLine();
        String pilihan = pilih.toUpperCase();
        System.out.println("O========================================O");
        switch (pilihan) {
                case "1", "UPDATE PROFILE" -> {
                System.out.print("  Username           : ");
                String userBaru = input.readLine();
                System.out.print("  Password           : ");
                String passBaru = input.readLine();
                System.out.print("  Address            : ");
                String alamatBaru = input.readLine();
                System.out.print("  Email              : ");
                String emailBaru = input.readLine();
                boolean isValidInput = false;
                do {
                    try {
                        System.out.print("  Age                : ");
                        int umurBaru = Integer.parseInt(input.readLine());
                        if (umurBaru < 1) {
                            throw new NumberFormatException();
                        } else {
                            isValidInput = true;
                            System.out.println("""
                                          O========================================O
                                          |        Data Changed Successfully       |
                                          O========================================O""");
                        }
                        for (int i = 1; i < dataUser.size(); i++) {
                            if (dataUser.get(i).getUsername().equals(userAktif)) {
                                dataUser.get(i).setUsername(userBaru);
                                dataUser.get(i).setPassword(passBaru);
                                dataUser.get(i).setAddress(alamatBaru);
                                dataUser.get(i).setEmail(emailBaru);
                                dataUser.get(i).setAge(umurBaru);
                            }

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("""
                                O========================================O
                                |    Invalid input! Enter with number    |
                                O========================================O""");
                        isValidInput = false;
                    }
                } while (!isValidInput);

            }

            case "0" -> {
                System.out.println("");
            }

            default -> {
                System.out.println("""
                |              Menu Not Exist!!          |
                O========================================O""");
                System.out.print("Press [enter] to continue...");
                new java.util.Scanner(System.in).nextLine();
            }
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
                               | [1] Profile                            |
                               | [2] Product                            |
                               | [3] Buy                                |
                               | [4] Cart                               |
                               | [0] Log Out                            |
                               O========================================O""");
            System.out.print("  Input choice : ");
            pilih = input.readLine();
            String pilihan = pilih.toUpperCase();
            if (null == pilihan) {
                System.out.println("O========================================O");
                System.out.println("|             Invalid Choice             |");
                System.out.println("O========================================O");

            } else {
                switch (pilihan) {
                    case "1", "PROFIL" -> {
                        updateProfil();
                    }
                    
                    case "2", "PRODUCT" -> {
                        readDataObatUser();
                    }

                    case "3", "BUY" -> {
                        pembelian();
                    }

                    case "4", "CART" -> {
                        keranjang();
                    }

                    case "0" -> {
                        System.out.println("");
                        System.out.println("O========================================O");
                        System.out.println("|         Thank You For Visiting         |");
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
}
