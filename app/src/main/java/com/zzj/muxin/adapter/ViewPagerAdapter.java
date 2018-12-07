package com.zzj.muxin.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;


import com.blankj.utilcode.util.LogUtils;
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
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    private List<String> mTitles;
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> lists){
        super(fm);
        this.fm = fm;
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragments.addAll(lists);
    }

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> lists, List<String> titles) {
        super(fm);
        this.fm = fm;
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        fragments.addAll(lists);
        mTitles.addAll(titles);
    }

//    @NonNull
////    @Override
////    public Object instantiateItem(@NonNull ViewGroup container, int position) {
////        return super.instantiateItem(container, position);
////
////    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
//            FragmentTransaction ft=fm.beginTransaction();
//            ft.remove((BaseFragment) object);
//            ft.commit();
//
//    }

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
