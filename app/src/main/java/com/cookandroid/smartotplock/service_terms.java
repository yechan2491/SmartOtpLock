package com.cookandroid.smartotplock;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class service_terms extends AppCompatActivity {
    ImageButton backBtn; //뒤로가기 버튼
    Button nextBtn;    // 다음버튼
    Boolean nextBtn_check=false;
    ImageButton termBtn[]=new ImageButton[5]; //약관 상세보기 이미지 버튼
    Integer termBtn_id[]={R.id.termsBtn1,R.id.termsBtn2,R.id.termsBtn3,R.id.termsBtn4,R.id.termsBtn5};//약관상세보기 이미지 버튼 id값들

    /****[필수]와 [선택] 글씨체를 바꾸기위한 변수들***/
    TextView textView2,textView3,textView4,textView5,textView6,textView7;
    String content1,content2,content3,content4,content5,content6,content7;
    int start1,end1,start2,end2,start3,end3,start4,end4,start5,end5,start6,end6,start7,end7;
    String word1="[필수]",word2="[선택]";
    ///////////////////////////////////////////////////

    /***약관 전체동의 및 동의를 눌렀을때 체크박스의 상태를 세팅하기 위한 변수들***/
    int for1=0,for2=0;
    CheckBox allcheck;
    CheckBox check[]=new CheckBox[6];
    Integer check_id[]={R.id.check1,R.id.check2,R.id.check3,R.id.check4,R.id.check5,R.id.check6};
    int check_count=0; // 모두 동의를 제외한 체크박스가 선택된 수를 체크
    //////////////////////////////////////////////////


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_terms);

        backBtn=(ImageButton)findViewById(R.id.backBtn); //뒤로가기 버튼 매칭
        nextBtn=(Button)findViewById(R.id.nextBtn);   //다음 버튼 매칭

        for(int i=0; i<termBtn.length; i++){ //이용약관 상세보기 이미지 버튼 클릭시 사용할 컴포넌트 매칭
            termBtn[i]=findViewById(termBtn_id[i]);
        }

        allcheck=(CheckBox)findViewById(R.id.allcheck); // 약관동의 체크/미체크 체크박스 클릭시 사용할 컴포넌트 매칭
        for(int i=0; i<check_id.length; i++){
            check[i]=(CheckBox)findViewById(check_id[i]);
        }

        textView2=(TextView) findViewById(R.id.textView2);  //텍스트뷰 특정 문자열 색상을 바꾸기 위한 컴포넌트 매칭
        textView3=(TextView) findViewById(R.id.textView3);
        textView4=(TextView) findViewById(R.id.textView4);
        textView5=(TextView) findViewById(R.id.textView5);
        textView6=(TextView) findViewById(R.id.textView6);
        textView7=(TextView) findViewById(R.id.textView7);

        /****[필수]와 [선택] 글씨체를 바꾸기위한 코드***/
        content1=textView2.getText().toString();
        content2=textView3.getText().toString();
        content3=textView4.getText().toString();
        content4=textView5.getText().toString();
        content5=textView6.getText().toString();
        content6=textView7.getText().toString();
        SpannableString spannableString1 = new SpannableString(content1);
        SpannableString spannableString2 = new SpannableString(content2);
        SpannableString spannableString3 = new SpannableString(content3);
        SpannableString spannableString4 = new SpannableString(content4);
        SpannableString spannableString5 = new SpannableString(content5);
        SpannableString spannableString6 = new SpannableString(content6);

        start1=content1.indexOf(word1);
        end1=start1 + word1.length();
        start2=content2.indexOf(word1);
        end2=start2 + word1.length();
        start3=content3.indexOf(word1);
        end3=start3 + word1.length();
        start4=content4.indexOf(word1);
        end4=start4 + word1.length();
        start5=content5.indexOf(word1);
        end5=start5 + word1.length();
        start6=content6.indexOf(word2);
        end6=start6 + word2.length();

        spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString3.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start3, end3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString4.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start4, end4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString5.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start5, end5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString6.setSpan(new ForegroundColorSpan(Color.parseColor("#1c68ff")), start6, end6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(spannableString1);
        textView3.setText(spannableString2);
        textView4.setText(spannableString3);
        textView5.setText(spannableString4);
        textView6.setText(spannableString5);
        textView7.setText(spannableString6);
        //////////////////////////////////////////////////////


        backBtn.setOnClickListener(new View.OnClickListener() { //뒤로가기 버튼 눌렀을때 인텐트 종료
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() { //다음 버튼 눌렀을때 다음 인텐트 호출
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), create_id2.class);
                if(nextBtn_check) startActivity(intent);
            }
        });

        /***약관상세보기 이미지 버튼 눌렀을때 동작이고 각 이미지 버튼에 맞는 인텐트 화면 호출***/
        for(int i=0; i<termBtn.length; i++){
            final int index;
            index=i;
            termBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(index==0){
                        Intent intent =new Intent(getApplicationContext(), terms1.class);
                        startActivity(intent);
                    }
                    else if(index==1){
                        Intent intent =new Intent(getApplicationContext(), terms2.class);
                        startActivity(intent);
                    }
                    else if(index==2){
                        Intent intent =new Intent(getApplicationContext(), terms3.class);
                        startActivity(intent);
                    }
                    else if(index==3){
                        Intent intent =new Intent(getApplicationContext(), terms4.class);
                        startActivity(intent);
                    }
                    else if(index==4){
                        Intent intent =new Intent(getApplicationContext(), terms5.class);
                        startActivity(intent);
                    }
                }
            });
        }
        /////////////////////////////////////////////////////////////

        allcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(allcheck.isChecked()){
                    for(int i=0; i<check_id.length; i++){
                        final int index;
                        index=i;
                        check[index].setChecked(true);

                    }
                }
                else{
                    for(int i=0; i<check_id.length; i++){
                        final int index;
                        index=i;

                        if(check_count>0){
                            if(check[index].isChecked()){
                                check[index].setChecked(true);
                            }
                        }
                        else
                            check[index].setChecked(false);
                    }
                }

                if(check[0].isChecked()&& check[1].isChecked()&& check[2].isChecked()
                        && check[3].isChecked() && check[4].isChecked()){
                    nextBtn.setBackgroundResource(R.drawable.solid_button);
                    nextBtn_check=true;
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
                    nextBtn_check=false;
                }
            }
        });

        for(int i=0; i<check.length; i++){
            final int index;
            index=i;
            check[index].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(check[index].isChecked()){
                        check_count++;
                    }
                    else{
                        check_count--;
                    }
                    if(check_count==6){
                        allcheck.setChecked(true);
                    }
                    else {
                        allcheck.setChecked(false);
                    }



                    if(check[0].isChecked() && check[1].isChecked() && check[2].isChecked()
                            && check[3].isChecked() && check[4].isChecked()){
                        nextBtn.setBackgroundResource(R.drawable.solid_button);
                        nextBtn_check=true;
                    }
                    else {
                        nextBtn.setBackgroundResource(R.drawable.solid_button_gray);
                        nextBtn_check=false;
                    }
                }
            });
        }



    }
}
