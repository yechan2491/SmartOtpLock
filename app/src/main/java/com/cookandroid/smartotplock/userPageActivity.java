package com.cookandroid.smartotplock;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class userPageActivity extends AppCompatActivity {

    Button otpBtn,logoutBtn, securityChangeBtn;
    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        otpBtn=(Button)findViewById(R.id.otpBtn);

        otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), otpCheckActivity.class);
                startActivity(intent);
            }
        });
        logoutBtn=(Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
            }
        });
        onBackPressed();

        securityChangeBtn = (Button) findViewById(R.id.securityChangeBtn);
        securityChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(userPageActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.security_dialog);

                showDialog1();
            }
        });

    }

    @Override
    public void onBackPressed(){   // 뒤로가기 버튼 막기

    }

    // 보안정보 수정 팝업창
    public void showDialog1() {
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button cancelBtn = dialog.findViewById(R.id.cancelBtn);
        Button changePin = dialog.findViewById(R.id.changePin);
        Button changePattern = dialog.findViewById(R.id.changePattern);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        changePattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), patternMatchActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //dialog.setCancelable(false);
    }
}
