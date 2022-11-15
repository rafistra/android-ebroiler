package com.rafistra.kedaireka.RestApi;

import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusModel;
import com.rafistra.kedaireka.Model.DataKandang.KandangModel;
import com.rafistra.kedaireka.Model.Intermiten.IntermitenModel;
import com.rafistra.kedaireka.Model.Nekropsi.NekropsiModel;
import com.rafistra.kedaireka.Model.HistoryModel;
import com.rafistra.kedaireka.Model.Penjarangan.PenjaranganModel;
import com.rafistra.kedaireka.Model.Populasi.PopulasiModel;
import com.rafistra.kedaireka.Model.Timbang.TimbangModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {



    String main_url = "/api/data/all/";
    String nodeId = "1";
    String lantai = "/2";

    String url = main_url+nodeId+lantai;


    // GET TOKEN API SERVICE
    // region TokenActivityMethods
    @FormUrlEncoded
    @POST("/api/login")
        @Headers({
                "Content-Type: application/x-www-form-urlencoded",
        })
    Call<ResponseBody> getToken(@Field("email") String email,
                                @Field("password") String password);

    String uri = "/api/data/actuator/1/1";
    //APi masih bersifat Statis jadi harus satu-satu
    @GET(uri)
    Call<DashboardStatusModel> getStatusData();

    @GET("/api/data/actuator/1/2")
    Call<DashboardStatusModel> getStatusData2();

    @GET("/api/data/actuator/all")
    Call<DashboardStatusModel> getStatus(@Query("nodeId")Integer nodeId);

    @GET("/api/data/actuator")
    Call<DashboardStatusModel> getActuator(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai);

    @GET("/api/data/all")
    Call<HistoryModel> getHistory(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai);

    @GET("/api/data/latest")
    Call<HistoryModel> getLatestDataSensor(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan1(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust1") Integer exhaust1);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan2(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust2") Integer exhaust2);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan3(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust3") Integer exhaust3);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan4(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust4") Integer exhaust4);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan5(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust5") Integer exhaust5);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan6(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust6") Integer exhaust6);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan7(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust7") Integer exhaust7);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateFan8(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("exhaust8") Integer exhaust8);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateHeater1(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("heater1") Integer heater1);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateHeater2(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("heater2") Integer heater2);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateEvaporator(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("evaporative") Integer evaporative);

    @PUT("/api/data/actuator")
    Call<DashboardStatusModel> updateMode(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("mode") Integer mode);

    @GET("/api/data/nekropsi")
    Call<NekropsiModel> getNekropsi(
            @Query("nodeId") Integer nodeId);

    @POST("/api/data/nekropsi")
    Call<NekropsiModel> setNekropsi(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("tanggal") String tanggal,
            @Query("umur") Integer umur,
            @Query("jumlah_mortalitas") Integer jumlah_mortalitas,
            @Query("gejala_klinis") String gejala_klinis,
            @Query("catatan") String catatan);


    //Mengambil Data Kandang
    @GET("/api/data/kandang")
    Call<KandangModel> getKandang(
            @Query("id") Integer id);

    //Populasi
    @GET("/api/data/populasi")
    Call<PopulasiModel> getPopulasi(
            @Query("nodeId") Integer nodeId
    );

    //Penjarangan
    @GET("/api/data/penjarangan")
    Call<PenjaranganModel> getPenjarangan(
            @Query("nodeId") Integer nodeId
    );

    @POST("/api/data/penjarangan")
    Call<PenjaranganModel> setPenjarangan(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("tanggal") String tanggal,
            @Query("umur") Integer umur,
            @Query("jumlah") Integer jumlah);

    //Intermiten
    @GET("/api/data/setpoint")
    Call<IntermitenModel> getIntermiten(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai
    );

    @POST("/api/data/setpoint")
    Call<IntermitenModel> updateIntervalOn(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("on_interval") Integer on_interval);

    @POST("/api/data/setpoint")
    Call<IntermitenModel> updateIntervalOff(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("off_interval") Integer off_interval);

    @POST("/api/data/setpoint")
    Call<IntermitenModel> updatePagi(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("jam_pagi") String jam_pagi);

    @POST("/api/data/setpoint")
    Call<IntermitenModel> updateMalam(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("jam_malam") String jam_malam);

    //Timbangan
    @GET("/api/data/timbangan")
    Call<TimbangModel> getTimbang(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai
    );

    @POST("/api/data/timbangan")
    Call<TimbangModel> setTimbang(
            @Query("nodeId") Integer nodeId,
            @Query("lantai") Integer lantai,
            @Query("umur") Integer umur,
            @Query("sampel1") Float sampel1,
            @Query("sampel2") Float sampel2,
            @Query("sampel3") Float sampel3,
            @Query("sampel4") Float sampel4,
            @Query("sampel5") Float sampel5);
}



