package com.rafistra.kedaireka.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.rafistra.kedaireka.PopulasiActivity;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.Activity.TimbanganActivity;


public class Service extends Fragment {


    CardView service_wa, service_nekropsi, service_timbangan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        // Inflate the layout for this fragment

        service_wa = (CardView) view.findViewById(R.id.service_wa);
        service_nekropsi = (CardView) view.findViewById(R.id.sercie_nekropsi);
        service_timbangan = (CardView) view.findViewById(R.id.service_timbangan);

        service_wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_wa = "https://Wa.me/+6289504808145?text=Halo saya mau konsultasi, apakah bisa?";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url_wa));
                startActivity(intent);
            }
        });

        service_nekropsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PopulasiActivity.class);
                startActivity(intent);
            }
        });

        service_timbangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TimbanganActivity.class);
                startActivity(intent);
            }
        });

//        loadDataHistory();

        return view;
    }



}