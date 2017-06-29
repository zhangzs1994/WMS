package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class OrderMangerActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout order_query,order_audit,order_delivery;
    private Intent intent;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_query:
                intent = new Intent(this, OrderQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.order_audit:
                Intent intent = new Intent(this, OrderListActivity.class);
                intent.putExtra("title", "订单审核");
                intent.putExtra("oid", "");
                intent.putExtra("ostatus", "0");
                intent.putExtra("starttime", "");
                intent.putExtra("endtime", "");
                startActivity(intent);
                break;
            case R.id.order_delivery:
//                intent = new Intent(this, OrderQueryActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
