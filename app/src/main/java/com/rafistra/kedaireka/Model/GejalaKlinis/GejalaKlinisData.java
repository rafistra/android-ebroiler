package com.rafistra.kedaireka.Model.GejalaKlinis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GejalaKlinisData {
    @SerializedName("gejala_klinis")
    @Expose
    private String gejala_klinis;

    public GejalaKlinisData(String gejala_klinis) {
        this.gejala_klinis = gejala_klinis;
    }

    public String getGejala_klinis() {
        return gejala_klinis;
    }

    public void setGejala_klinis(String gejala_klinis) {
        this.gejala_klinis = gejala_klinis;
    }
}
