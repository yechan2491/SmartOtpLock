package com.cookandroid.smartotplock;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class terms2Activity extends AppCompatActivity {

    ImageButton backBtn;
    TextView termText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms2);

        backBtn=(ImageButton)findViewById(R.id.backBtn);//
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        termText = (TextView) findViewById(R.id.termText);
        try {
            StringBuffer data = new StringBuffer();
            InputStream inputStream = getResources().openRawResource(R.raw.serviceterm2);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            String str = buffer.readLine();
            while (str != null) {
                data.append(str + "\n");
                str = buffer.readLine();
            }
            termText.setText(data);
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "파일없음", Toast.LENGTH_SHORT).show();
        }
    }
}