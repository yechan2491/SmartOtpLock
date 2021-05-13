package com.cookandroid.smartotplock;

import android.app.Activity;
import android.os.Build;

import androidx.core.content.ContextCompat;

/***상태바의 색상을 바꾸기 위한 자바파일****/
//otp_check.xml에서 사용함
public class utils {
    public enum StatusBarColorType{
        BLACK_STATUS_BAR( R.color.black ),
        BLUE_STATUS_BAR( R.color.statusBarColorBlue );

        private int backgroundColorId;

        StatusBarColorType(int backgroundColorId){
            this.backgroundColorId = backgroundColorId;
        }

        public int getBackgroundColorId() {
            return backgroundColorId;
        }

    }

    public static void setStatusBarColor(Activity activity, StatusBarColorType colorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, colorType.getBackgroundColorId()));
        }
    }
}
