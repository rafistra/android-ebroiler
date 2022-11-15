package com.rafistra.kedaireka.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rafistra.kedaireka.Model.tokenResponse;
import com.rafistra.kedaireka.R;
import com.rafistra.kedaireka.RestApi.APIService;
import com.rafistra.kedaireka.RestApi.ApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    EditText etUsername, etPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));


        btnSignIn  = (Button) findViewById(R.id.btnSignIn);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallLoginService();
            }
        });
    }



    private void CallLoginService() {
        //create validation
//        if (validate() == false){
//            onLoginFailed();
//            return;
//        }
        try{
            final String email = etUsername.getText().toString();
            final String password = etPassword.getText().toString();

            APIService service = ApiClient.getClient().create(APIService.class);
            Call<ResponseBody> srvLogin = service.getToken(email, password);
            srvLogin.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            String ResponseJson = response.body().string();
                            Gson objGson = new Gson();
                            tokenResponse objResp = objGson.fromJson(ResponseJson, tokenResponse.class);
                            getSharedPreferences("WFH", MODE_PRIVATE).edit().putString("token", objResp.getAccess_token()).commit();
                            //Save Name to SharedPreferences
                            getSharedPreferences("NAME", MODE_PRIVATE).edit().putString("name", objResp.getName()).commit();
                            getSharedPreferences("KANDANG", MODE_PRIVATE).edit().putString("nodeId", objResp.getNodeId()).commit();
//                            getSharedPreferences("LANTAI", Context.MODE_PRIVATE).edit().putString("lantai", "").commit();
                            //Toast.makeText(MainActivity.this, objResp.getAccess_token(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, objResp.getName(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Login Succes", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Gagal coba lagi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "System error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed(){
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}