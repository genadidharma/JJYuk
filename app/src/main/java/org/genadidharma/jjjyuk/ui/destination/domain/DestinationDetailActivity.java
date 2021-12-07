package org.genadidharma.jjjyuk.ui.destination.domain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import org.genadidharma.jjjyuk.MainActivity;
import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.AppDatabaseDest;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapterFav;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DestinationDetailActivity extends AppCompatActivity implements OnMapReadyCallback, MapboxMap.OnMapClickListener {

    private String title, image, video, distance, description, time, address, price, status, protocol, jam_buka, jam_tutup, id;
    private double rating, lat, longitude;
    private int type, review;

    private TextView tvTitle, tvPrice, tvDistance, tvRating, tvReview, tvAddress, tvTime, tvStatus, tvProtocol, tvDescription;
    private MapView mvMap;
    private ShapeableImageView ivImage;
    private ImageView ivFgVideo, ivPlayVideo;
    private Toolbar toolbar;
    List<Destination> dataList = new ArrayList<>();
    AppDatabaseDest database;
    private String jenis;
    boolean found = true;

    MapboxMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_destination_detail);

        database = AppDatabaseDest.getInstance(this);

        initLayout();
        getIntentExtra();
        initButtonFavorite();

        mvMap.onCreate(savedInstanceState);
        mvMap.getMapAsync(this);

        ImageButton favoriteButton = findViewById(R.id.btn_fav);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Destination> dest = new ArrayList<>();
                dest = database.destDao().getAll();
                if (database.destDao().getRowCount() == 0) {
                    insertNewDestination();
                    favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                    autoRefresh();
                } else {
                    for (int i = 0; i < database.destDao().getRowCount(); i++) {
                        if (dest.get(i).getNamaWisata().equalsIgnoreCase(title)) {
                            database.destDao().deleteOne(dest.get(i));

                            dest.clear();
                            found = false;
                            favoriteButton.setImageResource(R.drawable.ic_favorite_border_24dp);
                            autoRefresh();
                            break;
                        }
                    }
                    if (found == true) {
                        insertNewDestination();
                        favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                        autoRefresh();
                    }
                }
            }
        });


        if (type == DestinationAdapter.LAYOUT_IMAGE) {
            ivFgVideo.setVisibility(View.GONE);
            ivPlayVideo.setVisibility(View.GONE);
            ivImage.setClickable(false);
        } else {
            ivImage.setClickable(true);
        }

        Glide
                .with(getApplicationContext())
                .load(type == DestinationAdapter.LAYOUT_IMAGE ? image : "https://img.youtube.com/vi/" + video + "/0.jpg")
                .into(ivImage);
        tvTitle.setText(title);
        tvReview.setText(getResources().getString(R.string.jumlah_ulasan, String.valueOf(review)));
        tvStatus.setText(status);
        tvProtocol.setText(protocol);
        tvTime.setText(time);
        tvPrice.setText(price);
        tvDescription.setText(description);
        tvAddress.setText(address);
        tvDistance.setText(distance);
        tvStatus.setText(status);
        tvRating.setText(String.valueOf(rating));

        tvStatus.setEnabled(status.equalsIgnoreCase(DestinationAdapter.KEY_OPEN));

        if (ivImage.isClickable()) {
            ivImage.setOnClickListener(view -> {
                Intent intent = new Intent(DestinationDetailActivity.this, DestinationVideoActivity.class);
                intent.putExtra(MainActivity.EXTRA_KEY_DESTINATION_VIDEO, video);
                startActivity(intent);
            });
        }

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initButtonFavorite() {
        List<Destination> dest = new ArrayList<>();
        ImageButton favoriteButton = findViewById(R.id.btn_fav);
        boolean foundFav = false;
        dest = database.destDao().getAll();
        for (int i = 0; i < database.destDao().getRowCount(); i++) {
            if (dest.get(i).getNamaWisata().equalsIgnoreCase(title)) {
                favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                foundFav = true;
            }
        }
        if (foundFav == false) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_border_24dp);
        }
    }

    private void initLayout() {
        tvTitle = findViewById(R.id.tv_title);
        tvAddress = findViewById(R.id.tv_address);
        tvDistance = findViewById(R.id.tv_distance);
        tvDescription = findViewById(R.id.tv_description);
        tvPrice = findViewById(R.id.tv_price);
        tvProtocol = findViewById(R.id.tv_health_safety_protocol);
        mvMap = findViewById(R.id.mv_map);
        tvTime = findViewById(R.id.tv_time);
        tvRating = findViewById(R.id.tv_rating);
        tvStatus = findViewById(R.id.tv_status);
        tvReview = findViewById(R.id.tv_review);
        ivImage = findViewById(R.id.iv_image);
        ivFgVideo = findViewById(R.id.iv_fg_video);
        ivPlayVideo = findViewById(R.id.iv_play_video);
        toolbar = findViewById(R.id.toolbar);
    }

    private void getIntentExtra() {
        type = getIntent().getIntExtra(MainActivity.EXTRA_KEY_DESTINATION_TYPE, -1);
        title = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_NAME);
        address = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_ADDRESS);
        description = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_DESCRIPTION);
        price = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_PRICE);
        protocol = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_PROTOCOL);
        time = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_TIME);
        rating = getIntent().getDoubleExtra(MainActivity.EXTRA_KEY_DESTINATION_RATING, 0);
        status = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_STATUS);
        review = getIntent().getIntExtra(MainActivity.EXTRA_KEY_DESTINATION_REVIEW, 0);
        image = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_IMAGE);
        video = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_VIDEO);
        distance = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_DISTANCE);
        jam_buka = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_OPEN);
        jam_tutup = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_CLOSE);
        id = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESTINATION_ID);
        lat = getIntent().getDoubleExtra(MainActivity.EXTRA_KEY_DESTINATION_LATITUDE, 0);
        longitude = getIntent().getDoubleExtra(MainActivity.EXTRA_KEY_DESTINATION_LONGITUDE, 0);

    }

    private void insertNewDestination() {
        Destination data = new Destination();
        if (type == 0) {
            jenis = "gambar";
        } else if (type == 1) {
            jenis = "video";
        }
        data.setId(id);
        data.setJenis(jenis);
        data.setFoto(image);
        data.setVideo(video);
        data.setNamaWisata(title);
        data.setDeskripsi(description);
        data.setJamBuka(jam_buka);
        data.setJamTutup(jam_tutup);
        data.setLat(lat);
        data.setJsonMemberLong(longitude);
        data.setAlamat(address);
        data.setJarak(distance);
        data.setHargaTiket(price);
        data.setRating(rating);
        data.setUlasan(review);
        data.setStatus(status);
        data.setProtokol(protocol);
        data.setFavorite(true);

        database.destDao().insertDest(data);
        dataList.clear();
        dataList.addAll(database.destDao().getAll());
        dataList.clear();
    }

    private void autoRefresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void addMapMarker() {
        LatLng location = new LatLng(lat, longitude);
        map.addMarker(new MarkerOptions().position(location));
        map.setCameraPosition(
                new CameraPosition.Builder()
                        .target(location)
                        .build()
        );
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        map.addOnMapClickListener(this);

        addMapMarker();
    }

    @Override
    public void onResume() {
        super.onResume();
        mvMap.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvMap.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvMap.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mvMap.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mvMap.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mvMap.onLowMemory();
    }
}