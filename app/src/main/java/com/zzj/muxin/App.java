package com.zzj.muxin;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.finddreams.languagelib.MultiLanguageUtil;
import com.tqzhang.stateview.core.LoadState;
import com.zzj.mvvm.MVVMApp;
import com.zzj.mvvm.config.URL;
import com.zzj.mvvm.http.HttpHelper;
import com.zzj.mvvm.stateview.ErrorState;
import com.zzj.mvvm.stateview.LoadingState;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 16:51
 * @desc :
 * @version: 1.0
 */
public class App extends Application implements ComponentCallbacks2 {
    public static App mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        MultiLanguageUtil.init(this);
    }


    public static App instance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        new HttpHelper.Builder(this)
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build();
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
