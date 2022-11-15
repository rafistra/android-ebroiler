package com.rafistra.kedaireka.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.Model.Timbang.TimbangModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimbangUkur extends Fragment {

    EditText inp_tb_umur, inp_tb_sampel1, inp_tb_sampel2, inp_tb_sampel3, inp_tb_sampel4, inp_tb_sampel5;

    Button btn_hitung;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timbang_ukur, container, false);

        inp_tb_umur = view.findViewById(R.id.tb_umur);
        inp_tb_sampel1 = view.findViewById(R.id.tb_sampel1);
        inp_tb_sampel2 = view.findViewById(R.id.tb_sampel2);
        inp_tb_sampel3 = view.findViewById(R.id.tb_sampel3);
        inp_tb_sampel4 = view.findViewById(R.id.tb_sampel4);
        inp_tb_sampel5 = view.findViewById(R.id.tb_sampel5);

        btn_hitung = view.findViewById(R.id.btn_tb_hitung);


        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungBerat();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void hitungBerat(){
        final String umur_str = inp_tb_umur.getText().toString();
        Integer umur = Integer.parseInt(umur_str);

        final String sm1_str = inp_tb_sampel1.getText().toString();
        Float sm1 = Float.parseFloat(sm1_str);

        final String sm2_str = inp_tb_sampel2.getText().toString();
        Float sm2 = Float.parseFloat(sm2_str);

        final String sm3_str = inp_tb_sampel3.getText().toString();
        Float sm3 = Float.parseFloat(sm3_str);

        final String sm4_str = inp_tb_sampel4.getText().toString();
        Float sm4 = Float.parseFloat(sm4_str);

        final String sm5_str = inp_tb_sampel5.getText().toString();
        Float sm5 = Float.parseFloat(sm5_str);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<TimbangModel> setTimbang = service.setTimbang(1,1, umur, sm1, sm2, sm3, sm4, sm5);
        setTimbang.enqueue(new Callback<TimbangModel>() {
            @Override
            public void onResponse(Call<TimbangModel> call, Response<TimbangModel> response) {
                if(response.isSuccessful()){

                } else{
                    Toast.makeText(getContext().getApplicationContext(), "Update Gagal", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<TimbangModel> call, Throwable t) {

            }
        });

    }
}