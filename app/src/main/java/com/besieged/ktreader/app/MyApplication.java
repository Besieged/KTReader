package com.besieged.ktreader.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class MyApplication extends Application {

    private static Context aContext;

    private static String sCacheDir;

    public static Context getAPPContext(){
        return aContext;
    }
    public static String getAPPCacheDir(){
        return sCacheDir;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        aContext = getApplicationContext();
        if (getExternalCacheDir()!=null && ExistSDCard()){
            sCacheDir = getExternalCacheDir().toString();
        }else{
            sCacheDir = getCacheDir().toString();
        }
    }

    private boolean ExistSDCard(){
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
