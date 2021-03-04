package com.example.photostore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photostore.ImageAPI;
import com.example.photostore.ImageAPIDetail;
import com.example.photostore.R;

import org.parceler.Parcels;

import java.util.List;

public class ImageAPIAdapter extends RecyclerView.Adapter<ImageAPIAdapter.ViewHolder> {

    public static final String TAG = "ImageAPIAdapter";
    Context context;
    List<ImageAPI> imageAPIS;

    public ImageAPIAdapter(Context c, List<ImageAPI> imageAPIS) {
        this.context = c;
        this.imageAPIS = imageAPIS;
    }

    @NonNull
    @Override
    public ImageAPIAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAPIAdapter.ViewHolder holder, int position) {
        ImageAPI imageAPI = imageAPIS.get(position);
        holder.bind(imageAPI);
    }

    @Override
    public int getItemCount() {
        return imageAPIS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = itemView.findViewById(R.id.ivImage);
        }

        public void bind(final ImageAPI imageAPI) {
            Glide.with(context).load(imageAPI.getImage()).into(ivImage);
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // details
                    Intent i = new Intent(context, ImageAPIDetail.class);
                    i.putExtra("image", Parcels.wrap(imageAPI));
                    context.startActivity(i);
                }
            });
        }
    }
}
