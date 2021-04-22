package com.cookandroid.smartotplock;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PINCreateActivity2 extends AppCompatActivity {

    int[] pin = {0, 0, 0, 0};
    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    TextView[] textViews = new TextView[6];
    Integer[] textViewsID = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6};
    ImageView blueImg;
    int i=0;


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
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);


                }
            });
        }

    }
}
