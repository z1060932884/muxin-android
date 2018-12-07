package com.zzj.muxin.activity;

import android.os.Bundle;

import com.zzj.muxin.MainActivity;
import com.zzj.mvvm.base.BaseActivity;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/7 15:23
 * @desc :
 * @version: 1.0
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        MainActivity.start(this);
        finish();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
