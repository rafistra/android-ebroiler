package com.rafistra.kedaireka.Model.Penjarangan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PenjaranganModel {
    @SerializedName("data")
    @Expose
    private List<PenjaranganData> penjaranganData = null;

    public PenjaranganModel(List<PenjaranganData> penjaranganData) {
        this.penjaranganData = penjaranganData;
    }

    public List<PenjaranganData> getPenjaranganData() {
        return penjaranganData;
    }

    public void setPenjaranganData(List<PenjaranganData> penjaranganData) {
        this.penjaranganData = penjaranganData;
    }
}

