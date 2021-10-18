package org.genadidharma.jjjyuk;

import java.util.ArrayList;
import java.util.List;
public class WisataData {

    private static final String[] foto = {
            "https://promoliburan.com/userfiles/uploads/batu-night-spectacular-bns-10.jpg",
            "https://images6.alphacoders.com/378/thumb-1920-378352.jpg",
            "https://i.pinimg.com/originals/0f/d0/89/0fd0890e0133d1a9413ac949c1928baa.jpg",
            "https://1.bp.blogspot.com/-NlJvqolPrx4/Wa1HiaclzDI/AAAAAAAAG_s/SghfTHgMsAsD5PsEZYPAmb_OnqSgY19BACLcBGAs/w1280-h720-p-k-no-nu/pantai%2B3%2Bwarna.jpg",
            "https://www.malang-guidance.com/wp-content/uploads/2012/06/Taman-Wisata-Selecta-Batu-Malang-MG.jpg"
    };

    private static final String[] nama_wisata={
            "Batu Night Spectacular",
            "Gunung Bromo",
            "Air Terjun Coban Pelangi",
            "Pantai 3 Warna",
            "Taman Rekreasi Selecta",
    };

    private static final String[] deskripsi={
            "BNS hanya beroperasi pada malam hari. BNS menggabungkan konsep pusat perbelanjaan, permainan, olahraga, dan hiburan di dalamnya. BNS tidak hanya dapat dinikmati kalangan muda, juga untuk segala usia. Karena letaknya di dataran tinggi, pengunjung akan disuguhi oleh pemandangan alam yang gemerlapan kota Malang. ",
            "Gunung Bromo memiliki ketinggian 2.392 Meter dari atas permukaan laut dan berada dalam empat lingkup kabupaten, yaitu Probolinggo, Pasuruan, Lumajang dan Kabupaten Malang . Gunung Bromo juga termasuk dalam satu kawasan Bromo Tengger Semeru National Park, dimana terdapat beberapa obyek wisata yang bisa dikunjungi seperti, Gunung Semeru, Gunung Tengger, Gunung Batok, beberapa danau dan Gunung Bromo sendiri.",
            "Dinamakan Kabut Pelangi atau Coban Pelangi karena di lokasi air terjun ini sering terlihat pelangi yang diakibatkan oleh pembiasan air dan cahaya matahari.diperkirakan tingginya sekira 100 meter dengan debit air yang cukup deras dan sangat deras di musim penghujan. Air Terjun Kabut Pelangi merupakan salah satu dari banyaknya air terjun yang terbentuk ditepian lembah-lembah curam Sungai Glidik di perbatasan Kabupaten Malang - Kabupaten Lumajang",
            "Pantai Tiga Warna memiliki alam yang sangat indah, hamparan pasirnya berwarna putih, warna air dekat pantainya hijau toska, sedangkan warna air laut lainnya berwarna biru. Di sekitar Pantai Tiga Warna tumbuh pepohonan yang cukup banyak, sehingga lokasinya teduh tidak terlalu panas.Disana anda juga bisa menikmati Snorkeling dan Diving , Hunting dan Spot Foto",
            "Taman Selecta mempunyai luas kurang lebih 18 hektar. Dimana, wisatawan akan dihibur dengan banyak sekali wahana. Seperti kolam renang, bunga, waterboom, playground dan masih banyak hal lagi yang bisa ditemukan disini. Kabar menariknya, keindahan selecta ini ternyata sudah sampai ke mancanegara. Di kolam renang yang disediakan ada perosotan yang cukup tinggi, yang mampu memacu adrenalain wisatawan. Disini pula wisatawan bisa melihat cinema 4 dimensi yang seru . "
    };

    private static final String[] jam ={
            "15.00 - 21.00",
            "24 Jam",
            "08.00 - 17.00",
            "07.00 - 14.00",
            "07.00 - 16.00",
    };

    private static final String[] alamat={
            "Jl. Hayam Wuruk, Oro-Oro Ombo, Kec.Batu, Kota Batu",
            "Taman Nasional Bromo Tengger Semeru",
            "Dusun Ngadas, Ngadas, Poncokusumo, Malang",
            "Jl. Sendang Biru, Tambakrejo, Sumbermanjing, Malang",
            "Jl. Raya Selecta No.1, Tulungrejo, Kec. Bumiaji, Kota Batu"
    };

    private static final String[] harga_tiket={
            "Rp 35 Ribu",
            "Rp 32 Ribu",
            "Rp 10 Ribu",
            "Rp 10 Ribu",
            "Rp 40 Ribu"
    };
    private static final double[] rating={
            4.5,
            4.6,
            4.1,
            4.7,
            4.5
    };
    private static final int[] ulasan ={
            200,
            467,
            123,
            221,
            156
    };
    private static final String[] status={
            "Buka",
            "Buka",
            "Buka",
            "Buka",
            "Buka"
    };
    private static final String[] protokol={
            "Pakai Masker , Jaga Jarak , Vaksin minimal Dosis Pertama",
            "Pakai Masker , Vaksin minimal Dosis Pertama",
            "Pakai Masker",
            "1 Rombongan maks.5 Orang ,Pakai Masker , Jaga Jarak",
            "Pakai Masker , Jaga Jarak , Vaksin minimal Dosis Pertama",
    };

    public static List<Wisata> getWisataData(){
        ArrayList<Wisata> listWisata =new ArrayList<>();
        for (int i = 0; i < foto.length; i++) {
            Wisata wisata = new Wisata();
            wisata.setFoto(foto[i]);
            wisata.setNama_wisata(nama_wisata[i]);
            wisata.setDeskipsi(deskripsi[i]);
            wisata.setJam(jam[i]);
            wisata.setAlamat(alamat[i]);
            wisata.setHarga_tiket(harga_tiket[i]);
            wisata.setRating(rating[i]);
            wisata.setUlasan(ulasan[i]);
            wisata.setStatus(status[i]);
            wisata.setProtokol(protokol[i]);

            listWisata.add(wisata);
        }
        return listWisata;
    }
}
