package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    TextView textPin, textFingerPrint, forgotPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        textPin = (TextView) findViewById(R.id.text5);
        textFingerPrint = (TextView) findViewById(R.id.text6);
        forgotPassword = (TextView) findViewById(R.id.text2);
        forgotPassword.setPaintFlags(forgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // "패턴을 잊으셨나요?"에 밑줄긋기
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatternForgot.class);
                startActivity(intent);
                finish();
            }
        });
        
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                if(PatternLockUtils.patternToString(mPatternLockView, pattern).length() >= 4) {  // 4개 이상의 점이 연결되었을 경우
                    editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern));  // "password"라는 key에 패턴 저장
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
                    startActivity(intent);
                    finish();

                } else {  // 4개 미만의 점이 연결되었을 경우
                    Toast.makeText(ChangePasswordActivity.this, "4개 이상의 점을 연결해주세요.", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {

            }
        });

        // 다른 인증방식 선택 - PIN 눌렀을 경우
        textPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), pinCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 다른 인증방식 선택 - 지문 눌렀을 경우
        textFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), fingerVerificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
