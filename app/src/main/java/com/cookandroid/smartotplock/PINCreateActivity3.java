package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PINCreateActivity3 extends AppCompatActivity {
    //int[] pin = {0, 0, 0, 0};
    String pin="";

    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    Button btnCancel;
    ImageButton btnDelete;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
    TextView[] textViews = new TextView[6];
    Integer[] textViewsID = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6};

    int i=0, j=0, count=0, deleteCount=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pincreate3);

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnDelete = (ImageButton) findViewById(R.id.btn_delete);

        for(i=0; i<imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(numImageIDs[i]);
        }

        for(i=0; i<numBtnIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for(i=0; i<textViews.length; i++) {
            textViews[i] = (TextView) findViewById(textViewsID[i]);
        }

        textViews[2].setPaintFlags(textViews[2].getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // "PIN을 잊으셨나요?"에 밑줄긋기
        textViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PinForgot.class);
                startActivity(intent);
                finish();
            }
        });

        for(i=0; i<numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pin[j] = Integer.parseInt(numButtons[index].getText().toString()); // pin 배열에 사용자가 누른 버튼의 숫자 대입
                    pin = pin + numButtons[index].getText().toString();
                    System.out.println(pin);

                    imageViews[j-deleteCount].setImageResource(R.drawable.bluecircle);  // 버튼 1개 누를 때마다 pin 입력 이미지 변경
                    j++;
                    count++; // 버튼 누른 횟수 1씩 증가

                    if(j == 4) {
                        count=0;
                        j=0;

                        // Toast.makeText(getApplicationContext(), "PIN : " + pin[0]+pin[1]+pin[2]+pin[3], Toast.LENGTH_LONG).show();

                        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("pin", pin); // PIN이라는 key에 입력받은 pin 네자리 저장
                        editor.apply(); // editor 종료

                        Intent intent = new Intent(getApplicationContext(), PINCheckActivity2.class);
                        startActivity(intent);
                        finish(); // 현재 intent 종료
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
                    imageViews[a-1].setImageResource(R.drawable.graycircle);
                } else {
                    imageViews[0].setImageResource(R.drawable.graycircle);
                }

                deleteCount++;

                if(j>=0){
                    //j = count-deleteCount;
                    j--;
                }

                if(j<0) {
                    j=0;
                }
            }
        });
    }
}
