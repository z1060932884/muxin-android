package com.zzj.muxin.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.OspLinkmanViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : zzj
 * e-mail : zhangzhijun@pansoft.com
 * date   : 2018/8/2913:53
 * desc   :
 * version: 1.0
 */
public class OspLinkManFragment extends BaseLifecycleFragment<OspLinkmanViewModel> {
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static OspLinkManFragment newInstance() {

        Bundle args = new Bundle();

        OspLinkManFragment fragment = new OspLinkManFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        initViewPager();
//        setIndicator(_mActivity,tabLayout,15,15);
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(new LinkManFriendFragment());
        fragments.add(new LinkManFriendFragment());
        fragments.add(new LinkManFriendFragment());
        fragments.add(new LinkManFriendFragment());
        fragments.add(new LinkManFriendFragment());

        // 创建ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager(),fragments);
        // 给ViewPager设置适配器
        viewPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        tabLayout.setupWithViewPager(viewPager);
        // TabLayout指示器添加文本
        tabLayout.getTabAt(0).setText("好友");
        tabLayout.getTabAt(1).setText("群聊");
        tabLayout.getTabAt(2).setText("设备");
        tabLayout.getTabAt(3).setText("通讯录");
        tabLayout.getTabAt(4).setText("公众号");
    }
    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.osplinkman_main_fragment;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;


        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            mFragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = mFragmentList.get(position);

            return fragment;
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }
}
