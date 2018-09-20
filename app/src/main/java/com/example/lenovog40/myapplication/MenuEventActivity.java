package com.example.lenovog40.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovog40.myapplication.Action.PrefManager;

public class MenuEventActivity extends AppCompatActivity {
    private LinearLayout le,ls, lout;
    private TextView ts;
    boolean doubleBackToExitPressedOnce = false;


    PrefManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_event);


        le = (LinearLayout) findViewById(R.id.layout_event);
        ls = (LinearLayout) findViewById(R.id.layout_scanning);
        lout = findViewById(R.id.logOut);

        ts = (TextView) findViewById(R.id.txScan);

        manager = new PrefManager(this);

        le.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuEventActivity.this, ListEventActivity.class);
                startActivity(intent);
                finish();


            }


        });
        ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("-> tombol di klik");
                Intent intent2 = new Intent(MenuEventActivity.this, ScanningActivity.class);
                startActivity(intent2);
            }
        });

        lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Logout Sukses");
                manager.clear();
                Intent intent2 = new Intent(MenuEventActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        manager.clear();
//    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik Sekali Lagi untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 2000);
    }
}
