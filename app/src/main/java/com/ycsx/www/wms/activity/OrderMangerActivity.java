package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class OrderMangerActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout order_query, order_audit, order_delivery;
    private Intent intent;
    private SharedPreferences pref;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_manger);
        initView();
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        order_query = (LinearLayout) findViewById(R.id.order_query);
        order_audit = (LinearLayout) findViewById(R.id.order_audit);
        order_delivery = (LinearLayout) findViewById(R.id.order_delivery);
        order_query.setOnClickListener(this);
        order_audit.setOnClickListener(this);
        order_delivery.setOnClickListener(this);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        if (pref.getString("menuNode", "").indexOf("301") >= 0) {
            order_audit.setVisibility(View.VISIBLE);
        } else {
            order_audit.setVisibility(View.GONE);
        }
        if (pref.getString("menuNode", "").indexOf("303") >= 0) {
            order_delivery.setVisibility(View.VISIBLE);
        } else {
            order_delivery.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_query:
                intent = new Intent(this, OrderQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.order_audit:
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra("title", "审核列表");
                intent.putExtra("oid", "");
                intent.putExtra("ostatus", "0");
                intent.putExtra("classify", "");
                intent.putExtra("starttime", "");
                intent.putExtra("endtime", "");
                startActivity(intent);
                break;
            case R.id.order_delivery:
                intent = new Intent(this, OrderListActivity.class);
                intent.putExtra("title", "发货列表");
                intent.putExtra("oid", "");
                intent.putExtra("ostatus", "1");
                intent.putExtra("classify", "");
                intent.putExtra("starttime", "");
                intent.putExtra("endtime", "");
                startActivity(intent);
                break;
        }
    }
}
