package org.genadidharma.jjjyuk.ui.destination.domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import org.genadidharma.jjjyuk.MainActivity;
import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.db.AppDatabase;
import org.genadidharma.jjjyuk.db.Dest;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapterFav;
import org.genadidharma.jjjyuk.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Destination_Favorite extends AppCompatActivity {

    public static final String EXTRA_KEY_DESTINATION_TYPE = "type";
    public static final String EXTRA_KEY_DESTINATION_NAME = "dest";
    public static final String EXTRA_KEY_DESTINATION_ADDRESS = "address";
    public static final String EXTRA_KEY_DESTINATION_DESCRIPTION = "description";
    public static final String EXTRA_KEY_DESTINATION_PRICE = "price";
    public static final String EXTRA_KEY_DESTINATION_PROTOCOL = "protocol";
    public static final String EXTRA_KEY_DESTINATION_TIME = "time";
    public static final String EXTRA_KEY_DESTINATION_RATING = "rating";
    public static final String EXTRA_KEY_DESTINATION_STATUS = "status";
    public static final String EXTRA_KEY_DESTINATION_REVIEW = "review";
    public static final String EXTRA_KEY_DESTINATION_IMAGE = "image";
    public static final String EXTRA_KEY_DESTINATION_VIDEO = "video";
    public static final String EXTRA_KEY_DESTINATION_DISTANCE = "distance";

    private RecyclerView rv_fav;
    private List<Dest> dest = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    AppDatabase database;
    private DestinationAdapterFav adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_favorite);

        initLayout();
        iniData();
        setupAdapter(dest);
    }

    private void initLayout() {
        rv_fav = findViewById(R.id.rv_destination_fav);
    }

    private void iniData() {
        database = AppDatabase.getInstance(this);
        dest = database.destDao().getAll();
    }

    private void setupAdapter(List<Dest> list) {
        adapter = new DestinationAdapterFav(list, (destination) -> {
            Intent intent = new Intent(Destination_Favorite.this, DestinationDetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, (destination.getJenis().equals(DestinationAdapter.KEY_IMAGE)) ? DestinationAdapter.LAYOUT_IMAGE : DestinationAdapter.LAYOUT_VIDEO);
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, destination.getNama_wisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, destination.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, destination.getDeskripsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, destination.getHarga_tiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, destination.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, destination.getJam_buka() + "-" + destination.getJam_tutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_RATING, destination.getRating());
            intent.putExtra(EXTRA_KEY_DESTINATION_STATUS, destination.getStatus());
            intent.putExtra(EXTRA_KEY_DESTINATION_REVIEW, destination.getUlasan());
            intent.putExtra(EXTRA_KEY_DESTINATION_IMAGE, destination.getFoto());
            intent.putExtra(EXTRA_KEY_DESTINATION_VIDEO, destination.getVideo());
            intent.putExtra(EXTRA_KEY_DESTINATION_DISTANCE, destination.getJarak());
            startActivity(intent);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_fav.setLayoutManager(layoutManager);
        rv_fav.addItemDecoration(new GridSpacingItemDecoration(
                2,
                getResources().getDimensionPixelSize(R.dimen.sm_margin_padding),
                true,
                0));

        rv_fav.setAdapter(adapter);
    }
}