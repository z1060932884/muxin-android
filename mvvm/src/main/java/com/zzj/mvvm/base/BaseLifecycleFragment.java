package com.zzj.mvvm.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zzj.mvvm.event.LiveBus;
import com.zzj.mvvm.util.TUtil;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:51
 * @desc :
 * @version: 1.0
 */
public abstract class BaseLifecycleFragment <T extends BaseViewModel> extends BaseFragment {

    protected T mBaseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));

    }

    /**
     *
     * @return ViewModel
     */
    protected <T extends ViewModel> T VMProviders(BaseFragment fragment, @NonNull Class<T> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

    }

}
