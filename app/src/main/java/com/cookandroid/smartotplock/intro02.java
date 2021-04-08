package com.cookandroid.smartotplock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class intro02 extends AppCompatActivity {
    ImageButton intro02ImgBtn01;

    @Override
    public void onBackPressed() {
        super.onBackPressed(); //어플리케이션이 종료되거나 이전 액티비티로 되돌아갑니다.
        overridePendingTransition(R.anim.in_left,R.anim.out_right);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro02);
        intro02ImgBtn01=(ImageButton)findViewById(R.id.intro02ImgBtn01);

        intro02ImgBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent);
            }
        });

    }

}
