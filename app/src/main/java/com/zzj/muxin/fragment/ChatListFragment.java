package com.zzj.muxin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zzj.muxin.R;
import com.zzj.muxin.activity.SplashActivity;
import com.zzj.muxin.listener.EchoWebSocketListener;
import com.zzj.muxin.viewmodel.ChatListViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/12 12:56
 * @desc : 消息列表界面
 * @version: 1.0
 */
public class ChatListFragment extends BaseLifecycleFragment<ChatListViewModel> {

    private Button start;
    private TextView text;


    public static ChatListFragment newInstance() {

        Bundle args = new Bundle();

        ChatListFragment fragment = new ChatListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        start = (Button) root.findViewById(R.id.start);
        text = (TextView) root.findViewById(R.id.text);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(mActivity,SplashActivity.class));
            }
        });

    }

    private void connect() {

        EchoWebSocketListener listener = new EchoWebSocketListener();
        Request request = new Request.Builder()
                .url("ws://192.168.2.129:8088/ws")
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);

        client.dispatcher().executorService().shutdown();
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chatlist;
    }
}
