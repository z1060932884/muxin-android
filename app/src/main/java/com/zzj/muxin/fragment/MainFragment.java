package com.zzj.muxin.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zzj.muxin.R;
import com.zzj.muxin.adapter.DealAnalyzeTurnoverOrderAdapter;
import com.zzj.muxin.adapter.ViewPagerAdapter;
import com.zzj.muxin.bean.BottomItem;
import com.zzj.muxin.view.BottomTabLayout;
import com.zzj.muxin.viewmodel.MianViewModel;
import com.zzj.mvvm.base.BaseFragment;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/5 17:13
 * @desc :
 * @version: 1.0
 */
public class MainFragment extends BaseLifecycleFragment<MianViewModel> {

    @BindView(R.id.tab_bottom)
    BottomTabLayout tab_bottom;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<BaseFragment> items = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;
    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        LogUtils.e(TAG+"----initWidget--->");
        viewPagerAdapter  = new ViewPagerAdapter(getFragmentManager(),getFragments());
        viewPager.setAdapter(viewPagerAdapter);
        tab_bottom.setBottomItems(getBottomItem());
        tab_bottom.setBottomItemOnClickListener(new BottomTabLayout.BottomItemOnClickListener() {
            @Override
            public void bottomItemOnClick(View view, int i, BottomItem item) {
            }
        });
        tab_bottom.setShowIndex(0);

        tab_bottom.bindViewPager(viewPager);
    }

    private List<BaseFragment> getFragments(){
        items.add(ChatListFragment.newInstance());
        items.add(OspLinkManFragment.newInstance());
        items.add(DealRealAnalyzeFragment.newInstance());
        items.add(MineFragment.newInstance());
        return items;
    }
    private List<BottomItem> getBottomItem(){
        List<BottomItem> items = new ArrayList<>();
        items.add(new BottomItem("消息",R.mipmap.ic_heart_blue));
        items.add(new BottomItem("联系人",R.mipmap.ic_heart_green));
        items.add(new BottomItem("发现",R.mipmap.ic_heart_red));
        items.add(new BottomItem("我的",R.mipmap.ic_heart_yellow));
        return items;
    }
    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main;
    }
}
