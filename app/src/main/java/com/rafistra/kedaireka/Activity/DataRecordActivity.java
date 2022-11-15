package com.rafistra.kedaireka.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rafistra.kedaireka.Fragment.DataGraph;
import com.rafistra.kedaireka.Fragment.RiwayatData;
import com.rafistra.kedaireka.R;

public class DataRecordActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_record);

        bottomNavigation_record = findViewById(R.id.bottom_navigation_data_record);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_data_record, new RiwayatData()).commit();
        bottomNavigation_record.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.nav_riwayat:
                        selectedFragment = new RiwayatData();
                        break;
                    case R.id.nav_grafik:
                        selectedFragment = new DataGraph();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_data_record, selectedFragment).commit();
                return true;
            }
        });
    }
}