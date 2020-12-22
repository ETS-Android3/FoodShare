package com.example.photostore.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photostore.Adapters.CreatedImageAdapter;
import com.example.photostore.Adapters.ScheduledImageAdapter;
import com.example.photostore.Models.CreatedImage;
import com.example.photostore.Models.ScheduledImage;
import com.example.photostore.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CreatedMealsFragment extends Fragment {

    private RecyclerView rvCreatedMeals;
    List<CreatedImage> createdImages;
    CreatedImageAdapter createdImageAdapter;

    public CreatedMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_created_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCreatedMeals = view.findViewById(R.id.rvCreatedMeals);
        createdImages = new ArrayList<>();

        createdImageAdapter = new CreatedImageAdapter(getContext(), createdImages);

        rvCreatedMeals.setAdapter(createdImageAdapter);

        rvCreatedMeals.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

        // query created meals
    }

    private void queryPosts() {
        ParseQuery<CreatedImage> query = ParseQuery.getQuery(CreatedImage.class);
        //query.include(Post.KEY_USER);
        query.setLimit(20);
        //query.addDescendingOrder(ScheduledImage.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<CreatedImage>() {
            @Override
            public void done(List<CreatedImage> images, ParseException e) {
                if (e != null) {
                    Log.e("Created Image", "Issue with getting posts", e);
                    return;
                }

                createdImageAdapter.clear();
                createdImages.addAll(images);
                //swipeContainer.setRefreshing(false);
                createdImageAdapter.notifyDataSetChanged();
            }
        });
    }
}