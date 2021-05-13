package com.cookandroid.smartotplock;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
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
    int count = 0;
    TextView textPin, textFingerPrint, forgotPassword, errorMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_check);

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

        errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);

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
                        count++;
                        if(count>=1 && count<3) {
                            errorMessage.setVisibility(View.VISIBLE);
                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            Toast.makeText(CheckPasswordActivity.this, count + "회 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mPatternLockView.clearPattern();
                                }
                            }, 1000);
                            //count++;
                        }
                        if(count>=3 && count<5) {
                            errorMessage.setVisibility(View.VISIBLE);
                            errorMessage.setText("5회 이상 틀릴 시 본인인증이 필요합니다.");
                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            Toast.makeText(CheckPasswordActivity.this, count + "회 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mPatternLockView.clearPattern();
                                }
                            }, 200);
                            //count++;
                        }
                        if(count==5) {
                            errorMessage.setVisibility(View.VISIBLE);
                            errorMessage.setText("본인인증 화면으로 넘어갑니다.");
                            mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            Toast.makeText(CheckPasswordActivity.this, "5회 이상 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Verification.class);
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

        // 다른 인증방식 선택 - PIN 눌렀을 경우
        textPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PINCreateActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        // 다른 인증방식 선택 - 지문 눌렀을 경우
        textFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FingerPrint.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
