package org.genadidharma.jjjyuk.ui.destination.domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
    public static final String EXTRA_KEY_DESTINATION_CLOSE = "jam_tutup";
    public static final String EXTRA_KEY_DESTINATION_OPEN = "jam_buka";
    public static final String EXTRA_KEY_DESTINATION_LATITUDE = "lat";
    public static final String EXTRA_KEY_DESTINATION_LONGITUDE = "long";


    private RecyclerView rv_fav;
    private SwipeRefreshLayout srlRefresh;
    private List<Dest> dest = new ArrayList<>();
    AppDatabase database;
    private DestinationAdapterFav adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_favorite);

        initLayout();
        iniData();
        setupAdapter(dest);

        srlRefresh = findViewById(R.id.srl_refresh1);
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initLayout();
                iniData();
                setupAdapterOnRefresh(dest);
                srlRefresh.setRefreshing(false);
            }
        });
    }

    private void initLayout() {
        rv_fav = findViewById(R.id.rv_destination_fav);
    }

    private void iniData() {
        database = AppDatabase.getInstance(this);
        dest = database.destDao().getAll();
    }

    private void setupAdapter(List<Dest> list) {
        adapter = new DestinationAdapterFav(list,Destination_Favorite.this, (dest) -> {
            Intent intent = new Intent(Destination_Favorite.this, DestinationDetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, (dest.getJenis().equals(DestinationAdapter.KEY_IMAGE)) ? DestinationAdapter.LAYOUT_IMAGE : DestinationAdapter.LAYOUT_VIDEO);
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, dest.getNama_wisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, dest.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, dest.getDeskripsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, dest.getHarga_tiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, dest.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, dest.getJam_buka() + "-" + dest.getJam_tutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_RATING, dest.getRating());
            intent.putExtra(EXTRA_KEY_DESTINATION_STATUS, dest.getStatus());
            intent.putExtra(EXTRA_KEY_DESTINATION_REVIEW, dest.getUlasan());
            intent.putExtra(EXTRA_KEY_DESTINATION_IMAGE, dest.getFoto());
            intent.putExtra(EXTRA_KEY_DESTINATION_VIDEO, dest.getVideo());
            intent.putExtra(EXTRA_KEY_DESTINATION_DISTANCE, dest.getJarak());
            intent.putExtra(EXTRA_KEY_DESTINATION_CLOSE, dest.getJam_tutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_OPEN, dest.getJam_buka());
            intent.putExtra(EXTRA_KEY_DESTINATION_LATITUDE, dest.getLatitude());
            intent.putExtra(EXTRA_KEY_DESTINATION_LONGITUDE, dest.getLongitude());

            startActivity(intent);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_fav.setLayoutManager(layoutManager);
        rv_fav.addItemDecoration(new GridSpacingItemDecoration(
                1,
                getResources().getDimensionPixelSize(R.dimen.sm_margin_padding),
                true,
                0));

        rv_fav.setAdapter(adapter);
    }

    private void setupAdapterOnRefresh(List<Dest> list) {
        adapter = new DestinationAdapterFav(list,Destination_Favorite.this, (dest) -> {
            Intent intent = new Intent(Destination_Favorite.this, DestinationDetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, (dest.getJenis().equals(DestinationAdapter.KEY_IMAGE)) ? DestinationAdapter.LAYOUT_IMAGE : DestinationAdapter.LAYOUT_VIDEO);
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, dest.getNama_wisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, dest.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, dest.getDeskripsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, dest.getHarga_tiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, dest.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, dest.getJam_buka() + "-" + dest.getJam_tutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_RATING, dest.getRating());
            intent.putExtra(EXTRA_KEY_DESTINATION_STATUS, dest.getStatus());
            intent.putExtra(EXTRA_KEY_DESTINATION_REVIEW, dest.getUlasan());
            intent.putExtra(EXTRA_KEY_DESTINATION_IMAGE, dest.getFoto());
            intent.putExtra(EXTRA_KEY_DESTINATION_VIDEO, dest.getVideo());
            intent.putExtra(EXTRA_KEY_DESTINATION_DISTANCE, dest.getJarak());
            intent.putExtra(EXTRA_KEY_DESTINATION_CLOSE, dest.getJam_tutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_OPEN, dest.getJam_buka());
            intent.putExtra(EXTRA_KEY_DESTINATION_LATITUDE, dest.getLatitude());
            intent.putExtra(EXTRA_KEY_DESTINATION_LONGITUDE, dest.getLongitude());
            startActivity(intent);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_fav.setLayoutManager(layoutManager);
        rv_fav.addItemDecoration(new GridSpacingItemDecoration(
                2,
                getResources().getDimensionPixelSize(R.dimen.sm_margin_padding),
                true,
                5));
        rv_fav.setAdapter(adapter);
    }

}