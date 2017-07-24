package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleOrderActivity extends BaseActivity implements View.OnClickListener {
    private Button submit;
    private AddOrderInfo order = new AddOrderInfo();
    private List<AddOrderInfo.DataBean> datas = new ArrayList<>();
    private List<Map<String, Object>> list = new ArrayList<>();
    private String orderDetial;
    private GsonBuilder builder;
    private Gson gson;
    private Button add_shop;
    private Intent intent;
    private ListView listView;
    private AddOrderListAdapter adapter;
    private Double price = 0.00;
    private TextView ocost;
    private EditText receiving, contact, ouaddress,remarke;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> spinnerValue = new ArrayList<>();
    private List<String> spinnerCode = new ArrayList<>();
    private String status = "1";//订单分类,1为销售订单;2为返厂订单;3销毁订单;内部领用
    private SharedPreferences pref;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_add_order);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        initList();
        initView();
        builder = new GsonBuilder();
        gson = builder.create();
        queryDropdown();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < spinnerValue.size(); i++) {
                    if (spinnerValue.get(i).toString().equals(spinnerValue.get(position).toString())) {
                        status = spinnerCode.get(i).toString();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status = "1";
            }
        });
    }

    private void queryDropdown() {
        Map<String, String> params = new HashMap<>();
        params.put("colName", "order1");
        Call<CategoryInfo> call = RetrofitUtil.getInstance(API.URL).queryDropdown(params);
        call.enqueue(new Callback<CategoryInfo>() {
            @Override
            public void onResponse(Call<CategoryInfo> call, Response<CategoryInfo> response) {
                if (response.isSuccessful()) {
                    CategoryInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            spinnerValue.add(info.getData().get(i).getValue() + "");
                            spinnerCode.add(info.getData().get(i).getCode() + "");
                        }
                        arrayAdapter = new ArrayAdapter<String>(SaleOrderActivity.this, R.layout.spinner_item, spinnerValue);
                        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner.setAdapter(arrayAdapter);
                    } else {
                        Toast.makeText(SaleOrderActivity.this, "获取类别失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SaleOrderActivity.this, "获取类别失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(SaleOrderActivity.this, "获取类别失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        add_shop = (Button) findViewById(R.id.add_shop);
        submit = (Button) findViewById(R.id.submit);
        listView = (ListView) findViewById(R.id.listView);
        ocost = (TextView) findViewById(R.id.ocost);
        receiving = (EditText) findViewById(R.id.receiving);
        contact = (EditText) findViewById(R.id.contact);
        ouaddress = (EditText) findViewById(R.id.ouaddress);
        remarke = (EditText) findViewById(R.id.remarke);
        submit.setOnClickListener(this);
        add_shop.setOnClickListener(this);
        adapter = new AddOrderListAdapter(list, this);
        listView.setAdapter(adapter);
    }

    private void initData() {
        dialog.show();
        orderDetial = gson.toJson(order, AddOrderInfo.class);
        Log.e("orderDetial===", orderDetial);
        Map<String, String> params = new HashMap<>();
        params.put("orderDetial", orderDetial);
        Call<Common> call = RetrofitUtil.getInstance(API.URL).addOrder(params);
        call.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Common info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        finish();
                        Toast.makeText(SaleOrderActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SaleOrderActivity.this, "提交失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SaleOrderActivity.this, "提交失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SaleOrderActivity.this, "提交失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initList() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("uid", pref.getInt("id", 0) + "");
        Call<OrderShop> call = RetrofitUtil.getInstance(API.URL).selectMyorde(params);
        call.enqueue(new Callback<OrderShop>() {
            @Override
            public void onResponse(Call<OrderShop> call, Response<OrderShop> response) {
                dialog.dismiss();
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
                            map.put("shop_allPrice", info.getData().get(i).getIocost() + "");
                            map.put("describe", info.getData().get(i).getDescribee() + "");
                            map.put("pictureUrl", info.getData().get(i).getPictureUrl() + "");
                            list.add(map);
                            AddOrderInfo.DataBean data = new AddOrderInfo.DataBean();
                            data.setPid(info.getData().get(i).getPid());
                            data.setNum(info.getData().get(i).getNum());
                            data.setPrice(info.getData().get(i).getPrice());
                            data.setIocost(info.getData().get(i).getIocost());
                            data.setDescribe(info.getData().get(i).getDescribee());
                            datas.add(data);
                            price = price + Double.parseDouble(info.getData().get(i).getPrice() + "") * Double.parseDouble(info.getData().get(i).getNum() + "");
                        }
                        ocost.setText(new DecimalFormat("######0.00").format(price));
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(info.getStatus())) {
                        AddOrderInfo.DataBean data = new AddOrderInfo.DataBean();
                        datas.add(data);
                    } else {
                        Toast.makeText(SaleOrderActivity.this, "查询失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SaleOrderActivity.this, "查询失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                dialog.dismiss();
                Log.e("getMessage", "===" + t.getMessage());
                Toast.makeText(SaleOrderActivity.this, "查询失败3！", Toast.LENGTH_SHORT).show();
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
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                order.setId(pref.getInt("id", 0));
                order.setName(pref.getString("username", ""));
                order.setDateTime(getTimeByMinute(0));
                order.setOuaddress(ouaddress.getText().toString());
                order.setReceiving(receiving.getText().toString());
                order.setContact(contact.getText().toString());
                order.setContact(contact.getText().toString());
                order.setRemarke(remarke.getText()+"");
                order.setClassify(status);
                if (ocost.getText().toString().equals("")) {
                    order.setOcost(0.00);
                } else {
                    order.setOcost(Double.parseDouble(ocost.getText().toString()));
                }
                if (list.size() == 0) {
                    Toast.makeText(SaleOrderActivity.this, "请添加商品！", Toast.LENGTH_SHORT).show();
                } else if (order.getReceiving().equals("")) {
                    Toast.makeText(SaleOrderActivity.this, "请输入收货人！", Toast.LENGTH_SHORT).show();
                } else if (order.getContact().equals("")) {
                    Toast.makeText(SaleOrderActivity.this, "请输入联系方式！", Toast.LENGTH_SHORT).show();
                } else if (order.getOuaddress().equals("")) {
                    Toast.makeText(SaleOrderActivity.this, "请输入收货地址！", Toast.LENGTH_SHORT).show();
                } else {
                    order.setData(datas);
                    initData();
                }
                break;
            case R.id.add_shop:
                intent = new Intent(this, ShopAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
