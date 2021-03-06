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

        // ????????? ?????? ??????????????? ????????????
        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if ((status != NetworkStatus.TYPE_MOBILE) && (status != NetworkStatus.TYPE_WIFI)) {   // 3G, LTE, WIFI??? ???????????? ????????? ??????(???????????? ?????? X)
            //textView.setText("???????????? ?????????");
//            AlertDialog.Builder NoNetwork = new AlertDialog.Builder(this)
//                    .setMessage("???????????? ????????? ???????????? ????????????.\n?????? ????????? ?????? ???\n?????? ????????? ?????????.")
//                    .setPositiveButton("??????", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            finish();
//                        }
//                    })
//                    .setCancelable(false);  // ???????????? ???????????? ???????????? ????????? ?????? ???
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

        ////////////////////////?????????/////////////////////////


        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newidEditText.getText().toString().equals("") || password_edit.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "???????????? ??????????????? ??????????????????.", Toast.LENGTH_SHORT).show();
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


                                if(Result){   // ???????????? ????????? ?????????
//                                    Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();

                                    if(pref.contains("pin")==false || pref.contains("password")==false) {  // PIN??? ????????? ???????????? ?????? ?????????
                                        //System.out.println("AAA");
                                        Intent intent =new Intent(getApplicationContext(), pinCreateActivity.class); // PIN ?????? ???????????? ??????
                                        startActivity(intent);
                                    } else {
                                        Intent intent =new Intent(getApplicationContext(), passVerificationActivity.class);  // ??? ??? ???????????? ????????? PIN ?????? ???????????? ??????
                                        startActivity(intent);
                                    }


//                                    String username = response.body().getCLIENT_NAME();
//                                    System.out.println(username);
//                                    Log.d("Asd", username);

                                    /////////////////????????? ?????? ??????!!!/////////////////
//                                    if(pref.contains("pin")==false || pref.contains("password")==false) {  // PIN??? ????????? ???????????? ?????? ?????????
//                                        //System.out.println("AAA");
//                                        Intent intent =new Intent(getApplicationContext(), pinCreateActivity.class); // PIN ?????? ???????????? ??????
//                                        startActivity(intent);
//                                    } else {
//                                        Intent intent =new Intent(getApplicationContext(), passVerificationActivity.class);  // ??? ??? ???????????? ????????? PIN ?????? ???????????? ??????
//                                        startActivity(intent);
//                                    }
                                }else{  // ???????????? ????????? ?????????
                                    Toast.makeText(getApplicationContext(), "????????? ?????? ??????????????? ????????????.", Toast.LENGTH_SHORT).show();
                                    System.out.println("Result : " + Result);
                                }
                            }
//                        Post result = response.body();
//                        Toast.makeText(signUpActivity.this, "????????? : " + result.getCLIENT_ID() + "???????????? : " + result.getCLIENT_PWD(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Toast.makeText(signUpActivity.this, "????????? ?????? : ?????? ?????? ??????", Toast.LENGTH_SHORT).show();
                            Log.d("????????? ?????? : ", t.getMessage());
                        }
                    });


                    //??????????????? ????????? ?????? ????????? ?????? ????????? ?????? ???????????? ????????? ?????? ????????? ?????? ????????????
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
//                            Toast.makeText(getApplicationContext(), "?????????????????? ??????", Toast.LENGTH_SHORT).show();
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




                    // ??????????????? ????????? ?????? ????????? ?????? ????????? ?????? ???????????? ????????? ?????? ????????? ?????? ????????????
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
//                            Toast.makeText(getApplicationContext(), "?????????????????? ??????", Toast.LENGTH_SHORT).show();
//                        }
//                    });






                }
            }
        });


        ///////////////////////////??????????????? ????????????
//        idEditText=(EditText)findViewById(R.id.idEditText) ;
//        init();
//        GitHub gitHub = retrofit.create(GitHub.class);
//        // ?????????????????? ????????? ???????????? contributors??? param ?????? ????????? ?????? ?????????.
//        Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");
//        // ???????????? ????????? ??????
//        call.enqueue(new Callback<List<Contributor>>() {
//            @Override
//            // ?????????
//            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                List<Contributor> contributors = response.body();
//                // ????????? ???????????? ???????????????
//
//                idEditText.setText(contributors.get(0).login);
////                for (Contributor contributor : contributors) {
////                    // ????????? ?????? login ????????? ??????
////                    textView.append(contributor.login);
////                }
//            }
//            @Override
//            // ?????????
//            public void onFailure(Call<List<Contributor>> call, Throwable t) {
//                Toast.makeText(signUpActivity.this, "?????????????????? ??????", Toast.LENGTH_LONG)
//                        .show();
//            }
//        });

    }

    // ???????????? ?????? ?????????
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
        // GSON ???????????? ???????????? REST ????????? ??????
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
            Toast.makeText(signUpActivity.this, "?????? ?????? ????????? ??? ??? ??? ???????????? ???????????????.", Toast.LENGTH_SHORT).show();
        }
        else {
//            AppFinish();
            // ??? ?????? ?????? : ????????? ?????????????????? ????????? ????????? ??????
            moveTaskToBack(true);
            finishAndRemoveTask();
            android.os.Process.killProcess(android.os.Process.myPid());

            // ????????? ??????????????? ?????? ?????? ??????
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
