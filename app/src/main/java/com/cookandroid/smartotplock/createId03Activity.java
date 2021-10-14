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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createId03Activity extends AppCompatActivity {

    private final String BASEURL = "http://34.204.61.107";

    ImageButton backBtn;
    Button nextBtn;
    TextView warningText1;
    EditText phoneText, certificationText;
    String CLIENT_NAME, CLIENT_PHONE, CLIENT_ID, CLIENT_PWD, CLIENT_EMAIL;

    public static Activity Create_03;
//    Boolean resultA=true, resultB=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id03);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        backBtn=(ImageButton)findViewById(R.id.backBtn);
        nextBtn=(Button)findViewById(R.id.nextBtn);
        warningText1=(TextView)findViewById(R.id.warningText1);
        phoneText=(EditText)findViewById(R.id.phoneText);
        certificationText =(EditText)findViewById(R.id.certificationText);

        Create_03 = createId03Activity.this;

        /***phoneText 이벤트처리***/
        phoneText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // 포커스가 없고
                    if(!isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 아닐때 회색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
//                            resultA = false;
                        }
                    }
                }
                if(b){ // 포커스가 있고
                    if(!isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 아닐때 검은색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
//                            resultA = true;
                        }
                    }
                    if(isContainsSymbol(phoneText.getText().toString())){  //특수 문자가 있을때 빨간색
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
//                            resultA = false;
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
//                        resultB = false;
                        nextBtn.setEnabled(false);
                        nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
                    }
                }
                else{  // 특수문자가 아닐때 검정색
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
//                        resultB = true;
                        nextBtn.setEnabled(true);
                        nextBtn.setBackgroundResource(R.drawable.solid_button);
                    }
                }
            }

            @Override  //입력이 끝났을 때 //무언가 바뀐 이후
            public void afterTextChanged(Editable editable) {

            }
        });
        ///////////////////////////////////////////////////////////////////////

        /***next 버튼 활성화 처리***/
//        if (resultA==true && resultB==true) {  // 핸드폰 번호 입력 조건 성립할 경우
//            nextBtn.setEnabled(true);
//            nextBtn.setBackgroundResource(R.drawable.solid_button);
//        }
//        else {  // 핸드폰 번호 입력 조건 성립하지 않을 경우
//            nextBtn.setEnabled(false);
//            nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
//        }
        ///////////////////////////////////////////////////////////////////////

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPhoneNum = phoneText.getText().toString();

                SharedPreferences preferences = getSharedPreferences("User1", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userPhoneNum", userPhoneNum);
                editor.apply();

                SharedPreferences pref = getSharedPreferences("User1", 0);
                CLIENT_NAME = pref.getString("userName", "");
                CLIENT_PHONE = pref.getString("userPhoneNum", "");
                CLIENT_ID = pref.getString("userID", "");
                CLIENT_PWD = pref.getString("userPassword", "");
                CLIENT_EMAIL = pref.getString("userEmail", "");

                HashMap<String, Object> input = new HashMap<>();
                input.put("CLIENT_ID", CLIENT_ID);
                input.put("CLIENT_PWD", CLIENT_PWD);
                input.put("CLIENT_NAME", CLIENT_NAME);
                input.put("CLIENT_PHONE", CLIENT_PHONE);
                input.put("CLIENT_EMAIL", CLIENT_EMAIL);

                jsonPlaceHolderApi.postData(input).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()) {
                            Post postResponse = response.body();
                            Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(), signUpActivity.class);
                            startActivity(intent);
//                            finish();
                        }
                        //else Toast.makeText(getApplicationContext(),"성공",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"회원가입이 실패하였습니다.",Toast.LENGTH_LONG).show();
                        Log.d("오류 : ", t.getMessage());

                        Intent intent=new Intent(getApplicationContext(), signUpActivity.class);
                        startActivity(intent);
                    }
                });
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
