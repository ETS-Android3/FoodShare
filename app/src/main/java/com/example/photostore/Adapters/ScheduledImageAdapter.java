package com.example.photostore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.photostore.Models.ScheduledImage;
import com.example.photostore.R;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.List;

public class ScheduledImageAdapter extends RecyclerView.Adapter<ScheduledImageAdapter.ViewHolder> {

    public static final String TAG = "SchedImageAdapter";
    Context context;
    List<ScheduledImage> scheduledImages;


    public ScheduledImageAdapter(Context c, List<ScheduledImage> imageAPIS) {
        this.context = c;
        this.scheduledImages = imageAPIS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from(context).inflate(R.layout.item_scheduled_meal, parent, false);
        return new ScheduledImageAdapter.ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduledImage scheduledImage = scheduledImages.get(position);
        holder.bind(scheduledImage);
    }

    @Override
    public int getItemCount() {
        return scheduledImages.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        scheduledImages.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<ScheduledImage> list) {
        scheduledImages.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivScheduledImage;
        TextView tvSchedule;
        Button btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivScheduledImage = itemView.findViewById(R.id.ivScheduledImage);
            this.tvSchedule = itemView.findViewById(R.id.tvSchedule);
            this.btnAdd = itemView.findViewById(R.id.btnAddImage);
        }

        public void bind(final ScheduledImage scheduledImage) {
            tvSchedule.setText(scheduledImage.getDate());
            Glide.with(context).load(scheduledImage.getImage()).into(ivScheduledImage);

            // connect AddPhoto button to CreatedMeals - startActivityForResult
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddCreationActivity.class);
                    intent.putExtra("scheduledImage", scheduledImage.getImage());
                    Log.i(TAG, scheduledImage.getImage());
                    context.startActivity(intent);
                }
            });
        }
    }
}
