package com.example.photostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.photostore.Fragments.CreatedMealsFragment;
import com.example.photostore.Fragments.ScheduledMealsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduledMeals extends AppCompatActivity {

    private BottomNavigationView navigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meals);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Scheduled Meals");

        navigationView = findViewById(R.id.top_meals_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_scheduled_meals:
                        fragment = new ScheduledMealsFragment();
                        break;
                    case R.id.action_created_meals:
                    default:
                        fragment = new CreatedMealsFragment();
                        break;
                }

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContainer2, fragment)
                        .commit();

                return true;
            }
        });
        // Set default selection
        navigationView.setSelectedItemId(R.id.action_scheduled_meals);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}