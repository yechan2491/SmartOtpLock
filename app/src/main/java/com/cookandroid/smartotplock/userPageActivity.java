package com.cookandroid.smartotplock;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class userPageActivity extends AppCompatActivity {

    TextView nameText, nameText2;
    LinearLayout linearLayout;
    Button create_OTP, regist_key, logoutBtn2;
    Dialog dialog;
    private DrawerLayout drawerLayout;
    private View drawerView;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        SharedPreferences pref = getSharedPreferences("User1", 0);
        String userName = pref.getString("userName", "");

        nameText = (TextView) findViewById(R.id.text1);
        nameText.setText("어서오세요.\n" + userName + "님 환영합니다.");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        nameText2 = (TextView) findViewById(R.id.nameText2);
        nameText2.setText(userName + " 님");


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

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        ExpandableListAdapter.Item change = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "보안정보 수정");
        change.invisibleChildren = new ArrayList<>();
        change.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "PIN 수정"));
        change.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "패턴 수정"));
        data.add(change);

        recyclerview.setAdapter(new ExpandableListAdapter(this, data));


        // 로그아웃
        logoutBtn2 = (Button) findViewById(R.id.logoutBtn2);
        logoutBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(userPageActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.logout_dialog);
                showDialog1();
            }
        });


        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
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

    // 로그아웃 팝업창
    public void showDialog1() {
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button yesBtn = dialog.findViewById(R.id.yesBtn);
        Button noBtn = dialog.findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(new View.OnClickListener() {  // 예
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {  // 아니오
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), pinMatchActivity.class);
//                startActivity(intent);
                dialog.dismiss();
            }
        });
        //dialog.setCancelable(false);
    }
}
