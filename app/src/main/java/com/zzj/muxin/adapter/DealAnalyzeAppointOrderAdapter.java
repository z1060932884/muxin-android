package com.zzj.muxin.adapter;

import android.graphics.Color;
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
import com.zzj.muxin.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/13 19:17
 * @desc :  挂单列表的adapter
 * @version: 1.0
 */
public class DealAnalyzeAppointOrderAdapter extends BaseQuickAdapter<TableInfo,BaseViewHolder> {

    public DealAnalyzeAppointOrderAdapter(int layoutResId, @Nullable List<TableInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TableInfo item) {
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.tv_buy,item.getBuy());
        helper.setText(R.id.tv_sale,item.getSale());
        helper.setText(R.id.tv_flow_into,item.getFlowInto());
        HorizontalBarChart barChart = helper.getView(R.id.barChart);
        initBarChart(barChart,item);
        initBarChartData(barChart,item);

    }

    /**
     * 加载数据
     */
    private void initBarChartData(HorizontalBarChart barChart,TableInfo item) {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, new float[]{ item.getBarLeft(), item.getBarRight()}));
        BarDataSet set = new BarDataSet(values, "");
        set.setDrawIcons(false);
        set.setDrawValues(false);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setColors(R.color.blue_500, R.color.red_500);
        BarData data = new BarData(set);
        data.setBarWidth(20);
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
        chart.getAxisRight().setEnabled(false);
        chart.getAxisRight().setAxisMaximum(item.getBarMax());
        chart.getAxisRight().setAxisMinimum(item.getBarMin());
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawZeroLine(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setCenterAxisLabels(true);
        Legend l = chart.getLegend();
        l.setEnabled(false);
    }
}
