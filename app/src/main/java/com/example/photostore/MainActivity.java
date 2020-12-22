package com.example.photostore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.photostore.Fragments.InspirationFragment;
import com.example.photostore.Fragments.MyMealsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.top_main_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_profile:
                        fragment = new MyMealsFragment();
                        break;
                    case R.id.action_dashboard:
                    default:
                        fragment = new InspirationFragment();
                        break;
                }

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContainer, fragment)
                        .commit();

                return true;
            }
        });
        // Set default selection
        navigationView.setSelectedItemId(R.id.action_profile);
    }

}