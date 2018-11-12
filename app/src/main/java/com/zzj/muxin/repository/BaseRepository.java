package com.zzj.muxin.repository;

import com.zzj.muxin.http.ApiService;
import com.zzj.mvvm.base.BaseViewModel;
import com.zzj.mvvm.base.Repository;
import com.zzj.mvvm.http.HttpHelper;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 15:20
 * @desc :
 * @version: 1.0
 */
public class BaseRepository extends Repository {


    protected ApiService apiService;

    public BaseRepository() {
        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }
    }
}
