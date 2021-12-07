package org.genadidharma.jjjyuk.ui.destination.domain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.genadidharma.jjjyuk.MainActivity;
import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.db.AppDatabaseDest;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.util.GridSpacingItemDecoration;
import org.genadidharma.jjjyuk.util.ListSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class DestinationFavoriteActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_DESTINATION_ID = "id";
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
    public static final String EXTRA_KEY_DESTINATION_FAVORITE = "favorite";

    private RecyclerView rv_fav;
    private SwipeRefreshLayout srlRefresh;
    private LinearLayout llError;
    private Button btnBack;
    private List<Destination> dest = new ArrayList<>();
    private AppDatabaseDest database;
    private DestinationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_favorite);

        database = AppDatabaseDest.getInstance(this);
        dest = new ArrayList<>();

        initLayout();
        setupAdapter(dest);
        iniData();

        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iniData();
                adapter.updateData(dest);
                srlRefresh.setRefreshing(false);
            }
        });
        btnBack.setOnClickListener((view -> {
            finish();
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniData();
        adapter.updateData(dest);
    }

    private void initLayout() {
        rv_fav = findViewById(R.id.rv_destination_fav);
        srlRefresh = findViewById(R.id.srl_refresh);
        llError = findViewById(R.id.ll_error);
        btnBack = findViewById(R.id.btn_back);
    }

    private void iniData() {
        dest = database.destDao().getAll();

        if (dest.size() == 0) {
            llError.setVisibility(View.VISIBLE);
            rv_fav.setVisibility(View.GONE);
        } else {
            llError.setVisibility(View.GONE);
            rv_fav.setVisibility(View.VISIBLE);
        }
    }

    private void setupAdapter(List<Destination> list) {
        adapter = new DestinationAdapter(list, (dest, position) -> {
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
            intent.putExtra(EXTRA_KEY_DESTINATION_ID, dest.getId());
            intent.putExtra(EXTRA_KEY_DESTINATION_FAVORITE, dest.isFavorite());

            startActivity(intent);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_fav.setLayoutManager(layoutManager);
        rv_fav.addItemDecoration(new ListSpacingItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.lg_margin_padding),
                getResources().getDimensionPixelSize(R.dimen.lg_margin_padding)
        ));

        rv_fav.setAdapter(adapter);
    }

    private void sendFavoriteStatus() {
        Intent intent = new Intent();
        setResult(MainActivity.RESULT_FAVORITE_ACTIVITY, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        sendFavoriteStatus();
        super.onBackPressed();
    }
}