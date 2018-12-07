package com.zzj.muxin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.zzj.muxin.fragment.DealCmoneyDetailsRealInfoFragment;
import com.zzj.muxin.fragment.MainFragment;
import com.zzj.mvvm.base.BaseActivity;

/**
 * @author JamesZhang
 */

public class MainActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        LogUtils.e(TAG+"---initView--");
        loadRootFragment(R.id.fl_container,MainFragment.newInstance());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.e(TAG+"---onNewIntent--");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressedSupport() {
        moveTaskToBack(false);
    }
}
