package com.rafistra.kedaireka.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafistra.kedaireka.Adapter.HistoryAdapter;
import com.rafistra.kedaireka.Adapter.PenjaranganAdapter;
import com.rafistra.kedaireka.Model.AllData;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganData;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganModel;
import com.rafistra.kedaireka.Model.Populasi.PopulasiModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulasiPenjarangan extends Fragment {

    private RecyclerView recyclerView;
    private PenjaranganAdapter adapter;

    CardView card_addpenjarangan;

    Button btn_inp_penjarangan;

    FloatingActionButton add_penjarangan;

    Integer nodeId, node, lantai;
    final Calendar myCalendar = Calendar.getInstance();

    EditText inp_date, inp_umur, inp_lantai, inp_penjarangan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_populasi_penjarangan, container, false);

        card_addpenjarangan = (CardView) view.findViewById(R.id.card_addpenjarangan);
        add_penjarangan = (FloatingActionButton) view.findViewById(R.id.add_penjarangan);
        btn_inp_penjarangan = (Button) view.findViewById(R.id.btn_inp_penjarangan);

        inp_date = (EditText) view.findViewById(R.id.inp_date_jr);
        inp_umur = (EditText) view.findViewById(R.id.inp_umur_jr);
        inp_lantai = (EditText) view.findViewById(R.id.inp_lantai_jr);
        inp_penjarangan = (EditText) view.findViewById(R.id.inp_mpenjarangan_jr);

        recyclerView= (RecyclerView) view.findViewById(R.id.rv_penjarangan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //sharedpreferences get nodeId
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedPreferences.getString("nodeId","");
        nodeId = Integer.parseInt(nodeId_str);



        btn_inp_penjarangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPenjarangan();
                loadDataPenjarangan();
                card_addpenjarangan.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        add_penjarangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (card_addpenjarangan.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                int w = (recyclerView.getVisibility() == View.VISIBLE)? View.GONE: View.VISIBLE;

//                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                card_addpenjarangan.setVisibility(v);
                recyclerView.setVisibility(w);
                loadDataPenjarangan();
            }
        });

        loadDataPenjarangan();

        datePicker();

        return view;
    }



    private void loadDataPenjarangan() {
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getPenjarangan(nodeId).enqueue(new Callback<PenjaranganModel>() {
            @Override
            public void onResponse(Call<PenjaranganModel> call, Response<PenjaranganModel> response) {
                if (response.isSuccessful()) {
                    List<PenjaranganData> penjaranganDataList = response.body().getPenjaranganData();
                    for (int i = 0; i < penjaranganDataList.size(); i++) {
                        adapter = new PenjaranganAdapter((ArrayList<PenjaranganData>)penjaranganDataList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<PenjaranganModel> call, Throwable t) {

            }
        });
    }

    public void addPenjarangan(){
        try{
            final String date = inp_date.getText().toString();

            final String lantai_str = inp_lantai.getText().toString();
            Integer lantai = Integer.parseInt(lantai_str);

            final String umur_str = inp_umur.getText().toString();
            Integer umur = Integer.parseInt(umur_str);

            final String penjarangan_str = inp_penjarangan.getText().toString();
            Integer jumlah = Integer.parseInt(penjarangan_str);


            APIService service = ApiClient.getClient().create(APIService.class);
            Call<PenjaranganModel> setNekropsi = service.setPenjarangan(nodeId, lantai, date, umur, jumlah);
            setNekropsi.enqueue(new Callback<PenjaranganModel>() {
                @Override
                public void onResponse(Call<PenjaranganModel> call, Response<PenjaranganModel> response) {
                    if (response.isSuccessful()){
                        loadDataPenjarangan();
                        Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext().getApplicationContext(), "Update Gagal", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<PenjaranganModel> call, Throwable t) {
                    Toast.makeText(getContext().getApplicationContext(), "Update Gagal2", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    public void datePicker(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();
            }
        };
        inp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void updateLabel(){
        String myformat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myformat, Locale.US);
        inp_date.setText(dateFormat.format(myCalendar.getTime()));
    }
}
