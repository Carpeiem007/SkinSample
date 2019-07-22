package com.gg.skinsample;

import android.os.Bundle;
import android.view.View;

import com.gg.skinlib.BaseActivity;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void skin(View view) {
            updateSkin(getWindow().getDecorView());
    }
}
