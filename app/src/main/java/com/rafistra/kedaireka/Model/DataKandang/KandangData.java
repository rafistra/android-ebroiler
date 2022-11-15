package com.rafistra.kedaireka.Model.DataKandang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rafistra.kedaireka.Model.GejalaKlinis.GejalaKlinisData;

import java.util.List;

public class KandangData {
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    @SerializedName("jumlah_lantai")
    @Expose
    private Integer jumlah_lantai;

    @SerializedName("periode")
    @Expose
    private Integer periode;

    @SerializedName("populasi")
    @Expose
    private Integer populasi;

    public KandangData(String nama, String lokasi, Integer jumlah_lantai, Integer periode, Integer populasi) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.jumlah_lantai = jumlah_lantai;
        this.periode = periode;
        this.populasi = populasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getJumlah_lantai() {
        return jumlah_lantai;
    }

    public void setJumlah_lantai(Integer jumlah_lantai) {
        this.jumlah_lantai = jumlah_lantai;
    }

    public Integer getPeriode() {
        return periode;
    }

    public void setPeriode(Integer periode) {
        this.periode = periode;
    }

    public Integer getPopulasi() {
        return populasi;
    }

    public void setPopulasi(Integer populasi) {
        this.populasi = populasi;
    }
}
