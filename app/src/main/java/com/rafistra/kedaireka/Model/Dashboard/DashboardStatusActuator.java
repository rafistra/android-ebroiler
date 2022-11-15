package com.rafistra.kedaireka.Model.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DashboardStatusActuator {
    @SerializedName("data")
    @Expose
    private Data data;

    public class Data{
        @SerializedName("exhaust1")
        @Expose
        private Integer exhaust1;

        @SerializedName("exhaust2")
        @Expose
        private Integer exhaust2;

        @SerializedName("exhaust3")
        @Expose
        private Integer exhaust3;

        @SerializedName("exhaust4")
        @Expose
        private Integer exhaust4;

        @SerializedName("exhaust5")
        @Expose
        private Integer exhaust5;

        @SerializedName("exhaust6")
        @Expose
        private Integer exhaust6;

        @SerializedName("exhaust7")
        @Expose
        private Integer exhaust7;

        @SerializedName("exhaust8")
        @Expose
        private Integer exhaust8;

        @SerializedName("heater1")
        @Expose
        private Integer heater1;

        @SerializedName("heater2")
        @Expose
        private Integer heater2;

        @SerializedName("evaporative")
        @Expose
        private Integer evaporative;

        @SerializedName("alarm")
        @Expose
        private Integer alarm;

        @SerializedName("sync")
        @Expose
        private Integer sync;

        @SerializedName("lantai")
        @Expose
        private Integer lantai;

        @SerializedName("nodeId")
        @Expose
        private Integer nodeId;

        public Integer getExhaust1() {
            return exhaust1;
        }

        public void setExhaust1(Integer exhaust1) {
            this.exhaust1 = exhaust1;
        }

        public Integer getExhaust2() {
            return exhaust2;
        }

        public void setExhaust2(Integer exhaust2) {
            this.exhaust2 = exhaust2;
        }

        public Integer getExhaust3() {
            return exhaust3;
        }

        public void setExhaust3(Integer exhaust3) {
            this.exhaust3 = exhaust3;
        }

        public Integer getExhaust4() {
            return exhaust4;
        }

        public void setExhaust4(Integer exhaust4) {
            this.exhaust4 = exhaust4;
        }

        public Integer getExhaust5() {
            return exhaust5;
        }

        public void setExhaust5(Integer exhaust5) {
            this.exhaust5 = exhaust5;
        }

        public Integer getExhaust6() {
            return exhaust6;
        }

        public void setExhaust6(Integer exhaust6) {
            this.exhaust6 = exhaust6;
        }

        public Integer getExhaust7() {
            return exhaust7;
        }

        public void setExhaust7(Integer exhaust7) {
            this.exhaust7 = exhaust7;
        }

        public Integer getExhaust8() {
            return exhaust8;
        }

        public void setExhaust8(Integer exhaust8) {
            this.exhaust8 = exhaust8;
        }

        public Integer getHeater1() {
            return heater1;
        }

        public void setHeater1(Integer heater1) {
            this.heater1 = heater1;
        }

        public Integer getHeater2() {
            return heater2;
        }

        public void setHeater2(Integer heater2) {
            this.heater2 = heater2;
        }

        public Integer getEvaporative() {
            return evaporative;
        }

        public void setEvaporative(Integer evaporative) {
            this.evaporative = evaporative;
        }

        public Integer getAlarm() {
            return alarm;
        }

        public void setAlarm(Integer alarm) {
            this.alarm = alarm;
        }

        public Integer getSync() {
            return sync;
        }

        public void setSync(Integer sync) {
            this.sync = sync;
        }

        public Integer getLantai() {
            return lantai;
        }

        public void setLantai(Integer lantai) {
            this.lantai = lantai;
        }

        public Integer getNodeId() {
            return nodeId;
        }

        public void setNodeId(Integer nodeId) {
            this.nodeId = nodeId;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

