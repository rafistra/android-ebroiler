package com.rafistra.kedaireka.Model.Intermiten;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;

import java.util.List;

public class IntermitenModel {
    @SerializedName("data")
    @Expose
    private List<IntermitenData> intermitenData = null;

    public IntermitenModel(List<IntermitenData> intermitenData) {
        this.intermitenData = intermitenData;
    }

    public List<IntermitenData> getIntermitenData() {
        return intermitenData;
    }

    public void setIntermitenData(List<IntermitenData> intermitenData) {
        this.intermitenData = intermitenData;
    }
}
