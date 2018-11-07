package com.zzj.mvvm.stateview;

import com.tqzhang.stateview.stateview.BaseStateControl;
import com.zzj.mvvm.R;


/**
 * @author：tqzhang  on 18/7/16 15:07
 */
public class LoadingState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.loading_view;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

}
