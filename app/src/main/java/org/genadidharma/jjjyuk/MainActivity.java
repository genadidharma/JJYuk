package org.genadidharma.jjjyuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.genadidharma.jjjyuk.data.model.DestinationResponse;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.networks.APIBuilder;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.ui.destination.domain.DestinationDetailActivity;
import org.genadidharma.jjjyuk.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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

    private SwipeRefreshLayout srlRefresh;
    private RecyclerView rvDestination;
    private LinearLayout llError;
    private Button btnRefresh;
    private DestinationAdapter destinationAdapter;
    private List<Destination> destinationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        destinationList = new ArrayList<>();

        initLayout();
        setupAdapter(destinationList);
        doAsync();
        onRefresh();
    }

    private void initLayout() {
        srlRefresh = findViewById(R.id.srl_refresh);
        rvDestination = findViewById(R.id.rv_destination);
        llError = findViewById(R.id.ll_error);
        btnRefresh = findViewById(R.id.btn_refresh);
    }

    private void doAsync() {
        srlRefresh.setRefreshing(true);
        APIBuilder apiBuilder = new APIBuilder();
        Call<DestinationResponse> call = apiBuilder.service.getDestinations();
        call.enqueue(new Callback<DestinationResponse>() {
            @Override
            public void onResponse(@NonNull Call<DestinationResponse> call, @NonNull Response<DestinationResponse> response) {
                destinationList = response.body() != null ? response.body().getDestinations() : null;
                destinationAdapter.updateData(destinationList);

                rvDestination.setVisibility(View.VISIBLE);
                llError.setVisibility(View.GONE);
                srlRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<DestinationResponse> call, @NonNull Throwable t) {
                Log.e("Error Message", t.getMessage());
                rvDestination.setVisibility(View.GONE);
                llError.setVisibility(View.VISIBLE);
                srlRefresh.setRefreshing(false);
            }
        });
    }

    private void setupAdapter(List<Destination> list) {
        if (list.size() == 0) {
            llError.setVisibility(View.VISIBLE);
            rvDestination.setVisibility(View.GONE);
        }

        destinationAdapter = new DestinationAdapter(list, (destination) -> {
            Intent intent = new Intent(MainActivity.this, DestinationDetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, (destination.getJenis().equals(DestinationAdapter.KEY_IMAGE)) ? DestinationAdapter.LAYOUT_IMAGE : DestinationAdapter.LAYOUT_VIDEO);
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, destination.getNamaWisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, destination.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, destination.getDeskripsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, destination.getHargaTiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, destination.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, destination.getJamBuka() + "-" + destination.getJamTutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_RATING, destination.getRating());
            intent.putExtra(EXTRA_KEY_DESTINATION_STATUS, destination.getStatus());
            intent.putExtra(EXTRA_KEY_DESTINATION_REVIEW, destination.getUlasan());
            intent.putExtra(EXTRA_KEY_DESTINATION_IMAGE, destination.getFoto());
            intent.putExtra(EXTRA_KEY_DESTINATION_VIDEO, destination.getVideo());
            intent.putExtra(EXTRA_KEY_DESTINATION_DISTANCE, destination.getJarak());
            startActivity(intent);
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rvDestination.setLayoutManager(layoutManager);
        rvDestination.addItemDecoration(new GridSpacingItemDecoration(
                2,
                getResources().getDimensionPixelSize(R.dimen.sm_margin_padding),
                true,
                0));

        rvDestination.setAdapter(destinationAdapter);
    }

    private void onRefresh() {
        srlRefresh.setOnRefreshListener(this::doAsync);
        btnRefresh.setOnClickListener((v) -> doAsync());
    }
}