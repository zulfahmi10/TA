package com.example.lenovog40.myapplication.Action;

import android.content.Context;
import android.content.SharedPreferences;



public class PrefManager {

    // Shared preferences file name
    public static final String PREF_NAME = "PREF_EVENT";
    private static final String CEK_SUDAH_LOGIN = "sessionLogin";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    // context
    public PrefManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    // session login
    public boolean sessionLogin() {
        return pref.getBoolean(CEK_SUDAH_LOGIN, false);
    }

    public void setSudahLogin(boolean sudahLogin, String... strings) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);

        //Data User Lokal
        editor.putString("id_user_android",strings[0]);
        editor.putString("nama",strings[1]);
        editor.putString("alamat",strings[2]);
        editor.putString("telpon",strings[3]);
        editor.putString("email",strings[4]);

        editor.commit();
    }

    // ini method khusus pemanggilan button logout
    // karena logout tidak memerlukan data user;
    public void setSudahLogin(boolean sudahLogin) {
        editor.putBoolean(CEK_SUDAH_LOGIN, sudahLogin);
        editor.commit();
    }
    public String getNamaUser() {
        return pref.getString("nama", "");
    }
    public String getId(){return pref.getString("id_user_android","");}
    public String getAlamat(){return pref.getString("alamat","");}
    public String getTelpon(){return pref.getString("telpon","");}
    public String getEmail(){return pref.getString("email","");}



    public void clear() {
        setSudahLogin(false);

        editor.clear();
        editor.commit();
    }
}

