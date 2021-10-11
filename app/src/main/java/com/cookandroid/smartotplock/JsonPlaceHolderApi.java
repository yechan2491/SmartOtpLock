package com.cookandroid.smartotplock;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {


    //Call<List<post> 구조를 가지는 이유는 https://jsonplaceholder.typicode.com
    //서버에 접속해보면 post라는 데이터양식들의 lIST로 이루어져있기때문!
    @GET("/posts")
    Call<List<Post>> getPost(@Query("userId") String id);

    @FormUrlEncoded
    @POST("/user/join")
    Call<Post> postData( @FieldMap HashMap<String , Object> param);

    @FormUrlEncoded
    @POST("/user/checkUsername")
    Call<Post> checkUsername(@Field("CLIENT_ID") String checkUsername);

    @FormUrlEncoded
    @POST("/user/checkId")
    Call<Post> checkData(@Field("CLIENT_ID") String checkId);

    @FormUrlEncoded
    @POST("/user/login")
    Call<Post> loginData(@FieldMap HashMap<String, String> login);

//    @POST("/user/login")
//    Call<Post> loginData(@Body POST CLIENT_ID,
//                        @Body POST CLIENT_PWD);

    @PUT("/posts/{path}")
    Call<Post> putData(@Path("path") int path,@Body Post post);

    @DELETE("posts/{path}")
    Call<Void> deleteData(@Path("path") int path);

    @FormUrlEncoded
    @POST("/user/otp")
    Call<Post> OTPData(@Field("OTP_NUM") String otpNum);
}
