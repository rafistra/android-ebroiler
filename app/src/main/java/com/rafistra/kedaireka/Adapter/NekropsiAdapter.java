package com.rafistra.kedaireka.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;
import com.rafistra.kedaireka.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NekropsiAdapter extends RecyclerView.Adapter<NekropsiAdapter.ViewHolder> {

    private ArrayList<NekropsiData> arrayList;

    public NekropsiAdapter(ArrayList<NekropsiData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_nekropsi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
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
        viewHolder.tv_gejalaklinis.setText(arrayList.get(i).getGejala_klinis());
        viewHolder.tv_mortalitas.setText(String.valueOf(arrayList.get(i).getJumlah_mortalitas()+" Ekor"));
        viewHolder.tv_catatan.setText(arrayList.get(i).getCatatan());
        viewHolder.tv_umur.setText(String.valueOf(arrayList.get(i).getUmur()+" Hari"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_date, tv_umur, tv_gejalaklinis, tv_catatan, tv_mortalitas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date         = (TextView) itemView.findViewById(R.id.date_nekropsi);
            tv_umur         = (TextView) itemView.findViewById(R.id.ne_umur);
            tv_gejalaklinis = (TextView) itemView.findViewById(R.id.ne_gejalaklinis);
            tv_mortalitas   = (TextView) itemView.findViewById(R.id.ne_mortalitas);
            tv_catatan      = (TextView) itemView.findViewById(R.id.ne_catatan);

        }
    }



}
