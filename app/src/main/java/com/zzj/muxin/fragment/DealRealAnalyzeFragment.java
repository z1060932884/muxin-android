package com.zzj.muxin.fragment;

import android.os.Bundle;

import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/10 13:51
 * @desc : 分析fragment
 * @version: 1.0
 */
public class DealRealAnalyzeFragment extends BaseLifecycleFragment<DealRealInfoViewModel> {


    public static DealRealAnalyzeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DealRealAnalyzeFragment fragment = new DealRealAnalyzeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_deal_real_analyze;
    }
}
