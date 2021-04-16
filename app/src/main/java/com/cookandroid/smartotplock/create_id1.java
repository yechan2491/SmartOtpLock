package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class create_id1 extends AppCompatActivity { // commit first test
    ImageButton backBtn; //yechan - test commit2
    Button nextBtn;  //lee  //test2

    EditText idText,passText,passCheckText;
    TextView warningText1, warningText2,warningText3;

    @Override //aa
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id1);

        backBtn=(ImageButton) findViewById(R.id.backBtn);
        nextBtn=(Button)findViewById(R.id.nextBtn);

        idText=(EditText)findViewById(R.id.idText);
        passText=(EditText)findViewById(R.id.passText);
        passCheckText=(EditText)findViewById(R.id.passCheckText);

        warningText1=(TextView)findViewById(R.id.warningText1);
        warningText2=(TextView)findViewById(R.id.warningText2);
        warningText3=(TextView)findViewById(R.id.warningText3);


        idText.addTextChangedListener(new TextWatcher() {
            @Override //입력하여 변화가 생기기 전에 처리는 beforeTextChanged() 부분에 // 입력하기 전에 조치
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                }
            }

            @Override  //변화와 동시에 처리를 해주고자 할 때는 onTextChanged() 부분에
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override  //입력이 끝났을 때 처리는 afterTextChanged() 부분
            public void afterTextChanged(Editable editable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                }
            }
        });


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
