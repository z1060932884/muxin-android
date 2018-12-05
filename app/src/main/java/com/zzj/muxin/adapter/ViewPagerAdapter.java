package com.zzj.muxin.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.zzj.mvvm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/3 10:17
 * @desc : BaseViewPagerActivity的适配器
 * @version: 1.0
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;

    private List<String> mTitles;

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> lists){
        super(fm);
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragments.addAll(lists);
    }

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> lists, List<String> titles) {
        super(fm);
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragments.addAll(lists);
        mTitles.addAll(titles);
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
