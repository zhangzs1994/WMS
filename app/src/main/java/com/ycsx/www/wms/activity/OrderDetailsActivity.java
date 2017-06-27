package com.ycsx.www.wms.activity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.OrderDetailsAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.OrderDetailsInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends BaseActivity {
    private ListView order_shopInfo;
    private OrderDetailsAdapter adapter;
    private List<Map<String,Object>> list=new ArrayList<>();
    private View view;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_details);
        initView();
        initData();
        adapter=new OrderDetailsAdapter(view,list,this);
        order_shopInfo.addHeaderView(view);
        order_shopInfo.setAdapter(adapter);
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        Log.e("order_id===", getIntent().getStringExtra("order_id"));
        params.put("oid", getIntent().getStringExtra("order_id"));
        Call<OrderDetailsInfo> call = RetrofitUtil.getInstance(API.URL).getOrderinven(params);
        call.enqueue(new Callback<OrderDetailsInfo>() {
            @Override
            public void onResponse(Call<OrderDetailsInfo> call, Response<OrderDetailsInfo> response) {
                if (response.isSuccessful()) {
                    OrderDetailsInfo info = response.body();
                    Log.e("Status===", info.getStatus());
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("oid", info.getData().get(i).getOid() + "");//订单号
                            map.put("name", info.getData().get(i).getName() + "");//商品名称
                            map.put("ostatus", info.getData().get(i).getOstatus() + "");//订单状态
                            map.put("ouaddress", info.getData().get(i).getOuaddress() + "");//收货地址
                            map.put("price", info.getData().get(i).getPrice() + "");//单价
                            map.put("freightamount", info.getData().get(i).getFreightamount() + "");//商品数量
                            map.put("iocost", info.getData().get(i).getIocost() + "");//商品总价
                            map.put("ocost", info.getData().get(i).getOcost() + "");//总价
                            map.put("inventime", info.getData().get(i).getInventime() + "");//订单时间
                            list.add(map);
                        }

                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(OrderDetailsActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("返回码===", response.code() + "");
                    Toast.makeText(OrderDetailsActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsInfo> call, Throwable t) {
                Log.e("TAG", "==="+t.getMessage());
                Toast.makeText(OrderDetailsActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        view=View.inflate(this,R.layout.order_details_header,null);
        order_shopInfo= (ListView) findViewById(R.id.order_shopInfo);
    }

    public void back(View view){
        finish();
    }
}
