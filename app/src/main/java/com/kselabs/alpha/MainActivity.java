package com.kselabs.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kselabs.alpha.fragments.HomeFrag;
import com.kselabs.alpha.fragments.PreviewFrag;
import com.kselabs.alpha.fragments.StatusFrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StatusFrag()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrag = null;
                    //Here we're  switching between fragments on our bottom navigation view
                    switch (item.getItemId()){
                        case R.id.nav_status:
                            selectedFrag = new StatusFrag();
                            break;
                        case R.id.nav_home:
                            selectedFrag = new HomeFrag();
                            break;
                        case R.id.nav_preview:
                            selectedFrag = new PreviewFrag();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFrag).commit();
                    return true;
                }
            };
}
