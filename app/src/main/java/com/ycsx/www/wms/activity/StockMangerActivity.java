package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class StockMangerActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout shop_query, in_stock, out_stock;
    private Intent intent;
    private SharedPreferences pref;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_stock_manger);
        initView();
    }

    private void initView() {
        shop_query = (LinearLayout) findViewById(R.id.shop_query);
        in_stock = (LinearLayout) findViewById(R.id.in_stock);
        out_stock = (LinearLayout) findViewById(R.id.out_stock);
        shop_query.setOnClickListener(this);
        in_stock.setOnClickListener(this);
        out_stock.setOnClickListener(this);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        if(pref.getString("menuNode","").indexOf("30304")>=0){
            shop_query.setVisibility(View.VISIBLE);
        }else {
            shop_query.setVisibility(View.GONE);
        }
        if(pref.getString("menuNode","").indexOf("30102")>=0){
            in_stock.setVisibility(View.VISIBLE);
        }else {
            in_stock.setVisibility(View.GONE);
        }
        if(pref.getString("menuNode","").indexOf("30202")>=0){
            out_stock.setVisibility(View.VISIBLE);
        }else {
            out_stock.setVisibility(View.GONE);
        }
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_query:
                intent = new Intent(this, ShopQueryActivity.class);
                startActivity(intent);
                break;
            case R.id.in_stock:
                intent = new Intent(this, InStockActivity.class);
                startActivity(intent);
                break;
            case R.id.out_stock:
                intent = new Intent(this, OutStockActivity.class);
                startActivity(intent);
                break;
        }
    }
}
