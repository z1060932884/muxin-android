package com.zzj.muxin.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.zzj.muxin.bean.JSONResult;
import com.zzj.muxin.bean.UsersVO;
import com.zzj.muxin.http.CallBack;
import com.zzj.muxin.repository.UserRepository;
import com.zzj.mvvm.base.BaseViewModel;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/11 14:19
 * @desc :
 * @version: 1.0
 */
public class UserViewModel extends BaseViewModel<UserRepository> {
    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        mRepository.login(username, password, new CallBack<JSONResult<UsersVO>>() {
            @Override
            public void success(JSONResult<UsersVO> data) {
                if(data!=null&&data.getStatus() == 200){
                    ToastUtils.showShort("登陆成功");
                }else {
                    ToastUtils.showShort(data.getMsg());
                }
            }

            @Override
            public void fails(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });
    }
}
