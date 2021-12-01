package org.genadidharma.jjjyuk.ui.destination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import org.genadidharma.jjjyuk.R;
import org.genadidharma.jjjyuk.data.model.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter {
    public static final String KEY_IMAGE = "gambar";
    public static final String KEY_VIDEO = "video";
    public static final String KEY_OPEN = "buka";

    public static final int LAYOUT_IMAGE = 0;
    public static final int LAYOUT_VIDEO = 1;

    private final OnDestinationClickListener destinationClickListener;
    private final List<Destination> destinations;

    public DestinationAdapter(List<Destination> destinations, OnDestinationClickListener destinationClickListener) {
        this.destinations = destinations;
        this.destinationClickListener = destinationClickListener;
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
        switch (destinations.get(position).getJenis()) {
            case KEY_IMAGE:
                ((DestinationImageAdapterViewHolder) holder).bindItem(destinations.get(position), destinationClickListener);
                break;
            case KEY_VIDEO:
                ((DestinationVideoAdapterViewHolder) holder).bindItem(destinations.get(position), destinationClickListener);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public void updateData(List<Destination> newDestinations){
        destinations.clear();
        destinations.addAll(newDestinations);
        notifyDataSetChanged();
    }
}

class DestinationImageAdapterViewHolder extends RecyclerView.ViewHolder {

    public DestinationImageAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bindItem(Destination destination, OnDestinationClickListener destinationClickListener) {
        ShapeableImageView ivImage = itemView.findViewById(R.id.iv_image);
        TextView tvPrice = itemView.findViewById(R.id.tv_price);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvRating = itemView.findViewById(R.id.tv_rating);
        TextView tvReview = itemView.findViewById(R.id.tv_review);
        TextView tvPlace = itemView.findViewById(R.id.tv_place);
        TextView tvStatus = itemView.findViewById(R.id.tv_status);
        ImageButton iv_fav_img = itemView.findViewById(R.id.iv_fav_img);

        Glide.with(itemView.getContext())
                .load(destination.getFoto())
                .into(ivImage);
        tvPrice.setText(destination.getHargaTiket());
        tvName.setText(destination.getNamaWisata());
        tvRating.setText(String.valueOf(destination.getRating()));
        tvReview.setText(itemView.getResources().getString(R.string.jumlah_ulasan, String.valueOf(destination.getUlasan())));
        tvPlace.setText(destination.getTempat());
        tvStatus.setText(destination.getStatus());

        if (destination.isFavorite() == true){
            iv_fav_img.setVisibility(View.VISIBLE);
        }else if(destination.isFavorite() == false){
            iv_fav_img.setVisibility(View.GONE);
        }

        tvStatus.setEnabled(destination.getStatus().equalsIgnoreCase(DestinationAdapter.KEY_OPEN));

        itemView.setOnClickListener(view -> destinationClickListener.onDestinationClick(destination));
    }
}

class DestinationVideoAdapterViewHolder extends RecyclerView.ViewHolder {

    public DestinationVideoAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bindItem(Destination destination, OnDestinationClickListener destinationClickListener) {

        ShapeableImageView ivImage = itemView.findViewById(R.id.iv_image);
        TextView tvPrice = itemView.findViewById(R.id.tv_price);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvRating = itemView.findViewById(R.id.tv_rating);
        TextView tvReview = itemView.findViewById(R.id.tv_review);
        TextView tvPlace = itemView.findViewById(R.id.tv_place);
        TextView tvStatus = itemView.findViewById(R.id.tv_status);
        ImageButton iv_fav_vid = itemView.findViewById(R.id.iv_fav_vid);

        Glide.with(itemView.getContext())
                .load("https://img.youtube.com/vi/" + destination.getVideo() + "/0.jpg")
                .into(ivImage);
        tvPrice.setText(destination.getHargaTiket());
        tvName.setText(destination.getNamaWisata());
        tvRating.setText(String.valueOf(destination.getRating()));
        tvReview.setText(itemView.getResources().getString(R.string.jumlah_ulasan, String.valueOf(destination.getUlasan())));
        tvPlace.setText(destination.getTempat());
        tvStatus.setText(destination.getStatus());

        if (destination.isFavorite() == true){
            iv_fav_vid.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else if(destination.isFavorite() == false){
            iv_fav_vid.setVisibility(View.GONE);
        }

        tvStatus.setEnabled(destination.getStatus().equalsIgnoreCase(DestinationAdapter.KEY_OPEN));

        itemView.setOnClickListener(view -> destinationClickListener.onDestinationClick(destination));
    }
}

