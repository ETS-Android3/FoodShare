package com.example.photostore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photostore.AddCreationActivity;
import com.example.photostore.Models.CreatedImage;
import com.example.photostore.Models.ScheduledImage;
import com.example.photostore.R;

import java.util.List;

public class CreatedImageAdapter extends RecyclerView.Adapter<CreatedImageAdapter.ViewHolder> {

    public static final String TAG = "CreatedImageAdapter";
    Context context;
    List<CreatedImage> createdImages;

    public CreatedImageAdapter(Context c, List<CreatedImage> createdImages) {
        this.context = c;
        this.createdImages = createdImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from(context).inflate(R.layout.item_created_meal, parent, false);
        return new CreatedImageAdapter.ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CreatedImage createdImage = createdImages.get(position);
        holder.bind(createdImage);
    }

    @Override
    public int getItemCount() {
        return createdImages.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        createdImages.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<CreatedImage> list) {
        createdImages.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivScheduledMeal, ivCreatedMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivScheduledMeal = itemView.findViewById(R.id.ivScheduledMeal);
            this.ivCreatedMeal = itemView.findViewById(R.id.ivCreatedMeal);
        }

        public void bind(CreatedImage createdImage) {
            Glide.with(context).load(createdImage.getScheduledPic()).into(ivScheduledMeal);

            if (createdImage.getCreatedPic() != null) {
                Glide.with(context).load(createdImage.getCreatedPic().getUrl()).into(ivCreatedMeal);
            }

        }

    }
}
