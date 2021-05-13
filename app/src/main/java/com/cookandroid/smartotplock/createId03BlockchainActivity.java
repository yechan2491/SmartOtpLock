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

public class createId03BlockchainActivity extends AppCompatActivity {
    ImageButton backBtn;
    TextView warningText1;
    EditText phoneText;
    Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id03_blockchain);
        backBtn=findViewById(R.id.backBtn);
        warningText1=(TextView)findViewById(R.id.warningText1);
        phoneText=(EditText)findViewById(R.id.phoneText);


        nextBtn=(Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
            }
        });

        /***phoneText 이벤트처리***/
        phoneText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 아닐때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 아닐때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 있을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override  //입력하기 전에 호출되는 API  //무언가 바뀐시점전에
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override  //EditText에 변화가 있을 때  //무언가 바뀐시점
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol(phoneText.getText().toString())){ //특수 문자가 있을때 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText1.setVisibility(View.VISIBLE);
                    }
                }
                else{  // 특수문자가 아닐때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override  //입력이 끝났을 때 //무언가 바뀐 이후
            public void afterTextChanged(Editable editable) {

            }
        });
        ///////////////////////////////////////////////////////////////////////



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /***phoneText 에딧텍스트 컴포넌트에 숫자만입력하고 ***/ //true 리턴시 빨간색 false 리턴시 검은색
    boolean isContainsSymbol(String input){
        char c;
        char array[];

        array=input.toCharArray();

        for(int i=0; i<array.length; i++  ){
            c=array[i];
            if( (!(c>=0x30 && c<=0x39) || array.length!=11 )) {  //숫자 또는 입력한 숫자개수가 11아니면
                return true;    // 한글/특수문자 발견되었을 경우 true리턴
            }
        }
        return false; // 영문, 숫자만 있을 경우  false 리턴
    }
    /////////////////////////////////////////////////////////////////////



}
