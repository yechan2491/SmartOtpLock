package com.cookandroid.smartotplock;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class passVerificationActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    TextView[] textViews = new TextView[5];
    Integer[] textViewsID = {R.id.text1, R.id.text2, R.id.text4, R.id.text5, R.id.text6};
    int i=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_verification);

        for(i=0; i<imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(numImageIDs[i]);
        }

        for(i=0; i<textViews.length; i++) {
            textViews[i] = (TextView) findViewById(textViewsID[i]);
        }

//        textViews[2].setPaintFlags(textViews[2].getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // "PIN을 잊으셨나요?"에 밑줄긋기
//        textViews[2].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), PinForgot.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pinVerificationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 다른 인증방식 선택 - 패턴 눌렀을 경우
        textViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), patternVerification.class);
                startActivity(intent);
                finish();
            }
        });

        // 다른 인증방식 선택 - 지문 눌렀을 경우
        textViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), fingerVerificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
