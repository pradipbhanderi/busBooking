package com.example.busbooking.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by shubham on 2/8/17.
 */

public class CustomButtonSemiBold extends AppCompatButton {

    public CustomButtonSemiBold(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public CustomButtonSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public CustomButtonSemiBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Montserrat-SemiBold.ttf", context);
        setTypeface(customFont);
    }
}
