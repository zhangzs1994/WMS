package com.ycsx.www.wms.activity;

import android.view.View;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class SaleInfoActivity extends BaseActivity {

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_sale_info);
        initView();
    }

    private void initView() {

    }

    public void back(View view) {
        finish();
    }
}
