package com.rafistra.kedaireka.Model.Penjarangan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenjaranganData {
    @SerializedName("nodeId")
    @Expose
    private Integer nodeId;

    @SerializedName("lantai")
    @Expose
    private Integer lantai;

    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;

    @SerializedName("umur")
    @Expose
    private Integer umur;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public PenjaranganData(Integer nodeId, Integer lantai, Integer jumlah, Integer umur, String tanggal) {
        this.nodeId = nodeId;
        this.lantai = lantai;
        this.jumlah = jumlah;
        this.umur = umur;
        this.tanggal = tanggal;
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

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
