package com.cookandroid.smartotplock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class create_id1 extends AppCompatActivity { // commit first test
    ImageButton backBtn; //yechan - test commit2
    Button nextBtn;  //lee  //test2
    @Override //aa
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id1);

        backBtn=(ImageButton) findViewById(R.id.backBtn);
        nextBtn=(Button)findViewById(R.id.nextBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), service_terms.class);
                startActivity(intent);
            }
        });
    }
}
