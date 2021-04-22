package com.cookandroid.smartotplock;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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


        idText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(!isContainsSymbol(idText.getText().toString())){  //특수 문자가 아닐때
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){
                    if(!isContainsSymbol(idText.getText().toString())){  //특수 문자가 아닐때
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol(idText.getText().toString())){  //특수 문자가 일때
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });
        idText.addTextChangedListener(new TextWatcher() {
            @Override  //입력하기 전에 호출되는 API  //무언가 바뀐시점전에
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override  //EditText에 변화가 있을 때  //무언가 바뀐시점
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol(idText.getText().toString())){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                    }
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                    }
                }
            }
            @Override  //입력이 끝났을 때 //무언가 바뀐 이후
            public void afterTextChanged(Editable editable) {

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

    boolean isContainsSymbol(String input){
        char c;
        char array[];
        array=input.toCharArray();
        for(int i=0; i<array.length; i++  ){
            c=array[i];
            if( !((c>=0x30 && c<=0x39) || (c>=0x41 && c<=0x5A) || (c>=0x61 && c<=0x7A))) {
                return true;    // 한글/특수문자 발견되었을 경우 true리턴
            }
        }
        return false; // 영문, 숫자만 있을 경우  false 리턴

    }

}
