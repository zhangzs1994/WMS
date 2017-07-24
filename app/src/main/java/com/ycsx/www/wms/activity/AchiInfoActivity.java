package com.ycsx.www.wms.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.AchiInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchiInfoActivity extends BaseActivity implements View.OnClickListener{
    private TextView seven_day, thirty_day, half_year, one_year, order, order_num, order_price;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_achi_info);
        initView();
    }

    private void initView() {
        seven_day = (TextView) findViewById(R.id.seven_day);
        thirty_day = (TextView) findViewById(R.id.thirty_day);
        half_year = (TextView) findViewById(R.id.half_year);
        one_year = (TextView) findViewById(R.id.one_year);
        seven_day.setOnClickListener(this);
        thirty_day.setOnClickListener(this);
        half_year.setOnClickListener(this);
        one_year.setOnClickListener(this);
        initBackGround();
        seven_day.setTextColor(getResources().getColor(R.color.purpleColor));
        order = (TextView) findViewById(R.id.order);
        order_num = (TextView) findViewById(R.id.order_num);
        order_price = (TextView) findViewById(R.id.order_price);
        order.setText("近7日订单");
        initData(-7,-1);
    }

    private void initBackGround() {
        seven_day.setTextColor(getResources().getColor(R.color.colorWhite));
        thirty_day.setTextColor(getResources().getColor(R.color.colorWhite));
        half_year.setTextColor(getResources().getColor(R.color.colorWhite));
        one_year.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void back(View view) {
        finish();
    }

    private void initData(int satrt,int end) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", getIntent().getStringExtra("id"));
        params.put("starttime", getTimeByMinute(satrt));
        params.put("endtime", getTimeByMinute(end));
        Call<AchiInfo> call = RetrofitUtil.getInstance(API.URL).getDetial(params);
        call.enqueue(new Callback<AchiInfo>() {
            @Override
            public void onResponse(Call<AchiInfo> call, Response<AchiInfo> response) {
                if (response.isSuccessful()) {
                    AchiInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            order_num.setText(info.getData().get(i).getCount()+"");
                            order_price.setText(new DecimalFormat("######0.00").format(info.getData().get(i).getSum()));
                        }
                    }else {
                        Toast.makeText(AchiInfoActivity.this, "获取业绩信息失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AchiInfoActivity.this, "获取业绩信息失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AchiInfo> call, Throwable t) {
                Toast.makeText(AchiInfoActivity.this, "获取业绩信息失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //返回当前时间-day
    public static String getTimeByMinute(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seven_day:
                initBackGround();
                seven_day.setTextColor(getResources().getColor(R.color.purpleColor));
                order.setText("近7日订单");
                initData(-7,-1);
                break;
            case R.id.thirty_day:
                initBackGround();
                thirty_day.setTextColor(getResources().getColor(R.color.purpleColor));
                order.setText("近30日订单");
                initData(-30,-1);
                break;
            case R.id.half_year:
                initBackGround();
                half_year.setTextColor(getResources().getColor(R.color.purpleColor));
                order.setText("近半年订单");
                initData(-180,-1);
                break;
            case R.id.one_year:
                initBackGround();
                one_year.setTextColor(getResources().getColor(R.color.purpleColor));
                order.setText("近1年订单");
                initData(-365,-1);
                break;
        }
    }
}
