package com.ycsx.www.wms.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.MyOrderAdapter;
import com.ycsx.www.wms.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends BaseActivity {
    private ListView listView;
    private Button submit_order;
    private MyOrderAdapter adapter;
    private List<Map<String, String>> list = new ArrayList<>();

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_my_order);
        initView();
        initData();
        adapter = new MyOrderAdapter(list, this);
        listView.setAdapter(adapter);
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("name","商品"+(i+1));
            map.put("num",(i+1)+"");
            map.put("price",(i+1)+"");
            list.add(map);
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        submit_order = (Button) findViewById(R.id.submit_order);
    }

    public void back(View view) {
        finish();
    }
}
