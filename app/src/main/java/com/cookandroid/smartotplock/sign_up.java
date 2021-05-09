package com.cookandroid.smartotplock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
//import com.kakao.sdk.template.model.Button;

import java.util.HashMap;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sign_up extends AppCompatActivity {
    TextView sign_up_text;
    private View loginButton;
    Button loginBtn;
    SharedPreferences pref_firstRun, pref;
    //private TextView nickName;
    //private ImageView profileImage;

    private EditText idEditText,pwEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);



        sign_up_text=(TextView)findViewById(R.id.sign_up_text);

        loginBtn=(Button)findViewById(R.id.textView2);
        loginButton = findViewById(R.id.login);
//        nickName=findViewById(R.id.nickname);
//        profileImage=findViewById(R.id.profile);


        pref_firstRun = getSharedPreferences("FirstRun", MODE_PRIVATE);
        pref = getSharedPreferences("PREFS", MODE_PRIVATE);


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
                //preferences.contains("pin");
//                System.out.println(pref.contains("pin"));
//                System.out.println(pref.contains("password"));

                if(pref.contains("pin")==false && pref.contains("password")==false) {  // PIN과 패턴이 등록되어 있지 않다면
                    //System.out.println("AAA");
                    Intent intent =new Intent(getApplicationContext(), PINCreateActivity2.class); // PIN 설정 화면으로 이동
                    startActivity(intent);
                } else {
                    Intent intent =new Intent(getApplicationContext(), PINVerification.class);  // 둘 다 등록되어 있다면 PIN 확인 화면으로 이동
                    startActivity(intent);
                }
            }
        });

        //////////////여기부터 레트로핏 관련 코드//////////////////////////////
        idEditText=(EditText)findViewById(R.id.idEditText);
        pwEditText=(EditText)findViewById(R.id.pwEditText);

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com") //여기는 서버 URL 정의
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI =retrofit.create(RetrofitAPI.class);

        HashMap<String, Object> input =new HashMap<>();
        input.put("userId",1);
        input.put("title","title title");
        input.put("body","body body 당근 당근");

        retrofitAPI.postData(input).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post data=response.body();
                    Log.d("TEST","POST 성공성공");
                    Log.d("TEST",data.getBody());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


        retrofitAPI.getData("1").enqueue( new Callback<List<Post>>(){    //id 1에 대한 행 정보 다 가져온다고 생각하면됨
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> data=response.body();
                    Log.d("TEST","성공성공");
                    Log.d("TEST",data.get(0).getTitle());   // 가져온 행에 정보중에서 "title" 정보출력
                    idEditText.setText(data.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
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
