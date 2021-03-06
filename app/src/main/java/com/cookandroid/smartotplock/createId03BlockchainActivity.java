package com.cookandroid.smartotplock;

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

public class createId03BlockchainActivity extends AppCompatActivity {

    private final String BASEURL = "http://34.204.61.107";

    ImageButton backBtn;
    TextView warningText1;
    EditText phoneText;
    Button nextBtn;
    String CLIENT_NAME, CLIENT_PHONE, CLIENT_ID, CLIENT_PWD, CLIENT_EMAIL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_id03_blockchain);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        backBtn=findViewById(R.id.backBtn);
        warningText1=(TextView)findViewById(R.id.warningText1);
        phoneText=(EditText)findViewById(R.id.phoneText);

        SharedPreferences preferences = getSharedPreferences("User1", 0);
        CLIENT_NAME = preferences.getString("userName", "");
        CLIENT_PHONE = preferences.getString("userPhoneNum", "");
        CLIENT_ID = preferences.getString("userID", "");
        CLIENT_PWD = preferences.getString("userPassword", "");
        CLIENT_EMAIL = preferences.getString("userEmail", "");

        nextBtn=(Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Toast.makeText(getApplicationContext(),"??????????????? ?????????????????????.",Toast.LENGTH_LONG).show();
//                            finish();
                        }
                        //else Toast.makeText(getApplicationContext(),"??????",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"??????????????? ?????????????????????.",Toast.LENGTH_LONG).show();
                        Log.d("?????? : ", t.getMessage());
                    }
                });

//                Intent intent=new Intent(getApplicationContext(), signUpActivity.class);
//                startActivity(intent);
            }
        });

        /***phoneText ???????????????***/
        phoneText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){ // ???????????? ??????
                    if(!isContainsSymbol(phoneText.getText().toString())){  //?????? ????????? ????????? ??????
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d4d4d8")));
                        }
                    }
                }
                if(b){ // ???????????? ??????
                    if(!isContainsSymbol(phoneText.getText().toString())){  //?????? ????????? ????????? ?????????
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        }
                    }
                    if(isContainsSymbol(phoneText.getText().toString())){  //?????? ????????? ????????? ?????????
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        }
                    }


                }
            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override  //???????????? ?????? ???????????? API  //????????? ??????????????????
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override  //EditText??? ????????? ?????? ???  //????????? ????????????
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isContainsSymbol(phoneText.getText().toString())){ //?????? ????????? ????????? ?????????
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff3120")));
                        warningText1.setVisibility(View.VISIBLE);
                    }
                }
                else{  // ??????????????? ????????? ?????????
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        phoneText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#191919")));
                        warningText1.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override  //????????? ????????? ??? //????????? ?????? ??????
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

    /***phoneText ??????????????? ??????????????? ????????????????????? ***/ //true ????????? ????????? false ????????? ?????????
    boolean isContainsSymbol(String input){
        char c;
        char array[];

        array=input.toCharArray();

        for(int i=0; i<array.length; i++  ){
            c=array[i];
            if( (!(c>=0x30 && c<=0x39) || array.length!=11 )) {  //?????? ?????? ????????? ??????????????? 11?????????
                return true;    // ??????/???????????? ??????????????? ?????? true??????
            }
        }
        return false; // ??????, ????????? ?????? ??????  false ??????
    }
    /////////////////////////////////////////////////////////////////////



}
