package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
//import com.kakao.sdk.template.model.Button;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class sign_up extends AppCompatActivity {
    TextView sign_up_text;
    private View loginButton;
    Button loginBtn;
    SharedPreferences preferences;
    //private TextView nickName;
    //private ImageView profileImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sign_up_text=(TextView)findViewById(R.id.sign_up_text);

        loginBtn=(Button)findViewById(R.id.textView2);
        loginButton = findViewById(R.id.login);
//        nickName=findViewById(R.id.nickname);
//        profileImage=findViewById(R.id.profile);


        //하은 수정(04.30)
        Intent intent = new Intent(this, loading.class);
        startActivity(intent);
        preferences = getSharedPreferences("FirstRun", MODE_PRIVATE);
        checkFirstRun();



        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken!=null){

                }
                if (throwable != null){

                }
//                updateKakaoLoginUi();
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(LoginClient.getInstance().isKakaoTalkLoginAvailable(sign_up.this)){
                    LoginClient.getInstance().loginWithKakaoTalk(sign_up.this, callback);
                }
                else {
                    LoginClient.getInstance().loginWithKakaoAccount(sign_up.this,callback);
                }
            }
        });

        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), create_id1.class);
                startActivity(intent);
            }
        });

//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(), setting.class);
//                startActivity(intent);
//            }
//        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), PINCreateActivity2.class);
                startActivity(intent);
            }
        });
    }

    // 앱을 처음으로 실행한다면 인트로화면1, 2 출력
    // 처음 실행이 아니라면 로딩화면 다음으로 바로 로그인 화면 출력
    public void checkFirstRun() {
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if(isFirstRun) {
            Intent intent = new Intent(sign_up.this, intro01.class);
            startActivity(intent);

            preferences.edit().putBoolean("isFirstRun", false).apply();
        }
    }




//    private void updateKakaoLoginUi(){
//        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
//            @Override
//            public Unit invoke(User user, Throwable throwable) {
//                if(user != null){
//
//                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
//                    user.getKakaoAccount().getProfile().getThumbnailImageUrl();
//
//                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);
//                    loginButton.setVisibility(View.GONE);
//                }
//                else {
//                    nickName.setText(null);
//                    profileImage.setImageBitmap(null);
//                    loginButton.setVisibility(View.VISIBLE);
//                }
//                return null;
//            }
//        });
//    }
}
