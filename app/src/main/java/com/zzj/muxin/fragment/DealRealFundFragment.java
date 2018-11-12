package com.zzj.muxin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zzj.muxin.R;
import com.zzj.muxin.adapter.DealBigMonitoringAdapter;
import com.zzj.muxin.adapter.DealRealFundAdapter;
import com.zzj.muxin.bean.TableInfo;
import com.zzj.muxin.formatter.ValueFormatter;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/10 13:51
 * @desc : 资金fragment
 * @version: 1.0
 */
public class DealRealFundFragment extends BaseLifecycleFragment<DealRealInfoViewModel> implements
        OnChartValueSelectedListener {
    @BindView(R.id.pieChart)
    PieChart pieChart;
    @BindView(R.id.lc_real_time)
    LineChart lc_real_time;
    @BindView(R.id.barChart)
    BarChart barChart;

//    @BindView(R.id.table)
//    SmartTable table;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.rv_deal_big_monitoring)
    RecyclerView rv_deal_big_monitoring;
    List<TableInfo> tableInfos = new ArrayList<>();
    List<TableInfo> dealBigMonitoringList = new ArrayList<>();
    private DealRealFundAdapter adapter;
    private DealBigMonitoringAdapter dealBigMonitoringAdapter;
    public static DealRealFundFragment newInstance() {
        Bundle args = new Bundle();
        DealRealFundFragment fragment = new DealRealFundFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initData() {
        super.initData();
        setData(2,2);
        initTableData();
    }

    /**
     * 模拟表格数据
     */
    private void initTableData() {

        for(int i = 0;i<5;i++){
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTime(i+"分钟");
            tableInfo.setBuy("2313.1"+i);
            tableInfo.setSale("54657"+i);
            tableInfo.setFlowInto("8765"+i);
            tableInfos.add(tableInfo);
        }
        TableInfo tableInfo1 = new TableInfo();
        tableInfo1.setTime("时间");
        tableInfo1.setBuy("买入");
        tableInfo1.setSale("卖出");
        tableInfo1.setFlowInto("净流出");
        tableInfos.add(0,tableInfo1);
        adapter.notifyDataSetChanged();

        for(int i = 0;i<30;i++){
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTime("11.21."+i);
            tableInfo.setBuy("2313.1"+i);
            tableInfo.setSale("54657"+i);
            tableInfo.setFlowInto("8765"+i);
            dealBigMonitoringList.add(tableInfo);
        }
        TableInfo tableInfo2 = new TableInfo();
        tableInfo2.setTime("时间");
        tableInfo2.setBuy("金额(￥)");
        tableInfo2.setSale("数量(FGT)");
        tableInfo2.setFlowInto("价格(￥)");
        dealBigMonitoringList.add(0,tableInfo2);
        dealBigMonitoringAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DealRealFundAdapter(R.layout.item_deal_real_fund_table,tableInfos);
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(true);

        rv_deal_big_monitoring.setLayoutManager(new LinearLayoutManager(getActivity()));
        dealBigMonitoringAdapter = new DealBigMonitoringAdapter(R.layout.item_deal_big_monitoring_table,dealBigMonitoringList);
        rv_deal_big_monitoring.setAdapter(dealBigMonitoringAdapter);
        rv_deal_big_monitoring.setNestedScrollingEnabled(true);

        pieChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pieChart.setOnChartValueSelectedListener(this);



        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);

        initBarChart();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_deal_real_fund;
    }

    protected final String[] parties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    private void setData(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) (Math.random() * range) + range / 5, parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setUsingSliceColorAsValueLineColor(true);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
//        data.setValueTypeface(tf);
        pieChart.setData(data);
        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();

        initLineChartTime();
        setLineChartTimeData(100,100);
    }




    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private void initLineChartTime(){
        // no description text
        lc_real_time.getDescription().setEnabled(false);

        // enable touch gestures
        lc_real_time.setTouchEnabled(true);

        lc_real_time.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        lc_real_time.setDragEnabled(true);
        lc_real_time.setScaleEnabled(true);
        lc_real_time.setDrawGridBackground(false);
        lc_real_time.setHighlightPerDragEnabled(true);

        // set an alternative background color
        lc_real_time.setBackgroundColor(Color.WHITE);
        lc_real_time.setViewPortOffsets(0f, 0f, 0f, 0f);


        // get the legend (only possible after setting data)
        Legend l = lc_real_time.getLegend();
        l.setEnabled(false);

        XAxis xAxis = lc_real_time.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setValueFormatter(new ValueFormatter() {

            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {

                long millis = TimeUnit.HOURS.toMillis((long) value);
                return mFormat.format(new Date(millis));
            }
        });

        YAxis leftAxis = lc_real_time.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(170f);
        leftAxis.setYOffset(-9f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = lc_real_time.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setLineChartTimeData(int count, float range) {

        // now in hours
        long now = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());

        ArrayList<Entry> values = new ArrayList<>();

        // count = hours
        float to = now + count;

        // increment by 1 hour
        for (float x = now; x < to; x++) {

            float y = getRandom(range, 50);
            values.add(new Entry(x, y)); // add one entry per hour
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1.5f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        // create a data object with the data sets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        // set data
        lc_real_time.setData(data);
    }

    protected float getRandom(float range, float start) {
        return (float) (Math.random() * range) + start;
    }

    private void initBarChart(){
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setExtraTopOffset(-30f);
        barChart.setExtraBottomOffset(10f);
        barChart.setExtraLeftOffset(70f);
        barChart.setExtraRightOffset(70f);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.LTGRAY);
        xAxis.setTextSize(13f);
        xAxis.setLabelCount(5);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);

        YAxis left = barChart.getAxisLeft();
        left.setDrawLabels(false);
        left.setSpaceTop(25f);
        left.setSpaceBottom(25f);
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true); // draw a zero line
        left.setZeroLineColor(Color.GRAY);
        left.setZeroLineWidth(0.7f);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);

        // THIS IS THE ORIGINAL DATA YOU WANT TO PLOT
        final List<Data> data = new ArrayList<>();
        data.add(new Data(0f, -224.1f, "12-29"));
        data.add(new Data(1f, 238.5f, "12-30"));
        data.add(new Data(2f, 1280.1f, "12-31"));
        data.add(new Data(3f, -442.3f, "01-01"));
        data.add(new Data(4f, -2280.1f, "01-02"));

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return data.get(Math.min(Math.max((int) value, 0), data.size()-1)).xAxisValue;
            }
        });
        setData(data);
    }


    private void setData(List<Data> dataList) {

        ArrayList<BarEntry> values = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);

        for (int i = 0; i < dataList.size(); i++) {

            Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);
            values.add(entry);

            // specific colors
            if (d.yValue >= 0) {
                colors.add(red);
            } else {
                colors.add(green);
            }
        }

        BarDataSet set;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setValueTextSize(13f);
            data.setValueFormatter(new Formatter());
            data.setBarWidth(0.8f);

            barChart.setData(data);
            barChart.invalidate();
        }
    }
    /**
     * Demo class representing data.
     */
    private class Data {

        final String xAxisValue;
        final float yValue;
        final float xValue;

        Data(float xValue, float yValue, String xAxisValue) {
            this.xAxisValue = xAxisValue;
            this.yValue = yValue;
            this.xValue = xValue;
        }
    }

    private class Formatter extends ValueFormatter
    {

        private final DecimalFormat mFormat;

        Formatter() {
            mFormat = new DecimalFormat("######.0");
        }

        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(value);
        }
    }}
