package com.cookandroid.smartotplock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class intro01 extends AppCompatActivity {
    ImageButton intro01ImgBtn01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro01);

        intro01ImgBtn01=(ImageButton)findViewById(R.id.intro01ImgBtn01);
        intro01ImgBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), intro02.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_right,R.anim.out_left);

                //startActivity(new Intent(현재Activity.this, 불러올Activity.class));
                //overridePendingTransition(R.anim.현재(나타날)Activity애니메이션, R.anim.현재(사라질)Activity애니메이션);


            }
        });

    }
}