package com.rafistra.kedaireka.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rafistra.kedaireka.Activity.MainActivity;
import com.rafistra.kedaireka.R;


public class Profile extends Fragment {

    Button btn_logout;
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btn_logout = view.findViewById(R.id.btn_logout);
        name       = view.findViewById(R.id.name_profil);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLogout();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        callName();


        // Inflate the layout for this fragment
        return view;
    }

    private void callLogout(){
        this.getActivity().getSharedPreferences("WFH", Context.MODE_PRIVATE).edit().clear().commit();
        this.getActivity().getSharedPreferences("NAME", Context.MODE_PRIVATE).edit().clear().commit();
        this.getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE).edit().clear().commit();
    }

    private void callName(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NAME", Context.MODE_PRIVATE);
        String name_owner = sharedPreferences.getString("name","");
        name.setText("Pak "+name_owner);
    }

}