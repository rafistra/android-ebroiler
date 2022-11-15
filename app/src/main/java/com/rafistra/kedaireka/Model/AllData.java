package com.rafistra.kedaireka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllData {
    @SerializedName("nodeId")
    @Expose
    private Integer nodeId;

    @SerializedName("lantai")
    @Expose
    private Integer lantai;

    @SerializedName("day")
    @Expose
    private Integer day;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("ammonia_sensor")
    @Expose
    private Float ammonia_sensor;

    @SerializedName("temperature_sensor")
    @Expose
    private Float temperature_sensor;

    @SerializedName("humidity_sensor")
    @Expose
    private Float humidity_sensor;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getAmmonia_sensor() {
        return ammonia_sensor;
    }

    public void setAmmonia_sensor(Float ammonia_sensor) {
        this.ammonia_sensor = ammonia_sensor;
    }

    public Float getTemperature_sensor() {
        return temperature_sensor;
    }

    public void setTemperature_sensor(Float temperature_sensor) {
        this.temperature_sensor = temperature_sensor;
    }

    public Float getHumidity_sensor() {
        return humidity_sensor;
    }

    public void setHumidity_sensor(Float humidity_sensor) {
        this.humidity_sensor = humidity_sensor;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
