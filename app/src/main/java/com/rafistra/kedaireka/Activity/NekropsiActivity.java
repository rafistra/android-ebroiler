package com.rafistra.kedaireka.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rafistra.kedaireka.Adapter.NekropsiAdapter;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NekropsiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NekropsiAdapter adapter;

    Button btn_nekropsi;
    Button btn_inp_nekropsi;

    Integer nodeId;
    ScrollView details;
    LinearLayout layout;

    EditText inp_date, inp_umur, inp_lantai, inp_mortalitas, inp_gejalaklinis, inp_catatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nekropsi);

        recyclerView = (RecyclerView) findViewById(R.id.rv_nekropsi);
//        btn_nekropsi = (Button) findViewById(R.id.btn_nekropsi);
        details = (ScrollView) findViewById(R.id.detail_addnekropsi);
        layout = (LinearLayout) findViewById(R.id.layout_addnekropsi);

        btn_inp_nekropsi = (Button) findViewById(R.id.btn_inp_nekropsi);
        inp_date         = (EditText) findViewById(R.id.inp_date);
        inp_umur         = (EditText) findViewById(R.id.inp_umur);
        inp_lantai       = (EditText) findViewById(R.id.inp_lantai);
        inp_mortalitas   = (EditText) findViewById(R.id.inp_mortalitas);
        inp_gejalaklinis = (EditText) findViewById(R.id.inp_gejalaklinis);
        inp_catatan      = (EditText) findViewById(R.id.inp_catatan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //sharedpreferences get nodeId
        SharedPreferences sharedPreferences = getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedPreferences.getString("nodeId","");
        nodeId = Integer.parseInt(nodeId_str);

        loadDataNekropsi();
        btn_inp_nekropsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNekropsi();
                loadDataNekropsi();
            }
        });

//        btn_nekropsi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addNekropsi();
////                Intent intent = new Intent(getApplicationContext(), InputNekropsiActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });
    }

    private void loadDataNekropsi(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getNekropsi(1).enqueue(new Callback<NekropsiModel>() {
            @Override
            public void onResponse(Call<NekropsiModel> call, Response<NekropsiModel> response) {
                if(response.isSuccessful()){
                    List<NekropsiData> dataList = response.body().getNekropsiData();
                    for (int i=0; i<dataList.size(); i++){
                        adapter = new NekropsiAdapter((ArrayList<NekropsiData>)dataList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<NekropsiModel> call, Throwable t) {

            }
        });
    }

    public void addNekropsi(){

        final String date = inp_date.getText().toString();

        final String lantai_str = inp_lantai.getText().toString();
        Integer lantai = Integer.parseInt(lantai_str);

        final String umur_str = inp_umur.getText().toString();
        Integer umur = Integer.parseInt(umur_str);

        final String mortalitas_str = inp_mortalitas.getText().toString();
        Integer jumlah_mortalitas = Integer.parseInt(mortalitas_str);

        final String gejala_klinis = inp_gejalaklinis.getText().toString();

        final String catatan = inp_catatan.getText().toString();

//        node, lantai, date, umur, jumlah_mortalitas, gejala_klinis, catatan

//        1,1,"2022-10-04", 6, 10, "Mata merah", "Kedepan harus diperhatikan lagi"
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<NekropsiModel> postNekropsi = service.setNekropsi(nodeId, lantai, date, umur, jumlah_mortalitas, gejala_klinis, catatan);
        postNekropsi.enqueue(new Callback<NekropsiModel>() {
            @Override
            public void onResponse(Call<NekropsiModel> call, Response<NekropsiModel> response) {
                if (response.isSuccessful()){
                    loadDataNekropsi();
                }

            }

            @Override
            public void onFailure(Call<NekropsiModel> call, Throwable t) {

            }
        });
    }

//    public void expand(View view) {
//        int v = (details.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//
//        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
//        details.setVisibility(v);
//    }
}