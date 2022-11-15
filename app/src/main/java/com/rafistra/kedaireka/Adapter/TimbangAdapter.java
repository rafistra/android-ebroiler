package com.rafistra.kedaireka.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganData;
import com.rafistra.kedaireka.Model.Timbang.TimbangData;
import com.rafistra.kedaireka.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimbangAdapter extends RecyclerView.Adapter<TimbangAdapter.ViewHolder> {
    private ArrayList<TimbangData> arrayList;

    public TimbangAdapter(ArrayList<TimbangData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TimbangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_berat,viewGroup,false);
        return new TimbangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimbangAdapter.ViewHolder viewHolder, int i) {
        String date = arrayList.get(i).getCreated_at();
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
        viewHolder.tv_berat.setText(String.valueOf(arrayList.get(i).getMassa()+" Kg"));
        viewHolder.tv_umur.setText(String.valueOf(arrayList.get(i).getUmur()+" Hari"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_umur, tv_berat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date         = (TextView) itemView.findViewById(R.id.br_date);
            tv_umur         = (TextView) itemView.findViewById(R.id.br_umur);
            tv_berat  = (TextView) itemView.findViewById(R.id.br_berat);

        }
    }
}
