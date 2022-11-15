package com.rafistra.kedaireka.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rafistra.kedaireka.Adapter.FragmentHistoryAdapter;
import com.rafistra.kedaireka.Fragment.SubFragmentControl.ControlLantai1;
import com.rafistra.kedaireka.Fragment.SubFragmentControl.ControlLantai2;
import com.rafistra.kedaireka.Fragment.SubFragmentControl.ControlLantai3;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai1;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai2;
import com.rafistra.kedaireka.Fragment.SubFragmentHistory.HistoryLantai3;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusData;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Control extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView backButton;
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        //==================

        viewPager = view.findViewById(R.id.pager_control);
        tabLayout = view.findViewById(R.id.tab_control_lantai);


        return view;
    }

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

        adapter.addFragment(new ControlLantai1(), "Lantai 1");
        adapter.addFragment(new ControlLantai2(), "Lantai 2");
        adapter.addFragment(new ControlLantai3(),"Lantai 3");

        viewPager.setAdapter(adapter);
    }
}