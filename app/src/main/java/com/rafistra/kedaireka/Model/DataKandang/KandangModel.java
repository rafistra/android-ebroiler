package com.rafistra.kedaireka.Model.DataKandang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.GejalaKlinis.GejalaKlinisData;

import java.util.List;

public class KandangModel {
    @SerializedName("data")
    @Expose
    private List<KandangData> kandangData = null;

    public KandangModel(List<KandangData> kandangData) {
        this.kandangData = kandangData;
    }

    public List<KandangData> getKandangData() {
        return kandangData;
    }

    public void setKandangData(List<KandangData> kandangData) {
        this.kandangData = kandangData;
    }
}
