package com.cookandroid.smartotplock;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
//import com.kakao.sdk.template.model.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signUpActivity extends AppCompatActivity {

    EditText password_edit, newidEditText;
    TextView sign_up_text;
    private View loginButton;
    Button loginBtn;
    SharedPreferences pref_firstRun, pref;
    Dialog dialog;
    CheckBox checkBox1, checkBox2;
    LinearLayout linearLayout;
    private long backKeyPressedTime = 0;
    //private TextView nickName;
    //private ImageView profileImage;

    private Retrofit retrofit;
;
    private final String BASEURL = "http://34.204.61.107";
    private EditText idEditText,pwEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        id_edit = (EditText)findViewById(R.id.idEditText);
        password_edit = (EditText) findViewById(R.id.pwEditText);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);
        loginBtn = (Button) findViewById(R.id.textView2);
//        loginButton = (ImageView) findViewById(R.id.login);
//        nickName=findViewById(R.id.nickname);
//        profileImage=findViewById(R.id.profile);

        newidEditText = (EditText)findViewById(R.id.newidEditText);


        pref_firstRun = getSharedPreferences("FirstRun", MODE_PRIVATE);
        pref = getSharedPreferences("PREFS", MODE_PRIVATE);

        // 인터넷 연결 되어있는지 확인하기
        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if ((status != NetworkStatus.TYPE_MOBILE) && (status != NetworkStatus.TYPE_WIFI)) {   // 3G, LTE, WIFI로 연결되지 않았을 경우(네트워크 연결 X)
            //textView.setText("모바일로 연결됨");
//            AlertDialog.Builder NoNetwork = new AlertDialog.Builder(this)
//                    .setMessage("네트워크 상태가 원활하지 않습니다.\n연결 상태를 확인 후\n다시 시도해 주세요.")
//                    .setPositiveButton("종료", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                        }
//                    })
//                    .setCancelable(false);  // 뒤로가기 버튼으로 팝업창이 닫히지 않게 함
//            AlertDialog msgDlg = NoNetwork.create();
//            //msgDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//            msgDlg.setOnShowListener(new DialogInterface.OnShowListener() {
//                @Override
//                public void onShow(DialogInterface dialogInterface) {
//                    msgDlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
//
//                }
//            });
//            msgDlg.show();

            dialog = new Dialog(signUpActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog);

            showDialog1();

        }


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

//        loginButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(LoginClient.getInstance().isKakaoTalkLoginAvailable(signUpActivity.this)){
//                    LoginClient.getInstance().loginWithKakaoTalk(signUpActivity.this, callback);
//                }
//                else {
//                    LoginClient.getInstance().loginWithKakaoAccount(signUpActivity.this,callback);
//                }
//            }
//        });

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), createId01Activity.class);
                startActivity(intent);
            }
        });

//        sign_up_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(), createId01Activity.class);
//                startActivity(intent);
//            }
//        });

//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getApplicationContext(), setting.class);
//                startActivity(intent);
//            }
//        });

        ////////////////////////로그인/////////////////////////


        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newidEditText.getText().toString().equals("") || password_edit.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
//                    HashMap<String, String> input = new HashMap<>();
//                    input.put("CLIENT_ID", inputID);
//                    input.put("CLIENT_PWD", inputPassword);

                    String inputID = newidEditText.getText().toString();
                    String inputPassword = password_edit.getText().toString();

//                    jsonPlaceHolderApi.loginData(input).enqueue(new Callback<Post>() {
                    jsonPlaceHolderApi.loginData2(inputID, inputPassword).enqueue(new Callback<Post>() {
//                    jsonPlaceHolderApi.loginData2(Nametext).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if(response.isSuccessful() && response.body() != null){
                                Boolean Result = response.body().getResult();
                                String username = response.body().getCLIENT_NAME().toString();
                                System.out.println("adsadasdasdasdasdasdasdasdasdasdas : "+ username);

                                SharedPreferences pref2 = getSharedPreferences("CheckUsername", 0);
                                SharedPreferences.Editor editor = pref2.edit();
                                editor.putString("username", username);
                                editor.apply();

//                                Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
//                                intent.putExtra("username", username);


                                if(Result){   // 아이디와 비번이 맞으면
                                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                                    if(pref.contains("pin")==false || pref.contains("password")==false) {  // PIN과 패턴이 등록되어 있지 않다면
                                        //System.out.println("AAA");
                                        Intent intent =new Intent(getApplicationContext(), pinCreateActivity.class); // PIN 설정 화면으로 이동
                                        startActivity(intent);
                                    } else {
                                        Intent intent =new Intent(getApplicationContext(), passVerificationActivity.class);  // 둘 다 등록되어 있다면 PIN 확인 화면으로 이동
                                        startActivity(intent);
                                    }


//                                    String username = response.body().getCLIENT_NAME();
//                                    System.out.println(username);
//                                    Log.d("Asd", username);

                                    /////////////////로그인 수정 필요!!!/////////////////
//                                    if(pref.contains("pin")==false || pref.contains("password")==false) {  // PIN과 패턴이 등록되어 있지 않다면
//                                        //System.out.println("AAA");
//                                        Intent intent =new Intent(getApplicationContext(), pinCreateActivity.class); // PIN 설정 화면으로 이동
//                                        startActivity(intent);
//                                    } else {
//                                        Intent intent =new Intent(getApplicationContext(), passVerificationActivity.class);  // 둘 다 등록되어 있다면 PIN 확인 화면으로 이동
//                                        startActivity(intent);
//                                    }
                                }else{  // 아이디와 비번이 틀리면
                                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                                    System.out.println("Result : " + Result);
                                }
                            }
//                        Post result = response.body();
//                        Toast.makeText(signUpActivity.this, "아이디 : " + result.getCLIENT_ID() + "비밀번호 : " + result.getCLIENT_PWD(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Toast.makeText(signUpActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            Log.d("로그인 실패 : ", t.getMessage());
                        }
                    });


                    //메인화면에 사용자 이름 띄우기 위해 로그인 하는 아이디를 서버로 보내 사용자 이름 받아오기
//                    jsonPlaceHolderApi.checkUsername(inputID).enqueue(new Callback<Post>() {
//                        @Override
//                        public void onResponse(Call<Post> call, Response<Post> response) {
//                            Toast.makeText(getApplicationContext(), "username", Toast.LENGTH_SHORT).show();
//                            if (response.isSuccessful() && response.body() != null) {
//                                String username = response.body().getCLIENT_NAME();
//                                System.out.print("adsadasdasdasdasdasdasdasdasdasdas : "+ username);
//                                Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
//                                intent.putExtra("username", username);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Post> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), "이름받아오기 실패", Toast.LENGTH_SHORT).show();
//                        }
//                    });


                    long nowTime = System.currentTimeMillis();
                    Date date = new Date(nowTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String getTime = dateFormat.format(date);

                    SharedPreferences preferences = getSharedPreferences("Time", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("time", getTime);
                    editor.apply();




                    // 메인화면에 사용자 이름 띄우기 위해 로그인 하는 아이디를 서버로 보내 사용자 이름 받아오기
//                    jsonPlaceHolderApi.checkUsername(inputID).enqueue(new Callback<Post>() {
//                        @Override
//                        public void onResponse(Call<Post> call, Response<Post> response) {
//                            if (response.isSuccessful() && response.body() != null) {
//                                String username = response.body().getCLIENT_NAME();
//                                Intent intent = new Intent(getApplicationContext(), userPageActivity.class);
//                                intent.putExtra("username", username);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Post> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), "이름받아오기 실패", Toast.LENGTH_SHORT).show();
//                        }
//                    });






                }
            }
        });


        ///////////////////////////여기서부터 레트로핏
//        idEditText=(EditText)findViewById(R.id.idEditText) ;
//        init();
//        GitHub gitHub = retrofit.create(GitHub.class);
//        // 인터페이스에 구현한 메소드인 contributors에 param 값을 넘기는 요청 만든다.
//        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");
//        // 앞서만든 요청을 수행
//        call.enqueue(new Callback<List<Contributor>>() {
//            @Override
//            // 성공시
//            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                List<Contributor> contributors = response.body();
//                // 받아온 리스트를 순회하면서
//
//                idEditText.setText(contributors.get(0).login);
////                for (Contributor contributor : contributors) {
////                    // 텍스트 뷰에 login 정보를 붙임
////                    textView.append(contributor.login);
////                }
//            }
//            @Override
//            // 실패시
//            public void onFailure(Call<List<Contributor>> call, Throwable t) {
//                Toast.makeText(signUpActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
//                        .show();
//            }
//        });

    }

    // 네트워크 오류 팝업창
    public void showDialog1() {
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button exit = dialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dialog.setCancelable(false);
    }

    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(signUpActivity.this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        else {
//            AppFinish();
            // 앱 완전 종료 : 태스크 리스트에서도 완전히 지우는 방법
            moveTaskToBack(true);
            finishAndRemoveTask();
            android.os.Process.killProcess(android.os.Process.myPid());

            // 태스크 리스트에서 앱이 남는 방법
//            moveTaskToBack(true);
//            finish();
//            android.os.Process.killProcess(android.os.Process.myPid());

        }

//        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
//            finish();
//        }
    }

    private Activity activity;

    public void AppFinish() {
        createId02Activity Create_01 = (createId02Activity)createId01Activity.Create_01;
        createId02Activity Create_02 = (createId02Activity)createId02Activity.Create_02;
        createId02Activity Create_03 = (createId02Activity)createId03Activity.Create_03;

        Create_01.finish();
        Create_02.finish();
        Create_03.finish();

//        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
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
