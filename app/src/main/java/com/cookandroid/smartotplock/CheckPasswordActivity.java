package com.cookandroid.smartotplock;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class CheckPasswordActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    String password;
    int count = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        password = preferences.getString("password", "0");

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
                if(PatternLockUtils.patternToString(mPatternLockView, pattern).length() >= 4) {  // 4개 이상의 점이 연결되었을 경우
                    if(password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);

                        // 팝업창에서 인증방식 3개 중 1개 선택하기
                        final String[] versionArray = new String[] {"지문", "PIN", "패턴"};
                        AlertDialog.Builder dlg = new AlertDialog.Builder(CheckPasswordActivity.this);
                        dlg.setTitle("인증수단 선택");

                        dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), versionArray[which] + "(으)로 선택되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        dlg.show();
                    } else {
                        if(count<5) {
                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            Toast.makeText(CheckPasswordActivity.this, count + "회 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                            mPatternLockView.clearPattern();
                            count++;
                        } else if(count==5) {
                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            Toast.makeText(CheckPasswordActivity.this, "5회 이상 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), sign_up.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {  // 4개 미만의 점이 연결되었을 경우
                    Toast.makeText(CheckPasswordActivity.this, "4개 이상의 점을 연결해주세요.", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }


            }

            @Override
            public void onCleared() {

            }
        });
    }
}
