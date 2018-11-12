package com.zzj.mvvm;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.tqzhang.stateview.core.LoadState;
import com.zzj.mvvm.config.URL;
import com.zzj.mvvm.http.HttpHelper;
import com.zzj.mvvm.stateview.ErrorState;
import com.zzj.mvvm.stateview.LoadingState;



/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:02
 * @desc :
 * @version: 1.0
 */
public class MVVMApp extends Application implements ComponentCallbacks2 {


}
