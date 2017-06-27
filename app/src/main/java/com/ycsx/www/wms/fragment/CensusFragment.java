package com.ycsx.www.wms.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycsx.www.wms.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by ZZS_PC on 2017/6/6.
 */
public class CensusFragment extends Fragment implements View.OnClickListener{
    String[] date1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};//X轴的标注
    int[] score1 = {50, 42, 90, 33, 10, 74, 22, 18, 79, 20};//图表的数据点
    private List<PointValue> mPointValues1 = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues1 = new ArrayList<AxisValue>();
    String[] date2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};//X轴的标注
    int[] score2 = {50, 42, 90, 33, 10, 74, 22, 18, 79, 20};//图表的数据点
    private List<PointValue> mPointValues2 = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues2 = new ArrayList<AxisValue>();
    private LineChartView order_num,order_price;
    private View view;
    private TextView date,time,today,yesterday,seven_day,thirty_day;
    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mHour;
    private static String mWay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_census,container,false);
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY)); // 获取当前小时
        if(Integer.parseInt(mHour)<10){
            mHour="0"+mHour;
        }
        initView();
        getAxisXLables1();//获取x轴的标注
        getAxisPoints1();//获取坐标点
        initLineChart1();//初始化

        getAxisXLables2();//获取x轴的标注
        getAxisPoints2();//获取坐标点
        initLineChart2();//初始化
        return view;
    }

    private void initView() {
        order_num = (LineChartView) view.findViewById(R.id.order_num);
        order_price = (LineChartView) view.findViewById(R.id.order_price);
        date = (TextView) view.findViewById(R.id.date);
        time = (TextView) view.findViewById(R.id.time);
        date.setText(getNowData());
        time.setVisibility(View.VISIBLE);
        time.setText("截止到今天"+mHour+"时");
        today = (TextView) view.findViewById(R.id.today);
        yesterday = (TextView) view.findViewById(R.id.yesterday);
        seven_day = (TextView) view.findViewById(R.id.seven_day);
        thirty_day = (TextView) view.findViewById(R.id.thirty_day);
        today.setOnClickListener(this);
        yesterday.setOnClickListener(this);
        seven_day.setOnClickListener(this);
        thirty_day.setOnClickListener(this);
        initBackGround();
        today.setTextColor(getResources().getColor(R.color.purpleColor));
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables1() {
        for (int i = 0; i < date1.length; i++) {
            mAxisXValues1.add(new AxisValue(i).setLabel(date1[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints1() {
        for (int i = 0; i < score1.length; i++) {
            mPointValues1.add(new PointValue(i, score1[i]));
        }
    }

    private void initLineChart1() {
        Line line = new Line(mPointValues1).setColor(Color.parseColor("#33B5E5"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setName("天数");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(1); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues1);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        //axisY.setHasLines(true);//设置网格线
        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < 100; i += 10) {
            AxisValue value = new AxisValue(i);
            String label = i + "";
            value.setLabel(label);
            values.add(value);
        }
        axisY.setValues(values);//设置Y轴坐标
        axisY.setName("订单数");//y轴标注
        axisY.setTextColor(Color.WHITE);
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        order_num.setInteractive(true);
        order_num.setZoomType(ZoomType.HORIZONTAL);//设置放大缩小方向
        order_num.setMaxZoom((float) 2);//最大放大比例
        order_num.setContainerScrollEnabled(false, ContainerScrollType.HORIZONTAL);
        order_num.setLineChartData(data);
        order_num.setVisibility(View.VISIBLE);
        /**
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         * 设置X轴坐标个数（right）
         */
        Viewport v = new Viewport(order_num.getMaximumViewport());
        v.left = 0;
        v.right = 9;
        order_num.setCurrentViewport(v);
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables2() {
        for (int i = 0; i < date2.length; i++) {
            mAxisXValues2.add(new AxisValue(i).setLabel(date2[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints2() {
        for (int i = 0; i < score2.length; i++) {
            mPointValues2.add(new PointValue(i, score2[i]));
        }
    }

    private void initLineChart2() {
        Line line = new Line(mPointValues2).setColor(Color.parseColor("#33B5E5"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setName("天数");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(1); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues2);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        //axisY.setHasLines(true);//设置网格线
        List<AxisValue> values = new ArrayList<>();
        for (int i = 0; i < 100; i += 10) {
            AxisValue value = new AxisValue(i);
            String label = i + "";
            value.setLabel(label);
            values.add(value);
        }
        axisY.setValues(values);//设置Y轴坐标
        axisY.setName("销售额");//y轴标注
        axisY.setTextColor(Color.WHITE);
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        order_price.setInteractive(true);
        order_price.setZoomType(ZoomType.HORIZONTAL);//设置放大缩小方向
        order_price.setMaxZoom((float) 2);//最大放大比例
        order_price.setContainerScrollEnabled(false, ContainerScrollType.HORIZONTAL);
        order_price.setLineChartData(data);
        order_price.setVisibility(View.VISIBLE);
        /**
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         * 设置X轴坐标个数（right）
         */
        Viewport v = new Viewport(order_price.getMaximumViewport());
        v.left = 0;
        v.right = 9;
        order_price.setCurrentViewport(v);
    }

    public static String getNowData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if(Integer.parseInt(mMonth)<10){
            mMonth="0"+mMonth;
        }
        if(Integer.parseInt(mDay)<10){
            mDay="0"+mDay;
        }
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return mYear + "-" + mMonth + "-" + mDay+"星期"+mWay;
    }

    //返回当前时间-day
    public static String getTimeByMinute(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    private void initBackGround() {
        today.setTextColor(getResources().getColor(R.color.colorWhite));
        yesterday.setTextColor(getResources().getColor(R.color.colorWhite));
        seven_day.setTextColor(getResources().getColor(R.color.colorWhite));
        thirty_day.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.today:
                initBackGround();
                today.setTextColor(getResources().getColor(R.color.purpleColor));
                date.setText(getNowData());
                time.setVisibility(View.VISIBLE);
                time.setText("截止到今天"+mHour+"时");
                break;
            case R.id.yesterday:
                initBackGround();
                yesterday.setTextColor(getResources().getColor(R.color.purpleColor));
                date.setText(getTimeByMinute(-1)+" 至 "+getTimeByMinute(-1));
                time.setVisibility(View.GONE);
                break;
            case R.id.seven_day:
                initBackGround();
                seven_day.setTextColor(getResources().getColor(R.color.purpleColor));
                date.setText(getTimeByMinute(-7)+" 至 "+getTimeByMinute(-1));
                time.setVisibility(View.GONE);
                break;
            case R.id.thirty_day:
                initBackGround();
                thirty_day.setTextColor(getResources().getColor(R.color.purpleColor));
                date.setText(getTimeByMinute(-30)+" 至 "+getTimeByMinute(-1));
                time.setVisibility(View.GONE);
                break;
        }
    }
}
