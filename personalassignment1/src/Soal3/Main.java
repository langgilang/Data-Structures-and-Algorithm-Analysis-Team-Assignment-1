package Soal3;

import java.util.Scanner;

public class Main {

    // Class Mahasiswa
    static class Mahasiswa {
        String nama;
        String nim;
        String jurusan;
        private double ipk; // enkapsulasi

        // Constructor
        Mahasiswa(String nama, String nim, String jurusan, double ipk) {
            this.nama = nama;
            this.nim = nim;
            this.jurusan = jurusan;
            this.ipk = ipk;
        }

        // Getter & Setter
        public double getIpk() {
            return ipk;
        }

        public void setIpk(double ipk) {
            this.ipk = ipk;
        }

        // Method update IPK
        public void updateIpk(double ipkBaru) {
            this.ipk = ipkBaru;
        }

        // Method cek kelulusan
        public String cekKelulusan() {
            return (ipk >= 3.00) ? "Lulus" : "Belum Lulus";
        }

        // Method hitung predikat akademik
        public String hitungPredikat() {
            if (ipk >= 3.75) {
                return "Dengan Pujian";
            } else if (ipk >= 3.50) {
                return "Sangat Memuaskan";
            } else if (ipk >= 3.00) {
                return "Memuaskan";
            } else {
                return "Perlu Perbaikan";
            }
        }

        // Method tampilkan info lengkap
        public void tampilkanInfo() {
            System.out.println("Nama     : " + nama);
            System.out.println("NIM      : " + nim);
            System.out.println("Jurusan  : " + jurusan);
            System.out.println("IPK      : " + ipk);
            System.out.println("Status   : " + cekKelulusan());
            System.out.println("Predikat : " + hitungPredikat());
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Array mahasiswa
        Mahasiswa[] dataMahasiswa = {
            new Mahasiswa("Gilang", "2902784896", "Informatika", 3.5),
            new Mahasiswa("Budi Santoso", "2440002", "Sistem Informasi", 2.8),
            new Mahasiswa("Citra Lestari", "2440003", "Teknik Informatika", 3.9),
            new Mahasiswa("Andi Wijaya", "2022000004", "Manajemen", 2.6),
            new Mahasiswa("Rina Putri", "2022000005", "Akuntansi", 3.9)
        };

        // Input NIM
        System.out.print("Masukkan NIM mahasiswa yang ingin diupdate: ");
        String cariNim = input.nextLine();

        boolean ditemukan = false;

        // Cari dan update
        for (Mahasiswa mhs : dataMahasiswa) {
            if (mhs.nim.equals(cariNim)) {
                ditemukan = true;

                System.out.print("Masukkan IPK baru: ");
                double ipkBaru = input.nextDouble();

                mhs.updateIpk(ipkBaru);

                System.out.println("Data berhasil diperbarui!\n");

                System.out.println("=== Data Mahasiswa ===");
                mhs.tampilkanInfo();
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Mahasiswa tidak ditemukan.");
        }

        input.close();
    }
}
