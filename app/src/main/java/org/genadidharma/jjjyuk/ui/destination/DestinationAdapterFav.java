package org.genadidharma.jjjyuk.ui.destination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.Destination;
import org.genadidharma.jjjyuk.db.Dest;

import java.util.List;

public class DestinationAdapterFav extends RecyclerView.Adapter {
    public static final String KEY_IMAGE = "gambar";
    public static final String KEY_VIDEO = "video";
    public static final String KEY_OPEN = "buka";

    public static final int LAYOUT_IMAGE = 0;
    public static final int LAYOUT_VIDEO = 1;

    private final OnDestinationFavClick destinationClickListenerFav;
    private final List<Dest> destinations;

    public DestinationAdapterFav(List<Dest> destinations, OnDestinationFavClick destinationClickListener) {
        this.destinations = destinations;
        this.destinationClickListenerFav = destinationClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        switch (destinations.get(position).getJenis()) {
            case KEY_IMAGE:
                return LAYOUT_IMAGE;
            case KEY_VIDEO:
                return LAYOUT_VIDEO;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LAYOUT_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination_image, parent, false);
                return new DestinationImageAdapterViewHolder(view);
            case LAYOUT_VIDEO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination_video, parent, false);
                return new DestinationVideoAdapterViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Dest dest = destinations.get(position);
        switch (dest.getJenis()) {
            case KEY_IMAGE:
                ((DestinationImageFavAdapterViewHolder) holder).bindItem(destinations.get(position), destinationClickListenerFav);
                break;
            case KEY_VIDEO:
                ((DestinationVideoFavAdapterViewHolder) holder).bindItem(destinations.get(position), destinationClickListenerFav);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }
}

class DestinationImageFavAdapterViewHolder extends RecyclerView.ViewHolder {

    public DestinationImageFavAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bindItem(Dest destination, OnDestinationFavClick destinationClickListener) {
        ShapeableImageView ivImage = itemView.findViewById(R.id.iv_image);
        TextView tvPrice = itemView.findViewById(R.id.tv_price);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvRating = itemView.findViewById(R.id.tv_rating);
        TextView tvReview = itemView.findViewById(R.id.tv_review);
        TextView tvPlace = itemView.findViewById(R.id.tv_place);
        TextView tvStatus = itemView.findViewById(R.id.tv_status);

        Glide.with(itemView.getContext())
                .load(destination.getFoto())
                .into(ivImage);
        tvPrice.setText(destination.getHarga_tiket());
        tvName.setText(destination.getNama_wisata());
        tvRating.setText(String.valueOf(destination.getRating()));
        tvReview.setText(itemView.getResources().getString(R.string.jumlah_ulasan, String.valueOf(destination.getUlasan())));
        tvPlace.setText(destination.getTempat());
        tvStatus.setText(destination.getStatus());

        tvStatus.setEnabled(destination.getStatus().equalsIgnoreCase(DestinationAdapter.KEY_OPEN));

        itemView.setOnClickListener(view -> destinationClickListener.onDestinationClickFav(destination));
    }
}

class DestinationVideoFavAdapterViewHolder extends RecyclerView.ViewHolder {

    public DestinationVideoFavAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bindItem(Dest destination, OnDestinationFavClick destinationClickListener) {

        ShapeableImageView ivImage = itemView.findViewById(R.id.iv_image);
        TextView tvPrice = itemView.findViewById(R.id.tv_price);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvRating = itemView.findViewById(R.id.tv_rating);
        TextView tvReview = itemView.findViewById(R.id.tv_review);
        TextView tvPlace = itemView.findViewById(R.id.tv_place);
        TextView tvStatus = itemView.findViewById(R.id.tv_status);

        Glide.with(itemView.getContext())
                .load("https://img.youtube.com/vi/" + destination.getVideo() + "/0.jpg")
                .into(ivImage);
        tvPrice.setText(destination.getHarga_tiket());
        tvName.setText(destination.getNama_wisata());
        tvRating.setText(String.valueOf(destination.getRating()));
        tvReview.setText(itemView.getResources().getString(R.string.jumlah_ulasan, String.valueOf(destination.getUlasan())));
        tvPlace.setText(destination.getTempat());
        tvStatus.setText(destination.getStatus());

        tvStatus.setEnabled(destination.getStatus().equalsIgnoreCase(DestinationAdapter.KEY_OPEN));

        itemView.setOnClickListener(view -> destinationClickListener.onDestinationClickFav(destination));
    }
}
