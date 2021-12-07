package org.genadidharma.jjjyuk.ui.destination.domain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import org.genadidharma.jjjyuk.MainActivity;
import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.db.AppDatabaseDest;
import org.genadidharma.jjjyuk.ui.destination.DestinationAdapter;

import java.util.Locale;

public class DestinationDetailActivity extends AppCompatActivity implements OnMapReadyCallback, MapboxMap.OnMapClickListener {

    private String title, image, video, distance, description, time, address, price, status, protocol, jam_buka, jam_tutup, id;
    private double rating, lat, lng;
    private int position, type, review;
    private boolean isFavorite;

    private TextView tvTitle, tvPrice, tvRating, tvReview, tvAddress, tvTime, tvStatus, tvProtocol, tvDescription;
    private MapView mvMap;
    private ShapeableImageView ivImage;
    private ImageView ivFgVideo, ivPlayVideo;
    private FloatingActionButton fabFavorite;
    private Toolbar toolbar;

    private AppDatabaseDest database;
    private MapboxMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        database = AppDatabaseDest.getInstance(this);

        setContentView(R.layout.activity_destination_detail);

        initLayout();
        getIntentExtra();
        initData(savedInstanceState);
        toggleFavoriteButton();

    }

    private void toggleFavoriteButton() {
        fabFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                database.destDao().deleteDestination(id);
                fabFavorite.setImageResource(R.drawable.ic_favorite_border_24dp);
                Snackbar.make(findViewById(R.id.fl_parent), getResources().getString(R.string.remove_from_favorite_message, String.valueOf(title)), Snackbar.LENGTH_SHORT)
                        .show();
                isFavorite = false;
            } else {
                insertDestinationToDB();
                fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                Snackbar.make(findViewById(R.id.fl_parent), getResources().getString(R.string.add_to_favorite_message, String.valueOf(title)), Snackbar.LENGTH_SHORT)
                        .show();
                isFavorite = true;
            }
        });
    }

    private void initLayout() {
        tvTitle = findViewById(R.id.tv_title);
        tvAddress = findViewById(R.id.tv_address);
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
        fabFavorite = findViewById(R.id.fab_favorite);
    }

    private void getIntentExtra() {
        position = getIntent().getIntExtra(MainActivity.EXTRA_KEY_DESTINATION_FAVORITE_POSITION, -1);
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
        lng = getIntent().getDoubleExtra(MainActivity.EXTRA_KEY_DESTINATION_LONGITUDE, 0);
        isFavorite = getIntent().getBooleanExtra(MainActivity.EXTRA_KEY_DESTINATION_FAVORITE, false);
    }

    private void initData(Bundle savedInstanceState) {
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
        if (!jam_buka.equals(jam_tutup)) {
            tvTime.setText(time);
        } else {
            tvTime.setText(R.string.desc_24_hour_open);
        }
        tvPrice.setText(price);
        tvDescription.setText(description);
        tvAddress.setText(address);
        tvStatus.setText(status);
        tvRating.setText(String.valueOf(rating));
        tvStatus.setEnabled(status.equalsIgnoreCase(DestinationAdapter.KEY_OPEN));
        if (isFavorite) {
            fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_border_24dp);
        }

        mvMap.onCreate(savedInstanceState);
        mvMap.getMapAsync(this);

        if (ivImage.isClickable()) {
            ivImage.setOnClickListener(view -> {
                Intent intent = new Intent(DestinationDetailActivity.this, DestinationVideoActivity.class);
                intent.putExtra(MainActivity.EXTRA_KEY_DESTINATION_VIDEO, video);
                startActivity(intent);
            });
        }

        toolbar.setNavigationOnClickListener(view -> sendFavoriteStatus());
    }

    private void insertDestinationToDB() {
        Destination data = new Destination();

        data.setId(id);
        data.setJenis(type == 0 ? "gambar" : "video");
        data.setFoto(image);
        data.setVideo(video);
        data.setNamaWisata(title);
        data.setDeskripsi(description);
        data.setJamBuka(jam_buka);
        data.setJamTutup(jam_tutup);
        data.setLat(lat);
        data.setJsonMemberLong(lng);
        data.setAlamat(address);
        data.setJarak(distance);
        data.setHargaTiket(price);
        data.setRating(rating);
        data.setUlasan(review);
        data.setStatus(status);
        data.setProtokol(protocol);
        data.setFavorite(true);

        database.destDao().insertDest(data);
    }

    private void sendFavoriteStatus() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_KEY_DESTINATION_FAVORITE_POSITION, position);
        intent.putExtra(MainActivity.EXTRA_KEY_DESTINATION_FAVORITE_STATUS, isFavorite);
        setResult(MainActivity.RESULT_FAVORITE_DETAIL, intent);
        finish();
    }

    private void addMapMarker() {
        LatLng location = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(location));
        map.setCameraPosition(
                new CameraPosition.Builder()
                        .target(location)
                        .build()
        );
    }

    @Override
    public void onBackPressed() {
        sendFavoriteStatus();
        super.onBackPressed();
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        map.addOnMapClickListener(this);

        addMapMarker();
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lng);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
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