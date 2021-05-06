package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class loading extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        startLoading();

    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp=getSharedPreferences("intro_control",0);
                Boolean first_check=sp.getBoolean("first_run",true);

                if(first_check==true){
                    Intent intent = new Intent(loading.this, intro01.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("first_run",false);
                    editor.commit();


                } else if(first_check==false){
                    Intent intent = new Intent(loading.this, sign_up.class);
                    startActivity(intent);

                }
                finish();
            }
        }, 2000);
    }


}
