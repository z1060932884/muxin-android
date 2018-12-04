package com.zzj.mvvm.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/7 20:17
 * @desc :
 * @version: 1.0
 */
public abstract class Repository {



    private CompositeDisposable compositeDisposable;

    public Repository(){

    }
    /**
     *   添加任务
     */
    protected void addDisposable(Disposable disposable){
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 清除任务，防止内存泄漏
     */
    protected void unDisposable(){
        if(compositeDisposable!=null&&compositeDisposable.isDisposed()){
            compositeDisposable.clear();
        }
    }


}
