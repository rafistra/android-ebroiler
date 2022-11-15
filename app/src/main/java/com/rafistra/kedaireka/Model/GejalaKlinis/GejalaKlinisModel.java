package com.rafistra.kedaireka.Model.GejalaKlinis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GejalaKlinisModel {
    @SerializedName("data")
    @Expose
    private List<GejalaKlinisData> gejalaKlinisData = null;

    public List<GejalaKlinisData> getGejalaKlinisData() {
        return gejalaKlinisData;
    }

    public void setGejalaKlinisData(List<GejalaKlinisData> gejalaKlinisData) {
        this.gejalaKlinisData = gejalaKlinisData;
    }
}
