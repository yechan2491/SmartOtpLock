package com.cookandroid.smartotplock;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class otpCheckActivity extends AppCompatActivity {

    private final String BASEURL = "http://34.204.61.107";

    ImageButton backBtn;
    TextView timeText;
    TextView[] text = new TextView[6];
    Integer[] textIDs = {R.id.OTPNum0, R.id.OTPNum1, R.id.OTPNum2, R.id.OTPNum3, R.id.OTPNum4, R.id.OTPNum5};
    Button button;
    String OTPNumber;
    CountDownTimer countDownTimer, timeCounter;
    int value = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_check);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        utils.setStatusBarColor(this, utils.StatusBarColorType.BLUE_STATUS_BAR); // 상태바 색상을 안드로이드 코드에서 바꾸기 위한 용도 - 이와 관련된 내용은 utils.java 파일에 기술되어 있음

        backBtn=(ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                }

                finish();
            }
        });

        timeText = (TextView) findViewById(R.id.timeText);
        button = (Button) findViewById(R.id.button);

        for(int i=0; i<text.length; i++) {
            text[i] = (TextView) findViewById(textIDs[i]);
        }

        if(countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(500000, 30000) {
            @Override
            public void onTick(long l) {
                updateCountDownText();

                otpGenerate generateCerNumber = new otpGenerate();
                OTPNumber = generateCerNumber.excuteGenerate();
                char[] array_text = new char[OTPNumber.length()];

                for(int i=0; i<OTPNumber.length(); i++) {
                    array_text[i]=(OTPNumber.charAt(i));
                    text[i].setText(array_text[i]+"");
                    System.out.println(array_text[i]);
                }

                jsonPlaceHolderApi.OTPData(OTPNumber).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()) {
                            Post postResponse = response.body();
//                            Toast.makeText(getApplicationContext(),"OTP가 성공적으로 등록되었습니다.",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"OTP 등록 실패 : 서버 연결 오류",Toast.LENGTH_LONG).show();
                        Log.d("오류 : ", t.getMessage());
                    }
                });
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                }

                countDownTimer = new CountDownTimer(500000, 30000) {
                    @Override
                    public void onTick(long l) {
                        updateCountDownText();

                        otpGenerate generateCerNumber = new otpGenerate();
                        OTPNumber = generateCerNumber.excuteGenerate();
                        char[] array_text = new char[OTPNumber.length()];

                        for(int i=0; i<OTPNumber.length(); i++) {
                            array_text[i]=(OTPNumber.charAt(i));
                            text[i].setText(array_text[i]+"");
                            System.out.println(array_text[i]);
                        }

                        jsonPlaceHolderApi.OTPData(OTPNumber).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                if(response.isSuccessful()) {
                                    Post postResponse = response.body();
//                                    Toast.makeText(getApplicationContext(),"OTP가 성공적으로 등록되었습니다.",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {
//                                Toast.makeText(getApplicationContext(),"OTP 등록에 실패하였습니다.",Toast.LENGTH_LONG).show();
                                Log.d("오류 : ", t.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();
            }
        });

    }
    public void updateCountDownText() {
        if(timeCounter != null) {
            timeCounter.cancel();
        }
        value = 30;

        timeCounter = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                value--;
                timeText.setText(value + " 초");
                timeText.setTextColor(Color.parseColor("#ff3120"));
            }

            @Override
            public void onFinish() {

            }
        };
        timeCounter.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }

        Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
        startActivity(intent);
    }
}

