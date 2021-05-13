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


    private String passArray=""; // 패스워드 저장을 위한 배열


    @Override //aa
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id01);

        backBtn=(ImageButton) findViewById(R.id.backBtn);
        nextBtn=(Button)findViewById(R.id.nextBtn);

        idText=(EditText)findViewById(R.id.phoneText);
        passText=(EditText)findViewById(R.id.certificationText);
        passCheckText=(EditText)findViewById(R.id.passCheckText);

        warningText1=(TextView)findViewById(R.id.warningText1);
        warningText2=(TextView)findViewById(R.id.warningText2);
        warningText3=(TextView)findViewById(R.id.warningText3);






        /***idText 이벤트처리***/
        idText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol(idText.getText().toString())){  //특수 문자가 아닐때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol(idText.getText().toString())){  //특수 문자가 아닐때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol(idText.getText().toString())){  //특수 문자가 있을때 빨간색
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
                if(isContainsSymbol(idText.getText().toString())){ //특수 문자가 있을때 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText1.setVisibility(View.VISIBLE);
                    }
                }
                else{  // 특수문자가 아닐때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        idText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override  //입력이 끝났을 때 //무언가 바뀐 이후
            public void afterTextChanged(Editable editable) {

            }
        });
        ///////////////////////////////////////////////////////////////////////

        /***passText 이벤트 처리***/
        passText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol2(passText.getText().toString())){  //정상적인 조합일때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol2(passText.getText().toString())){  //정상적인 조합일때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol2(passText.getText().toString())){  //정상적인 조합이 아닐때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });

        passText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol2(passText.getText().toString())){ //문자 숫자 특정특수문자 조합이 아닐때 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        passText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText2.setVisibility(View.VISIBLE);
                    }
                }
                else{  //문자 숫자 특정특수문자 조합일때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        passText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText2.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //////////////////////////////////////////////////////////

        /***passCheckText 이벤트 처리***/
        passCheckText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol3(passCheckText.getText().toString())){ //비밀번호 일치인상태 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol3(passCheckText.getText().toString())){  //비밀번호 일치인상태 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol3(passCheckText.getText().toString())){  //비밀번호 불일치시
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });

        passCheckText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol3(passCheckText.getText().toString())){ //비밀번호 불일치시 빨간색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText3.setVisibility(View.VISIBLE);
                    }
                }
                else{  //비밀번호 일치시 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText3.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //////////////////////////////////////////////////////////


        /***뒤로가기버튼 이벤트***/
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //////////////////////////////


        /***다음버튼 이벤트***/
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), service_terms.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////
    }

    /***idText 에딧텍스트 컴포넌트에 특수문자가 있으면 true 리턴 그 외 false 리턴***/
    boolean isContainsSymbol(String input){
        char c;
        char array[];

        array=input.toCharArray();

        for(int i=0; i<array.length; i++  ){
            c=array[i];
            if( !((c>=0x30 && c<=0x39) || (c>=0x41 && c<=0x5A) || (c>=0x61 && c<=0x7A))) {  //숫자 소문자 대문자 아니면
                return true;    // 한글/특수문자 발견되었을 경우 true리턴
            }
            else if(array.length<6 || array.length>20) {return true;}
        }
        return false; // 영문, 숫자만 있을 경우  false 리턴
    }
    /////////////////////////////////////////////////////////////////////

    /***passText 에딧텍스트 컴포넌트에 문자,영문,숫자조합으로 비밀번호가 입력되지 않거나 10자리 미만이면 true 리턴***/
    boolean isContainsSymbol2(String input) {
        char c;                            //true를 리턴하면 빨간색    //false를 리턴하면 검정색
        char array[];
        passArray=input;
        array = input.toCharArray();
        Boolean state[]=new Boolean[3];

        ////
        if(!passCheckText.getText().toString().equals(passArray)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                warningText3.setVisibility(View.VISIBLE);
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                passCheckText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                warningText3.setVisibility(View.INVISIBLE);
            }
        }
        ///


        for(int i=0; i<3; i++)state[i]=false;
        for (int i = 0; i < array.length; i++) {
            c = array[i];
//           if ((c >= 0x30 && c <= 0x39) || (c >= 0x41 && c <= 0x5A) || (c >= 0x61 && c <= 0x7A) || c == '!' || c == '@' || c == '#'
//                    || c == '$' || c == '%' || c == '^' || c == '&' || c == '*') {
//                return false;
                if(c>=0x30 && c<=0x39)
                    state[0]=true;
                else if((c>=0x41&&c<=0x5a) || (c>=0x61&&c<=0x7A))
                    state[1]=true;
                else if(c == '!' || c == '@' || c == '#'
                    || c == '$' || c == '%' || c == '^' || c == '&' || c == '*')
                    state[2]=true;
                else return true;
        }
        if(array.length==0) return false;
        else if(array.length<10) return true;
        else if(state[0] && state[1] && state[2])
            return false;
        return true;
    }
    /////////////////////////////////////////////////////////////////////


    /***passCheckText 에딧텍스트 비밀번호 일치여부 이벤트 발생용용***/
    boolean isContainsSymbol3(String input){  //true를 리턴하면 빨간색    //false를 리턴하면 검정색
        if(input.equals(passArray)){
            return false;
        }
        return  true; // 그 외 true;
    }
    /////////////////////////////////////////////////////////////////////


}
