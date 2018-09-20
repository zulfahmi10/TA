package com.example.lenovog40.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;

import com.example.lenovog40.myapplication.adapter.listAdapter;
import com.example.lenovog40.myapplication.Api.BaseApiService;
import com.example.lenovog40.myapplication.Api.UtilsApi;
import com.example.lenovog40.myapplication.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEventActivity extends AppCompatActivity {

    //API_Service
    BaseApiService mApiService;

    private ArrayList<Event> eventArrayList = new ArrayList<>();
    private listAdapter listadapter  ;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mApiService = UtilsApi.getApiService();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataEvent();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplication(), MenuEventActivity.class);
        startActivity(intent);
        finish();
    }

    public void dataEvent() {
        mApiService.daftarRequest().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());

                        JSONArray jsonArray = jsonRESULTS.getJSONArray("event");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Event event = new Event();
                            event.setNamaEvent(object.getString("nama_event"));
                            event.setLokasi(object.getString("lokasi"));
                            event.setTanggal(object.getString("tanggal"));
                            event.setJenisEvent (object.getString("jenis_event"));
                            event.setHarga (object.getString("harga"));
                            event.setDescEvent(object.getString("desc_event"));


                            eventArrayList.add(event);
                             listadapter = new listAdapter(eventArrayList);
                             listadapter.notifyDataSetChanged();
                        }
                        recyclerView.setAdapter(listadapter);
//                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL));


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
}