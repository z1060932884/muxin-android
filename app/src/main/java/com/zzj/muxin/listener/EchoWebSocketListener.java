package com.zzj.muxin.listener;

import com.blankj.utilcode.util.LogUtils;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/12 13:00
 * @desc :
 * @version: 1.0
 */
public class EchoWebSocketListener extends WebSocketListener {
    @Override
    public void onOpen(WebSocket webSocket, Response response) {

        webSocket.send("hello world");
        webSocket.send("welcome");
        webSocket.send(ByteString.decodeHex("adef"));
        webSocket.close(1000, "再见");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LogUtils.e("onMessage: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        LogUtils.e("onMessage byteString: " + bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        LogUtils.e("onClosing: " + code + "/" + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        LogUtils.e("onClosed: " + code + "/" + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        LogUtils.e("onFailure: " + t.getMessage());
    }

}
