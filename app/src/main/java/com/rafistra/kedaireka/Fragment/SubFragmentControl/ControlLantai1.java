package com.rafistra.kedaireka.Fragment.SubFragmentControl;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rafistra.kedaireka.Activity.MainActivity;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusData;
import com.rafistra.kedaireka.Model.Dashboard.DashboardStatusModel;
import com.rafistra.kedaireka.Model.Intermiten.IntermitenData;
import com.rafistra.kedaireka.Model.Intermiten.IntermitenModel;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControlLantai1 extends Fragment {

    Switch switch_modekontrol, switch_kipas1, switch_kipas2, switch_kipas3, switch_kipas4, switch_kipas5, switch_kipas6, switch_kipas7, switch_kipas8, switch_heater1, switch_heater2, switch_evaporator;
    ImageView ic_on_kipas1, ic_off_kipas1,
            ic_on_kipas2, ic_off_kipas2,
            ic_on_kipas3, ic_off_kipas3,
            ic_on_kipas4, ic_off_kipas4,
            ic_on_kipas5, ic_off_kipas5,
            ic_on_kipas6, ic_off_kipas6,
            ic_on_kipas7, ic_off_kipas7,
            ic_on_kipas8, ic_off_kipas8,
            ic_on_heater1, ic_off_heater1,
            ic_on_heater2, ic_off_heater2, ic_on_evaporator, ic_off_evaporator;
    CardView card_atursetpoint, card_aturlantai, card_aturkontrol, card_intermiten_info;
    Button btn_update_imtn_on, btn_update_imtn_off, btn_update_imtn_pagi, btn_update_imtn_malam;

    TextView coba,txt_imtn_on, txt_imtn_off, txt_imtn_pagi, txt_imtn_malam;
    EditText inp_imtn_on, inp_imtn_pagi, inp_imnt_off, inp_imtn_malam;

    Integer node, lantai, state;

    DashboardStatusModel status;
    IntermitenModel intermitenModel;

    Integer exhaust1, exhaust2, exhaust3, exhaust4, exhaust5, exhaust6, exhaust7, exhaust8, heater1, heater2, evaporator, mode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_lantai1, container, false);

        switch_modekontrol = (Switch) view.findViewById(R.id.switch_modekontrol);
        switch_kipas1 = (Switch) view.findViewById(R.id.switch_kipas1);
        switch_kipas2 = (Switch) view.findViewById(R.id.switch_kipas2);
        switch_kipas3 = (Switch) view.findViewById(R.id.switch_kipas3);
        switch_kipas4 = (Switch) view.findViewById(R.id.switch_kipas4);
        switch_kipas5 = (Switch) view.findViewById(R.id.switch_kipas5);
        switch_kipas6 = (Switch) view.findViewById(R.id.switch_kipas6);
        switch_kipas7 = (Switch) view.findViewById(R.id.switch_kipas7);
        switch_kipas8 = (Switch) view.findViewById(R.id.switch_kipas8);
        switch_heater1 = (Switch) view.findViewById(R.id.switch_pemanas1);
        switch_heater2 = (Switch) view.findViewById(R.id.switch_pemanas2);
        switch_evaporator = (Switch) view.findViewById(R.id.switch_evaporator);
//        btn_ubahmode = (Button) view.findViewById(R.id.control_ubahmode);
        btn_update_imtn_on = (Button) view.findViewById(R.id.btn_update_imtn_on);
        btn_update_imtn_off = (Button) view.findViewById(R.id.btn_update_imtn_off);
        btn_update_imtn_pagi = (Button) view.findViewById(R.id.btn_update_imtn_pagi);
        btn_update_imtn_malam = (Button) view.findViewById(R.id.btn_update_imtn_malam);

        ic_on_kipas1 = (ImageView) view.findViewById(R.id.ic_on_kipas1);
        ic_off_kipas1 = (ImageView) view.findViewById(R.id.ic_off_kipas1);
        ic_on_kipas2 = (ImageView) view.findViewById(R.id.ic_on_kipas2);
        ic_off_kipas2 = (ImageView) view.findViewById(R.id.ic_off_kipas2);
        ic_on_kipas3 = (ImageView) view.findViewById(R.id.ic_on_kipas3);
        ic_off_kipas3 = (ImageView) view.findViewById(R.id.ic_off_kipas3);
        ic_on_kipas4 = (ImageView) view.findViewById(R.id.ic_on_kipas4);
        ic_off_kipas4 = (ImageView) view.findViewById(R.id.ic_off_kipas4);
        ic_on_kipas5 = (ImageView) view.findViewById(R.id.ic_on_kipas5);
        ic_off_kipas5 = (ImageView) view.findViewById(R.id.ic_off_kipas5);
        ic_on_kipas6 = (ImageView) view.findViewById(R.id.ic_on_kipas6);
        ic_off_kipas6 = (ImageView) view.findViewById(R.id.ic_off_kipas6);
        ic_on_kipas7 = (ImageView) view.findViewById(R.id.ic_on_kipas7);
        ic_off_kipas7 = (ImageView) view.findViewById(R.id.ic_off_kipas7);
        ic_on_kipas8 = (ImageView) view.findViewById(R.id.ic_on_kipas8);
        ic_off_kipas8 = (ImageView) view.findViewById(R.id.ic_off_kipas8);
        ic_on_heater1 = (ImageView) view.findViewById(R.id.ic_on_heater1);
        ic_off_heater1 = (ImageView) view.findViewById(R.id.ic_off_heater1);
        ic_on_heater2 = (ImageView) view.findViewById(R.id.ic_on_heater2);
        ic_off_heater2 = (ImageView) view.findViewById(R.id.ic_off_heater2);
        ic_on_evaporator = (ImageView) view.findViewById(R.id.ic_on_evaporator);
        ic_off_evaporator = (ImageView) view.findViewById(R.id.ic_off_evaporator);

        card_aturkontrol = (CardView) view.findViewById(R.id.card_aturkontrol);
        card_atursetpoint = (CardView) view.findViewById(R.id.card_atursetpoint);
        card_intermiten_info = (CardView) view.findViewById(R.id.card_intermiten_info);

        txt_imtn_on = view.findViewById(R.id.imtn_on);
        txt_imtn_off = view.findViewById(R.id.imtn_off);
        txt_imtn_pagi = view.findViewById(R.id.imtn_pagi);
        txt_imtn_malam = view.findViewById(R.id.imtn_malam);

        inp_imtn_on = view.findViewById(R.id.inp_imtn_on);
        inp_imtn_pagi = view.findViewById(R.id.inp_imtn_pagi);
        inp_imnt_off = view.findViewById(R.id.inp_imtn_off);
        inp_imtn_malam = view.findViewById(R.id.inp_imtn_malam);

        //sharedpreferences get nodeId
        SharedPreferences sharedNodeId = getActivity().getSharedPreferences("KANDANG", Context.MODE_PRIVATE);
        String nodeId_str = sharedNodeId.getString("nodeId", "");
        node   = Integer.parseInt(nodeId_str);

        lantai = 1;

        statusSwitch();
        switchActuator();
        intermitenStatus();
        btn_update_imtn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIntermiten("on");

            }
        });

        btn_update_imtn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIntermiten("off");

            }
        });

        inp_imtn_pagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker("pagi");
            }
        });


        inp_imtn_malam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker("malam");
            }
        });

        btn_update_imtn_pagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIntermiten("pagi");

            }
        });

        btn_update_imtn_malam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIntermiten("malam");

            }
        });

        return view;
    }

    private void timePicker(String waktu){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(waktu.equals("pagi")){
                    inp_imtn_pagi.setText( String.format("%02d:%02d" , selectedHour, selectedMinute));
                }
                if(waktu.equals("malam")){
                    inp_imtn_malam.setText( String.format("%02d:%02d" , selectedHour, selectedMinute));
                }
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Pilih Jam");
        mTimePicker.show();
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

                        txt_imtn_on.setText(String.valueOf(on_interval)+" menit");
                        txt_imtn_off.setText(String.valueOf(off_interval)+" menit");
                        txt_imtn_pagi.setText(jam_pagi);
                        txt_imtn_malam.setText(jam_malam);
                    }
                }
            }
            @Override
            public void onFailure(Call<IntermitenModel> call, Throwable t) {

            }
        });
    }
    private void switchActuator(){
        switch_kipas1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas1.setVisibility(View.VISIBLE);
                    ic_off_kipas1.setVisibility(View.GONE);
                    updateActuator("fan1");
                } else {
                    state = 0;
                    ic_on_kipas1.setVisibility(View.GONE);
                    ic_off_kipas1.setVisibility(View.VISIBLE);
                    updateActuator("fan1");
                }
            }
        });
        switch_kipas2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas2.setVisibility(View.VISIBLE);
                    ic_off_kipas2.setVisibility(View.GONE);
                    updateActuator("fan2");
                } else {
                    state = 0;
                    ic_on_kipas2.setVisibility(View.GONE);
                    ic_off_kipas2.setVisibility(View.VISIBLE);
                    updateActuator("fan2");
                }
            }
        });
        switch_kipas3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas3.setVisibility(View.VISIBLE);
                    ic_off_kipas3.setVisibility(View.GONE);
                    updateActuator("fan3");
                } else {
                    state = 0;
                    ic_on_kipas3.setVisibility(View.GONE);
                    ic_off_kipas3.setVisibility(View.VISIBLE);
                    updateActuator("fan3");
                }
            }
        });
        switch_kipas4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas4.setVisibility(View.VISIBLE);
                    ic_off_kipas4.setVisibility(View.GONE);
                    updateActuator("fan4");
                } else {
                    state = 0;
                    ic_on_kipas4.setVisibility(View.GONE);
                    ic_off_kipas4.setVisibility(View.VISIBLE);
                    updateActuator("fan4");
                }
            }
        });
        switch_kipas5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas5.setVisibility(View.VISIBLE);
                    ic_off_kipas5.setVisibility(View.GONE);
                    updateActuator("fan5");
                } else {
                    state = 0;
                    ic_on_kipas5.setVisibility(View.GONE);
                    ic_off_kipas5.setVisibility(View.VISIBLE);
                    updateActuator("fan5");
                }
            }
        });
        switch_kipas6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas6.setVisibility(View.VISIBLE);
                    ic_off_kipas6.setVisibility(View.GONE);
                    updateActuator("fan6");
                } else {
                    state = 0;
                    ic_on_kipas6.setVisibility(View.GONE);
                    ic_off_kipas6.setVisibility(View.VISIBLE);
                    updateActuator("fan6");
                }
            }
        });
        switch_kipas7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas7.setVisibility(View.VISIBLE);
                    ic_off_kipas7.setVisibility(View.GONE);
                    updateActuator("fan7");
                } else {
                    state = 0;
                    ic_on_kipas7.setVisibility(View.GONE);
                    ic_off_kipas7.setVisibility(View.VISIBLE);
                    updateActuator("fan7");
                }
            }
        });
        switch_kipas8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_kipas8.setVisibility(View.VISIBLE);
                    ic_off_kipas8.setVisibility(View.GONE);
                    updateActuator("fan8");
                } else {
                    state = 0;
                    ic_on_kipas8.setVisibility(View.GONE);
                    ic_off_kipas8.setVisibility(View.VISIBLE);
                    updateActuator("fan8");
                }
            }
        });
        switch_heater1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_heater1.setVisibility(View.VISIBLE);
                    ic_off_heater1.setVisibility(View.GONE);
                    updateActuator("heater1");
                } else {
                    state = 0;
                    ic_on_heater1.setVisibility(View.GONE);
                    ic_off_heater1.setVisibility(View.VISIBLE);
                    updateActuator("heater1");
                }
            }
        });
        switch_heater2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_heater2.setVisibility(View.VISIBLE);
                    ic_off_heater2.setVisibility(View.GONE);
                    updateActuator("heater2");

                } else {
                    state = 0;
                    ic_on_heater2.setVisibility(View.GONE);
                    ic_off_heater2.setVisibility(View.VISIBLE);
                    updateActuator("heater2");

                }
            }
        });
        switch_evaporator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    state = 1;
                    ic_on_evaporator.setVisibility(View.VISIBLE);
                    ic_off_evaporator.setVisibility(View.GONE);
                    updateActuator("evaporator");
                } else {
                    state = 0;
                    ic_on_evaporator.setVisibility(View.GONE);
                    ic_off_evaporator.setVisibility(View.VISIBLE);
                    updateActuator("evaporator");
                }
            }
        });
        switch_modekontrol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    state = 0;
                    card_atursetpoint.setVisibility(View.VISIBLE);
                    card_aturkontrol.setVisibility(View.GONE);
                    card_intermiten_info.setVisibility(View.VISIBLE);
                    updateActuator("mode");
                } else{
                    state = 1;
                    card_atursetpoint.setVisibility(View.GONE);
                    card_aturkontrol.setVisibility(View.VISIBLE);
                    card_intermiten_info.setVisibility(View.GONE);
                    updateActuator("mode");
                }
            }
        });
    }

    private void statusSwitch() {
        APIService service = ApiClient.getClient().create(APIService.class);
        service.getActuator(node, lantai).enqueue(new Callback<DashboardStatusModel>() {
            @Override
            public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                if (response.isSuccessful()) {
                    status = response.body();
                    statusData();

//                    updateActuator("mode");
                }
            }

            @Override
            public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
            }
        });
    }

    private void statusData() {
        List<DashboardStatusData> statusList = status.getStatusData();
//        Integer exhaust1, exhaust2, exhaust3, exhaust4, exhaust5, exhaust6, exhaust7, exhaust8, heater1, heater2, evaporator, mode;
        for (int i = 0; i < statusList.size(); i++) {
            exhaust1 = statusList.get(i).getExhaust1();
            exhaust2 = statusList.get(i).getExhaust2();
            exhaust3 = statusList.get(i).getExhaust3();
            exhaust4 = statusList.get(i).getExhaust4();
            exhaust5 = statusList.get(i).getExhaust5();
            exhaust6 = statusList.get(i).getExhaust6();
            exhaust7 = statusList.get(i).getExhaust7();
            exhaust8 = statusList.get(i).getExhaust8();
            heater1 = statusList.get(i).getHeater1();
            heater2 = statusList.get(i).getHeater2();
            evaporator = statusList.get(i).getEvaporative();
            mode    = statusList.get(i).getMode();
            if (exhaust1 == 1) {
                switch_kipas1.setChecked(true);
                ic_on_kipas1.setVisibility(View.VISIBLE);
                ic_off_kipas1.setVisibility(View.GONE);
            }
            else if (exhaust1 == 0) {
                switch_kipas1.setChecked(false);
                ic_on_kipas1.setVisibility(View.GONE);
                ic_off_kipas1.setVisibility(View.VISIBLE);
            }
            if (exhaust2 == 1) {
                switch_kipas2.setChecked(true);
                ic_on_kipas2.setVisibility(View.VISIBLE);
                ic_off_kipas2.setVisibility(View.GONE);
            }
            else if (exhaust2 == 0) {
                switch_kipas2.setChecked(false);
                ic_on_kipas2.setVisibility(View.GONE);
                ic_off_kipas2.setVisibility(View.VISIBLE);
            }
            if (exhaust3 == 1) {
                switch_kipas3.setChecked(true);
                ic_on_kipas3.setVisibility(View.VISIBLE);
                ic_off_kipas3.setVisibility(View.GONE);
            }
            else if (exhaust3 == 0) {
                switch_kipas3.setChecked(false);
                ic_on_kipas3.setVisibility(View.GONE);
                ic_off_kipas3.setVisibility(View.VISIBLE);
            }
            if (exhaust4 == 1) {
                switch_kipas4.setChecked(true);
                ic_on_kipas4.setVisibility(View.VISIBLE);
                ic_off_kipas4.setVisibility(View.GONE);
            }
            else if (exhaust4 == 0) {
                switch_kipas4.setChecked(false);
                ic_on_kipas4.setVisibility(View.GONE);
                ic_off_kipas4.setVisibility(View.VISIBLE);
            }
            if (exhaust5 == 1) {
                switch_kipas5.setChecked(true);
                ic_on_kipas5.setVisibility(View.VISIBLE);
                ic_off_kipas5.setVisibility(View.GONE);
            }
            else if (exhaust5 == 0) {
                switch_kipas5.setChecked(false);
                ic_on_kipas5.setVisibility(View.GONE);
                ic_off_kipas5.setVisibility(View.VISIBLE);
            }

            if (exhaust6 == 1) {
                switch_kipas6.setChecked(true);
                ic_on_kipas6.setVisibility(View.VISIBLE);
                ic_off_kipas6.setVisibility(View.GONE);
            }
            else if (exhaust6 == 0) {
                switch_kipas6.setChecked(false);
                ic_on_kipas6.setVisibility(View.GONE);
                ic_off_kipas6.setVisibility(View.VISIBLE);
            }
            if (exhaust7 == 1) {
                switch_kipas7.setChecked(true);
                ic_on_kipas7.setVisibility(View.VISIBLE);
                ic_off_kipas7.setVisibility(View.GONE);
            }
            else if (exhaust7 == 0) {
                switch_kipas7.setChecked(false);
                ic_on_kipas7.setVisibility(View.GONE);
                ic_off_kipas7.setVisibility(View.VISIBLE);
            }
            if (exhaust8 == 1) {
                switch_kipas8.setChecked(true);
                ic_on_kipas8.setVisibility(View.VISIBLE);
                ic_off_kipas8.setVisibility(View.GONE);
            }
            else if (exhaust8 == 0) {
                switch_kipas8.setChecked(false);
                ic_on_kipas8.setVisibility(View.GONE);
                ic_off_kipas8.setVisibility(View.VISIBLE);
            }
            if (heater1 == 1) {
                switch_heater1.setChecked(true);
                ic_on_heater1.setVisibility(View.VISIBLE);
                ic_off_heater1.setVisibility(View.GONE);
            }
            else if (heater1 == 0) {
                switch_heater1.setChecked(false);
                ic_on_heater1.setVisibility(View.GONE);
                ic_off_heater1.setVisibility(View.VISIBLE);
            }
            if (heater2 == 1) {
                switch_heater2.setChecked(true);
                ic_on_heater2.setVisibility(View.VISIBLE);
                ic_off_heater2.setVisibility(View.GONE);
            }
            else if (heater2 == 0) {
                switch_heater2.setChecked(false);
                ic_on_heater2.setVisibility(View.GONE);
                ic_off_heater2.setVisibility(View.VISIBLE);
            }
            if (evaporator == 1) {
                switch_evaporator.setChecked(true);
                ic_on_evaporator.setVisibility(View.VISIBLE);
                ic_off_evaporator.setVisibility(View.GONE);
            }
            else if (evaporator == 0) {
                switch_evaporator.setChecked(false);
                ic_on_evaporator.setVisibility(View.GONE);
                ic_off_evaporator.setVisibility(View.VISIBLE);
            }
            if (mode==1){
                switch_modekontrol.setChecked(false);
                card_atursetpoint.setVisibility(View.GONE);
                card_aturkontrol.setVisibility(View.VISIBLE);
                card_intermiten_info.setVisibility(View.GONE);
            }
            else if(mode==0){
                switch_modekontrol.setChecked(true);
                card_atursetpoint.setVisibility(View.VISIBLE);
                card_aturkontrol.setVisibility(View.GONE);
                card_intermiten_info.setVisibility(View.VISIBLE);
            }

        }

    }

    private void updateIntermiten(String imtn){
        APIService service = ApiClient.getClient().create(APIService.class);

        if(imtn.equals("on")){
            try{
                int on = Integer.parseInt(inp_imtn_on.getText().toString());
                service.updateIntervalOn(node, lantai, on).enqueue(new Callback<IntermitenModel>() {
                    @Override
                    public void onResponse(Call<IntermitenModel> call, Response<IntermitenModel> response) {
                        if(response.isSuccessful()){
                            intermitenStatus();
                            inp_imtn_on.getText().clear();
                            Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(getContext().getApplicationContext(), "Gagal Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<IntermitenModel> call, Throwable t) {

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext().getApplicationContext(), "Masukkan Nilai", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        if(imtn.equals("off")){
            try{
                int off = Integer.parseInt(inp_imnt_off.getText().toString());
                service.updateIntervalOff(node, lantai, off).enqueue(new Callback<IntermitenModel>() {
                    @Override
                    public void onResponse(Call<IntermitenModel> call, Response<IntermitenModel> response) {
                        if(response.isSuccessful()){
                            intermitenStatus();
                            inp_imnt_off.getText().clear();
                            Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(getContext().getApplicationContext(), "Gagal Update", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<IntermitenModel> call, Throwable t) {

                    }
                });

            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext().getApplicationContext(), "Masukkan Nilai", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        if(imtn.equals("pagi")){
            String jam_pagi = inp_imtn_pagi.getText().toString();
            service.updatePagi(node, lantai, jam_pagi).enqueue(new Callback<IntermitenModel>() {
                @Override
                public void onResponse(Call<IntermitenModel> call, Response<IntermitenModel> response) {
                    if(response.isSuccessful()){
                        intermitenStatus();
                        inp_imtn_pagi.getText().clear();
                        Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext().getApplicationContext(), "Gagal Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<IntermitenModel> call, Throwable t) {

                }
            });
        }
        if(imtn.equals("malam")){
            String jam_malam = inp_imtn_malam.getText().toString();
            service.updateMalam(node, lantai, jam_malam).enqueue(new Callback<IntermitenModel>() {
                @Override
                public void onResponse(Call<IntermitenModel> call, Response<IntermitenModel> response) {
                    if(response.isSuccessful()){
                        intermitenStatus();
                        inp_imtn_malam.getText().clear();
                        Toast.makeText(getContext().getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext().getApplicationContext(), "Gagal Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<IntermitenModel> call, Throwable t) {

                }
            });
        }

    }


    private void updateActuator (String actuator){
        APIService service = ApiClient.getClient().create(APIService.class);
        if(actuator.equals("fan1")){
            service.updateFan1(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan2")){
            service.updateFan2(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan3")){
            service.updateFan3(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan4")){
            service.updateFan4(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {

                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan5")){
            service.updateFan5(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {

                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan6")){
            service.updateFan6(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan7")){
            service.updateFan7(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("fan8")){
            service.updateFan8(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }
                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {

                }
            });
        }
        if(actuator.equals("heater1")){
            service.updateHeater1(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
                }
            });
        }
        if(actuator.equals("heater2")){
            service.updateHeater2(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
                }
            });
        }
        if(actuator.equals("evaporator")){
            service.updateEvaporator(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
                }
            });
        }
        if(actuator.equals("mode")){
            service.updateMode(node, lantai, state).enqueue(new Callback<DashboardStatusModel>() {
                @Override
                public void onResponse(Call<DashboardStatusModel> call, Response<DashboardStatusModel> response) {
                }

                @Override
                public void onFailure(Call<DashboardStatusModel> call, Throwable t) {
                }
            });
        }
    }

}