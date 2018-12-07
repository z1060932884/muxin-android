package com.zzj.muxin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/10 13:51
 * @desc :  简况fragment
 * @version: 1.0
 */
public class DealRealBriefingFragment extends BaseLifecycleFragment<DealRealInfoViewModel> {

    public static DealRealBriefingFragment newInstance() {

        Bundle args = new Bundle();

        DealRealBriefingFragment fragment = new DealRealBriefingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        LogUtils.e(TAG+"-----DealRealBriefingFragment->lazyLoad---");
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_deal_real_briefing;
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }
}
