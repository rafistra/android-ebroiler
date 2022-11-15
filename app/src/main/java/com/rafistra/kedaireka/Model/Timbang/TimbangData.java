package com.rafistra.kedaireka.Model.Timbang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganData;

import java.util.List;

public class TimbangData {
    @SerializedName("nodeId")
    @Expose
    private Integer nodeId;

    @SerializedName("lantai")
    @Expose
    private Integer lantai;

    @SerializedName("umur")
    @Expose
    private Integer umur;

    @SerializedName("massa")
    @Expose
    private Float massa;

    @SerializedName("sampel1")
    @Expose
    private Float sampel1;

    @SerializedName("sampel2")
    @Expose
    private Float sampel2;

    @SerializedName("sampel3")
    @Expose
    private Float sampel3;

    @SerializedName("sampel4")
    @Expose
    private Float sampel4;

    @SerializedName("sampel5")
    @Expose
    private Float sampel5;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    public TimbangData(Integer nodeId, Integer lantai, Integer umur, Float massa, Float sampel1, Float sampel2, Float sampel3, Float sampel4, Float sampel5, String created_at) {
        this.nodeId = nodeId;
        this.lantai = lantai;
        this.umur = umur;
        this.massa = massa;
        this.sampel1 = sampel1;
        this.sampel2 = sampel2;
        this.sampel3 = sampel3;
        this.sampel4 = sampel4;
        this.sampel5 = sampel5;
        this.created_at = created_at;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getLantai() {
        return lantai;
    }

    public void setLantai(Integer lantai) {
        this.lantai = lantai;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Float getMassa() {
        return massa;
    }

    public void setMassa(Float massa) {
        this.massa = massa;
    }

    public Float getSampel1() {
        return sampel1;
    }

    public void setSampel1(Float sampel1) {
        this.sampel1 = sampel1;
    }

    public Float getSampel2() {
        return sampel2;
    }

    public void setSampel2(Float sampel2) {
        this.sampel2 = sampel2;
    }

    public Float getSampel3() {
        return sampel3;
    }

    public void setSampel3(Float sampel3) {
        this.sampel3 = sampel3;
    }

    public Float getSampel4() {
        return sampel4;
    }

    public void setSampel4(Float sampel4) {
        this.sampel4 = sampel4;
    }

    public Float getSampel5() {
        return sampel5;
    }

    public void setSampel5(Float sampel5) {
        this.sampel5 = sampel5;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
