package com.rafistra.kedaireka.Model.Timbang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganData;

import java.util.List;

public class TimbangModel {
    @SerializedName("data")
    @Expose
    private List<TimbangData> timbangData = null;

    public TimbangModel(List<TimbangData> timbangData) {
        this.timbangData = timbangData;
    }

    public List<TimbangData> getTimbangData() {
        return timbangData;
    }

    public void setTimbangData(List<TimbangData> timbangData) {
        this.timbangData = timbangData;
    }
}
