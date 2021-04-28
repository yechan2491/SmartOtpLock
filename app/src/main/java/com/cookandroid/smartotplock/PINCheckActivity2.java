package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class PINCheckActivity2 extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[4];
    Integer[] numImageIDs = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    TextView[] textViews = new TextView[6];
    Integer[] textViewsID = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6};
    int i=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pincheck2);

        for(i=0; i<imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(numImageIDs[i]);
        }

        for(i=0; i<textViews.length; i++) {
            textViews[i] = (TextView) findViewById(textViewsID[i]);
        }

        textViews[2].setPaintFlags(textViews[2].getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // "PIN을 잊으셨나요?"에 밑줄긋기


        for(i=0; i<imageViews.length; i++) {
            final int index;
            index = i;

            imageViews[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), PINCheckActivity3.class);
                    startActivity(intent);
                    finish();

                }
            });
        }

    }
}
