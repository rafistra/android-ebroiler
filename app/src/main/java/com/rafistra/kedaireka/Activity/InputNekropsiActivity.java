package com.rafistra.kedaireka.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rafistra.kedaireka.Fragment.RiwayatData;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputNekropsiActivity extends AppCompatActivity {

    Button btn_inp_nekropsi;
    EditText inp_date, inp_umur, inp_lantai, inp_mortalitas, inp_gejalaklinis, inp_catatan;
    Integer node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_nekropsi);

        btn_inp_nekropsi = (Button) findViewById(R.id.btn_inp_nekropsi);
        inp_date         = (EditText) findViewById(R.id.inp_date);
        inp_umur         = (EditText) findViewById(R.id.inp_umur);
        inp_lantai       = (EditText) findViewById(R.id.inp_lantai);
        inp_mortalitas   = (EditText) findViewById(R.id.inp_mortalitas);
        inp_gejalaklinis = (EditText) findViewById(R.id.inp_gejalaklinis);
        inp_catatan      = (EditText) findViewById(R.id.inp_catatan);

        //sharedpreferences get nodeId
        SharedPreferences sharedNodeId = getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedNodeId.getString("nodeId", "");
        node   = Integer.parseInt(nodeId_str);

        btn_inp_nekropsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void addNekropsi(){
        final String date = inp_date.getText().toString();

        final String lantai_str = inp_lantai.getText().toString();
        Float lantai = Float.parseFloat(lantai_str);

        final String umur_str = inp_umur.getText().toString();
        Float umur = Float.parseFloat(umur_str);

        final String mortalitas_str = inp_mortalitas.getText().toString();
        Float jumlah_mortalitas = Float.parseFloat(mortalitas_str);

        final String gejala_klinis = inp_gejalaklinis.getText().toString();

        final String catatan = inp_catatan.getText().toString();

//        node, lantai, date, umur, jumlah_mortalitas, gejala_klinis, catatan

//        1,1,"2022-10-04", 6, 10, "Mata merah", "Kedepan harus diperhatikan lagi"
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<NekropsiModel> postNekropsi = service.setNekropsi(1,1,"2022-10-04", 6, 10, "Mata merah", "Kedepan harus diperhatikan lagi");
        postNekropsi.enqueue(new Callback<NekropsiModel>() {
            @Override
            public void onResponse(Call<NekropsiModel> call, Response<NekropsiModel> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), RiwayatData.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<NekropsiModel> call, Throwable t) {

            }
        });
    }
}