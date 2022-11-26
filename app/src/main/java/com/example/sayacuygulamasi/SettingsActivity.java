package com.example.sayacuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    Button btn_ust_limit_arttir,btn_ust_limit_azalt,btn_alt_limit_arttir, btn_alt_limit_azalt;
    CheckBox cb_ust_limit_titresim, cb_ust_limit_sesli,cb_alt_limit_titresim, cb_alt_limit_sesli;
    EditText etn_ustLimit,etn_altLimit;

    SettingsClass settingsClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn_alt_limit_arttir = (Button) findViewById(R.id.btn_alt_limit_arttir);
        btn_ust_limit_arttir = (Button) findViewById(R.id.btn_ust_limit_arttir);
        btn_ust_limit_azalt = (Button) findViewById(R.id.btn_ust_limit_azalt);
        btn_alt_limit_azalt = (Button) findViewById(R.id.btn_alt_limit_azalt);
        cb_ust_limit_titresim = (CheckBox) findViewById(R.id.cb_ust_limit_titresim);
        cb_ust_limit_sesli = (CheckBox) findViewById(R.id.cb_ust_limit_sesli);
        cb_alt_limit_titresim = (CheckBox) findViewById(R.id.cb_alt_limit_titresim);
        cb_alt_limit_sesli = (CheckBox) findViewById(R.id.cb_alt_limit_sesli);
        etn_ustLimit = (EditText) findViewById(R.id.etn_ustLimit);
        etn_altLimit = (EditText) findViewById(R.id.etn_altLimit);

        settingsClass = SettingsClass.getSettingsClass(getApplicationContext());

        btn_ust_limit_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.ustLimit++;
                etn_ustLimit.setText(String.valueOf(settingsClass.ustLimit));
            }
        });
        btn_ust_limit_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.ustLimit--;
                etn_ustLimit.setText(String.valueOf(settingsClass.ustLimit));
            }
        });
        btn_alt_limit_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.altLimit++;
                etn_altLimit.setText(String.valueOf(settingsClass.altLimit));
            }
        });
        btn_alt_limit_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.altLimit--;
                etn_altLimit.setText(String.valueOf(settingsClass.altLimit));
            }
        });

        cb_ust_limit_sesli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.ustSes = b;
            }
        });
        cb_alt_limit_sesli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.altSes = b;
            }
        });
        cb_ust_limit_titresim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.ustTitresim = b;
            }
        });
        cb_alt_limit_titresim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.altTitresim = b;
            }
        });

        etn_ustLimit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etn_ustLimit.getText().toString().length() != 0){
                    settingsClass.ustLimit = Integer.parseInt(etn_ustLimit.getText().toString());
                }
            }
        });
        etn_altLimit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etn_altLimit.getText().toString().length() != 0){
                    settingsClass.altLimit = Integer.parseInt(etn_altLimit.getText().toString());
                }
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        etn_ustLimit.setText(String.valueOf((settingsClass.ustLimit)));
        etn_altLimit.setText(String.valueOf((settingsClass.altLimit)));
        cb_alt_limit_titresim.setChecked(settingsClass.altTitresim);
        cb_ust_limit_titresim.setChecked(settingsClass.ustTitresim);
        cb_alt_limit_sesli.setChecked(settingsClass.altSes);
        cb_ust_limit_sesli.setChecked(settingsClass.ustSes);
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.degerleriKaydet();
    }

}