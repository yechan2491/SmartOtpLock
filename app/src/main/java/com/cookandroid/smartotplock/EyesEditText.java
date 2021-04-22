package com.cookandroid.smartotplock;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class EyesEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
    private int count=0;
    private Drawable clearDrawable;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;

    public EyesEditText(final Context context){
        super(context);
        if(count==0)init();
        else if(count==1) init2();
    }
    public EyesEditText(final Context context, final AttributeSet attrs){
        super(context,attrs);
        if(count==0)init();
        else if(count==1) init2();
    }
    public EyesEditText(final Context context, final AttributeSet attrs, final int defStyleAttr){
        super(context,attrs,defStyleAttr);
        if(count==0)init();
        else if(count==1) init2();
    }
    @Override
    public void setOnTouchListener( OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public void setOnFocusChangeListener( OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }



    private void init(){
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.eye_size_edit);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        //DrawableCompat.setTintList(clearDrawable,getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    private void init2(){
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.eye_off_size_edit);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        //DrawableCompat.setTintList(clearDrawable,getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }


    private void setClearIconVisible(boolean visible) {
        clearDrawable.setVisible(visible, false);
        setCompoundDrawables(null,null, visible ? clearDrawable : null,null);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (isFocused()) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, b);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                count++;
                if(count==2)count=0;
                if(count==0){
                    init();
                    setInputType(0x00000081); //비밀번호 표시



                }
                else if(count==1){
                    init2();
                    setInputType(0x00000001);  //공개


                }
                setSelection(getText().length()); //커서 위치조정(글자뒤로)


            }
            return true;
        }


        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, motionEvent);
        } else {
            return false;
        }
    }
}
