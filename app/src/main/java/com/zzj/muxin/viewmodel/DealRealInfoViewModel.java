package com.zzj.muxin.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.zzj.muxin.bean.DealRealInfoBean;
import com.zzj.muxin.bean.NewsBean;
import com.zzj.muxin.http.CallBack;
import com.zzj.muxin.repository.DealRealInfoRepository;
import com.zzj.mvvm.base.BaseViewModel;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 15:53
 * @desc :
 * @version: 1.0
 */
public class DealRealInfoViewModel extends BaseViewModel<DealRealInfoRepository> {

    private MutableLiveData<NewsBean> mutableLiveData = null;
    /**
     * 区块链交易币的数据
     */
    private MutableLiveData<DealRealInfoBean> dataBeanMutableLiveData = null;

    public DealRealInfoViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<DealRealInfoBean> getDealData(){
        if(dataBeanMutableLiveData == null){
            dataBeanMutableLiveData = new MutableLiveData<>();
        }

        return dataBeanMutableLiveData;
    }

    /**
     * 加载交易币数据
     */
    public void loadDealData(){
        mRepository.loadDealData(new CallBack<DealRealInfoBean>() {
            @Override
            public void success(DealRealInfoBean data) {
                LogUtils.e("loadDealData---->"+data);
                dataBeanMutableLiveData.setValue(data);
            }

            @Override
            public void fails(int code, String msg) {
                DealRealInfoViewModel.this.fails(code,msg);
                LogUtils.e("fails---->"+msg);
            }
        });
    }

}
