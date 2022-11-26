package com.example.sayacuygulamasi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_arttirma, btn_azaltma, btn_ayarlar;
    TextView tv_sayac;
    int sayac;

    SettingsClass settingsClass;
    MediaPlayer mediaPlayer = null;
    Vibrator vibrator = null;

    SensorManager sensorManager;
    Sensor sensorShake;
    SensorEventListener sensorEventListener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayac = 0;
        btn_arttirma = (Button) findViewById(R.id.btn_arttirma);
        btn_azaltma = (Button) findViewById(R.id.btn_azaltma);
        btn_ayarlar = (Button) findViewById(R.id.btn_ayarlar);
        tv_sayac = (TextView) findViewById(R.id.tv_sayac);

        Context context = getApplicationContext();
        settingsClass = SettingsClass.getSettingsClass(context);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.beep);

        btn_arttirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                degeriGuncelle(1);
            }
        });
        btn_azaltma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degeriGuncelle(-1);
            }
        });
        btn_ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    public void degeriGuncelle(int miktar) {
        if (miktar < 0) {
            if (settingsClass.mevcutDeger + miktar < settingsClass.altLimit) {
                settingsClass.mevcutDeger = settingsClass.altLimit;
                if (settingsClass.altTitresim) {
                    titret();
                }
                if (settingsClass.altSes) {
                    sesCal();
                }
            } else {
                settingsClass.mevcutDeger += miktar;

            }
        }
        if (miktar > 0) {
            if (settingsClass.mevcutDeger + miktar > settingsClass.ustLimit) {
                settingsClass.mevcutDeger = settingsClass.ustLimit;
                if (settingsClass.ustTitresim) {
                    titret();
                }
                if (settingsClass.ustSes) {
                    sesCal();
                }
            } else {
                settingsClass.mevcutDeger += miktar;
            }
        }
        tv_sayac.setText(String.valueOf(settingsClass.mevcutDeger));
    }

    protected void onResume() {
        super.onResume();
        tv_sayac.setText(String.valueOf(settingsClass.mevcutDeger));
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.degerleriKaydet();
    }

    public void sesCal() {
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void titret() {
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }
        vibrator.vibrate(1000);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    degeriGuncelle(-5);
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    degeriGuncelle(5);
                }
                return true;
        }
        return super.dispatchKeyEvent(event);
    }
}