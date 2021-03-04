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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photostore.Adapters.CreatedImageAdapter;
import com.example.photostore.Adapters.ScheduledImageAdapter;
import com.example.photostore.Models.CreatedImage;
import com.example.photostore.Models.ScheduledImage;
import com.example.photostore.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CreatedMealsFragment extends Fragment {

    ImageView iv;
    TextView tv;
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

        iv = view.findViewById(R.id.iv_extra2);
        tv = view.findViewById(R.id.tv_extra2);

        iv.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);

        rvCreatedMeals = view.findViewById(R.id.rvCreatedMeals);
        createdImages = new ArrayList<>();

        createdImageAdapter = new CreatedImageAdapter(getContext(), createdImages);

        rvCreatedMeals.setAdapter(createdImageAdapter);

        rvCreatedMeals.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }

    private void queryPosts() {
        ParseQuery<CreatedImage> query = ParseQuery.getQuery(CreatedImage.class);
        query.include(ScheduledImage.KEY_USER);
        query.whereEqualTo(ScheduledImage.KEY_USER, ParseUser.getCurrentUser());
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
                if (createdImages.size() == 0) {
                    iv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
                createdImageAdapter.notifyDataSetChanged();
            }
        });
    }
}