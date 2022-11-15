package com.rafistra.kedaireka.Model.Populasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopulasiModel {
    @SerializedName("populasi_awal")
    @Expose
    private Integer populasi_awal;

    @SerializedName("populasi_sekarang")
    @Expose
    private Integer populasi_sekarang;

    @SerializedName("jumlah_mortalitas")
    @Expose
    private Integer jumlah_mortalitas;

    @SerializedName("jumlah_penjarangan")
    @Expose
    private Integer jumlah_penjarangan;

    public PopulasiModel(Integer populasi_awal, Integer populasi_sekarang, Integer jumlah_mortalitas, Integer jumlah_penjarangan) {
        this.populasi_awal = populasi_awal;
        this.populasi_sekarang = populasi_sekarang;
        this.jumlah_mortalitas = jumlah_mortalitas;
        this.jumlah_penjarangan = jumlah_penjarangan;
    }

    public Integer getPopulasi_awal() {
        return populasi_awal;
    }

    public void setPopulasi_awal(Integer populasi_awal) {
        this.populasi_awal = populasi_awal;
    }

    public Integer getPopulasi_sekarang() {
        return populasi_sekarang;
    }

    public void setPopulasi_sekarang(Integer populasi_sekarang) {
        this.populasi_sekarang = populasi_sekarang;
    }

    public Integer getJumlah_mortalitas() {
        return jumlah_mortalitas;
    }

    public void setJumlah_mortalitas(Integer jumlah_mortalitas) {
        this.jumlah_mortalitas = jumlah_mortalitas;
    }

    public Integer getJumlah_penjarangan() {
        return jumlah_penjarangan;
    }

    public void setJumlah_penjarangan(Integer jumlah_penjarangan) {
        this.jumlah_penjarangan = jumlah_penjarangan;
    }
}
