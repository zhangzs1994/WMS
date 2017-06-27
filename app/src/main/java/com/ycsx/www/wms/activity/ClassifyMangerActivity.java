package com.ycsx.www.wms.activity;

import android.view.View;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class ClassifyMangerActivity extends BaseActivity {

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_classify_manger);
    }

    public void back(View view) {
        finish();
    }
}
