package teamassignment1;

import java.util.Scanner;

public class Main {

    // =========================
    // CLASS LAGU
    // =========================
    static class Lagu {
        private String judul;
        private String artis;
        private double durasi;

        public Lagu(String judul, String artis, double durasi) {
            this.judul = judul;
            this.artis = artis;
            this.durasi = durasi;
        }

        public String getJudul() { return judul; }
        public String getArtis() { return artis; }
        public double getDurasi() { return durasi; }

        public void tampilkanInfo() {
            System.out.printf("Judul: %s | Artis: %s | Durasi: %.2f menit\n",
                    judul, artis, durasi);
        }
    }

    // =========================
    // CLASS USER (PARENT)
    // =========================
    static class User {
        protected String nama;

        public User(String nama) {
            this.nama = nama;
        }

        public void tampilkanAkses() {
            System.out.println("User biasa");
        }

        public void tampilkanPlaylist(Lagu[] playlist) {
            if (playlist.length == 0) {
                System.out.println("Playlist kosong.");
                return;
            }

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

        public Admin(String nama) {
            super(nama);
        }

        @Override
        public void tampilkanAkses() {
            System.out.println("Admin: tambah & lihat lagu");
        }

        public Lagu[] tambahLagu(Lagu[] playlist, Lagu laguBaru) {
            Lagu[] baru = new Lagu[playlist.length + 1];

            for (int i = 0; i < playlist.length; i++) {
                baru[i] = playlist[i];
            }

            baru[playlist.length] = laguBaru;
            return baru;
        }
    }

    // =========================
    // CLASS MEMBER
    // =========================
    static class Member extends User {

        public Member(String nama) {
            super(nama);
        }

        @Override
        public void tampilkanAkses() {
            System.out.println("Member: lihat, cari, hitung durasi");
        }

        public void cariLagu(Lagu[] playlist, String keyword) {
            boolean found = false;

            for (Lagu lagu : playlist) {
                if (lagu.getJudul().equalsIgnoreCase(keyword)) {
                    lagu.tampilkanInfo();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Lagu tidak ditemukan.");
            }
        }

        public void rataDurasi(Lagu[] playlist) {
            if (playlist.length == 0) {
                System.out.println("Playlist kosong.");
                return;
            }

            double total = 0;
            for (Lagu lagu : playlist) {
                total += lagu.getDurasi();
            }

            System.out.printf("Rata-rata durasi: %.2f menit\n",
                    total / playlist.length);
        }
    }

    // =========================
    // MAIN PROGRAM (INTERAKTIF)
    // =========================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Lagu[] playlist = new Lagu[0]; // awal kosong
        Admin admin = new Admin("Admin");
        Member member = new Member("User");

        int pilihan;

        do {
            System.out.println("\n=== MENU PLAYLIST ===");
            System.out.println("1. Tambah Lagu (Admin)");
            System.out.println("2. Tampilkan Playlist");
            System.out.println("3. Cari Lagu");
            System.out.println("4. Rata-rata Durasi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {

                case 1:
                    // Input lagu baru
                    System.out.print("Judul: ");
                    String judul = input.nextLine();

                    System.out.print("Artis: ");
                    String artis = input.nextLine();

                    System.out.print("Durasi (menit): ");
                    double durasi = input.nextDouble();

                    Lagu laguBaru = new Lagu(judul, artis, durasi);
                    playlist = admin.tambahLagu(playlist, laguBaru);

                    System.out.println("Lagu berhasil ditambahkan!");
                    break;

                case 2:
                    admin.tampilkanPlaylist(playlist);
                    break;

                case 3:
                    System.out.print("Masukkan judul lagu: ");
                    String cari = input.nextLine();
                    member.cariLagu(playlist, cari);
                    break;

                case 4:
                    member.rataDurasi(playlist);
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);

        input.close();
    }
}