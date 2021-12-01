package org.genadidharma.jjjyuk.ui.destination.domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.AppDatabaseDest;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapterFav;
import org.genadidharma.jjjyuk.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class DestinationFavoriteActivity extends AppCompatActivity {

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
    private List<Destination> dest = new ArrayList<>();
    AppDatabaseDest database;
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
                adapter.updateData(dest);
                srlRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLayout();
        iniData();
        adapter.updateData(dest);
    }

    private void initLayout() {
        rv_fav = findViewById(R.id.rv_destination_fav);
    }

    private void iniData() {
        database = AppDatabaseDest.getInstance(this);
        dest = database.destDao().getAll();
    }

    private void setupAdapter(List<Destination> list) {
        adapter = new DestinationAdapterFav(list, DestinationFavoriteActivity.this, (dest) -> {
            Intent intent = new Intent(DestinationFavoriteActivity.this, DestinationDetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, (dest.getJenis().equals(DestinationAdapter.KEY_IMAGE)) ? DestinationAdapter.LAYOUT_IMAGE : DestinationAdapter.LAYOUT_VIDEO);
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, dest.getNamaWisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, dest.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, dest.getDeskripsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, dest.getHargaTiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, dest.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, dest.getJamBuka() + "-" + dest.getJamTutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_RATING, dest.getRating());
            intent.putExtra(EXTRA_KEY_DESTINATION_STATUS, dest.getStatus());
            intent.putExtra(EXTRA_KEY_DESTINATION_REVIEW, dest.getUlasan());
            intent.putExtra(EXTRA_KEY_DESTINATION_IMAGE, dest.getFoto());
            intent.putExtra(EXTRA_KEY_DESTINATION_VIDEO, dest.getVideo());
            intent.putExtra(EXTRA_KEY_DESTINATION_DISTANCE, dest.getJarak());
            intent.putExtra(EXTRA_KEY_DESTINATION_CLOSE, dest.getJamTutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_OPEN, dest.getJamBuka());
            intent.putExtra(EXTRA_KEY_DESTINATION_LATITUDE, dest.getLat());
            intent.putExtra(EXTRA_KEY_DESTINATION_LONGITUDE, dest.getJsonMemberLong());

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

}