package com.rafistra.kedaireka.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rafistra.kedaireka.Fragment.Control;
import com.rafistra.kedaireka.Fragment.Dashboard;
import com.rafistra.kedaireka.Fragment.Service;
import com.rafistra.kedaireka.Fragment.Profile;
import com.rafistra.kedaireka.R;

public class HomeActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Dashboard()).commit();
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.nav_dashboard:
                        selectedFragment = new Dashboard();
                        break;
                    case R.id.nav_control:
                        selectedFragment = new Control();
                        break;
                    case  R.id.nav_history:
                        selectedFragment = new Service();
                        break;
                    case  R.id.nav_profile:
                        selectedFragment = new Profile();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}