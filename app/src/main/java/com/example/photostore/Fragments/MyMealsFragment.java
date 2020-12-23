package com.example.photostore.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photostore.FavoriteMeals;
import com.example.photostore.Models.FavoriteImage;
import com.example.photostore.ScheduledMeals;
import com.example.photostore.R;

public class MyMealsFragment extends Fragment {

    CardView card_myMeals, card_favorite;

    public MyMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card_myMeals = view.findViewById(R.id.card_myMeals);
        card_favorite = view.findViewById(R.id.card_favorites);

        card_myMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_myMeals.setElevation(0);
                Intent i = new Intent(getContext(), ScheduledMeals.class);
                startActivity(i);
            }
        });

        card_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_favorite.setElevation(0);
                Intent i = new Intent(getContext(), FavoriteMeals.class);
                startActivity(i);
            }
        });
    }
}