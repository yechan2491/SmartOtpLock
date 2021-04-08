package com.cookandroid.smartotplock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class create_id3 extends AppCompatActivity {
    ImageButton backBtn;
    Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id3);

        backBtn=(ImageButton)findViewById(R.id.backBtn);
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
                Intent intent=new Intent(getApplicationContext(),create_id3_blockchain.class);
                startActivity(intent);
            }
        });
    }
}
