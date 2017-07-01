package com.ycsx.www.wms.activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener{
    private ListView order_shopInfo;
    private OrderDetailsAdapter adapter;
    private List<Map<String,Object>> list=new ArrayList<>();
    private View view;
    private TextView title;
    private LinearLayout layout_audit,layout_express,express;
    private Button audit_yes,audit_no,btn_express;
    private Spinner spinner;
    private String[] spinnerChild = {"一般发货", "快递发货"};
    private ArrayAdapter<String> arrayAdapter;
    private EditText audit_explain,express_id;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_details);
        initView();
        initData();
        adapter=new OrderDetailsAdapter(view,list,this);
        order_shopInfo.addHeaderView(view);
        order_shopInfo.setAdapter(adapter);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerChild);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    layout_express.setVisibility(View.GONE);
                } else {
                    layout_express.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btn_express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        layout_audit= (LinearLayout) findViewById(R.id.layout_audit);
        title= (TextView) findViewById(R.id.title);
        if(getIntent().getStringExtra("title").equals("订单审核")){
            title.setText("审核详情");
            layout_audit.setVisibility(View.VISIBLE);
        }else{
            title.setText("订单详情");
            layout_audit.setVisibility(View.GONE);
        }
        audit_yes= (Button) findViewById(R.id.audit_yes);
        audit_no= (Button) findViewById(R.id.audit_no);
        audit_yes.setOnClickListener(this);
        audit_no.setOnClickListener(this);
        btn_express = (Button) findViewById(R.id.btn_express);
        spinner = (Spinner) findViewById(R.id.spinner);
        express_id = (EditText) findViewById(R.id.express_id);
        audit_explain = (EditText) findViewById(R.id.audit_explain);
        layout_express = (LinearLayout) findViewById(R.id.layout_express);
        express = (LinearLayout) findViewById(R.id.express);
        if(getIntent().getStringExtra("title").equals("订单审核")){
            title.setText("审核详情");
            layout_audit.setVisibility(View.VISIBLE);
            express.setVisibility(View.GONE);
        }else if(getIntent().getStringExtra("title").equals("订单发货")){
            title.setText("发货详情");
            layout_audit.setVisibility(View.GONE);
            express.setVisibility(View.VISIBLE);
        }else{
            title.setText("订单详情");
            layout_audit.setVisibility(View.GONE);
            express.setVisibility(View.GONE);
        }
    }

    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.audit_yes:
                break;
            case R.id.audit_no:
                break;
        }
    }
}
