package com.cookandroid.smartotplock;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class userPageActivity extends AppCompatActivity {


    Button settingBtn, create_OTP, regist_key, change_verification, logoutBtn2, otpBtn,logoutBtn, securityChangeBtn;
    Dialog dialog;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);

        // 설정
        settingBtn = (Button) findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        // OTP 생성
        create_OTP = (Button) findViewById(R.id.create_OTP);
        create_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), otpCheckActivity.class);
                startActivity(intent);
            }
        });

        // 시리얼 정보 등록
        regist_key = (Button) findViewById(R.id.regist_key);
        regist_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 정보보안 수정
        change_verification = (Button) findViewById(R.id.change_verification);
        change_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(userPageActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.security_dialog);

                showDialog1();
            }
        });

        // 로그아웃
        logoutBtn2 = (Button) findViewById(R.id.logoutBtn2);
        logoutBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });





//        otpBtn=(Button)findViewById(R.id.otpBtn);
//        otpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(), otpCheckActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        logoutBtn=(Button)findViewById(R.id.logoutBtn);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(), signUpActivity.class);
//                startActivity(intent);
//            }
//        });
//        onBackPressed();
//
//        securityChangeBtn = (Button) findViewById(R.id.securityChangeBtn);
//        securityChangeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog = new Dialog(userPageActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.security_dialog);
//
//                showDialog1();
//            }
//        });

    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

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

        changePin.setOnClickListener(new View.OnClickListener() {  // PIN 수정
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pinMatchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        changePattern.setOnClickListener(new View.OnClickListener() {  // 패턴 수정
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
