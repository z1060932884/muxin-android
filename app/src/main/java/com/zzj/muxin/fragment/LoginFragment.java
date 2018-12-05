package com.zzj.muxin.fragment;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.UserViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/11 14:19
 * @desc :
 * @version: 1.0
 */
public class LoginFragment extends BaseLifecycleFragment<UserViewModel> {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @OnClick({R.id.btn_login})
    void onViewClick(View view){
        //登录注册
        if(view.getId() == R.id.btn_login){
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if(!username.equals("")&&!password.equals("")){
                mBaseViewModel.login(username,password);
            }else {
                ToastUtils.showShort("用户名或密码为空");
            }
        }
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }
}
