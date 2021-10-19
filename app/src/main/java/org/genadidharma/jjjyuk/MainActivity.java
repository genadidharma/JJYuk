package org.genadidharma.jjjyuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

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

    private RecyclerView rvDestination;
    private ArrayList<Destination> destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        iniData();
        setupAdapter();
    }

    private void initLayout() {
        rvDestination = findViewById(R.id.rv_destination);
    }

    private void iniData() {
        destinations = DestinationData.getWisataData();
    }

    private void setupAdapter() {
        DestinationAdapter destinationAdapter = new DestinationAdapter(destinations, (destination) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(EXTRA_KEY_DESTINATION_TYPE, destination.getJenis());
            intent.putExtra(EXTRA_KEY_DESTINATION_NAME, destination.getNama_wisata());
            intent.putExtra(EXTRA_KEY_DESTINATION_ADDRESS, destination.getAlamat());
            intent.putExtra(EXTRA_KEY_DESTINATION_DESCRIPTION, destination.getDeskipsi());
            intent.putExtra(EXTRA_KEY_DESTINATION_PRICE, destination.getHarga_tiket());
            intent.putExtra(EXTRA_KEY_DESTINATION_PROTOCOL, destination.getProtokol());
            intent.putExtra(EXTRA_KEY_DESTINATION_TIME, destination.getJam());
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
}