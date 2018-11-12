package com.zzj.mvvm.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.tqzhang.stateview.stateview.BaseStateControl;
import com.zzj.mvvm.event.LiveBus;
import com.zzj.mvvm.stateview.ErrorState;
import com.zzj.mvvm.stateview.LoadingState;
import com.zzj.mvvm.stateview.StateConstants;
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

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        dataObserver();
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

//    @Override
//    protected void onStateRefresh() {
//        showLoading();
//    }


    /**
     * 获取网络数据
     */
    protected void getRemoteData() {

    }

//    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
//        loadManager.showStateView(stateView, tag);
//    }
//
//    protected void showError(Class<? extends BaseStateControl> stateView) {
//        showError(stateView, null);
//    }
//
//    protected void showSuccess() {
//        loadManager.showSuccess();
//    }
//
//    protected void showLoading() {
//        loadManager.showStateView(LoadingState.class);
//    }


    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (StateConstants.ERROR_STATE.equals(state)) {
//                    showError(ErrorState.class, "2");
                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
//                    showError(ErrorState.class, "1");
                } else if (StateConstants.LOADING_STATE.equals(state)) {
//                    showLoading();
                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
//                    showSuccess();
                }
            }
        }
    };
}
