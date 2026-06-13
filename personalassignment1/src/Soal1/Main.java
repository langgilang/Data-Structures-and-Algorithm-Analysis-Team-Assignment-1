package Soal1;

public class Main {

    // Class Mahasiswa
    static class Mahasiswa {
        String nama;
        String nim;
        String jurusan;
        double ipk;

        // Constructor
        Mahasiswa(String nama, String nim, String jurusan, double ipk) {
            this.nama = nama;
            this.nim = nim;
            this.jurusan = jurusan;
            this.ipk = ipk;
        }

        // Method tampilkan info
        void tampilkanInfo() {
            System.out.println("Nama     : " + nama);
            System.out.println("NIM      : " + nim);
            System.out.println("Jurusan  : " + jurusan);
            System.out.println("IPK      : " + ipk);
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {

        // Array langsung berisi object data mahasiswa
        Mahasiswa[] dataMahasiswa = {
            new Mahasiswa("Gilang", "2902784896", "Informatika", 3.5),
            new Mahasiswa("Budi", "2022000002", "Sistem Informasi", 2.8),
            new Mahasiswa("Siti", "2022000003", "Teknik Komputer", 3.2),
            new Mahasiswa("Andi", "2022000004", "Manajemen", 2.6),
            new Mahasiswa("Rina", "2022000005", "Akuntansi", 3.9)
        };

        // Loop untuk menampilkan data
        for (Mahasiswa mhs : dataMahasiswa) {
            mhs.tampilkanInfo();
        }
    }
}
