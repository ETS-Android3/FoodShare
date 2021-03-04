package com.example.photostore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photostore.Models.FavoriteImage;
import com.example.photostore.R;

import java.util.List;

public class FavoriteImageAdapter extends RecyclerView.Adapter<FavoriteImageAdapter.ViewHolder> {

    public static final String TAG = "FavoriteImageAdapter";
    Context context;
    List<FavoriteImage> favoriteImages;

    public FavoriteImageAdapter(Context c, List<FavoriteImage> favoriteImages) {
        this.context = c;
        this.favoriteImages = favoriteImages;
    }

    @NonNull
    @Override
    public FavoriteImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteImageAdapter.ViewHolder holder, int position) {
        FavoriteImage favoriteImage = favoriteImages.get(position);
        holder.bind(favoriteImage);
    }

    // Clean all elements of the recycler
    public void clear() {
        favoriteImages.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<FavoriteImage> list) {
        favoriteImages.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favoriteImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = itemView.findViewById(R.id.ivImage);
        }

        public void bind(final FavoriteImage favoriteImage) {
            Glide.with(context).load(favoriteImage.getImage()).into(ivImage);
        }
    }
}