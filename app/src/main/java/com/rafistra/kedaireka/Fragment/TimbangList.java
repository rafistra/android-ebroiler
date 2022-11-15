package com.rafistra.kedaireka.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafistra.kedaireka.Adapter.NekropsiAdapter;
import com.rafistra.kedaireka.Adapter.TimbangAdapter;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.Model.Timbang.TimbangData;
import com.rafistra.kedaireka.Model.Timbang.TimbangModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimbangList extends Fragment {

    RecyclerView recyclerView;
    TimbangAdapter adapter;

    Integer nodeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timbang_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_timbang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //sharedpreferences get nodeId
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedPreferences.getString("nodeId","");
        nodeId = Integer.parseInt(nodeId_str);

        loadDataTimbang();

        return view;
    }

    private void loadDataTimbang(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getTimbang(nodeId,1).enqueue(new Callback<TimbangModel>() {
            @Override
            public void onResponse(Call<TimbangModel> call, Response<TimbangModel> response) {
                if(response.isSuccessful()){
                    List<TimbangData> dataList = response.body().getTimbangData();
                    for (int i=0; i<dataList.size(); i++){
                        adapter = new TimbangAdapter((ArrayList<TimbangData>)dataList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<TimbangModel> call, Throwable t) {

            }
        });
    }
}