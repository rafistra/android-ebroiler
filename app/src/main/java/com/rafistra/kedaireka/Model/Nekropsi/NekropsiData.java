package com.rafistra.kedaireka.Model.Nekropsi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NekropsiData {
    @SerializedName("nodeId")
    @Expose
    private Integer nodeId;

    @SerializedName("lantai")
    @Expose
    private Integer lantai;

    @SerializedName("jumlah_mortalitas")
    @Expose
    private Integer jumlah_mortalitas;

    @SerializedName("umur")
    @Expose
    private Integer umur;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("gejala_klinis")
    @Expose
    private String gejala_klinis;

    @SerializedName("catatan")
    @Expose
    private String catatan;

    public NekropsiData(Integer nodeId, Integer lantai, Integer jumlah_mortalitas, Integer umur, String tanggal, String gejala_klinis, String catatan) {
        this.nodeId = nodeId;
        this.lantai = lantai;
        this.jumlah_mortalitas = jumlah_mortalitas;
        this.umur = umur;
        this.tanggal = tanggal;
        this.gejala_klinis = gejala_klinis;
        this.catatan = catatan;
    }

    public Integer getJumlah_mortalitas() {
        return jumlah_mortalitas;
    }

    public void setJumlah_mortalitas(Integer jumlah_mortalitas) {
        this.jumlah_mortalitas = jumlah_mortalitas;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGejala_klinis() {
        return gejala_klinis;
    }

    public void setGejala_klinis(String gejala_klinis) {
        this.gejala_klinis = gejala_klinis;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
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
}
