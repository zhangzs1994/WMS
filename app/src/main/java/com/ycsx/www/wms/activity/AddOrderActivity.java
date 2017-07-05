package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.AddOrderListAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.AddOrderInfo;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrderActivity extends BaseActivity implements View.OnClickListener {
    private Button submit, keep;
    private AddOrderInfo order=new AddOrderInfo();
    private List<AddOrderInfo.DataBean> datas=new ArrayList<>();
    private List<Map<String,String>> list;
    private String orderDetial;
    private GsonBuilder builder;
    private Gson gson;
    private LinearLayout add_shop;
    private Intent intent;
    private ListView listView;
    private AddOrderListAdapter adapter;
    private Double price=0.0;
    private TextView ocost,receiving,contact,ouaddress;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] spinnerChild = {"正常", "非正常"};
    private String status;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_add_order);
        initView();
        builder=new GsonBuilder();
        gson=builder.create();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerChild);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("正常".equals(spinnerChild[position])) {
                    status = "1";
                } else if ("非正常".equals(spinnerChild[position])) {
                    status = "2";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status = "1";
            }
        });
        list=new ArrayList<>();
        initList();
        adapter=new AddOrderListAdapter(list,this);
        listView.setAdapter(adapter);
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        add_shop = (LinearLayout) findViewById(R.id.add_shop);
        submit = (Button) findViewById(R.id.submit);
        keep = (Button) findViewById(R.id.keep);
        listView = (ListView) findViewById(R.id.listView);
        ocost = (TextView) findViewById(R.id.ocost);
        receiving = (TextView) findViewById(R.id.receiving);
        contact = (TextView) findViewById(R.id.contact);
        ouaddress = (TextView) findViewById(R.id.ouaddress);
        submit.setOnClickListener(this);
        keep.setOnClickListener(this);
        add_shop.setOnClickListener(this);
    }

    private void initData() {
        orderDetial=gson.toJson(order, AddOrderInfo.class);
        Log.e("orderDetial===", orderDetial);
        Map<String, String> params = new HashMap<>();
        params.put("orderDetial", orderDetial);
        Call<Common> call = RetrofitUtil.getInstance(API.URL).addOrder(params);
        call.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.isSuccessful()) {
                    Common info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        finish();
                        Toast.makeText(AddOrderActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddOrderActivity.this, "提交失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddOrderActivity.this, "提交失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                Toast.makeText(AddOrderActivity.this, "提交失败3！", Toast.LENGTH_SHORT).show();
            }
        });
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
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("uid", info.getData().get(i).getUid() + "");
                            map.put("pid", info.getData().get(i).getPid() + "");
                            map.put("price", info.getData().get(i).getPrice() + "");
                            map.put("pname", info.getData().get(i).getPname() + "");
                            map.put("num", info.getData().get(i).getNum() + "");
                            list.add(map);
                            AddOrderInfo.DataBean data = new AddOrderInfo.DataBean();
                            data.setPid(info.getData().get(i).getPid());
                            data.setNum(info.getData().get(i).getNum());
                            data.setPrice(info.getData().get(i).getPrice());
                            datas.add(data);
                            price=price+Double.parseDouble(info.getData().get(i).getPrice()+"")*Double.parseDouble(info.getData().get(i).getNum()+"");
                        }
                        ocost.setText(price+"");
                    }else  if(("10365").equals(info.getStatus())) {
                        AddOrderInfo.DataBean data = new AddOrderInfo.DataBean();
                        datas.add(data);
                    } else{
                        Toast.makeText(AddOrderActivity.this, "查询失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddOrderActivity.this, "查询失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                Log.e("getMessage","==="+t.getMessage());
                Toast.makeText(AddOrderActivity.this, "查询失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view) {
        finish();
    }

    //返回当前时间-day
    public static String getTimeByMinute(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                order.setId(pref.getInt("id",0));
                order.setName(pref.getString("name",""));
                order.setDateTime(getTimeByMinute(0));
                order.setOuaddress(ouaddress.getText().toString());
                order.setReceiving(receiving.getText().toString());
                order.setContact(contact.getText().toString());
                order.setClassify(status);
                order.setOcost(Double.parseDouble(ocost.getText().toString()));
                if(datas.size()==0){
                    Toast.makeText(AddOrderActivity.this, "请添加商品！", Toast.LENGTH_SHORT).show();
                }else{
                    order.setData(datas);
                    initData();
                }
                break;
            case R.id.keep:
                break;
            case R.id.add_shop:
                intent=new Intent(this,ShopAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
