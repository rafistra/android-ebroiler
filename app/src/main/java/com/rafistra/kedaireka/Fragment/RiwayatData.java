package com.rafistra.kedaireka.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.rafistra.kedaireka.Activity.DataRecordActivity;
import com.rafistra.kedaireka.Activity.HomeActivity;
import com.rafistra.kedaireka.Adapter.FragmentHistoryAdapter;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai1;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai2;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai3;
import com.rafistra.kedaireka.R;


public class RiwayatData extends Fragment {


    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView backButton;
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat_data, container, false);

        viewPager = view.findViewById(R.id.pager_history);
        tabLayout = view.findViewById(R.id.tab_history_lantai);
        backButton = view.findViewById(R.id.btn_back_riwayat);
//        frameLayout = view.findViewById(R.id.fragment_riwayat);


//        getChildFragmentManager().beginTransaction().add(R.id.fragment_riwayat, new HistoryLantai1()).commit();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //Call onActivity Create Method

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        FragmentHistoryAdapter adapter = new FragmentHistoryAdapter(getChildFragmentManager());

        adapter.addFragment(new HistoryLantai1(), "Lantai 1");
        adapter.addFragment(new HistoryLantai2(), "Lantai 2");
        adapter.addFragment(new HistoryLantai3(),"Lantai 3");

        viewPager.setAdapter(adapter);
    }
}