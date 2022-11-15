package com.rafistra.kedaireka.Fragment.SubFragmentHistory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafistra.kedaireka.Adapter.HistoryAdapter;
import com.rafistra.kedaireka.Model.AllData;
import com.rafistra.kedaireka.Model.HistoryModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryLantai1 extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    Integer nodeId, node, lantai;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_lantai1, container, false);

        recyclerView= (RecyclerView) view.findViewById(R.id.rv_lantai1);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //sharedpreferences get nodeId
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedPreferences.getString("nodeId","");
        nodeId = Integer.parseInt(nodeId_str);
        loadDataHistory();

        // Inflate the layout for this fragment
        return view;
    }

    private void loadDataHistory() {
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getHistory(nodeId,1).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                if(response.isSuccessful()){
                    List<AllData> dataList = response.body().getAllData();
                    for(int i=0; i<dataList.size(); i++){
                        adapter = new HistoryAdapter((ArrayList<AllData>)dataList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {

            }
        });
    }

}