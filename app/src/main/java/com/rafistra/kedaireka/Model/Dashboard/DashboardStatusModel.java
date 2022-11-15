package com.rafistra.kedaireka.Model.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.AllData;

import java.util.List;

public class DashboardStatusModel {
    @SerializedName("data")
    @Expose
    private List<DashboardStatusData> statusData = null;


    public List<DashboardStatusData> getStatusData() {
        return statusData;
    }

    public void setStatusData(List<DashboardStatusData> statusData) {
        this.statusData = statusData;
    }
}
