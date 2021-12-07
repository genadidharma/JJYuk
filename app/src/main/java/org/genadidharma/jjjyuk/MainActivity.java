package org.genadidharma.jjjyuk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.data.model.DestinationResponse;
import org.genadidharma.jjjyuk.db.AppDatabaseDest;
import org.genadidharma.jjjyuk.networks.APIBuilder;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.ui.destination.domain.DestinationDetailActivity;
import org.genadidharma.jjjyuk.ui.destination.domain.DestinationFavoriteActivity;
import org.genadidharma.jjjyuk.util.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
    public static final String EXTRA_KEY_DESTINATION_FAVORITE_POSITION = "position";
    public static final String EXTRA_KEY_DESTINATION_FAVORITE_STATUS = "favorite_status";

    public static final int RESULT_FAVORITE_DETAIL = 333;
    public static final int RESULT_FAVORITE_ACTIVITY = 334;

    private SwipeRefreshLayout srlRefresh;
    private RecyclerView rvDestination;
    private LinearLayout llError;
    private Button btnRefresh;
    private ImageButton btnFav;
    private DestinationAdapter destinationAdapter;
    private List<Destination> destinationList;
    private AppDatabaseDest database;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        destinationList = new ArrayList<>();

        database = AppDatabaseDest.getInstance(this);

        initLayout();
        setupAdapter(destinationList);
        doAsync();
        onRefresh();

        btnFav.setOnClickListener(v -> resultLauncher.launch(new Intent(MainActivity.this, DestinationFavoriteActivity.class)));

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_FAVORITE_DETAIL) {
                        if (result.getData() != null) {
                            boolean isFavorite = result.getData().getBooleanExtra(EXTRA_KEY_DESTINATION_FAVORITE_STATUS, false);
                            int position = result.getData().getIntExtra(EXTRA_KEY_DESTINATION_FAVORITE_POSITION, 0);
                            destinationAdapter.toggleFavorite(position, isFavorite);
                        }
                    }else if(result.getResultCode() == RESULT_FAVORITE_ACTIVITY){
                        doAsync();
                    }
                });
    }

    private void initLayout() {
        srlRefresh = findViewById(R.id.srl_refresh);
        btnFav = findViewById(R.id.btn_to_fav);
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
                markFavoriteData();
                destinationAdapter.updateData(destinationList);

                rvDestination.setVisibility(View.VISIBLE);
                llError.setVisibility(View.GONE);
                srlRefresh.setRefreshing(false);

                if (destinationList.size() == 0) {
                    llError.setVisibility(View.VISIBLE);
                    rvDestination.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DestinationResponse> call, @NonNull Throwable t) {
                rvDestination.setVisibility(View.GONE);
                llError.setVisibility(View.VISIBLE);
                srlRefresh.setRefreshing(false);
            }
        });
    }

    private void setupAdapter(List<Destination> list) {

        destinationAdapter = new DestinationAdapter(list, (destination, position) -> {
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
            intent.putExtra(EXTRA_KEY_DESTINATION_CLOSE, destination.getJamTutup());
            intent.putExtra(EXTRA_KEY_DESTINATION_OPEN, destination.getJamBuka());
            intent.putExtra(EXTRA_KEY_DESTINATION_LATITUDE, destination.getLat());
            intent.putExtra(EXTRA_KEY_DESTINATION_LONGITUDE, destination.getJsonMemberLong());
            intent.putExtra(EXTRA_KEY_DESTINATION_ID, destination.getId());
            intent.putExtra(EXTRA_KEY_DESTINATION_FAVORITE, destination.isFavorite());
            intent.putExtra(EXTRA_KEY_DESTINATION_FAVORITE_POSITION, position);

            resultLauncher.launch(intent);
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

    private void markFavoriteData() {
        List<String> favoritesIds = database.destDao().getFavoriteIds();

        for (String favoriteId : favoritesIds) {
            for (Destination destination : destinationList) {
                if (destination.getId().equals(favoriteId)) {
                    destination.setFavorite(true);
                    break;
                }
            }
        }
    }
}