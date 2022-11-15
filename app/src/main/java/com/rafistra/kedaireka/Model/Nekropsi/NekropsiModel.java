package com.rafistra.kedaireka.Model.Nekropsi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NekropsiModel {
    @SerializedName("data")
    @Expose
    private List<NekropsiData> nekropsiData = null;

    public List<NekropsiData> getNekropsiData() {
        return nekropsiData;
    }

    public void setNekropsiData(List<NekropsiData> nekropsiData) {
        this.nekropsiData = nekropsiData;
    }
}
