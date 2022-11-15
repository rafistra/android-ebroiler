package com.rafistra.kedaireka.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafistra.kedaireka.Model.AllData;
import com.rafistra.kedaireka.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

private ArrayList<AllData> arrayList;

    public HistoryAdapter(ArrayList<AllData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.tv_nodeId.setText(arrayList.get(i).getNodeId());
        viewHolder.tv_date.setText(arrayList.get(i).getDate()+"/");
        viewHolder.tv_time.setText(arrayList.get(i).getTime());
        viewHolder.tv_temperature.setText(arrayList.get(i).getTemperature_sensor()+" °C");
        viewHolder.tv_humidity.setText(arrayList.get(i).getHumidity_sensor()+" %");
        viewHolder.tv_ammonia.setText(arrayList.get(i).getAmmonia_sensor()+" ppm");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_time, tv_ammonia, tv_temperature, tv_humidity, tv_nodeId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date        = (TextView) itemView.findViewById(R.id.tv_date);
            tv_time        = (TextView) itemView.findViewById(R.id.tv_time);
            tv_ammonia     = (TextView) itemView.findViewById(R.id.tv_ammonia);
            tv_temperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            tv_humidity    = (TextView) itemView.findViewById(R.id.tv_humidity);
        }
    }
}
