package com.rafistra.kedaireka.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.rafistra.kedaireka.Adapter.TimbangViewPagerAdapter;
import com.rafistra.kedaireka.R;

public class TimbanganActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    TimbangViewPagerAdapter timbangViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timbangan);

        viewPager = findViewById(R.id.vp_timbangan);
        tabLayout = findViewById(R.id.tab_timbang);

        timbangViewPagerAdapter = new TimbangViewPagerAdapter(this);
        viewPager.setAdapter(timbangViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }


}