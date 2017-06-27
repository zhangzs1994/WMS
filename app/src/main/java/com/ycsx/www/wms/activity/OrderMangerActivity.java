package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class OrderMangerActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout order_query;

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
        order_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_query:
                Intent intent = new Intent(this, OrderQueryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
