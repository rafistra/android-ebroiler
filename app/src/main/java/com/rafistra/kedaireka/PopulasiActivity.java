package com.rafistra.kedaireka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.rafistra.kedaireka.Adapter.ActivityViewPagerAdapter;

public class PopulasiActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ActivityViewPagerAdapter activityViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populasi);

        tabLayout = findViewById(R.id.tab_pops);
        viewPager = findViewById(R.id.vp_populasi);

        activityViewPagerAdapter = new ActivityViewPagerAdapter(this);
        viewPager.setAdapter(activityViewPagerAdapter);

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