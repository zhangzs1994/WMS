package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class AchievementActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout mine_query,junior_query,order_submit,submit_query,logistics_query;
    private Intent intent;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_achievement);
        initView();
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        mine_query = (LinearLayout) findViewById(R.id.mine_query);
        junior_query = (LinearLayout) findViewById(R.id.junior_query);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        if(pref.getString("subordinate","0").equals("1")){
            junior_query.setVisibility(View.VISIBLE);
        }else{
            junior_query.setVisibility(View.GONE);
        }
        order_submit = (LinearLayout) findViewById(R.id.order_submit);
        submit_query = (LinearLayout) findViewById(R.id.submit_query);
        logistics_query = (LinearLayout) findViewById(R.id.logistics_query);
        mine_query.setOnClickListener(this);
        junior_query.setOnClickListener(this);
        order_submit.setOnClickListener(this);
        submit_query.setOnClickListener(this);
        logistics_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        switch (v.getId()){
            case R.id.mine_query:
                //个人业绩查询
                intent = new Intent(this, MineAchiActivity.class);
                intent.putExtra("title","个人业绩");
                intent.putExtra("id",pref.getInt("id",0)+"");
                startActivity(intent);
                break;
            case R.id.junior_query:
                //下属业绩查询
                intent = new Intent(this, JuniorAchiActivity.class);
                intent.putExtra("id",pref.getInt("id",0)+"");
                startActivity(intent);
                break;
            case R.id.order_submit:
                //创建订单
                intent = new Intent(this, AddOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.submit_query:
                //我的订单
                intent = new Intent(this, SubmitListActivity.class);
                startActivity(intent);
                break;
            case R.id.logistics_query:
//                物流查询
                intent = new Intent(this, LogisticsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
