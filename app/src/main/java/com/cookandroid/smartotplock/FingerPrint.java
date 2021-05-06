package com.cookandroid.smartotplock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class FingerPrint extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    TextView textView, textPattern, textPin;
    ImageView fingerPrint;
    int i=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finger_print);

        textView = (TextView) findViewById(R.id.text2);
        textPattern = (TextView) findViewById(R.id.text5);
        textPin = (TextView) findViewById(R.id.text6);
        textView.setVisibility(View.INVISIBLE);

        fingerPrint = (ImageView) findViewById(R.id.fingerimg);

        fingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("지문 인증")
                        .setSubtitle("기기에 등록된 지문을 이용하여\n지문을 인증해주세요.")
                        .setNegativeButtonText("취소")
                        .setDeviceCredentialAllowed(false)
                        .build();

                biometricPrompt.authenticate(promptInfo);


            }
        });

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {  // 복구할 수 없는 오류가 있을 때
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), R.string.auth_error_message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {  // 지문이 인식되었을 때
                super.onAuthenticationSucceeded(result);

                Intent intent = new Intent(getApplicationContext(), FingerPrintSuccess.class);  // 지문 성공 화면으로 이동
                startActivity(intent);
                finish();

                //Toast.makeText(getApplicationContext(), R.string.auth_success_message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {  // 지문이 검색되었지만 디바이스에서 인식되지 않을 때
                super.onAuthenticationFailed();

                i++;
                if (i>=1 && i<3) {
                    textView.setVisibility(View.VISIBLE);
                    fingerPrint.setImageResource(R.drawable.wrong_finger_print);
                }
                if (i>=3 && i<5){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("5회 이상 틀릴 시 본인인증이 필요합니다.");
                    fingerPrint.setImageResource(R.drawable.wrong_finger_print);
                }

                if (i==5) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("본인인증 화면으로 넘어갑니다.");
                    Intent intent = new Intent(getApplicationContext(), Verification.class);
                    startActivity(intent);
                    finish();
                    i=0;
                }

                //Toast.makeText(getApplicationContext(), R.string.auth_fail_message, Toast.LENGTH_SHORT).show();
            }
        });

        // 다른 인증방식 선택 - 패턴 눌렀을 경우
        textPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                startActivity(intent);
                finish();
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

    }
}
