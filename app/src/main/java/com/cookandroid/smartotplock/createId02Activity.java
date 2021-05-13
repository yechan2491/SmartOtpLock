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

public class createId02Activity extends AppCompatActivity {
    ImageButton backBtn;
    Button nextBtn;

    EditText emailText,nameText;
    TextView warningText1, warningText2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id02);

        backBtn=(ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        emailText=(EditText)findViewById(R.id.certificationText);
        nameText=(EditText)findViewById(R.id.phoneText);
        warningText1=(TextView)findViewById(R.id.warningText1);
        warningText2=(TextView)findViewById(R.id.warningText2);


        /***nameText 이벤트처리***/
        nameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol(nameText.getText().toString())){  //특수 문자가 아닐때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol(nameText.getText().toString())){  //특수 문자가 아닐때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol(nameText.getText().toString())){  //특수 문자가 있을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });
        nameText.addTextChangedListener(new TextWatcher() {
            @Override  //입력하기 전에 호출되는 API  //무언가 바뀐시점전에
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override  //EditText에 변화가 있을 때  //무언가 바뀐시점
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol(nameText.getText().toString())){ //특수 문자가 있을때 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText1.setVisibility(View.VISIBLE);
                    }
                }
                else{  // 특수문자가 아닐때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override  //입력이 끝났을 때 //무언가 바뀐 이후
            public void afterTextChanged(Editable editable) {

            }
        });
        ///////////////////////////////////////////////////////////////////////


        /***emailText 이벤트 처리***/
        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol2(emailText.getText().toString())){  //정상적인 조합일때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol2(emailText.getText().toString())){  //정상적인 조합일때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol2(emailText.getText().toString())){  //정상적인 조합이 아닐때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });

        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol2(emailText.getText().toString())){ //메일에 @ 없을때 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText2.setVisibility(View.VISIBLE);
                    }
                }
                else{  //메일에 @있을때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText2.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //////////////////////////////////////////////////////////
        
        
        
        
        nextBtn=(Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), createId03Activity.class);
                startActivity(intent);
            }
        });

    }

    /***nameText 에딧텍스트 컴포넌트에 특수문자가 있으면 true 리턴 그 외 false 리턴***/
    boolean isContainsSymbol(String input){
        char c;
        char array[];

        array=input.toCharArray();

        for(int i=0; i<array.length; i++  ){
            c=array[i];
            if( !((c>=0x30 && c<=0x39) || (c>=0x41 && c<=0x5A) || (c>=0x61 && c<=0x7A))) {  //숫자 소문자 대문자 아니면
                return true;    // 한글/특수문자 발견되었을 경우 true리턴
            }
            else if(array.length>20) {return true;}
        }
        return false; // 영문, 숫자만 있을 경우  false 리턴
    }
    /////////////////////////////////////////////////////////////////////

    /***emailText 에딧텍스트 컴포넌트에 이메일에 @입력되지 않으면 true 리턴***/
    boolean isContainsSymbol2(String input) {
        char c;                            //true를 리턴하면 빨간색    //false를 리턴하면 검정색
        char array[];
        array = input.toCharArray();
        if(array.length==0) return false;
        if(input.contains("@"))return false;
        else return true;

    }
    /////////////////////////////////////////////////////////////////////

}
