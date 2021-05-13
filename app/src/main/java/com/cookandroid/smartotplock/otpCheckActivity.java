package com.cookandroid.smartotplock;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class otpCheckActivity extends AppCompatActivity {

    ImageButton backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_check);


        utils.setStatusBarColor(this, utils.StatusBarColorType.BLUE_STATUS_BAR); // 상태바 색상을 안드로이드 코드에서 바꾸기 위한 용도 - 이와 관련된 내용은 utils.java 파일에 기술되어 있음

        backBtn=(ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

