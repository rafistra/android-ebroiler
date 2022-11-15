package com.rafistra.kedaireka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryModel {
    @SerializedName("data")
    @Expose
    private List<AllData> data = null;

    public List<AllData> getAllData() {
        return data;
    }

    public void setData(List<AllData> data) {
        this.data = data;
    }
}
