package com.cookandroid.smartotplock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class sign_up extends AppCompatActivity {
    TextView sign_up_text;

    private View loginButton;
    //private TextView nickName;
    //private ImageView profileImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sign_up_text=(TextView)findViewById(R.id.sign_up_text);

        loginButton = findViewById(R.id.login);
//        nickName=findViewById(R.id.nickname);
//        profileImage=findViewById(R.id.profile);

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
