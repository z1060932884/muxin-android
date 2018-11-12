package com.zzj.muxin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzj.muxin.R;
import com.zzj.muxin.bean.TableInfo;

import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/12 14:07
 * @desc :
 * @version: 1.0
 */
public class DealBigMonitoringAdapter  extends BaseQuickAdapter<TableInfo,BaseViewHolder> {

    public DealBigMonitoringAdapter(int layoutResId, @Nullable List<TableInfo> data) {
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
