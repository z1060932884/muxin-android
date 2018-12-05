package com.zzj.muxin.fragment;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.zzj.muxin.R;
import com.zzj.muxin.adapter.DealAnalyzeAppointOrderAdapter;
import com.zzj.muxin.adapter.DealAnalyzeEntryOrderAdapter;
import com.zzj.muxin.adapter.DealAnalyzeTurnoverOrderAdapter;
import com.zzj.muxin.bean.DepthModel;
import com.zzj.muxin.bean.TableInfo;
import com.zzj.muxin.view.MyMarkerView;
import com.zzj.muxin.viewmodel.DealRealInfoViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : zzj
 * @e-mail : zhangzhijun@pansoft.com
 * @date : 2018/11/10 13:51
 * @desc : 分析fragment
 * @version: 1.0
 */
public class DealRealAnalyzeFragment extends BaseLifecycleFragment<DealRealInfoViewModel> implements OnChartValueSelectedListener {

    @BindView(R.id.lineChart)
    LineChart lineChart;

    /**
     * 买  卖  最新价 低  高  对应的数据
     */
    private LineDataSet dataSetBuy,dataSetSale,dataSetNewsPrice,dataSetLow,dataSetHigh;

    private boolean isBuy = true,isSale = true,isNewPrice = true,isLow =true ,isHigh = true;

    /**
     *挂单列表
     */
    @BindView(R.id.rv_entry_order)
    RecyclerView rv_entry_order;
    /**
     * 委单量列表
     */
    @BindView(R.id.rv_appoint)
    RecyclerView rv_appoint;

    /**
     * 成交量列表
     */
    @BindView(R.id.rv_turnover)
    RecyclerView rv_turnover;
    /**
     * 挂单列表适配器
     */
    private DealAnalyzeEntryOrderAdapter entryOrderAdapter;
    /**
     * 委托量列表适配器
     */
    private DealAnalyzeAppointOrderAdapter appointOrderAdapter;
    /**
     * 成交量列表适配器
     */
    private DealAnalyzeTurnoverOrderAdapter turnoverOrderAdapter;

    List<TableInfo> entryOrderList = new ArrayList<>();
    public static DealRealAnalyzeFragment newInstance() {
        Bundle args = new Bundle();
        DealRealAnalyzeFragment fragment = new DealRealAnalyzeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_deal_real_analyze;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        LogUtils.e(TAG+"------>lazyLoad---");
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        initLineChart();
        //挂单量列表
        rv_entry_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        entryOrderAdapter = new DealAnalyzeEntryOrderAdapter(R.layout.item_deal_analyze_entry_order,entryOrderList);
        rv_entry_order.setAdapter(entryOrderAdapter);
        //委单量列表
        rv_appoint.setLayoutManager(new LinearLayoutManager(getActivity()));
        appointOrderAdapter = new DealAnalyzeAppointOrderAdapter(R.layout.item_deal_analyze_appoint_order,entryOrderList);
        rv_appoint.setAdapter(appointOrderAdapter);
        //成交量列表
        rv_turnover.setLayoutManager(new LinearLayoutManager(getActivity()));
        turnoverOrderAdapter = new DealAnalyzeTurnoverOrderAdapter(R.layout.item_deal_analyze_turnover_order,entryOrderList);
        rv_turnover.setAdapter(turnoverOrderAdapter);

        rv_entry_order.setNestedScrollingEnabled(true);
        rv_appoint.setNestedScrollingEnabled(true);
        rv_turnover.setNestedScrollingEnabled(true);

    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    Random random = new Random();
    @Override
    protected void initData() {
        super.initData();

        for(int i = 0;i<20;i++){
            TableInfo tableInfo = new TableInfo();
            tableInfo.setFlowInto(random.nextInt(150)+"");
            tableInfo.setTime("0.00012"+i);
            tableInfo.setBuy(random.nextInt(150)+"");
            tableInfo.setSale("0.0041"+i);
            tableInfo.setBarMax(100f);
            tableInfo.setBarMin(-100f);
            tableInfo.setBarLeft(random.nextInt(100)*-1);
            tableInfo.setBarRight(random.nextInt(100));
            entryOrderList.add(tableInfo);
        }
        entryOrderAdapter.notifyDataSetChanged();
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.ll_buy,R.id.ll_sale,R.id.ll_news_price,R.id.ll_low,R.id.ll_high})
    void onViewClick(View view){

        if(view.getId() == R.id.ll_buy){
            //买
            if(dataSetBuy!=null){
                if(isBuy){
                    lineChart.getLineData().getDataSets().remove(dataSetBuy);
                    isBuy = false;
                }else {
                    lineChart.getLineData().getDataSets().add(dataSetBuy);
                    isBuy = true;
                }
                lineChart.invalidate();
            }

        }else if(view.getId() == R.id.ll_sale){
            //卖
            if(dataSetSale!=null){
                if(isSale){
                    lineChart.getLineData().getDataSets().remove(dataSetSale);
                    isSale = false;
                }else {
                    lineChart.getLineData().getDataSets().add(dataSetSale);
                    isSale = true;
                }
                lineChart.invalidate();
                lineChart.setPinchZoom(true);
            }
        }else if(view.getId() == R.id.ll_news_price){
            //最新价
            if(dataSetNewsPrice!=null){
                if(isNewPrice){
                    lineChart.getLineData().getDataSets().remove(dataSetNewsPrice);
                    isNewPrice = false;
                }else {
                    lineChart.getLineData().getDataSets().add(dataSetNewsPrice);
                    isNewPrice = true;
                }
                lineChart.invalidate();
            }
        }else if(view.getId() == R.id.ll_low) {
                //低
                if (dataSetLow != null) {
                    if (isLow) {
                        lineChart.getLineData().getDataSets().remove(dataSetLow);
                        isLow = false;
                    } else {
                        lineChart.getLineData().getDataSets().add(dataSetLow);
                        isLow = true;
                    }
                    lineChart.invalidate();
                }
        }else if(view.getId() == R.id.ll_high){
            //高
            if(dataSetHigh!=null){
                if(isHigh){
                    lineChart.getLineData().getDataSets().remove(dataSetHigh);
                    isHigh = false;
                }else {
                    lineChart.getLineData().getDataSets().add(dataSetHigh);
                    isHigh = true;
                }
                lineChart.invalidate();
            }
        }

    }

    private void initLineChart() {
        // disable description text
        lineChart.getDescription().setEnabled(false);
        // enable touch gestures
        lineChart.setTouchEnabled(true);
        // set listeners
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setDrawGridBackground(false);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        // Set the marker to the chart
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        // enable scaling and dragging
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = lineChart.getXAxis();

            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setAxisLineWidth(3);
            xAxis.setAxisLineColor(R.color.grey_700);
            xAxis.setDrawLabels(true);
            xAxis.setAvoidFirstLastClipping(true);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = lineChart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            lineChart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);
            yAxis.setDrawAxisLine(false);
            yAxis.setAxisMinimum(0);
        }
        lineChart.getLegend().setEnabled(false);
        setData(30, 20);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    String json = "{\"code\":0,\"ticker\":{\"id\":\"954\",\"high\":4147,\"low\":3734.67,\"buy\":4083.74,\"sell\":4083.74,\"last\":4083.74,\"vol\":45254.079489892,\"open\":3781.58,\"time\":1543403866573},\"depth\":{\"asks\":[[4083.46,0.0146],[4085.14,0.0221],[4085.74,0.663],[4085.75,0.681],[4085.94,1.321],[4085.95,1.7616],[4085.96,2.3436],[4086.3,2.3447],[4086.31,2.3463],[4086.53,2.5463],[4086.56,3.2913],[4086.95,3.2915],[4086.96,3.6703],[4087.06,3.6713],[4087.19,3.7713],[4087.23,3.7732],[4087.45,3.8612],[4087.84,4.4612],[4087.92,4.9277],[4088.41,4.9313],[4088.47,5.1413],[4088.86,5.5086],[4089.09,5.6846],[4089.24,5.8846],[4089.26,6.4846],[4089.46,6.8346],[4089.8,6.9194],[4090,7.2194],[4090.06,11.6267],[4090.08,12.7525],[4090.22,13.3525],[4090.51,14.0871],[4090.68,14.1871],[4091.57,14.3567],[4091.73,14.4067],[4091.83,14.4162],[4091.87,15.2082],[4092.09,15.2329],[4092.44,17.2329],[4092.5,17.2407],[4092.66,17.2907],[4093.15,17.2919],[4093.4,18.7612],[4093.78,21.6997],[4093.8,21.7997],[4094.49,22.5629],[4095.4,23.0199],[4095.52,23.0519],[4095.53,24.1357],[4096.3,24.1457],[4096.38,24.2399],[4096.56,24.4845],[4097.54,24.4923],[4097.63,27.2857],[4100,47.4869],[4100.77,47.487],[4102.13,47.4904],[4102.24,47.4944],[4102.32,47.516],[4102.81,47.53],[4103.14,48.13],[4103.25,48.43],[4103.7,48.439],[4103.73,48.739],[4103.74,49.039],[4103.94,49.4243],[4103.98,49.4343],[4104.04,49.4453],[4104.17,49.503],[4104.8,49.702],[4104.88,49.7055],[4106,49.8055],[4106.07,49.8516],[4106.14,49.8525],[4106.51,50.9388],[4106.77,50.9389],[4107.19,50.9504],[4108,51.3504],[4108.1,59.3504],[4110,59.6004],[4110.05,59.6036],[4110.59,60.5716],[4111,60.7716],[4111.18,61.0716],[4112,61.1716],[4112.17,61.1748],[4112.32,61.1781],[4112.89,61.1844],[4113.69,61.1876],[4113.89,61.1908],[4114.69,61.1942],[4114.89,61.2037],[4114.92,61.2071],[4115,62.4371],[4115.18,62.4443],[4118,62.4884],[4118.17,62.4902],[4120,69.0124],[4120.39,69.0338],[4120.79,69.0438],[4121,74.0441],[4122.57,74.7783],[4123,74.7806],[4123.57,75.3456],[4124,75.4561],[4124.82,76.6311],[4125,77.3362],[4125.6,78.3362],[4126,78.4362],[4126.03,88.1163],[4126.25,88.3663],[4127,88.3733],[4127.89,89.3033],[4128,89.4569],[4128.45,89.9569],[4128.98,90.2473],[4129,92.3307],[4129.33,92.4058],[4129.46,92.407],[4130,96.4761],[4131,97.1761],[4132.33,97.1903],[4133.33,97.2403],[4133.42,97.272],[4133.45,97.372],[4134.88,98.8751],[4135,98.9751],[4135.64,98.9756],[4136,99.0756],[4136.95,99.4326],[4137,99.4726],[4137.17,100.0126],[4137.72,100.0926],[4138.08,100.1726],[4139,101.1726],[4140,109.2193],[4141,109.3193],[4141.55,109.4201],[4142.88,109.4451],[4144,109.7413],[4144.4,109.8213],[4145,109.9113],[4145.45,109.9913],[4145.85,110.1913],[4146.2,110.1935],[4147,112.0025],[4147.06,112.0081],[4147.1,114.898],[4147.87,114.914],[4148,117.4297]],\"bids\":[[4083.26,0.0036],[4083.25,0.5036],[4083.14,0.5122],[4082.97,0.5132],[4082.94,0.682],[4082.48,1.282],[4082.4,1.882],[4082.23,1.982],[4082.18,2.042],[4081.53,2.094],[4081.38,2.0972],[4081.33,2.5836],[4081.32,2.7836],[4080.3,2.9836],[4080.01,3.2836],[4080,3.5294],[4079.82,3.8404],[4079.74,3.9284],[4079.08,4.1044],[4079.07,4.2044],[4078.71,4.4044],[4078.58,4.7717],[4078.54,4.8013],[4078.5,5.1593],[4078.47,5.8953],[4078.15,11.429],[4078.14,13.429],[4077.81,13.479],[4077.8,13.8503],[4077.71,13.8633],[4077.45,14.5979],[4077.36,15.0999],[4077.23,15.2387],[4077.17,15.2437],[4077.16,15.7717],[4076.77,15.7718],[4076.51,16.564],[4076.5,16.583],[4076.19,16.683],[4075.82,16.732],[4075.2,21.1398],[4074.01,21.1908],[4074,21.2161],[4073.99,21.2423],[4073.71,21.4828],[4073.39,22.2108],[4072.15,22.3463],[4072.11,22.705],[4071.08,23.3824],[4070.77,23.3825],[4070,23.7784],[4069.36,24.0784],[4069.35,24.3784],[4068.42,24.3786],[4068.38,24.9786],[4068.25,25.2786],[4068.17,25.5786],[4067.71,26.0697],[4066.66,26.1197],[4066,26.1298],[4062.58,26.2869],[4062.48,27.255],[4061,27.6153],[4060.27,33.4178],[4060.04,34.0141],[4060,34.4987],[4059,35.4987],[4058.47,35.4997],[4058.14,35.5007],[4057,35.6902],[4056.32,37.4641],[4056.27,37.4745],[4053.34,37.475],[4053.27,37.5472],[4052.63,37.5474],[4051.29,37.5484],[4051.26,38.3718],[4050.11,38.3791],[4050.01,38.4402],[4050,41.5823],[4049.59,42.3165],[4048.74,43.8765],[4046.33,53.5566],[4046,54.0937],[4045.5,54.2942],[4045,55.3087],[4044,56.3258],[4042,57.3258],[4041.21,57.3276],[4040,59.7276],[4038.13,59.8179],[4037.27,59.9184],[4037.01,61.244],[4037,61.9378],[4036.84,61.938],[4036.22,61.9398],[4035.18,62.0646],[4034.59,62.1146],[4033.33,62.1646],[4032.6,62.1717],[4032,62.1757],[4031.55,62.5085],[4030.85,62.5135],[4030,94.608],[4026.18,94.624],[4026,94.7613],[4025,96.8685],[4024,97.3914],[4023.44,97.4872],[4022,103.7037],[4021.05,103.7039],[4021,105.8639],[4020.82,107.8244],[4020,124.3464],[4017.97,124.3538],[4017.8,124.357],[4017.02,124.365],[4016.36,124.3678],[4016.23,124.5678],[4015,124.5828],[4014.95,125.6653],[4014.7,132.2582],[4013.6,132.4142],[4012,133.5342],[4011,138.3557],[4010.03,138.7331],[4010,152.2122],[4009,152.2322],[4008.88,152.322],[4008,152.8201],[4007.23,153.3201],[4007.22,153.5201],[4007.04,153.7913],[4007,153.8113],[4006.04,154.0751],[4006,154.0951],[4005.71,154.2156],[4005.26,154.2158],[4005.01,154.2258],[4005,163.6999],[4004,164.6999],[4003.28,164.7392],[4003.09,164.9392],[4003,165.3402],[4002,169.345],[4001.11,172.9652],[4001,174.7812],[4000.11,174.8812],[4000.06,174.9123],[4000.01,174.9223]]}}";

    private void setData(int count, float range) {
        DepthModel dealRealAnalyzeDepthModel = new Gson().fromJson(json, DepthModel.class);
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        List<List<Double>> buyDepth = dealRealAnalyzeDepthModel.getDepth().getBids();

        //设置卖的图表样式
        List<List<Double>> sellDepth = dealRealAnalyzeDepthModel.getDepth().getAsks();
        Collections.reverse(buyDepth);
        //设置最小值
        double capMin = buyDepth.get(0).get(0);
        lineChart.getXAxis().setAxisMinimum((float) capMin);

        //设置最大值
        double capMax = (double) sellDepth.get(sellDepth.size()-1).get(0);
        lineChart.getXAxis().setAxisMaximum((float) capMax);
        for (int i = 0; i < buyDepth.size(); i++) {
            List<Double> doubles = buyDepth.get(i);
            double x =doubles.get(0);
            double y =  doubles.get(1);
            float val = (float) ((new Random().nextInt(6) + 5) * range) - 30;
            values.add(new Entry((float)x,(float)y, getResources().getDrawable(R.drawable.ic_launcher_background)));
        }

        // create a dataset and give it a type
        dataSetBuy = new LineDataSet(values, "DataSet 1");

        dataSetBuy.setDrawIcons(false);

        // draw dashed line
        dataSetBuy.enableDashedLine(10f, 0, 0f);

        // black lines and points
        dataSetBuy.setColor(R.color.red_600);
        dataSetBuy.setCircleColor(Color.BLACK);

        // line thickness and point size
        dataSetBuy.setLineWidth(2f);
        dataSetBuy.setCircleRadius(3f);

        // draw points as solid circles
        dataSetBuy.setDrawCircleHole(false);

        dataSetBuy.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // customize legend entry
        dataSetBuy.setFormLineWidth(1f);
        dataSetBuy.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        dataSetBuy.setFormSize(15.f);

        // text size of values
        dataSetBuy.setValueTextSize(9f);

        // draw selection line as dashed
        dataSetBuy.enableDashedHighlightLine(10, 10, 0);
        dataSetBuy.setDrawValues(false);
        // set the filled area
        dataSetBuy.setDrawFilled(true);
        dataSetBuy.setDrawCircles(false);
        dataSetBuy.setHighlightEnabled(true);
        dataSetBuy.setDrawHighlightIndicators(false);
        dataSetBuy.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return lineChart.getAxisLeft().getAxisMinimum();
            }
        });

        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_blue);
            dataSetBuy.setFillDrawable(drawable);
        } else {
            dataSetBuy.setFillColor(Color.BLACK);
        }


        for (int i = 0; i < sellDepth.size(); i++) {
            List<Double> doubles = sellDepth.get(i);
            double x = doubles.get(0);
            double y =  doubles.get(1);
            float val = (float) ((new Random().nextInt(6) + 5) * range) - 30;
            values2.add(new Entry((float) x, (float) y, getResources().getDrawable(R.drawable.ic_launcher_background)));
        }


        // create a dataset and give it a type
        dataSetSale = new LineDataSet(values2, "DataSet 2");

        dataSetSale.setDrawIcons(false);
        // draw dashed line
        dataSetSale.enableDashedLine(10, 0, 0);
        // black lines and points
        dataSetSale.setColor(R.color.blue_600);
        // line thickness and point size
        dataSetSale.setLineWidth(2);

        // draw points as solid circles
        dataSetSale.setDrawValues(false);
        dataSetSale.setDrawCircleHole(false);
        dataSetSale.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // set the filled area
        dataSetSale.setDrawFilled(true);
        dataSetSale.setDrawCircles(false);
        dataSetSale.setHighlightEnabled(true);
        dataSetSale.setDrawHighlightIndicators(false);
        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_red);
            dataSetSale.setFillDrawable(drawable);
        } else {
            dataSetSale.setFillColor(Color.BLACK);
        }


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetBuy);
        dataSets.add(dataSetSale);

        List<Entry> values3 = new ArrayList<>();
        values3.add(new Entry((float) dealRealAnalyzeDepthModel.getTicker().getLast(), 0, getResources().getDrawable(R.mipmap.ic_circle)));
        dataSetNewsPrice = getDataSet(values3);
        dataSets.add(dataSetNewsPrice);
        List<Entry> values4 = new ArrayList<>();
        values4.add(new Entry((float) dealRealAnalyzeDepthModel.getTicker().getLow(), 0, getResources().getDrawable(R.mipmap.ic_circle)));
        dataSetLow = getDataSet(values4);
        dataSets.add(dataSetLow);
        List<Entry> values5 = new ArrayList<>();
        values5.add(new Entry(dealRealAnalyzeDepthModel.getTicker().getHigh(), 0, getResources().getDrawable(R.mipmap.ic_circle)));
        dataSetHigh = getDataSet(values5);
        dataSets.add(dataSetHigh);
        // create a data object with the data sets
        LineData data = new LineData(dataSets);

        // set data
        lineChart.setData(data);

    }

    private LineDataSet getDataSet(List<Entry> values){
        LineDataSet set2;
        // create a dataset and give it a type
        set2 = new LineDataSet(values, "");

        set2.setDrawIcons(true);
        // draw dashed line
        set2.enableDashedLine(10, 0, 0);
        // black lines and points
        set2.setColor(R.color.blue_600);
        // line thickness and point size
        set2.setLineWidth(2);

        // draw points as solid circles
        set2.setDrawCircleHole(false);
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // set the filled area
        set2.setDrawFilled(true);
        set2.setDrawCircles(false);
        set2.setHighlightEnabled(true);
        set2.setDrawValues(false);
        set2.setDrawHighlightIndicators(false);
        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_red);
            set2.setFillDrawable(drawable);
        } else {
            set2.setFillColor(Color.BLACK);
        }

        return set2;
    }
}
