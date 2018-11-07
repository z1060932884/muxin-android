package com.zzj.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.tqzhang.stateview.stateview.BaseStateControl;
import com.zzj.mvvm.stateview.ErrorState;
import com.zzj.mvvm.stateview.LoadingState;
import com.zzj.mvvm.stateview.StateConstants;
import com.zzj.mvvm.util.TUtil;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:16
 * @desc :
 * @version: 1.0
 */
public abstract class BaseLifecycleActivity<T extends BaseViewModel> extends BaseActivity {
    protected T mBaseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseViewModel = VMProvides(this, (Class<T>) TUtil.getInstance(this, 0));
        dataObserver();
    }

    /**
     * 获取该ViewModel
     * @param activity 当前activity
     * @param modelClass
     * @param <T>
     * @return
     */
    protected <T extends ViewModel> T VMProvides(AppCompatActivity activity, @NonNull Class modelClass){
        return (T)ViewModelProviders.of(activity).get(modelClass);
    }

    protected void dataObserver(){

    }

    /**
     *
     */
    @Override
    protected  void onStateRefresh(){
        showLoading();
    }
    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
        loadManager.showStateView(stateView, tag);
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showSuccess() {
        loadManager.showSuccess();
    }

    protected void showLoading() {
        loadManager.showStateView(LoadingState.class);
    }

    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (StateConstants.ERROR_STATE.equals(state)) {
                    showError(ErrorState.class, "2");
                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
                    showError(ErrorState.class, "1");
                } else if (StateConstants.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
                    showSuccess();
                }
            }
        }
    };

}
