package com.gg.skinlib.core;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

public class SkinManager {

    private Application application;
    private Resources skinResource, localResource;
    private boolean skinSuccess;
    private String packageName;

    private SkinManager(Application application) {
        this.application = application;
        this.localResource = application.getResources();
    }

    private volatile static SkinManager skinManager = null;

    public static void init(Application application) {
        if (skinManager == null) {
            synchronized (SkinManager.class) {
                if (skinManager == null)
                    skinManager = new SkinManager(application);
            }
        }
    }


    public static SkinManager getInstance() {

        return skinManager;
    }

    public void loadSkinPackage(String path) {
        try {

            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath", String.class);
            method.invoke(assetManager, path);
            skinResource = new Resources(assetManager, localResource.getDisplayMetrics(), localResource.getConfiguration());
            skinSuccess = true;
            packageName = application.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;
        } catch (Exception e) {
            e.printStackTrace();
            skinSuccess = false;
        }
    }

    private int getTargetRes(Resources from, Resources target, String targetPackageName, int resId) {

        String tagName = from.getResourceEntryName(resId);
        String defType = from.getResourceTypeName(resId);
        int targetId = target.getIdentifier(tagName, defType, targetPackageName);
        return targetId == 0 ? resId : targetId;


    }

    public Resources getSkinResource() {
        return skinResource;
    }

    public int getSkinRes(int localResId) {

        return getTargetRes(localResource, skinResource, packageName, localResId);


    }

    public int resetRes(int resId) {
        return getTargetRes(skinResource, localResource, application.getPackageName(), resId);


    }


}
