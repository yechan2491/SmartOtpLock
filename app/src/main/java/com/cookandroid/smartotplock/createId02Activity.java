package com.cookandroid.smartotplock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class createId02Activity extends AppCompatActivity {
    ImageButton backBtn;
    Button nextBtn;

    EditText emailText,nameText;
    TextView warningText1, warningText2;

    public static Activity Create_02;

//    Boolean resultA=true, resultB=true, resultC=true, resultD=true;

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

        Create_02 = createId02Activity.this;


        /***nameText 이벤트처리***/
        nameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol(nameText.getText().toString())){  //특수 문자가 아닐때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
//                            resultA = false;
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol(nameText.getText().toString())){  //특수 문자가 아닐때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
//                            resultA = true;
                        }
                    }
                    if(isContainsSymbol(nameText.getText().toString())){  //특수 문자가 있을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
//                            resultA = false;
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
//                        resultB = false;
                    }
                }
                else{  // 특수문자가 아닐때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        nameText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
//                        resultB = true;
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
//                            resultC = false;
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol2(emailText.getText().toString())){  //정상적인 조합일때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
//                            resultC = true;
                        }
                    }
                    if(isContainsSymbol2(emailText.getText().toString())){  //정상적인 조합이 아닐때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
//                            resultC = false;
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
//                        resultD = false;
                        nextBtn.setEnabled(false);
                        nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
                    }
                }
                else{  //메일에 @있을때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        emailText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText2.setVisibility(View.INVISIBLE);
//                        resultD = true;
                        nextBtn.setEnabled(true);
                        nextBtn.setBackgroundResource(R.drawable.solid_button);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //////////////////////////////////////////////////////////

        /***next 버튼 활성화 처리***/
//        if (resultA==true && resultB==true && resultC==true && resultD==true) {  // 이름, 이메일 입력창 모두 조건 성립할 경우
//            nextBtn.setEnabled(true);
//            nextBtn.setBackgroundResource(R.drawable.solid_button);
//        }
//        else {  // 이름, 이메일 입력창 하나라도 조건 성립하지 않을 경우
//            nextBtn.setEnabled(false);
//            nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
//        }
        //////////////////////////////////////////////////////////
        
        
        
        nextBtn=(Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(nameText.getText().toString().equals("")) && !(emailText.getText().toString().equals(""))) {
                    if(isContainsSymbol(nameText.getText().toString())){ //특수 문자가 있을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Toast.makeText(getApplicationContext(), "이름을 조건에 맞춰 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if(isContainsSymbol2(emailText.getText().toString())){ //메일에 @ 없을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Toast.makeText(getApplicationContext(), "이메일 주소를 조건에 맞춰 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if(!(isContainsSymbol(nameText.getText().toString()))&&!(isContainsSymbol2(emailText.getText().toString()))) {
                        String userName = nameText.getText().toString();
                        String userEmail = emailText.getText().toString();


                        SharedPreferences preferences = getSharedPreferences("User1", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userName", userName);
                        editor.putString("userEmail", userEmail);
                        editor.apply();

                        Intent intent =new Intent(getApplicationContext(), createId03Activity.class);
                        startActivity(intent);

                        // 사용자 이름 메인화면으로 보내기
                        Intent intentName = new Intent(getApplicationContext(), userPageActivity.class);
                        intentName.putExtra("username", userName);
                    }
                }
                else {
                    if(nameText.getText().toString().equals("")&&!(emailText.getText().toString().equals(""))){
                        Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    if(emailText.getText().toString().equals("")&&!(nameText.getText().toString().equals(""))){
                        Toast.makeText(getApplicationContext(), "이메일 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    if(emailText.getText().toString().equals("")&&nameText.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "이름과 이메일 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
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
            if((c>=0x30 && c<=0x39) || (c>=0x41 && c<=0x5A) || (c>=0x61 && c<=0x7A)) {  //숫자 소문자 대문자 입력될 경우
                return true;
            }
            if(c==0x21 || c==0x52 || c==0x40 || (c>=0x23 && c<=0x25)) {  // 특수문자 입력될 경우
                return true;
            }
            else if(array.length>20) {return true;}
        }
        return false; // 한글만 있을 경우  false 리턴
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
