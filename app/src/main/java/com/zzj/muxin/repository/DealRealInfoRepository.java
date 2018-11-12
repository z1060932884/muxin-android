package com.zzj.muxin.repository;

import com.blankj.utilcode.util.LogUtils;
import com.zzj.muxin.bean.DealRealInfoBean;
import com.zzj.muxin.bean.NewsBean;
import com.zzj.muxin.http.CallBack;
import com.zzj.muxin.http.rx.RxSubscriber;
import com.zzj.mvvm.http.rx.RxSchedulers;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 15:23
 * @desc :
 * @version: 1.0
 */
public class DealRealInfoRepository extends BaseRepository {

    public void loadNewsBean(final CallBack<NewsBean> callBack){
        addDisposable(apiService.getNewsBean()
                .compose(RxSchedulers.<NewsBean>io_main())
                .subscribeWith(new RxSubscriber<NewsBean>() {
            @Override
            public void onSuccess(NewsBean o) {
                callBack.success(o);
            }

            @Override
            public void onFailure(String msg) {
                callBack.fails(0,msg);
            }
        }));
    }

    /**
     * 请求交易币的数据
     */
    public void loadDealData(final CallBack<DealRealInfoBean> callBack){
        addDisposable(apiService.getDealData().compose(RxSchedulers.<DealRealInfoBean>io_main()).subscribeWith(new RxSubscriber<DealRealInfoBean>() {
            @Override
            public void onSuccess(DealRealInfoBean dealRealInfoBean) {
                LogUtils.e("DealRealInfoRepository----->");
                callBack.success(dealRealInfoBean);
            }

            @Override
            public void onFailure(String msg) {
                callBack.fails(0,msg);
            }
        }));
    }
}
