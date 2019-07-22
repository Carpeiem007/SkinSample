package com.gg.skinsample;

import android.app.Application;
import android.os.Environment;

import com.gg.skinlib.core.SkinManager;

import java.io.File;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
        SkinManager.getInstance().loadSkinPackage(Environment.getExternalStorageDirectory()+ File.separator+"skin.pkg");
    }
}
