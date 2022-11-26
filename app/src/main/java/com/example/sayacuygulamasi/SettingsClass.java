package com.example.sayacuygulamasi;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsClass {
    int ustLimit,altLimit,mevcutDeger;
    boolean ustTitresim,ustSes,altTitresim,altSes;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SettingsClass settingsClass = null;

    private SettingsClass(Context context){
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        degerleriYukle();
    }

    public static SettingsClass getSettingsClass(Context context){
        if (settingsClass == null){
            settingsClass = new SettingsClass(context);
        }
        return settingsClass;
    }

    public void degerleriKaydet(){
        editor.putInt("ustLimit",ustLimit);
        editor.putInt("altLimit",altLimit);
        editor.putInt("mevcutDeger",mevcutDeger);
        editor.putBoolean("ustTitresim",ustTitresim);
        editor.putBoolean("ustSes",ustSes);
        editor.putBoolean("altTitresim",altTitresim);
        editor.putBoolean("altSes",altSes);
        editor.commit();
    }

    public void  degerleriYukle(){
        ustLimit = sharedPreferences.getInt("ustLimit", 20);
        altLimit = sharedPreferences.getInt("altLimit", -10);
        mevcutDeger = sharedPreferences.getInt("mevcutDeger", 10);
        ustTitresim = sharedPreferences.getBoolean("ustTitresim", false);
        ustSes = sharedPreferences.getBoolean("ustSes", false);
        altTitresim = sharedPreferences.getBoolean("altTitresim", false);
        altSes = sharedPreferences.getBoolean("altSes", false);
    }
}
