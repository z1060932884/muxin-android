package com.zzj.mvvm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:13
 * @desc :
 * @version: 1.0
 */
public abstract class BaseActivity extends SupportActivity {

    protected LoadManager loadManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        loadManager = new LoadManager.Builder()
                .setViewParams(this)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View v) {
                        onStateRefresh();
                    }
                })
                .build();
    }

    /**
     *
     */
    protected  void onStateRefresh(){

    }

    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();
}
