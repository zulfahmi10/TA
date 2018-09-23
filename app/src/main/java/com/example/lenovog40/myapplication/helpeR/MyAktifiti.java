/*
 * Copyright (c) 2018. MDVK adalah sebuah aplikasi abal-abal yang dibangun atas keisengan semata :D
 * Umumnya kegunaan aplikasi MDVK adalah untuk membuat Perangkat kamu terhubung ke(@ wifi.id)
 * secara gratis tanpa harus membeli voucher atau dsb.
 *
 * Aplikasi Ini dibuat oleh : Aditya Pratama , pada tanggal : 6/14/2017
 *
 *
 */

package com.example.lenovog40.myapplication.helpeR;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovog40.myapplication.R;

import java.lang.reflect.Method;



@SuppressLint("Registered")
public class MyAktifiti extends AppCompatActivity {
    Dialog myDialog;



    public void prinT(String msg) {
        System.out.println("->" + msg);
    }

    public void SH_TOAST(String query) {
        Toast.makeText(MyAktifiti.this, query, Toast.LENGTH_SHORT).show();
    }

    public void SH_TOAST2(String query) {
        Toast.makeText(MyAktifiti.this, query, Toast.LENGTH_LONG).show();
    }

    public void SH_ALERT_POLOS(String judul, String query) {
        AlertDialog alertDialog = new AlertDialog.Builder(MyAktifiti.this).create();
        alertDialog.setTitle(judul);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(query);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }




    public void SH_SB(String query) {
        Snackbar.make(findViewById(android.R.id.content), query, Snackbar.LENGTH_SHORT)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }


    public void CEK_KEYBOAR() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm;
            imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}

