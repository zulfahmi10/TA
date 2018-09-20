package com.example.lenovog40.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovog40.myapplication.Action.PrefManager;
import com.example.lenovog40.myapplication.Api.BaseApiService;
import com.example.lenovog40.myapplication.Api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    BaseApiService mApiService;

    PrefManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        TextView regis = (TextView) findViewById(R.id.btnregister);
        Button login = (Button) findViewById(R.id.btnlogin);

        manager = new PrefManager(this);
        mApiService = UtilsApi.getApiService();


        if (manager.sessionLogin()==true){
            Intent intent = new Intent(getApplicationContext(), MenuEventActivity.class);
            startActivity(intent);
        }



        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);

            }});

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    mApiService.loginRequest(username.getText().toString(), password.getText().toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {

                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());

                                    // jika sukses
                                    if (jsonRESULTS.getString("error").equals("false")) {

                                        // parsing data

                                        String id_user_android = jsonRESULTS.getJSONObject("user").getString("id_user_android");
                                        String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                        String alamat = jsonRESULTS.getJSONObject("user").getString("alamat");
                                        String telpon = jsonRESULTS.getJSONObject("user").getString("telpon");
                                        String email = jsonRESULTS.getJSONObject("user").getString("email");




                                        manager.setSudahLogin(true, id_user_android,nama,alamat,telpon,email);

                                        Intent intent = new Intent(getApplicationContext(), MenuEventActivity.class);
                                        startActivity(intent);

                                    } else {

                                        String msg = jsonRESULTS. getString("error_msg");
                                        // Jika login gagal
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }});
    }
}

