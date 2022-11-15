package com.rafistra.kedaireka.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganData;
import com.rafistra.kedaireka.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PenjaranganAdapter extends RecyclerView.Adapter<PenjaranganAdapter.ViewHolder> {
    private ArrayList<PenjaranganData> arrayList;

    public PenjaranganAdapter(java.util.ArrayList<PenjaranganData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PenjaranganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_penjarangan,viewGroup,false);
        return new PenjaranganAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenjaranganAdapter.ViewHolder viewHolder, int i) {
        String date = arrayList.get(i).getTanggal();
        Date dateParse;
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
        try{
            dateParse = input.parse(date);
            viewHolder.tv_date.setText(output.format(dateParse));
        } catch (ParseException e){
            e.printStackTrace();
        }
//        viewHolder.tv_date.setText(arrayList.get(i).getTanggal());
        viewHolder.tv_penjarangan.setText(String.valueOf(arrayList.get(i).getJumlah()+" Ekor"));
        viewHolder.tv_umur.setText(String.valueOf(arrayList.get(i).getUmur()+" Hari"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_umur, tv_penjarangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date         = (TextView) itemView.findViewById(R.id.pe_date);
            tv_umur         = (TextView) itemView.findViewById(R.id.pe_umur);
            tv_penjarangan  = (TextView) itemView.findViewById(R.id.pe_penjarangan);

        }
    }
}
