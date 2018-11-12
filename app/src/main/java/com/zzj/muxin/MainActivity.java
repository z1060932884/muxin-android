package com.zzj.muxin;


import android.os.Bundle;

import com.zzj.muxin.fragment.DealCmoneyDetailsRealInfoFragment;
import com.zzj.mvvm.base.BaseActivity;

/**
 * @author JamesZhang
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadRootFragment(R.id.fl_container,DealCmoneyDetailsRealInfoFragment.newInstance());
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}
