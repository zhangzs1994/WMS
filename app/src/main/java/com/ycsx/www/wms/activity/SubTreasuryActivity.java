package com.ycsx.www.wms.activity;

import android.view.View;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class SubTreasuryActivity extends BaseActivity {

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_sub_treasury);
    }

    public void back(View view) {
        finish();
    }
}
