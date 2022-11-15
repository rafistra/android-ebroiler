package com.rafistra.kedaireka.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rafistra.kedaireka.Fragment.PopulasiHarian;
import com.rafistra.kedaireka.Fragment.PopulasiNekropsi;
import com.rafistra.kedaireka.Fragment.PopulasiPenjarangan;
import com.rafistra.kedaireka.Fragment.SubFragmentControl.ControlLantai1;

public class ActivityViewPagerAdapter extends FragmentStateAdapter {
    public ActivityViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PopulasiHarian();
            case 1:
                return new PopulasiNekropsi();
            case 2:
                return new PopulasiPenjarangan();
            default:
                return new PopulasiNekropsi();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
