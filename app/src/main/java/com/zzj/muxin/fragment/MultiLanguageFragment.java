package com.zzj.muxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.finddreams.languagelib.LanguageType;
import com.finddreams.languagelib.MultiLanguageUtil;
import com.zzj.muxin.MainActivity;
import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.ChatListViewModel;
import com.zzj.muxin.viewmodel.MultiLanguageViewModel;
import com.zzj.mvvm.base.BaseFragment;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/13 11:13
 * @desc :
 * @version: 1.0
 */
public class MultiLanguageFragment extends BaseLifecycleFragment<ChatListViewModel> {

    @BindView(R.id.tv_follow_system)
    TextView tv_follow_system;
    @BindView(R.id.tv_chinese)
    TextView tv_chinese;
    @BindView(R.id.tv_en)
    TextView tv_en;

    public static MultiLanguageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MultiLanguageFragment fragment = new MultiLanguageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    protected Object getStateEventKey() {
        return TAG;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_multi_language;
    }

    @OnClick({R.id.tv_follow_system,R.id.tv_chinese,R.id.tv_en})
    void onViewClick(View view){
        int selectedLanguage = 0;
        if(view.getId() == R.id.tv_follow_system){
            selectedLanguage = LanguageType.LANGUAGE_FOLLOW_SYSTEM;
        }else if(view.getId() == R.id.tv_chinese){
            selectedLanguage = LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;
        }else if(view.getId() == R.id.tv_en){
            selectedLanguage = LanguageType.LANGUAGE_EN;
        }
        MultiLanguageUtil.getInstance().updateLanguage(selectedLanguage);
        Intent intent = new Intent(_mActivity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
