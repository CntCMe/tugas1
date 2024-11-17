import java.util.Scanner;
// 1. Import Statement
// Mengimpor kelas Scanner dari paket java.util untuk mengambil input dari pengguna.
//---------------------------------------------------------------------------------------

// 2. Kelas Menu
// Atribut:
//  nama: Menyimpan nama menu.
//  harga: Menyimpan harga menu.
//  kategori: Menyimpan kategori menu (Makanan atau Minuman).
// Konstruktor: Menginisialisasi atribut dengan nilai yang diberikan saat objek Menu dibuat.
class Menu {
    String nama;
    double harga;
    String kategori;

    Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}
//--------------------------------------------------------------------

//3. Kelas Main
// Array menuList: Berisi daftar objek Menu yang mewakili menu yang tersedia di restoran, dengan harga dan kategori masing-masing.
public class Main {
    private static Menu[] menuList = {
        new Menu("Nasi Padang", 30000, "Makanan"),
        new Menu("Ayam Penyet", 25000, "Makanan"),
        new Menu("Sate Ayam", 20000, "Makanan"),
        new Menu("Mie Goreng", 15000, "Makanan"),
        new Menu("Teh Manis", 10000, "Minuman"),
        new Menu("Kopi Hitam", 15000, "Minuman"),
        new Menu("Es Jeruk", 12000, "Minuman"),
        new Menu("Coca Cola", 10000, "Minuman")
    };
//---------------------------------------------------------------------------------

// 4. Metode main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pesanan = new String[4];
        int[] jumlah = new int[4];
        double totalMakanan = 0, totalMinuman = 0;
        double totalBiaya = 0;
        String diskonInfo = "";
        // Inisialisasi:
        // scanner: Untuk membaca input dari pengguna.
        // pesanan: Array untuk menyimpan nama menu yang dipesan.
        // jumlah: Array untuk menyimpan jumlah setiap menu yang dipesan.
        // totalMakanan dan totalMinuman: Menyimpan total biaya makanan dan minuman.
        // totalBiaya: Menyimpan total keseluruhan biaya.
        // diskonInfo: Menyimpan informasi tentang diskon yang didapat.
        
        System.out.println("Daftar Menu Restoran:");
        tampilkanMenu();
            // Menampilkan daftar menu dengan memanggil metode tampilkanMenu().
//-----------------------------------------------------------
        // 5. Menerima Pesanan
        // Menggunakan loop untuk menerima pesanan hingga 4 item. Pengguna dapat mengetik "selesai" untuk mengakhiri proses pesanan.
        for (int i = 0; i < 4; i++) {
            System.out.print("Masukkan pesanan (format: Nama Menu = Jumlah) atau ketik 'selesai': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            String[] parts = input.split("=");
            if (parts.length == 2) {
                String namaMenu = parts[0].trim();
                int qty = Integer.parseInt(parts[1].trim());
                pesanan[i] = namaMenu;
                jumlah[i] = qty;
        // Memisahkan input menjadi nama menu dan jumlah.
        // Memasukkan data ke dalam array pesanan dan jumlah.
    // ----------------------------------------------------------------------- 
        // 6. Menghitung total biaya berdasarkan kategori
        // Looping melalui daftar menu untuk menghitung total biaya makanan dan minuman berdasarkan pesanan yang diterima.
                for (Menu menu : menuList) {
                    if (menu.nama.equalsIgnoreCase(namaMenu)) {
                        if (menu.kategori.equalsIgnoreCase("Makanan")) {
                            totalMakanan += menu.harga * qty;
                        } else if (menu.kategori.equalsIgnoreCase("Minuman")) {
                            totalMinuman += menu.harga * qty;
                        }
                    }
                }
            } else {
                System.out.println("Format input salah. Silakan coba lagi.");
            }
        }
//------------------------------------------------------------------------
        // 7. Menghitung total biaya dan pajak
        totalBiaya = totalMakanan + totalMinuman;
        double pajak = totalBiaya * 0.10;
        double biayaPelayanan = 20000;
        totalBiaya += pajak + biayaPelayanan;
        // Menghitung total biaya termasuk pajak (10%) dan biaya pelayanan tetap.
        //---------------------------------------------------------
        
// 8. Diskon dan penawaran khusus
if (totalMakanan > 100000) {
    totalBiaya *= 0.90; // Diskon 10%
    diskonInfo += "Diskon 10% untuk total belanja di atas Rp. 100.000\n";
}

if (totalBiaya > 100000 && totalMakanan > 0 && totalMinuman > 0) {
    // Jika total belanja di atas 100.000 dan ada makanan serta minuman
    totalBiaya -= getMinumanTermurah(); // Minuman termurah gratis
    diskonInfo += "Mendapatkan 1 minuman gratis (minuman termurah)\n";
} else if (totalBiaya > 50000 && totalBiaya < 100000 && totalMinuman > 0) {
    // Jika total belanja antara 50.000 dan 100.000 dan ada minuman
    totalBiaya -= getMinumanTermurah(); // Minuman termurah gratis
    diskonInfo += "Mendapatkan 1 minuman gratis (minuman termurah)\n";
}



        // 9. mencetak struk
        cetakStruk(pesanan, jumlah, totalMakanan, totalMinuman, pajak, biayaPelayanan, totalBiaya, diskonInfo);
        scanner.close();
    }
        // Memanggil metode cetakStruk untuk menampilkan rincian pesanan dan total biaya. Menutup scanner setelah selesai.
        //------------------------------------------------------------

        // 10. Metode tampilkanMenu
    private static void tampilkanMenu() {
        System.out.println("Makanan:");
        for (Menu menu : menuList) {
            if (menu.kategori.equalsIgnoreCase("Makanan")) {
                System.out.println(menu.nama + " - Rp. " + menu.harga);
            }
        }
        System.out.println("Minuman:");
        for (Menu menu : menuList) {
            if (menu.kategori.equalsIgnoreCase("Minuman")) {
                System.out.println(menu.nama + " - Rp. " + menu.harga);
            }
        }
    }
    // Menampilkan menu makanan dan minuman dari daftar menu.

    //--------------------------------------------

    // 11. Metode getMinumanTermurah
    private static double getMinumanTermurah() {
        double min = Double.MAX_VALUE;
        for (Menu menu : menuList) {
            if (menu.kategori.equalsIgnoreCase("Minuman") && menu.harga < min) {
                min = menu.harga;
            }
        }
        return min;
    }
// Mengembalikan harga dari minuman termurah yang ada di daftar menu.

// 12. Metode cetakStruk
    private static void cetakStruk(String[] pesanan, int[] jumlah, double totalMakanan, double totalMinuman, double pajak, double biayaPelayanan, double totalBiaya, String diskonInfo) {
        System.out.println("\nStruk Pesanan:");
        for (int i = 0; i < pesanan.length; i++) {
            if (pesanan[i] != null) {
                for (Menu menu : menuList) {
                    if (menu.nama.equalsIgnoreCase(pesanan[i])) {
                        double totalHargaItem = menu.harga * jumlah[i];
                        System.out.println(pesanan[i] + " x " + jumlah[i] + " = Rp. " + totalHargaItem);
                    }
                }
            }
        }
        System.out.println("\nTotal Makanan: Rp. " + totalMakanan);
        System.out.println("Total Minuman: Rp. " + totalMinuman);
        System.out.println("Pajak (10%): Rp. " + pajak);
        System.out.println("Biaya Pelayanan: Rp. " + biayaPelayanan);
        
        if (!diskonInfo.isEmpty()) {
            System.out.println(diskonInfo);
        }
        
        System.out.println("Total Biaya: Rp. " + totalBiaya);
    }
}
// Mencetak struk pesanan dengan rincian setiap item yang dipesan, total biaya makanan, minuman, pajak, biaya pelayanan, dan diskon (jika ada).