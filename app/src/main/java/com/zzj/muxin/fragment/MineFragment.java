package com.zzj.muxin.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zzj.muxin.MainActivity;
import com.zzj.muxin.R;
import com.zzj.muxin.viewmodel.MineViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/12/11 13:00
 * @desc :
 * @version: 1.0
 */
public class MineFragment extends BaseLifecycleFragment<MineViewModel> {
    public static int REQUEST_CODE_CHOOSE = 100;
    @BindView(R.id.iv_head_portrait)
    ImageView iv_head_portrait;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    @OnClick({R.id.iv_head_portrait})
    void onViewClick(View view) {
        if (view.getId() == R.id.iv_head_portrait) {
            PermissionManager.instance().request(_mActivity, new OnPermissionCallback() {
                @Override
                public void onRequestAllow(String permissionName) {
                    Matisse.from(MineFragment.this)
                            .choose(MimeType.ofAll())//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(5)//可选的最大数
                            .capture(true)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.example.xx.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(REQUEST_CODE_CHOOSE);//
                }

                @Override
                public void onRequestRefuse(String permissionName) {

                }

                @Override
                public void onRequestNoAsk(String permissionName) {

                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
            LogUtils.e("1111111111111111111111----->" + result.get(0).toString());
            iv_head_portrait.setImageBitmap(ImageUtils.getBitmap(result.get(0).toString()));
            Glide.with(this).load(result.get(0)).into(iv_head_portrait);

        }
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }



    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

}
