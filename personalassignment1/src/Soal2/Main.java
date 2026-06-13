package Soal2;

import java.util.Scanner;

public class Main {

    // Class Mahasiswa
    static class Mahasiswa {
        String nama;
        String nim;
        String jurusan;
        private double ipk; // enkapsulasi (private)

        // Constructor
        Mahasiswa(String nama, String nim, String jurusan, double ipk) {
            this.nama = nama;
            this.nim = nim;
            this.jurusan = jurusan;
            this.ipk = ipk;
        }

        // Getter IPK
        public double getIpk() {
            return ipk;
        }

        // Setter IPK
        public void setIpk(double ipk) {
            this.ipk = ipk;
        }

        // Method untuk update IPK
        public void updateIpk(double ipkBaru) {
            this.ipk = ipkBaru;
        }

        // Method cek kelulusan
        public String cekKelulusan() {
            if (ipk >= 3.00) {
                return "Lulus";
            } else {
                return "Belum Lulus";
            }
        }

        // Method tampilkan info
        public void tampilkanInfo() {
            System.out.println("Nama    : " + nama);
            System.out.println("NIM     : " + nim);
            System.out.println("Jurusan : " + jurusan);
            System.out.println("IPK     : " + ipk);
            System.out.println("Status  : " + cekKelulusan());
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Array mahasiswa
        Mahasiswa[] dataMahasiswa = {
            new Mahasiswa("Gilang", "2902784896", "Informatika", 3.5),
            new Mahasiswa("Budi Santoso", "2440002", "Sistem Informasi", 2.8),
            new Mahasiswa("Siti Aminah", "2022000003", "Teknik Komputer", 3.2),
            new Mahasiswa("Andi Wijaya", "2022000004", "Manajemen", 2.6),
            new Mahasiswa("Rina Putri", "2022000005", "Akuntansi", 3.9)
        };

        // Input NIM
        System.out.print("Masukkan NIM mahasiswa yang ingin diupdate: ");
        String cariNim = input.nextLine();

        boolean ditemukan = false;

        // Cari mahasiswa berdasarkan NIM
        for (Mahasiswa mhs : dataMahasiswa) {
            if (mhs.nim.equals(cariNim)) {
                ditemukan = true;

                // Input IPK baru
                System.out.print("Masukkan IPK baru: ");
                double ipkBaru = input.nextDouble();

                // Update IPK
                mhs.updateIpk(ipkBaru);

                System.out.println("Data berhasil diperbarui!\n");

                // Tampilkan data terbaru
                System.out.println("=== Data Mahasiswa ===");
                mhs.tampilkanInfo();
                break;
            }
        }

        // Jika tidak ditemukan
        if (!ditemukan) {
            System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
        }

        input.close();
    }
}
