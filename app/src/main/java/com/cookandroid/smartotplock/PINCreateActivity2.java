package com.cookandroid.smartotplock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Scanner;

public class PINCreateActivity2 extends AppCompatActivity {

    char[] pin = {'a', 'a', 'a', 'a'};
    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    TextView[] textViews = new TextView[6];
    Integer[] textViewsID = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6};
    ImageView blueImg;
    Scanner scanner = new Scanner(System.in);
    int i=0, j=0, count=0, deleteCount=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pincreate2);

        for(i=0; i<imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(numImageIDs[i]);
        }

        for(i=0; i<textViews.length; i++) {
            textViews[i] = (TextView) findViewById(textViewsID[i]);
        }

        blueImg = (ImageView) findViewById(R.id.blueimg);
        textViews[2].setPaintFlags(textViews[2].getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // "PIN 번호를 잊으셨나요?"에 밑줄긋기


        for(i=0; i<imageViews.length; i++) {
            final int index;
            index = i;

            imageViews[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // EditText 없을 때 키보드 열기
                    //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);

                    int pinNum = scanner.nextInt(); // 스캐너로 입력받아 정수형으로 저장
                    String pinString = Integer.toString(pinNum);  // 입력받은 pin을 문자열로 변환
                    pin[j]=(pinString.charAt(j)); // pin 배열에 한 글자씩 저장
                    imageViews[j-deleteCount].setImageResource(R.drawable.ic_black_circle);  // 버튼 1개 누를 때마다 pin 입력 이미지 변경
                    j++;
                    count++;

                    if(j==4) {
                        j=0;
                        count=0;

                        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("pin", Arrays.toString(pin));
                        editor.apply(); // editor 종료

                        Intent intent = new Intent(getApplicationContext(), PINCheckActivity.class);
                        startActivity(intent);
                        finish(); // 현재 intent 종료
                    }


                }
            });
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
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
        return false;
    }
}
