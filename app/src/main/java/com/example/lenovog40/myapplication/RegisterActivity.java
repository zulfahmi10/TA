package com.example.lenovog40.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovog40.myapplication.Api.BaseApiService;
import com.example.lenovog40.myapplication.Api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class RegisterActivity extends AppCompatActivity {

    BaseApiService mApiService;
    Button btnregister;
    EditText username, password, nama, alamat, telpon, email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        telpon = (EditText) findViewById(R.id.telpon);
        email = (EditText) findViewById(R.id.email);

        btnregister = (Button) findViewById(R.id.btnregister);

        mApiService = UtilsApi.getApiService();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    SH_TOAST("Username Tidak Boleh Kosong");
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else if (nama.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else if (alamat.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Alamat Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else if (telpon.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Telpon Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else {
                    requestRegister();
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(a);
        finish();
    }

    @SuppressLint("ShowToast")
    public void SH_TOAST(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }

    public void pRint(String msg) {
        System.out.println(msg);
    }

    private void requestRegister() {
        mApiService.registerRequest(username.getText().toString(),
                password.getText().toString(),
                nama.getText().toString(),
                alamat.getText().toString(),
                telpon.getText().toString(),
                email.getText().toString())
                .enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                // jika sukses
                                if (jsonRESULTS.getString("status").equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Register Sukses", Toast.LENGTH_SHORT).show();
                                    Intent a = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(a);
                                    finish();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            SH_TOAST("KONTOL");
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        SH_TOAST("KONEKSI CAM PELER");
                    }
                });
    }
}