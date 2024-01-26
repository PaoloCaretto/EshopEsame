package com.example.eshopesame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.productsFragment, ProductsFragment.class, null)
                .setReorderingAllowed(true)
                .commit();

        BottomNavigationView bottomBar = findViewById(R.id.bottomNavigationView);
        bottomBar.setOnItemSelectedListener(item -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.productsFragment, ProductsFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
            return true;
        });


    }
}