package com.example.photostore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.photostore.Models.FavoriteImage;
import com.example.photostore.Models.ScheduledImage;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

public class ImageAPIDetail extends AppCompatActivity {

    ImageView ivImageDetail;
    ImageAPI imageAPI;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ivImageDetail = findViewById(R.id.ivImageDetail);

        imageAPI = Parcels.unwrap(getIntent().getParcelableExtra("image"));

        Glide.with(this).load(imageAPI.getImage()).into(ivImageDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_schedule) {
            Intent intent = new Intent(this, ScheduleActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_favorite) {
            item.setIcon(R.drawable.ic_baseline_favorite_24);
            addToFavorite();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addToFavorite() {
        FavoriteImage favoriteImage = new FavoriteImage();
        ParseUser currentUser = ParseUser.getCurrentUser();
        favoriteImage.setUser(currentUser);
        favoriteImage.setImage(imageAPI.getImage());
        favoriteImage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("ImageDetail", String.valueOf(e));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");
            String schedule = date + " " + time;

            try {
                scheduleImage(imageAPI.getImage(), schedule);
                finish();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void scheduleImage(final String image, String schedule) throws ParseException {
        ParseUser currentUser = ParseUser.getCurrentUser();
        ScheduledImage scheduledImage = new ScheduledImage();
        scheduledImage.setUser(currentUser);
        scheduledImage.setImage(image);
        scheduledImage.setDate(schedule);
        scheduledImage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("ImageDetail", String.valueOf(e));
                }
            }
        });
    }
}