package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.MyOrderListAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderShopActivity extends BaseActivity {
    private ListView listView;
    private MyOrderListAdapter adapter;
    private List<Map<String,Object>> list=new ArrayList<>();

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_my_order_shop);
        initList();
        initView();
        adapter=new MyOrderListAdapter(list,this);
        listView.setAdapter(adapter);
    }

    public void back(View view) {
        Intent intent=new Intent(this,ShopAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
        startActivity(intent);
        finish();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initList() {
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("uid", pref.getInt("id",0)+"");
        Call<OrderShop> call = RetrofitUtil.getInstance(API.URL).selectMyorde(params);
        call.enqueue(new Callback<OrderShop>() {
            @Override
            public void onResponse(Call<OrderShop> call, Response<OrderShop> response) {
                if (response.isSuccessful()) {
                    OrderShop info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("uid", info.getData().get(i).getUid() + "");
                            map.put("pid", info.getData().get(i).getPid() + "");
                            map.put("price", info.getData().get(i).getPrice());
                            map.put("pname", info.getData().get(i).getPname() + "");
                            map.put("num", info.getData().get(i).getNum() + "");
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    }else  if(("10365").equals(info.getStatus())) {
                        Toast.makeText(MyOrderShopActivity.this, "无已选商品！", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(MyOrderShopActivity.this, "查询失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyOrderShopActivity.this, "查询失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                Toast.makeText(MyOrderShopActivity.this, "查询失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
