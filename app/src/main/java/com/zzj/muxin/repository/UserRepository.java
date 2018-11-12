package com.zzj.muxin.repository;

import com.zzj.muxin.bean.JSONResult;
import com.zzj.muxin.bean.UsersVO;
import com.zzj.muxin.http.CallBack;
import com.zzj.muxin.http.rx.RxSubscriber;
import com.zzj.mvvm.http.rx.RxSchedulers;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/11 14:20
 * @desc :
 * @version: 1.0
 */
public  class UserRepository extends BaseRepository{

    public void login(String username, String password, final CallBack<JSONResult<UsersVO>> callBack) {
        UsersVO usersVO = new UsersVO();
        usersVO.setUsername(username);
        usersVO.setPassword(password);
        addDisposable(apiService.login(usersVO).compose(RxSchedulers.<JSONResult<UsersVO>>io_main()).subscribeWith(new RxSubscriber<JSONResult<UsersVO>>() {
            @Override
            public void onSuccess(JSONResult<UsersVO> usersVO) {
                callBack.success(usersVO);
            }

            @Override
            public void onFailure(String msg) {
                callBack.fails(0,msg);
            }
        }));
    }
}
