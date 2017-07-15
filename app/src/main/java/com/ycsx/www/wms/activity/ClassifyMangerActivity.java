package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class ClassifyMangerActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout shop_classify;
    private Intent intent;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_classify_manger);
        initView();
    }

    private void initView() {
        shop_classify= (LinearLayout) findViewById(R.id.shop_classify);
        shop_classify.setOnClickListener(this);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_classify:
                intent = new Intent(this, ShopClassifyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
