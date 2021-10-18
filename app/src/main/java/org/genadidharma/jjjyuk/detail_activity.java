package org.genadidharma.jjjyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class detail_activity extends AppCompatActivity {

    private String nama_wisata,foto,deskripsi,jam_buka,alamat,harga,status,protokol;
    private double rating;
    private int ulasan;

    private TextView tvNamaWisata , tvHarga, tvRating ,tvUlasan , tvAlamat , tvJamBuka , tvStatusBuka , tvProtokol , tvDeskripsi ;
    private ImageView imgFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        initLayout();

        getIntentExtra();

        Glide
                .with(getApplicationContext())
                .load(foto)
                .apply(new RequestOptions().override(200,200 ))
                .into(imgFoto);

        tvNamaWisata.setText(nama_wisata);
        tvUlasan.setText(ulasan);
        tvStatusBuka.setText(status);
        tvProtokol.setText(protokol);
        tvHarga.setText(harga);
        tvDeskripsi.setText(deskripsi);
        tvAlamat.setText(alamat);
        tvStatusBuka.setText(jam_buka);

        String ratingResult = new Double(rating).toString();
        tvRating.setText(ratingResult);

    }

    private void initLayout(){
        tvNamaWisata = findViewById(R.id.tvNamaWisata);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvHarga = findViewById(R.id.tvHarga);
        tvProtokol = findViewById(R.id.tvProtokol);
        tvJamBuka = findViewById(R.id.tvJamBuka);
        tvRating = findViewById(R.id.tvRating);
        tvStatusBuka = findViewById(R.id.tvStatusBuka);
        tvUlasan = findViewById(R.id.tvUlasan);
        imgFoto =findViewById(R.id.imgFoto);
    }

    private void getIntentExtra(){
        nama_wisata = getIntent().getStringExtra(MainActivity.EXTRA_KEY_NAMA_WISATA);
        alamat = getIntent().getStringExtra(MainActivity.EXTRA_KEY_ALAMAT);
        deskripsi = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESKRIPSI);
        harga = getIntent().getStringExtra(MainActivity.EXTRA_KEY_HARGA);
        protokol = getIntent().getStringExtra(MainActivity.EXTRA_KEY_PROTOKOL);
        jam_buka = getIntent().getStringExtra(MainActivity.EXTRA_KEY_JAM_BUKA);
        rating = getIntent().getDoubleExtra(MainActivity.EXTRA_KEY_RATING);
        status = getIntent().getStringExtra(MainActivity.EXTRA_KEY_STATUS);
        ulasan = getIntent().getIntExtra(MainActivity.EXTRA_KEY_ULASAN);
        foto = getIntent().getStringExtra(MainActivity.EXTRA_KEY_FOTO);
    }
}