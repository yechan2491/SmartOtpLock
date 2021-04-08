package com.cookandroid.smartotplock;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "cba561fd2a722f6807a9692eb3d6f73a");
    }
}