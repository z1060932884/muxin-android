package com.zzj.muxin.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzj.muxin.R;
import com.zzj.muxin.bean.TableInfo;

import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/12 11:36
 * @desc :
 * @version: 1.0
 */
public class DealRealFundAdapter extends BaseQuickAdapter<TableInfo,BaseViewHolder> {


    public DealRealFundAdapter(int layoutResId, @Nullable List<TableInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TableInfo item) {
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.tv_buy,item.getBuy());
        helper.setText(R.id.tv_sale,item.getSale());
        helper.setText(R.id.tv_flow_into,item.getFlowInto());
    }
}
