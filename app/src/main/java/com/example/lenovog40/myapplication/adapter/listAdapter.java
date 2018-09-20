package com.example.lenovog40.myapplication.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovog40.myapplication.Action.PrefManager;
import com.example.lenovog40.myapplication.Api.BaseApiService;
import com.example.lenovog40.myapplication.Api.UtilsApi;
import com.example.lenovog40.myapplication.R;
import com.example.lenovog40.myapplication.model.Event;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listAdapter extends RecyclerView.Adapter<listAdapter.MyViewHolder> {

    private List<Event> eventList;
    private BaseApiService baseApiService;
    TextView judul,descrip;

    public listAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daftar_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Event event = eventList.get(position);
        holder.txnama.setText(event.getNamaEvent());
        holder.txlokasi.setText(event.getLokasi());
        holder.txTanggal.setText(event.getTanggal());
        holder.txjenis_event.setText(event.getJenisEvent());
        holder.txharga.setText("Rp. "+event.getHarga());
//        holder.txdescevent.setText(event.getDescEvent());
        holder.linearr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("id",""+eventList.get(position).getId_event());
                AlertDialog mBuilder = new AlertDialog.Builder(view.getContext()).create();
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View mview = inflater.inflate(R.layout.listdetail,null);
                mBuilder.setView(mview);
                Button btndaftar;
                judul= mview.findViewById(R.id.judul);
                descrip = mview.findViewById(R.id.descdetail);
                btndaftar= mview.findViewById(R.id.btndaftar);
                
                judul.setText(eventList.get(position).getId_event());
                descrip.setText(event.getDescEvent());
                btndaftar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerEvent(view.getContext(), eventList.get(position).getId_event());
                    }
                });

                mBuilder.show();
            }
        });
    }

    private void registerEvent(Context context, String idEvent) {
        idEvent = judul.getText().toString();
        baseApiService = UtilsApi.getApiService();
        PrefManager prefManager =new PrefManager(context);
        baseApiService.ikutevent(prefManager.getId(),idEvent,prefManager.getNamaUser(),prefManager.getAlamat(),prefManager.getTelpon(),prefManager.getEmail()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txnama, txlokasi, txTanggal, txjenis_event,txharga,txdescevent;
        public LinearLayout linearr;

        public MyViewHolder(View view) {
            super(view);
            txnama = (TextView) view.findViewById(R.id.nama_event);
            txlokasi = (TextView) view.findViewById(R.id.lokasi);
            txTanggal = (TextView) view.findViewById(R.id.tanggal);
            txjenis_event = (TextView) view.findViewById(R.id.jenis_event);
            txharga = (TextView) view.findViewById(R.id.harga);

            linearr = view.findViewById(R.id.linear1);
//  txjenis_event = (TextView) view.findViewById(R.id.desc_event);

        }
    }
}
