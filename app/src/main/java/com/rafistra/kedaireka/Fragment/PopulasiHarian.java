package com.rafistra.kedaireka.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafistra.kedaireka.Model.Populasi.PopulasiModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulasiHarian extends Fragment {

    TextView txt_popawal, txt_popsekarang, txt_popmati, txt_poppenjarangan;
    Integer node;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_populasi_harian, container, false);
        // Inflate the layout for this fragment

        txt_popawal = view.findViewById(R.id.pop_awal);
        txt_popsekarang = view.findViewById(R.id.pop_sekarang);
        txt_popmati = view.findViewById(R.id.pop_mati);
        txt_poppenjarangan = view.findViewById(R.id.pop_penjarangan);

        //sharedpreferences get nodeId
        SharedPreferences sharedNodeId = getActivity().getSharedPreferences("KANDANG", MODE_PRIVATE);
        String nodeId_str = sharedNodeId.getString("nodeId","");
        node = Integer.parseInt(nodeId_str);

        loadPopulasi();


        return view;
    }

    private void loadPopulasi(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getPopulasi(node).enqueue(new Callback<PopulasiModel>() {
            @Override
            public void onResponse(Call<PopulasiModel> call, Response<PopulasiModel> response) {
                if(response.isSuccessful()){
                    int populasi_akhir = response.body().getPopulasi_sekarang();
                    txt_popsekarang.setText(String.valueOf(populasi_akhir));

                    int populasi_awal = response.body().getPopulasi_awal();
                    txt_popawal.setText(String.valueOf(populasi_awal));

                    int pjr = response.body().getJumlah_penjarangan();
                    txt_poppenjarangan.setText(String.valueOf(pjr));

                    int mor = response.body().getJumlah_mortalitas();
                    txt_popmati.setText(String.valueOf(mor));
                }
            }

            @Override
            public void onFailure(Call<PopulasiModel> call, Throwable t) {

            }
        });
    }
}