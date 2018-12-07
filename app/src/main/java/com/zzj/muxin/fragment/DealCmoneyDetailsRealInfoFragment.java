package com.zzj.muxin.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zzj.muxin.R;
import com.zzj.muxin.bean.DealRealInfoBean;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseFragment;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/10 9:42
 * @desc :  详情fragment
 * @version: 1.0
 */
public class DealCmoneyDetailsRealInfoFragment extends BaseLifecycleFragment<DealRealInfoViewModel> {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    /**
     *  隐藏的fragment
     */

    private int hideFragment = 0;
    /**
     * 需要显示的fragment
     */
    private int showFragment = 0;

    private BaseFragment[] mFragments = new BaseFragment[3];

    @BindView(R.id.tv_real_time_price)
    TextView tv_real_time_price;
    @BindView(R.id.tv_real_time_change)
    TextView tv_real_time_change;
    @BindView(R.id.tv_real_time_percent)
    TextView tv_real_time_percent;
    @BindView(R.id.tv_current_turnover)
    TextView tv_current_turnover;
    @BindView(R.id.tv_deal_high)
    TextView tv_deal_high;
    @BindView(R.id.tv_deal_low)
    TextView tv_deal_low;
    @BindView(R.id.tv_deal_gross)
    TextView tv_deal_gross;
    @BindView(R.id.tv_deal_buy)
    TextView tv_deal_buy;
    @BindView(R.id.tv_deal_sellout)
    TextView tv_deal_sellout;
    @BindView(R.id.tv_deal_rate)
    TextView tv_deal_rate;
    /**
     * 资金
     */
    @BindView(R.id.tv_deal_real_fund)
    TextView tv_deal_real_fund;
    @BindView(R.id.view_deal_real_fund)
    View view_deal_real_fund;
    /**
     * 分析
     */
    @BindView(R.id.tv_deal_real_analyze)
    TextView tv_deal_real_analyze;
    @BindView(R.id.view_deal_real_analyze)
    View view_deal_real_analyze;
    /**
     * 简况
     */
    @BindView(R.id.tv_deal_real_briefing)
    TextView tv_deal_real_briefing;
    @BindView(R.id.view_deal_real_briefing)
    View view_deal_real_briefing;

    public static DealCmoneyDetailsRealInfoFragment newInstance() {
        Bundle args = new Bundle();
        DealCmoneyDetailsRealInfoFragment fragment = new DealCmoneyDetailsRealInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        LogUtils.e(TAG+"---DealCmoneyDetailsRealInfoFragment--->lazyLoad---");
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();
        //顶部数据显示的viewmodel
//        mBaseViewModel.getDealData().observe(this, new Observer<DealRealInfoBean>() {
//            @Override
//            public void onChanged(@Nullable DealRealInfoBean dealRealInfoBean) {
//                tv_real_time_price.setText(dealRealInfoBean.getOriginal().getOpen() + "");
//                tv_real_time_change.setText(dealRealInfoBean.getOriginal().getChange() + "");
//                tv_real_time_percent.setText((dealRealInfoBean.getOriginal().getChange_rate() * 100) + "%");
//                tv_current_turnover.setText(dealRealInfoBean.getOriginal().getVol() + "");
//                tv_deal_high.setText(dealRealInfoBean.getOriginal().getHigh() + "");
//                tv_deal_low.setText(dealRealInfoBean.getOriginal().getLow() + "");
//                tv_deal_gross.setText(dealRealInfoBean.getOriginal().getLast() + "");
//                tv_deal_buy.setText(dealRealInfoBean.getOriginal().getBuy() + "");
//                tv_deal_sellout.setText(dealRealInfoBean.getOriginal().getSell() + "");
//                tv_deal_rate.setText(dealRealInfoBean.getFee() + "%");
////                showSuccess();
//            }
//        });

    }

    /**
     * view点击事件
     * @param view
     */
    @BindView(R.id.ll_deal_real_fund)
    LinearLayout ll_deal_real_fund;
    @BindView(R.id.ll_deal_real_analyze)
    LinearLayout ll_deal_real_analyze;
    @BindView(R.id.ll_deal_real_briefing)
    LinearLayout ll_deal_real_briefing;
    @OnClick({R.id.ll_deal_real_fund,R.id.ll_deal_real_analyze,R.id.ll_deal_real_briefing})
    void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_deal_real_fund:
                //资金
                selection(ll_deal_real_fund);
                showFragment = FIRST;
//                showHideFragment(mFragments[showFragment], mFragments[hideFragment]);
                hideFragment = FIRST;
                break;
            case R.id.ll_deal_real_analyze:
                //分析
                selection(ll_deal_real_analyze);
                showFragment = SECOND;
//                showHideFragment(mFragments[showFragment], mFragments[hideFragment]);
                hideFragment = SECOND;
                break;
            case R.id.ll_deal_real_briefing:
                //简况
                selection(ll_deal_real_briefing);
                showFragment = THIRD;
//                showHideFragment(mFragments[showFragment], mFragments[hideFragment]);
                hideFragment = THIRD;
                break;

                default:
        }
    }

    /**
     * 设置tab按钮变化
     * @param linearLayout
     */
    private void selection(LinearLayout linearLayout) {
        ll_deal_real_fund.setSelected(false);
        ll_deal_real_analyze.setSelected(false);
        ll_deal_real_briefing.setSelected(false);
        linearLayout.setSelected(true);

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mBaseViewModel.loadDealData();
        selection(ll_deal_real_fund);
//        loadFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    private void loadFragment() {
        BaseFragment firstFragment = findChildFragment(DealRealFundFragment.class);
        if(firstFragment == null){
            mFragments[FIRST] = DealRealFundFragment.newInstance();
            mFragments[SECOND] = DealRealAnalyzeFragment.newInstance();
            mFragments[THIRD] = DealRealBriefingFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_bottom_content,FIRST,mFragments);
        }else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = (BaseFragment) findChildFragment(DealRealAnalyzeFragment.class);
            mFragments[THIRD] = (BaseFragment) findChildFragment(DealRealBriefingFragment.class);

        }

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_deal_details_realinfo;
    }
}
