package com.zzj.muxin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.zzj.muxin.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.blankj.utilcode.util.ThreadUtils.isMainThread;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 10:21
 * @desc :
 * @version: 1.0
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<NewsBean> mutableLiveData = null;

    public LiveData<NewsBean> getUsers(){
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        loadNewsData();

        return mutableLiveData;
    }

    private void loadNewsData() {
        //单个网络请求
        Observable<NewsBean> observable = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> e) throws Exception {
                Request.Builder builder = new Request.Builder()
                        .get()
                        .url("http://v.juhe.cn/toutiao/index?type=top&key=8d16f462a28ba044216e056a7ef4e79e");
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, NewsBean>() {
            @Override
            public NewsBean apply(Response response) throws Exception {
//                LogUtils.e("Response---->"+response.body().string());
                LogUtils.e("Response--isSuccessful-->"+response.isSuccessful());
                LogUtils.e("map------>"+isMainThread());
                NewsBean newsBean = new Gson().fromJson(response.body().string(),NewsBean.class);
                return newsBean;
            }
        }).subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NewsBean>() {
            @Override
            public void accept(NewsBean newsBean) throws Exception {
                LogUtils.e("MyViewModel请求新闻的title---》"+newsBean.getResult().getData().get(0).getTitle());
//
                mutableLiveData.setValue(newsBean);
            }

        });
    }

    public void getData(){
        Observable<NewsBean> observable = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> e) throws Exception {
                Request.Builder builder = new Request.Builder()
                        .get()
                        .url("http://v.juhe.cn/toutiao/index?type=&key=8d16f462a28ba044216e056a7ef4e79e");
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, NewsBean>() {
            @Override
            public NewsBean apply(Response response) throws Exception {
//                LogUtils.e("Response---->"+response.body().string());
                LogUtils.e("Response--isSuccessful-->"+response.isSuccessful());
                LogUtils.e("map------>"+isMainThread());
                NewsBean newsBean = new Gson().fromJson(response.body().string(),NewsBean.class);
                return newsBean;
            }
        }).subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NewsBean>() {
            @Override
            public void accept(NewsBean newsBean) throws Exception {
                LogUtils.e("MyViewModel请求新闻的title---》"+newsBean.getResult().getData().get(2).getTitle());
//
                mutableLiveData.postValue(newsBean);
            }

        });
    }
}
