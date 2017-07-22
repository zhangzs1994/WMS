package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class ClassifyMangerActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout shop_classify;
    private Intent intent;
    private SharedPreferences pref;

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
        pref=getSharedPreferences("login",MODE_PRIVATE);
        switch (v.getId()){
            case R.id.shop_classify:
                if (pref.getString("menuNode", "").indexOf("201") < 0) {
                    Toast.makeText(this, "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(this, ShopClassifyActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
