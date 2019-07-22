package com.gg.skinlib;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gg.skinlib.core.MyAppCompatViewInflater;
import com.gg.skinlib.view.ViewsMatch;


public class BaseActivity extends AppCompatActivity {

    private MyAppCompatViewInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isSupportSkin()) {
            inflater = new MyAppCompatViewInflater();
            getLayoutInflater().setFactory2(this);

        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        Log.e("info", name);
        if (inflater != null) {
            view = inflater.createView(context, name, attrs);
        }
        return view;

        // return super.onCreateView(parent, name, context, attrs);
    }

    protected void updateSkin(View view) {
        if (view instanceof ViewsMatch) {
            ((ViewsMatch) view).updateSkin();
        }
        if (view instanceof ViewGroup) {
            for (int i = ((ViewGroup) view).getChildCount()-1; i >=0 ; i--) {
                updateSkin(((ViewGroup) view).getChildAt(i));
            }
        }


    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    public boolean isSupportSkin() {
        return true;
    }

}
