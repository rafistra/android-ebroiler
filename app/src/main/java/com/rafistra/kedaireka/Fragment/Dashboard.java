package com.rafistra.kedaireka.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.rafistra.kedaireka.Activity.DataRecordActivity;
import com.rafistra.kedaireka.Model.AllData;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusData;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusModel;
import com.rafistra.kedaireka.Model.DataKandang.KandangData;
import com.rafistra.kedaireka.Model.DataKandang.KandangModel;
import com.rafistra.kedaireka.Model.HistoryModel;
import com.rafistra.kedaireka.Model.Intermiten.IntermitenData;
import com.rafistra.kedaireka.Model.Intermiten.IntermitenModel;
import com.rafistra.kedaireka.Model.Populasi.PopulasiModel;
import com.rafistra.kedaireka.PopulasiActivity;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends Fragment {

    TextView date_time, temperature, humidity, ammonia;
    TextView user_owner, user_owner_greet, greeting, txt_mode, txt_lastupdate, txt_umur, txt_populasi;
    TextView txt_fan1, txt_fan2, txt_fan3, txt_fan4, txt_fan5, txt_fan6, txt_fan7, txt_fan8, txt_heater1, txt_heater2, txt_evaporator;
    TextView txt_humidity, txt_temperature, txt_ammonia;
    TextView txt_on, txt_off, txt_pagi, txt_sore;

    ImageView dash_on_fan1, dash_off_fan1, dash_on_fan2, dash_off_fan2, dash_on_fan3, dash_off_fan3, dash_on_fan4, dash_off_fan4, dash_on_fan5, dash_off_fan5, dash_on_fan6, dash_off_fan6, dash_on_fan7, dash_off_fan7, dash_on_fan8, dash_off_fan8, dash_on_heater1, dash_off_heater1, dash_on_heater2, dash_off_heater2, dash_on_evaporator, dash_off_evaporator;
    ImageView more_data_record;
    Button btn_dash_lantai1, btn_off_dash_lantai1, btn_dash_lantai2, btn_off_dash_lantai2, btn_dash_lantai3, btn_off_dash_lantai3;

    CardView card_dash_intermiten, card_dash_populasi;
    DashboardStatusModel status;
    HistoryModel lastdatasensor;
    KandangModel dataKandang;
    PopulasiModel populasiModel;
    IntermitenModel intermitenModel;

    Integer node, lantai, populasi_awal;

    Integer populasi, jumlah_lantai, periode, populasiNow;
    Integer total_penjarangan, total_mortalitas;

    Animation anim_rotate_fan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        date_time        = view.findViewById(R.id.date_time);
        temperature      = view.findViewById(R.id.dash_temperature);
        humidity         = view.findViewById(R.id.dash_humidity);
        ammonia          = view.findViewById(R.id.dash_ammonia);
        user_owner       = view.findViewById(R.id.name_owner);
        user_owner_greet = view.findViewById(R.id.name_owner_greet);
        greeting         = view.findViewById(R.id.greeting);

        dash_on_fan1 = view.findViewById(R.id.dash_on_fan1);
        dash_on_fan2 = view.findViewById(R.id.dash_on_fan2);
        dash_on_fan3 = view.findViewById(R.id.dash_on_fan3);
        dash_on_fan4 = view.findViewById(R.id.dash_on_fan4);
        dash_on_fan5 = view.findViewById(R.id.dash_on_fan5);
        dash_on_fan6 = view.findViewById(R.id.dash_on_fan6);
        dash_on_fan7 = view.findViewById(R.id.dash_on_fan7);
        dash_on_fan8 = view.findViewById(R.id.dash_on_fan8);
        dash_on_heater1 = view.findViewById(R.id.dash_on_heater1);
        dash_on_heater2 = view.findViewById(R.id.dash_on_heater2);
        dash_on_evaporator = view.findViewById(R.id.dash_on_evaporator);
        dash_off_fan1 = view.findViewById(R.id.dash_off_fan1);
        dash_off_fan2 = view.findViewById(R.id.dash_off_fan2);
        dash_off_fan3 = view.findViewById(R.id.dash_off_fan3);
        dash_off_fan4 = view.findViewById(R.id.dash_off_fan4);
        dash_off_fan5 = view.findViewById(R.id.dash_off_fan5);
        dash_off_fan6 = view.findViewById(R.id.dash_off_fan6);
        dash_off_fan7 = view.findViewById(R.id.dash_off_fan7);
        dash_off_fan8 = view.findViewById(R.id.dash_off_fan8);
        dash_off_heater1 = view.findViewById(R.id.dash_off_heater1);
        dash_off_heater2 = view.findViewById(R.id.dash_off_heater2);
        dash_off_evaporator = view.findViewById(R.id.dash_off_evaporator);

        txt_fan1             = view.findViewById(R.id.status_fan);
        txt_fan2             = view.findViewById(R.id.status_fan2);
        txt_fan3             = view.findViewById(R.id.status_fan3);
        txt_fan4             = view.findViewById(R.id.status_fan4);
        txt_fan5             = view.findViewById(R.id.status_fan5);
        txt_fan6             = view.findViewById(R.id.status_fan6);
        txt_fan7             = view.findViewById(R.id.status_fan7);
        txt_fan8             = view.findViewById(R.id.status_fan8);
        txt_heater1          = view.findViewById(R.id.status_heater1);
        txt_heater2          = view.findViewById(R.id.status_heater2);
        txt_evaporator       = view.findViewById(R.id.status_evaporator);

        txt_ammonia          = view.findViewById(R.id.dash_ammonia);
        txt_humidity         = view.findViewById(R.id.dash_humidity);
        txt_temperature      = view.findViewById(R.id.dash_temperature);

        txt_mode             = view.findViewById(R.id.mode_status);
        txt_lastupdate       = view.findViewById(R.id.dash_last_update);
        txt_umur             = view.findViewById(R.id.dash_umur);
        txt_populasi         = view.findViewById(R.id.dash_populasi);

        txt_on     = view.findViewById(R.id.dash_ontime);
        txt_off    = view.findViewById(R.id.dash_offtime);
        txt_pagi   = view.findViewById(R.id.dash_pagi);
        txt_sore   = view.findViewById(R.id.dash_sore);

        //Button
        btn_dash_lantai1     = view.findViewById(R.id.btn_dash_lantai1);
        btn_dash_lantai2     = view.findViewById(R.id.btn_dash_lantai2);
        btn_dash_lantai3     = view.findViewById(R.id.btn_dash_lantai3);
        btn_off_dash_lantai1 = view.findViewById(R.id.btn_off_dash_lantai1);
        btn_off_dash_lantai2 = view.findViewById(R.id.btn_off_dash_lantai2);
        btn_off_dash_lantai3 = view.findViewById(R.id.btn_off_dash_lantai3);

        //Card
        card_dash_intermiten = view.findViewById(R.id.card_dash_intermiten);
        card_dash_populasi = view.findViewById(R.id.card_dash_populasi);

        //Misc
        more_data_record = view.findViewById(R.id.more_data_record);

        //Calendar
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date_time.setText(currentDate);
        //Greeting
        greeting();

        //SHAREDPREFERENCES
        //get name
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NAME", MODE_PRIVATE);
        String name_owner = sharedPreferences.getString("name","");
        user_owner.setText("Pak "+name_owner);
        user_owner_greet.setText("Pak "+name_owner);

        //sharedpreferences get nodeId
        SharedPreferences sharedNodeId = getActivity().getSharedPreferences("KANDANG", MODE_PRIVATE);
        String nodeId_str = sharedNodeId.getString("nodeId","");
        node = Integer.parseInt(nodeId_str);

        more_data_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DataRecordActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        card_dash_populasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PopulasiActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });


        loadPopulasi();


        //Button on CLick
        //--State Awal
        startState(); //nanti bisa pakai sharedpreference saja untuk meload pilihan terakhir
        btn_dash_lantai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(1);
                lantai = 1;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });
        btn_off_dash_lantai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(1);
                lantai = 1;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });
        btn_dash_lantai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(2);
                lantai = 2;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });
        btn_off_dash_lantai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(2);
                lantai = 2;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });
        btn_dash_lantai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(3);
                lantai = 3;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });
        btn_off_dash_lantai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(3);
                lantai = 3;
                intermitenStatus();
                loadStatusData();
                loadLatestDataSensor();
            }
        });


//       loadLatestDataSensor();
        //loadStatusData();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadPopulasi(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getPopulasi(node).enqueue(new Callback<PopulasiModel>() {
            @Override
            public void onResponse(Call<PopulasiModel> call, Response<PopulasiModel> response) {
                if(response.isSuccessful()){
                    int populasi_akhir = response.body().getPopulasi_sekarang();
                    txt_populasi.setText(String.valueOf(populasi_akhir));
                }
            }

            @Override
            public void onFailure(Call<PopulasiModel> call, Throwable t) {

            }
        });
    }

    private  void intermitenStatus(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getIntermiten(node, lantai).enqueue(new Callback<IntermitenModel>() {
            @Override
            public void onResponse(Call<IntermitenModel> call, Response<IntermitenModel> response) {
                if(response.isSuccessful()){
                    intermitenModel = response.body();
                    List<IntermitenData> intermitenDataList = intermitenModel.getIntermitenData();
                    Integer on_interval, off_interval;
                    String jam_pagi, jam_malam;
                    for (int i = 0; i < intermitenDataList.size(); i++) {
                        on_interval = intermitenDataList.get(i).getOn_interval();
                        off_interval = intermitenDataList.get(i).getOff_interval();
                        jam_pagi = intermitenDataList.get(i).getJam_pagi();
                        jam_malam = intermitenDataList.get(i).getJam_malam();

                        txt_on.setText(String.valueOf(on_interval)+" menit");
                        txt_off.setText(String.valueOf(off_interval)+" menit");
                        txt_pagi.setText(jam_pagi);
                        txt_sore.setText(jam_malam);
                    }
                }
            }
            @Override
            public void onFailure(Call<IntermitenModel> call, Throwable t) {

            }
        });
    }

    private void loadLatestDataSensor(){
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getLatestDataSensor(node,lantai).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                if(response.isSuccessful()){
                    lastdatasensor = response.body();
                    List<AllData> lastDataSensor = lastdatasensor.getAllData();
                    Float ammonia_sensor, temperature_sensor, humidity_sensor;
                    String time;
                    Integer day;
                    for(int i=0; i<lastDataSensor.size();i++){
                         ammonia_sensor = lastDataSensor.get(i).getAmmonia_sensor();
                         temperature_sensor = lastDataSensor.get(i).getTemperature_sensor();
                         humidity_sensor = lastDataSensor.get(i).getHumidity_sensor();
                         time = lastDataSensor.get(i).getTime();
                         day = lastDataSensor.get(i).getDay();

                         txt_umur.setText(String.valueOf(day)+" Hari");
                         txt_lastupdate.setText(time);
                         txt_ammonia.setText(String.valueOf(ammonia_sensor)+" ppm");
                         txt_temperature.setText(String.valueOf(temperature_sensor)+"°C");
                         txt_humidity.setText(String.valueOf(humidity_sensor)+"%");
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {

            }
        });
    }

    private void loadStatusData() {
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getActuator(node,lantai).enqueue(new Callback<DashboardStatusModel>() {
            @Override
            public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                if(response.isSuccessful()){
                    status = response.body();
                    loadStatusActuator();
                }
            }
            @Override
            public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
            }
        });
    }
    private void loadStatusActuator(){
        List<DashboardStatusData> statusList = status.getStatusData();
        Integer exhaust1, exhaust2, exhaust3, exhaust4, exhaust5, exhaust6, exhaust7, exhaust8, heater1, heater2, evaporator, mode;
        for(int i=0; i<statusList.size();i++){
//            nodeId   = statusList.get(i).getNodeId();
            exhaust1 = statusList.get(i).getExhaust1();
            exhaust2 = statusList.get(i).getExhaust2();
            exhaust3 = statusList.get(i).getExhaust3();
            exhaust4 = statusList.get(i).getExhaust4();
            exhaust5 = statusList.get(i).getExhaust5();
            exhaust6 = statusList.get(i).getExhaust6();
            exhaust7 = statusList.get(i).getExhaust7();
            exhaust8 = statusList.get(i).getExhaust8();
            heater1  = statusList.get(i).getHeater1();
            heater2  = statusList.get(i).getHeater2();
            evaporator = statusList.get(i).getEvaporative();
            mode = statusList.get(i).getMode();

            //Mode
            if(mode==0){
                txt_mode.setText("Otomatis");
                card_dash_intermiten.setVisibility(View.VISIBLE);
            }
            else if(mode==1){
                txt_mode.setText("Manual");
                card_dash_intermiten.setVisibility(View.GONE);
            }
            //Kipas 1
            if(exhaust1==1){
                txt_fan1.setText("ON");
                txt_fan1.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan1.setVisibility(View.VISIBLE);
                dash_off_fan1.setVisibility(GONE);

            } else {
                txt_fan1.setText("OFF");
                txt_fan1.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan1.setVisibility(GONE);
                dash_off_fan1.setVisibility(View.VISIBLE);
            }

            //Kipas 2
            if(exhaust2==1){
                txt_fan2.setText("ON");
                txt_fan2.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan2.setVisibility(View.VISIBLE);
                dash_off_fan2.setVisibility(GONE);
            } else {
                txt_fan2.setText("OFF");
                txt_fan2.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan2.setVisibility(GONE);
                dash_off_fan2.setVisibility(View.VISIBLE);
            }

            //Kipas 3
            if(exhaust3==1){
                txt_fan3.setText("ON");
                txt_fan3.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan3.setVisibility(View.VISIBLE);
                dash_off_fan3.setVisibility(GONE);
            } else {
                txt_fan3.setText("OFF");
                txt_fan3.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan3.setVisibility(GONE);
                dash_off_fan3.setVisibility(View.VISIBLE);
            }

            //Kipas 4
            if(exhaust4==1){
                txt_fan4.setText("ON");
                txt_fan4.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan4.setVisibility(View.VISIBLE);
                dash_off_fan4.setVisibility(GONE);
            } else {
                txt_fan4.setText("OFF");
                txt_fan4.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan4.setVisibility(GONE);
                dash_off_fan4.setVisibility(View.VISIBLE);
            }

            //Kipas 5
            if(exhaust5==1){
                txt_fan5.setText("ON");
                txt_fan5.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan5.setVisibility(View.VISIBLE);
                dash_off_fan5.setVisibility(GONE);
            } else {
                txt_fan5.setText("OFF");
                txt_fan5.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan5.setVisibility(GONE);
                dash_off_fan5.setVisibility(View.VISIBLE);
            }

            //Kipas 6
            if(exhaust6==1){
                txt_fan6.setText("ON");
                txt_fan6.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan6.setVisibility(View.VISIBLE);
                dash_off_fan6.setVisibility(GONE);
            } else {
                txt_fan6.setText("OFF");
                txt_fan6.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan6.setVisibility(GONE);
                dash_off_fan6.setVisibility(View.VISIBLE);
            }

            //Kipas 7
            if(exhaust7==1){
                txt_fan7.setText("ON");
                txt_fan7.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan7.setVisibility(View.VISIBLE);
                dash_off_fan7.setVisibility(GONE);
            } else {
                txt_fan7.setText("OFF");
                txt_fan7.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan7.setVisibility(GONE);
                dash_off_fan7.setVisibility(View.VISIBLE);
            }

            //Kipas 8
            if(exhaust8==1){
                txt_fan8.setText("ON");
                txt_fan8.setTextColor(Color.parseColor("#22C600"));
                dash_on_fan8.setVisibility(View.VISIBLE);
                dash_off_fan8.setVisibility(GONE);
            } else {
                txt_fan8.setText("OFF");
                txt_fan8.setTextColor(Color.parseColor("#C60000"));
                dash_on_fan8.setVisibility(GONE);
                dash_off_fan8.setVisibility(View.VISIBLE);
            }
            //txt_fan2.setText(""+statusList.get(i).getExhaust2());
            //Heater1
            if(heater1==1){
                txt_heater1.setText("ON");
                txt_heater1.setTextColor(Color.parseColor("#22C600"));
                dash_on_heater1.setVisibility(View.VISIBLE);
                dash_off_heater1.setVisibility(GONE);
            } else {
                txt_heater1.setText("OFF");
                txt_heater1.setTextColor(Color.parseColor("#C60000"));
                dash_on_heater1.setVisibility(GONE);
                dash_off_heater1.setVisibility(View.VISIBLE);
            }

            //Heater2
            if(heater2==1){
                txt_heater2.setText("ON");
                txt_heater2.setTextColor(Color.parseColor("#22C600"));
                dash_on_heater2.setVisibility(View.VISIBLE);
                dash_off_heater2.setVisibility(GONE);
            } else {
                txt_heater2.setText("OFF");
                txt_heater2.setTextColor(Color.parseColor("#C60000"));
                dash_on_heater2.setVisibility(GONE);
                dash_off_heater2.setVisibility(View.VISIBLE);
            }

            //Evaporator
            if(evaporator==1){
                txt_evaporator.setText("ON");
                txt_evaporator.setTextColor(Color.parseColor("#22C600"));
                dash_on_evaporator.setVisibility(View.VISIBLE);
                dash_off_evaporator.setVisibility(GONE);
            } else {
                txt_evaporator.setText("OFF");
                txt_evaporator.setTextColor(Color.parseColor("#C60000"));
                dash_on_evaporator.setVisibility(GONE);
                dash_off_evaporator.setVisibility(View.VISIBLE);
            }
        }
    }
    private void startState(){
        lantai = 1;
        loadStatusData();
        loadLatestDataSensor();
        visibility(1);
        intermitenStatus();
    }
    private void visibility(int lantai){
        if(lantai==1){
            btn_dash_lantai1.setVisibility(View.VISIBLE);
            btn_off_dash_lantai1.setVisibility(GONE);
            btn_dash_lantai2.setVisibility(GONE);
            btn_off_dash_lantai2.setVisibility(View.VISIBLE);
            btn_dash_lantai3.setVisibility(GONE);
            btn_off_dash_lantai3.setVisibility(View.VISIBLE);
        } if(lantai==2){
            btn_dash_lantai1.setVisibility(GONE);
            btn_off_dash_lantai1.setVisibility(View.VISIBLE);
            btn_dash_lantai2.setVisibility(View.VISIBLE);
            btn_off_dash_lantai2.setVisibility(GONE);
            btn_dash_lantai3.setVisibility(GONE);
            btn_off_dash_lantai3.setVisibility(View.VISIBLE);
        } if(lantai==3){
            btn_dash_lantai1.setVisibility(GONE);
            btn_off_dash_lantai1.setVisibility(View.VISIBLE);
            btn_dash_lantai2.setVisibility(GONE);
            btn_off_dash_lantai2.setVisibility(View.VISIBLE);
            btn_dash_lantai3.setVisibility(View.VISIBLE);
            btn_off_dash_lantai3.setVisibility(GONE);
        }
    }

    private void greeting(){
        Calendar kalender = Calendar.getInstance();
        int hour = kalender.get(Calendar.HOUR_OF_DAY);

        if(hour >= 0 && hour < 11){
            greeting.setText("Selamat Pagi, ");
        }
        else if(hour >= 11 && hour < 15){
            greeting.setText("Selamat Siang, ");
        }
        else if(hour >= 15 && hour < 16){
            greeting.setText("Selamat Sore, ");
        }
        else if(hour >= 16 && hour < 24){
            greeting.setText("Selamat Malam, ");
//            greeting.setTextColor(Color.parseColor("#FFFFFFFF"));
//            user_owner_greet.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
        else {
            greeting.setText("Halo, ");
        }
    }
}