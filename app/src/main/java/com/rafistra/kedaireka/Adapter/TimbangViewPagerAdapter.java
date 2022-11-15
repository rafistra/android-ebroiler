package com.rafistra.kedaireka.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rafistra.kedaireka.Fragment.PopulasiHarian;
import com.rafistra.kedaireka.Fragment.PopulasiNekropsi;
import com.rafistra.kedaireka.Fragment.PopulasiPenjarangan;
import com.rafistra.kedaireka.Fragment.TimbangList;
import com.rafistra.kedaireka.Fragment.TimbangUkur;

public class TimbangViewPagerAdapter extends FragmentStateAdapter {
    public TimbangViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TimbangList();
            case 1:
                return new TimbangUkur();
            default:
                return new TimbangUkur();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
