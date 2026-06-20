package teamassignment1;
import java.util.Scanner;

//NAMA ANGGOTA
//GILANG SURYA PRATAMA - 2902784896
//FAIZ AZHAR RISTYA NUGRAHA - 2902799903
//ADITYA SECHAN SAPUTRA - 2902795211
//ROBBY FAHSYA - 2902765505

public class Main {

    // =========================
    // CLASS LAGU
    // =========================
    static class Lagu {
        private String judul;
        private String artis;
        private double durasi; // disimpan dalam menit (desimal)

        // Constructor untuk inisialisasi objek Lagu
        public Lagu(String judul, String artis, double durasi) {
            this.judul = judul;
            this.artis = artis;
            this.durasi = durasi;
        }

        // Getter untuk mengambil data lagu
        public String getJudul() { return judul; }
        public String getArtis() { return artis; }
        public double getDurasi() { return durasi; }

        // Method untuk menampilkan informasi lagu ke console
        public void tampilkanInfo() {
            System.out.printf("Judul: %s | Artis: %s | Durasi: %.2f menit\n",
                    judul, artis, durasi);
        }
    }

    // =========================
    // CLASS USER
    // =========================
    static class User {
        protected String nama;

        // Constructor user
        public User(String nama) {
            this.nama = nama;
        }

        // Method untuk menampilkan jenis akses user
        public void tampilkanAkses() {
            System.out.println("User biasa");
        }

        // Method untuk menampilkan seluruh isi playlist
        public void tampilkanPlaylist(Lagu[] playlist) {
            // Cek apakah playlist kosong
            if (playlist.length == 0) {
                System.out.println("Playlist kosong.");
                return;
            }

            // Loop untuk menampilkan setiap lagu
            System.out.println("\n=== Playlist ===");
            for (Lagu lagu : playlist) {
                lagu.tampilkanInfo();
            }
        }
    }

    // =========================
    // CLASS ADMIN
    // =========================
    static class Admin extends User {

        // Constructor admin (inherit dari User)
        public Admin(String nama) {
            super(nama);
        }

        // Override method untuk menunjukkan hak akses admin
        @Override
        public void tampilkanAkses() {
            System.out.println("Admin: tambah & lihat lagu");
        }

        // Method untuk menambahkan lagu ke dalam array playlist
        public Lagu[] tambahLagu(Lagu[] playlist, Lagu laguBaru) {
            // Membuat array baru dengan ukuran +1
            Lagu[] baru = new Lagu[playlist.length + 1];

            // Menyalin data lama ke array baru
            for (int i = 0; i < playlist.length; i++) {
                baru[i] = playlist[i];
            }

            // Menambahkan lagu baru di posisi terakhir
            baru[playlist.length] = laguBaru;

            // Mengembalikan array baru
            return baru;
        }
    }

    // =========================
    // CLASS MEMBER
    // =========================
    static class Member extends User {

        // Constructor member
        public Member(String nama) {
            super(nama);
        }

        // Override method untuk menunjukkan hak akses member
        @Override
        public void tampilkanAkses() {
            System.out.println("Member: lihat, cari, hitung durasi");
        }

        // Method untuk mencari lagu berdasarkan judul
        public void cariLagu(Lagu[] playlist, String keyword) {
            boolean found = false;

            // Loop untuk mencari lagu yang sesuai
            for (Lagu lagu : playlist) {
                if (lagu.getJudul().equalsIgnoreCase(keyword)) {
                    lagu.tampilkanInfo();
                    found = true;
                }
            }

            // Jika tidak ditemukan
            if (!found) {
                System.out.println("Lagu tidak ditemukan.");
            }
        }

        // Method untuk menghitung rata-rata durasi lagu
        public void rataDurasi(Lagu[] playlist) {
            // Validasi jika playlist kosong
            if (playlist.length == 0) {
                System.out.println("Playlist kosong.");
                return;
            }

            double total = 0;

            // Menjumlahkan seluruh durasi lagu
            for (Lagu lagu : playlist) {
                total += lagu.getDurasi();
            }

            // Menghitung dan menampilkan rata-rata
            System.out.printf("Rata-rata durasi: %.2f menit\n",
                    total / playlist.length);
        }
    }

    // =========================
    // FUNCTION KONVERSI DURASI
    // =========================

    // Method untuk mengubah format mm:ss menjadi menit desimal
    public static double konversiDurasi(String input) {
        try {
            // Memisahkan menit dan detik
            String[] parts = input.split(":");

            int menit = Integer.parseInt(parts[0]);
            int detik = Integer.parseInt(parts[1]);

            // Konversi ke menit (desimal)
            return menit + (detik / 60.0);

        } catch (Exception e) {
            // Jika format salah
            System.out.println("Format salah! Gunakan mm:ss (contoh: 4:51)");
            return 0;
        }
    }

    // =========================
    // MAIN PROGRAM
    // =========================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Inisialisasi playlist kosong
        Lagu[] playlist = new Lagu[0];

        // Membuat objek admin dan member
        Admin admin = new Admin("Admin");
        Member member = new Member("User");

        int pilihan;

        // Loop menu utama
        do {
            System.out.println("\n=== MENU PLAYLIST ===");
            System.out.println("1. Tambah Lagu (Admin)");
            System.out.println("2. Tampilkan Playlist");
            System.out.println("3. Cari Lagu");
            System.out.println("4. Rata-rata Durasi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // membersihkan newline

            // Percabangan berdasarkan pilihan user
            switch (pilihan) {

                case 1:
                    // Input data lagu
                    System.out.print("Judul: ");
                    String judul = input.nextLine();

                    System.out.print("Artis: ");
                    String artis = input.nextLine();

                    System.out.print("Durasi (mm:ss): ");
                    String durasiInput = input.nextLine();

                    // Konversi durasi
                    double durasi = konversiDurasi(durasiInput);

                    // Membuat objek lagu baru dan menambah ke playlist
                    Lagu laguBaru = new Lagu(judul, artis, durasi);
                    playlist = admin.tambahLagu(playlist, laguBaru);

                    System.out.println("Lagu berhasil ditambahkan!");
                    break;

                case 2:
                    // Menampilkan playlist
                    admin.tampilkanPlaylist(playlist);
                    break;

                case 3:
                    // Mencari lagu berdasarkan judul
                    System.out.print("Masukkan judul lagu: ");
                    String cari = input.nextLine();
                    member.cariLagu(playlist, cari);
                    break;

                case 4:
                    // Menghitung rata-rata durasi
                    member.rataDurasi(playlist);
                    break;

                case 0:
                    // Keluar dari program
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);

        input.close();
    }
}