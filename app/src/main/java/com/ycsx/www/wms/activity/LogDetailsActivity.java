package com.ycsx.www.wms.activity;

import android.view.View;
import android.widget.TextView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class LogDetailsActivity extends BaseActivity {
    private TextView log_user, log_event, log_date, log_describe;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_log_details);
        initView();
    }

    private void initView() {
        log_user = (TextView) findViewById(R.id.log_user);
        log_event = (TextView) findViewById(R.id.log_event);
        log_date = (TextView) findViewById(R.id.log_date);
        log_describe = (TextView) findViewById(R.id.log_describe);
        log_user.setText("操作人："+getIntent().getStringExtra("operator"));
        log_event.setText("操作："+getIntent().getStringExtra("event"));
        log_date.setText("操作时间："+getIntent().getStringExtra("datetime"));
        log_describe.setText("操作描述："+getIntent().getStringExtra("content"));
    }

    public void back(View view) {
        finish();
    }
}
