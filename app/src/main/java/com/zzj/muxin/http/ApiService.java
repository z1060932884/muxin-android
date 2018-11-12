package com.zzj.muxin.http;



import com.zzj.muxin.bean.DealRealInfoBean;
import com.zzj.muxin.bean.JSONResult;
import com.zzj.muxin.bean.NewsBean;
import com.zzj.muxin.bean.UsersVO;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author JamesZhang
 */
public interface ApiService {

    @GET("http://v.juhe.cn/toutiao/index?type=top&key=8d16f462a28ba044216e056a7ef4e79e")
    Flowable<NewsBean> getNewsBean();

    @GET("http://coindata.openserver.cn/price/m_market_ticker_rich?number=920")
    Flowable<DealRealInfoBean> getDealData();

    @POST(APIUrl.LOGIN)
    Flowable<JSONResult<UsersVO>> login(@Body UsersVO usersVO);
}
