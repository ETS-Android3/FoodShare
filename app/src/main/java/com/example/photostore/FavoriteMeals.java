package com.example.photostore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photostore.Adapters.FavoriteImageAdapter;
import com.example.photostore.Adapters.ImageAdapter;
import com.example.photostore.Models.FavoriteImage;
import com.example.photostore.Models.ScheduledImage;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMeals extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    private RecyclerView rvFavoriteMeals;
    public static final String TAG = "FavoriteMeals";
    List<FavoriteImage> favoriteImages;
    FavoriteImageAdapter favoriteImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_meals);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Favorites");

        iv = findViewById(R.id.iv_extra3);
        tv = findViewById(R.id.tv_extra3);

        iv.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);

        rvFavoriteMeals = findViewById(R.id.rvFavoriteMeals);
        favoriteImages = new ArrayList<>();

        favoriteImageAdapter = new FavoriteImageAdapter(this, favoriteImages);

        rvFavoriteMeals.setAdapter(favoriteImageAdapter);

        rvFavoriteMeals.setLayoutManager(new GridLayoutManager(this, 2));

        queryPosts();

    }

    private void queryPosts() {
        ParseQuery<FavoriteImage> query = ParseQuery.getQuery(FavoriteImage.class);
        //query.include(Post.KEY_USER);
        query.setLimit(20);
        //query.addDescendingOrder(ScheduledImage.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<FavoriteImage>() {
            @Override
            public void done(List<FavoriteImage> images, ParseException e) {
                if (e != null) {
                    Log.e("Scheduled Image", "Issue with getting posts", e);
                    return;
                }

                favoriteImageAdapter.clear();
                favoriteImages.addAll(images);
                if (favoriteImages.size() == 0) {
                    iv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
                favoriteImageAdapter.notifyDataSetChanged();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}