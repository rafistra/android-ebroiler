package com.rafistra.kedaireka.Model.Intermiten;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiData;

import java.util.List;

public class IntermitenData {
    @SerializedName("on_interval")
    @Expose
    private Integer on_interval;

    @SerializedName("off_interval")
    @Expose
    private Integer off_interval;

    @SerializedName("jam_pagi")
    @Expose
    private String jam_pagi;

    @SerializedName("jam_malam")
    @Expose
    private String jam_malam;

    public IntermitenData(Integer on_interval, Integer off_interval, String jam_pagi, String jam_malam) {
        this.on_interval = on_interval;
        this.off_interval = off_interval;
        this.jam_pagi = jam_pagi;
        this.jam_malam = jam_malam;
    }

    public Integer getOn_interval() {
        return on_interval;
    }

    public void setOn_interval(Integer on_interval) {
        this.on_interval = on_interval;
    }

    public Integer getOff_interval() {
        return off_interval;
    }

    public void setOff_interval(Integer off_interval) {
        this.off_interval = off_interval;
    }

    public String getJam_pagi() {
        return jam_pagi;
    }

    public void setJam_pagi(String jam_pagi) {
        this.jam_pagi = jam_pagi;
    }

    public String getJam_malam() {
        return jam_malam;
    }

    public void setJam_malam(String jam_malam) {
        this.jam_malam = jam_malam;
    }
}
