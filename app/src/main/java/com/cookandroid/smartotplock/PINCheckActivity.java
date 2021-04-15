package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PINCheckActivity extends AppCompatActivity {

    int pincheck;
    int[] pin2 = {0, 0, 0, 0};
    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    Button btnCancel;
    ImageButton btnDelete;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};

    int i=0, j=0, count=0, wrongCount=0, deleteCount=0;

    private static final String TAG = "중간 확인";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pincheck);

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnDelete = (ImageButton) findViewById(R.id.btn_delete);

        for(i=0; i<imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(numImageIDs[i]);
        }

        for (i = 0; i < numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        pincheck = preferences.getInt("pin", 0);

        j = count - deleteCount;

        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(j>=0) {
                        pin2[j] = Integer.parseInt(numButtons[index].getText().toString()); // pin2 배열에 사용자가 누른 버튼의 숫자 대입
                        imageViews[j].setImageResource(R.drawable.ic_black_circle);  // 버튼 1개 누를 때마다 pin 입력 이미지 변경
                        j++;
                        count++;
                        Log.d(TAG, "j : "+j);
                        Log.d(TAG, "count : "+count);
                        Log.d(TAG, "PIN : "+pin2[0]+pin2[1]+pin2[2]+pin2[3]);

                        if (j == 4) {    // 사용자가 4자리의 수를 입력했을 때
                            count=0;
                            j=0;

                            if (pincheck == pin2[0] + pin2[1] + pin2[2] + pin2[3]) {    // 처음 설정한 pin이 저장된 pincheck와 입력한 4자리 수가 같을 경우
                                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
                                startActivity(intent);
                                finish();
                            } else {    // pin을 잘못 입력한 경우
                                for(int a=0; a<4; a++) {
                                    pin2[a] = 0;    // 4자리 수 모두 0으로 초기화
                                    imageViews[a].setImageResource(R.drawable.ic_white_circle);
                                }

                                wrongCount++;    // 틀린 횟수 증가
                                Toast.makeText(getApplication(), "PIN이 틀렸습니다.", Toast.LENGTH_SHORT).show();

                                if(wrongCount>=5) {    // @@수정해야 할 부분@@
                                    Intent intent = new Intent(getApplicationContext(), sign_up.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    } else{
                        //deleteCount = 0;
                    }
                }
            });
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = j;
                if(a>=1) {
                    imageViews[a-1].setImageResource(R.drawable.ic_white_circle);
                } else {
                    imageViews[0].setImageResource(R.drawable.ic_white_circle);
                }

                deleteCount++;

                if(j>=0){
                    //j = count-deleteCount;
                    j--;
                }

                if(j<0) {
                    j=0;
                }

                Log.d(TAG, "deleteCount : "+deleteCount);
                Log.d(TAG, "숫자 1개 삭제 후 j : "+j);
                Log.d(TAG, "숫자 1개 삭제 후 count : "+count);
                //Log.d(TAG, "PIN : "+pin2[0]+pin2[1]+pin2[2]+pin2[3]);
            }
        });
    }
}