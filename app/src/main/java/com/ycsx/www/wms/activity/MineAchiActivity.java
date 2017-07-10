package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
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

public class MineAchiActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout saleInfo;
    private TextView achi_title, show_more, today, yesterday, order, order_num, order_price;
    private Intent intent;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_mine_achi);
        initView();
        initData(0,1);
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        saleInfo = (LinearLayout) findViewById(R.id.saleInfo);
        achi_title = (TextView) findViewById(R.id.achi_title);
        show_more = (TextView) findViewById(R.id.show_more);
        today = (TextView) findViewById(R.id.today);
        yesterday = (TextView) findViewById(R.id.yesterday);
        achi_title.setText(getIntent().getStringExtra("title"));
        saleInfo.setOnClickListener(this);
        show_more.setOnClickListener(this);
        today.setOnClickListener(this);
        yesterday.setOnClickListener(this);
        today.setTextColor(getResources().getColor(R.color.purpleColor));
        yesterday.setTextColor(getResources().getColor(R.color.colorWhite));
        order = (TextView) findViewById(R.id.order);
        order_num = (TextView) findViewById(R.id.order_num);
        order_price = (TextView) findViewById(R.id.order_price);
        order.setText("今日订单");
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
                        Toast.makeText(MineAchiActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MineAchiActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AchiInfo> call, Throwable t) {
                Toast.makeText(MineAchiActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
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
        switch (v.getId()) {
            case R.id.saleInfo:
                intent = new Intent(this, SaleInfoActivity.class);
                intent.putExtra("title","订单列表");
                intent.putExtra("uid",getIntent().getStringExtra("id"));
                startActivity(intent);
                break;
            case R.id.show_more:
                intent = new Intent(this, AchiInfoActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                startActivity(intent);
                break;
            case R.id.today:
                today.setTextColor(getResources().getColor(R.color.purpleColor));
                yesterday.setTextColor(getResources().getColor(R.color.colorWhite));
                order.setText("今日订单");
                initData(0,1);
                break;
            case R.id.yesterday:
                today.setTextColor(getResources().getColor(R.color.colorWhite));
                yesterday.setTextColor(getResources().getColor(R.color.purpleColor));
                order.setText("昨日订单");
                initData(-1,0);
                break;
        }
    }
}
