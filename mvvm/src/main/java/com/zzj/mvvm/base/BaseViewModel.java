package com.zzj.mvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.zzj.mvvm.util.TUtil;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:16
 * @desc :
 * @version: 1.0
 */
public class BaseViewModel<T extends Repository> extends AndroidViewModel {

    public T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(mRepository!=null){
            mRepository.unDisposable();
        }
    }

    protected void fails(int code,String msg){

    }
}
