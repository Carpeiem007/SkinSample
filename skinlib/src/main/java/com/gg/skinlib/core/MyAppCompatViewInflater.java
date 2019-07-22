package com.gg.skinlib.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatViewInflater;
import android.util.AttributeSet;
import android.view.View;

import com.gg.skinlib.view.SkinTextView;

public class MyAppCompatViewInflater extends AppCompatViewInflater {

    @Nullable
    @Override
    public View createView(Context context, String name, AttributeSet attrs) {
        View view = dealCustomView(context, name, attrs);
        return view!=null?view:super.createView(context, name, attrs);
    }

    private View dealCustomView(Context context, String name, AttributeSet attrs) {
        View view = null;
        if ("TextView".equals(name)) {
            view = new SkinTextView(context, attrs);
        }
        return view;

    }



}
