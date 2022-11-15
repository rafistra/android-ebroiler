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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafistra.kedaireka.Activity.MainActivity;
import com.rafistra.kedaireka.Adapter.NekropsiAdapter;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
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

public class PopulasiNekropsi extends Fragment {

    RecyclerView recyclerView;
    NekropsiAdapter adapter;

    CardView card_addnekropsi;

    Button btn_inp_nekropsi;

    FloatingActionButton add_nekropsi;

    Integer nodeId;
    final Calendar myCalendar = Calendar.getInstance();

    ScrollView details;
    LinearLayout layout;

    EditText inp_date, inp_umur, inp_lantai, inp_mortalitas, inp_gejalaklinis, inp_catatan;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_populasi_nekropsi, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_nekropsi);

        details = (ScrollView) view.findViewById(R.id.detail_addnekropsi);
        layout = (LinearLayout) view.findViewById(R.id.layout_addnekropsi);

        btn_inp_nekropsi = (Button) view.findViewById(R.id.btn_inp_nekropsi);
        inp_date         = (EditText) view.findViewById(R.id.inp_date);
        inp_umur         = (EditText) view.findViewById(R.id.inp_umur);
        inp_lantai       = (EditText) view.findViewById(R.id.inp_lantai);
        inp_mortalitas   = (EditText) view.findViewById(R.id.inp_mortalitas);
        inp_gejalaklinis = (EditText) view.findViewById(R.id.inp_gejalaklinis);
        inp_catatan      = (EditText) view.findViewById(R.id.inp_catatan);

        card_addnekropsi = (CardView) view.findViewById(R.id.card_addnekr);

        add_nekropsi = (FloatingActionButton) view.findViewById(R.id.add_nekropsi);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //sharedpreferences get nodeId
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedPreferences.getString("nodeId","");
        nodeId = Integer.parseInt(nodeId_str);

        btn_inp_nekropsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNekropsi();
                loadDataNekropsi();
                card_addnekropsi.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

//        card_addnekropsi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int v = (details.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//                int w = (recyclerView.getVisibility() == View.VISIBLE)? View.GONE: View.VISIBLE;
//
//                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
//                details.setVisibility(v);
//                recyclerView.setVisibility(w);
//            }
//        });

        add_nekropsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (card_addnekropsi.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                int w = (recyclerView.getVisibility() == View.VISIBLE)? View.GONE: View.VISIBLE;

                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                card_addnekropsi.setVisibility(v);
                recyclerView.setVisibility(w);
                loadDataNekropsi();
            }
        });

        loadDataNekropsi();

        datePicker();

        return view;
    }

    private void loadDataNekropsi(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getNekropsi(nodeId).enqueue(new Callback<NekropsiModel>() {
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
        try{
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
                        Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext().getApplicationContext(), "Update Gagal", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<NekropsiModel> call, Throwable t) {
                    Toast.makeText(getContext().getApplicationContext(), "Update Gagal", Toast.LENGTH_SHORT).show();
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