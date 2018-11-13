package com.zzj.muxin;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zzj.muxin.bean.DealRealInfoBean;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseLifecycleActivity;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 14:56
 * @desc :
 * @version: 1.0
 */
public class TrendActivity extends BaseLifecycleActivity<DealRealInfoViewModel> {
    private TextView tv_real_time_price,tv_real_time_change,tv_real_time_percent,tv_current_turnover,
                        tv_deal_high,tv_deal_low,tv_deal_gross,tv_deal_buy,tv_deal_sellout,tv_deal_rate;
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tv_real_time_price = findViewById(R.id.tv_real_time_price);
        tv_real_time_change = findViewById(R.id.tv_real_time_change);
        tv_real_time_percent = findViewById(R.id.tv_real_time_percent);
        tv_current_turnover = findViewById(R.id.tv_current_turnover);
        tv_deal_high = findViewById(R.id.tv_deal_high);
        tv_deal_low = findViewById(R.id.tv_deal_low);
        tv_deal_gross = findViewById(R.id.tv_deal_gross);
        tv_deal_buy = findViewById(R.id.tv_deal_buy);
        tv_deal_sellout = findViewById(R.id.tv_deal_sellout);
        tv_deal_rate = findViewById(R.id.tv_deal_rate);
        mBaseViewModel.loadDealData();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trend;
    }


    @Override
    protected void dataObserver() {
        super.dataObserver();
        mBaseViewModel.getDealData().observe(this, new Observer<DealRealInfoBean>() {
            @Override
            public void onChanged(@Nullable DealRealInfoBean dealRealInfoBean) {

                tv_real_time_price.setText(dealRealInfoBean.getOriginal().getOpen()+"");
                tv_real_time_change.setText(dealRealInfoBean.getOriginal().getChange()+"");
                tv_real_time_percent.setText((dealRealInfoBean.getOriginal().getChange_rate()*100)+"%");
                tv_current_turnover.setText(dealRealInfoBean.getOriginal().getVol()+"");
                tv_deal_high.setText(dealRealInfoBean.getOriginal().getHigh()+"");
                tv_deal_low.setText(dealRealInfoBean.getOriginal().getLow()+"");
                tv_deal_gross.setText(dealRealInfoBean.getOriginal().getLast()+"");
                tv_deal_buy.setText(dealRealInfoBean.getOriginal().getBuy()+"");
                tv_deal_sellout.setText(dealRealInfoBean.getOriginal().getSell()+"");
                tv_deal_rate.setText(dealRealInfoBean.getFee()+"%");
                showSuccess();
            }
        });

    }
}
