package com.zzj.muxin.http;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/8 15:58
 * @desc : 请求回调
 * @version: 1.0
 */
public interface CallBack<T> {

    void success(T data);

    void fails(int code,String msg);
}
