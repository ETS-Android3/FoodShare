package com.example.photostore.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photostore.Adapters.ImageAdapter;
import com.example.photostore.Adapters.ScheduledImageAdapter;
import com.example.photostore.Models.ScheduledImage;
import com.example.photostore.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ScheduledMealsFragment extends Fragment {

    ImageView iv;
    TextView tv;
    private RecyclerView rvScheduledMeals;
    List<ScheduledImage> scheduledImages;
    ScheduledImageAdapter scheduledImageAdapter;

    public ScheduledMealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scheduled_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv = view.findViewById(R.id.iv_extra1);
        tv = view.findViewById(R.id.tv_extra1);

        iv.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);

        rvScheduledMeals = view.findViewById(R.id.rvScheduledMeals);
        scheduledImages = new ArrayList<>();

        scheduledImageAdapter = new ScheduledImageAdapter(getContext(), scheduledImages);

        rvScheduledMeals.setAdapter(scheduledImageAdapter);

        rvScheduledMeals.setLayoutManager(new LinearLayoutManager(getContext()));

        rvScheduledMeals.addItemDecoration(new DividerItemDecoration(rvScheduledMeals.getContext(), DividerItemDecoration.VERTICAL));

        queryPosts();

        // onActivityResult - delete Meal from ScheduledMeal and send Image to CreatedMeals
    }

    private void queryPosts() {
        ParseQuery<ScheduledImage> query = ParseQuery.getQuery(ScheduledImage.class);
        //query.include(Post.KEY_USER);
        query.setLimit(20);
        //query.addDescendingOrder(ScheduledImage.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<ScheduledImage>() {
            @Override
            public void done(List<ScheduledImage> images, ParseException e) {
                if (e != null) {
                    Log.e("Scheduled Image", "Issue with getting posts", e);
                    return;
                }

                scheduledImageAdapter.clear();
                scheduledImages.addAll(images);
                if (scheduledImages.size() == 0) {
                    iv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
                scheduledImageAdapter.notifyDataSetChanged();
            }
        });
    }
}