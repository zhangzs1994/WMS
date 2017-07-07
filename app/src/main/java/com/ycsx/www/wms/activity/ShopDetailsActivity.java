package com.ycsx.www.wms.activity;

import android.view.View;
import android.widget.TextView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class ShopDetailsActivity extends BaseActivity {
    private TextView shop_name,shop_describ,shop_transactor,shop_category,shop_instockTime,
            shop_outstockTime,shop_stock,shop_price,shop_spec,shop_manufactureTime,shop_qualityTime,
            shop_goodsStatus,shop_acceptedGoods;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_shop_details);
        initView();
    }

    private void initView() {
        shop_name= (TextView) findViewById(R.id.shop_name);
        shop_describ= (TextView) findViewById(R.id.shop_describ);
        shop_transactor= (TextView) findViewById(R.id.shop_transactor);
        shop_category= (TextView) findViewById(R.id.shop_category);
        shop_instockTime= (TextView) findViewById(R.id.shop_instockTime);
        shop_outstockTime= (TextView) findViewById(R.id.shop_outstockTime);
        shop_stock= (TextView) findViewById(R.id.shop_stock);
        shop_price= (TextView) findViewById(R.id.shop_price);
        shop_spec= (TextView) findViewById(R.id.shop_spec);
        shop_manufactureTime= (TextView) findViewById(R.id.shop_manufactureTime);
        shop_qualityTime= (TextView) findViewById(R.id.shop_qualityTime);
        shop_goodsStatus= (TextView) findViewById(R.id.shop_goodsStatus);
        shop_acceptedGoods= (TextView) findViewById(R.id.shop_acceptedGoods);
        if(getIntent().getStringExtra("name").equals("null")){
            shop_name.setText("无");
        }else {
            shop_name.setText(getIntent().getStringExtra("name"));
        }
        if(getIntent().getStringExtra("describ").equals("null")){
            shop_describ.setText("无");
        }else {
            shop_describ.setText(getIntent().getStringExtra("describ"));
        }
        if(getIntent().getStringExtra("transactor").equals("null")){
            shop_transactor.setText("无");
        }else {
            shop_transactor.setText(getIntent().getStringExtra("transactor"));
        }
        if(getIntent().getStringExtra("category").equals("null")){
            shop_category.setText("无");
        }else {
            shop_category.setText(getIntent().getStringExtra("category"));
        }
        if(getIntent().getStringExtra("instockTime").equals("null")){
            shop_instockTime.setText("无");
        }else {
            shop_instockTime.setText(getIntent().getStringExtra("instockTime"));
        }
        if(getIntent().getStringExtra("outstockTime").equals("null")){
            shop_outstockTime.setText("无");
        }else {
            shop_outstockTime.setText(getIntent().getStringExtra("outstockTime"));
        }
        if(getIntent().getStringExtra("stock").equals("null")){
            shop_stock.setText("无");
        }else {
            shop_stock.setText(getIntent().getStringExtra("stock"));
        }
        if(getIntent().getStringExtra("price").equals("null")){
            shop_price.setText("无");
        }else {
            shop_price.setText(getIntent().getStringExtra("price"));
        }
        if(getIntent().getStringExtra("spec").equals("null")){
            shop_spec.setText("无");
        }else {
            shop_spec.setText(getIntent().getStringExtra("spec"));
        }
        if(getIntent().getStringExtra("manufactureTime").equals("null")){
            shop_manufactureTime.setText("无");
        }else {
            shop_manufactureTime.setText(getIntent().getStringExtra("manufactureTime"));
        }
        if(getIntent().getStringExtra("qualityTime").equals("null")){
            shop_qualityTime.setText("无");
        }else {
            shop_qualityTime.setText(getIntent().getStringExtra("qualityTime"));
        }
        if(getIntent().getStringExtra("goodsStatus").equals("null")){
            shop_goodsStatus.setText("无");
        }else {
            shop_goodsStatus.setText(getIntent().getStringExtra("goodsStatus"));
        }
        if(getIntent().getStringExtra("nondefectiveNum").equals("null")){
            shop_acceptedGoods.setText("无");
        }else {
            shop_acceptedGoods.setText(getIntent().getStringExtra("nondefectiveNum"));
        }
    }

    public void back(View view){
        finish();
    }
}
