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

public class patternCreateActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    TextView cancelText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_create);

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

                    Intent intent = new Intent(getApplicationContext(), patternCheckActivity.class);
                    startActivity(intent);
                    finish();

                } else {  // 4개 미만의 점이 연결되었을 경우
                    Toast.makeText(patternCreateActivity.this, "4개 이상의 점을 연결해주세요.", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {

            }
        });

        cancelText = (TextView) findViewById(R.id.cancelText);
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
