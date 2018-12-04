package com.zzj.muxin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.zzj.muxin.R;
import com.zzj.muxin.bean.TableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/13 19:17
 * @desc :  挂单列表的adapter
 * @version: 1.0
 */
public class DealAnalyzeTurnoverOrderAdapter extends BaseQuickAdapter<TableInfo,BaseViewHolder> {

    public DealAnalyzeTurnoverOrderAdapter(int layoutResId, @Nullable List<TableInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TableInfo item) {
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.tv_buy,item.getBuy());
        helper.setText(R.id.tv_sale,item.getSale());
        HorizontalBarChart barChart = helper.getView(R.id.barChart);
        initBarChart(barChart,item);
        initBarChartData(barChart,item);

    }

    /**
     * 加载数据
     */
    private void initBarChartData(HorizontalBarChart barChart,TableInfo item) {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, item.getBarRight()));
        BarDataSet set = new BarDataSet(values, "");
        set.setDrawIcons(false);
        set.setDrawValues(false);
        BarData data = new BarData(set);
        data.setBarWidth(30);
        barChart.setData(data);
        barChart.invalidate();
    }

    /**
     * 初始化图表
     * @param chart
     */
    private void initBarChart(HorizontalBarChart chart,TableInfo item) {
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setTouchEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setHighlightFullBarEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisRight().setAxisMaximum(item.getBarMax());
        chart.getAxisRight().setAxisMinimum(0);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawZeroLine(true);
        chart.setFitBars(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setCenterAxisLabels(true);
        Legend l = chart.getLegend();
        l.setEnabled(false);
    }
}
