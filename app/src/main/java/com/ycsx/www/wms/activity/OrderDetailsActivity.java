package com.ycsx.www.wms.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.OrderDetailsAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderDetailsInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;
import com.ycsx.www.wms.view.MyListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends BaseActivity implements View.OnClickListener {
    private MyListView order_shopInfo;
    private OrderDetailsAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList<>();
    private View view;
    private TextView title;
    private LinearLayout layout_audit, layout_express, express, layout_cancel;
    private Button audit_yes, audit_no, btn_express, order_cancel;
    private Spinner spinner;
    private String[] spinnerChild = {"一般发货", "快递发货"};
    private ArrayAdapter<String> arrayAdapter;
    private EditText audit_explain, express_id;
    private LoadingDialog dialog;
    private SharedPreferences pref;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_details);
        initView();
        initData();
        adapter = new OrderDetailsAdapter(view, list, this);
        order_shopInfo.addHeaderView(view);
        order_shopInfo.setAdapter(adapter);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerChild);
        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    layout_express.setVisibility(View.GONE);
                } else {
                    layout_express.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        Log.e("order_id===", getIntent().getStringExtra("order_id"));
        params.put("oid", getIntent().getStringExtra("order_id"));
        dialog.show();
        Call<OrderDetailsInfo> call = RetrofitUtil.getInstance(API.URL).getOrderinven(params);
        call.enqueue(new Callback<OrderDetailsInfo>() {
            @Override
            public void onResponse(Call<OrderDetailsInfo> call, Response<OrderDetailsInfo> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    OrderDetailsInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("oid", info.getData().get(i).getOid() + "");//订单号
                            map.put("name", info.getData().get(i).getName() + "");//商品名称
                            map.put("ostatus", info.getData().get(i).getOstatus());//订单状态
                            map.put("dvalue", getIntent().getStringExtra("dvalue"));//订单状态值
                            map.put("value", getIntent().getStringExtra("value"));//订单分类值
                            map.put("ouaddress", info.getData().get(i).getOuaddress() + "");//收货地址
                            map.put("price", info.getData().get(i).getPrice());//单价
                            map.put("freightamount", info.getData().get(i).getFreightamount() + "");//商品数量
                            map.put("iocost", info.getData().get(i).getIocost());//商品总价
                            map.put("ocost", info.getData().get(i).getOcost());//总价
                            map.put("inventime", info.getData().get(i).getOctime() + "");//订单时间
                            map.put("contact", info.getData().get(i).getContact() + "");//联系方式
                            map.put("receiving", info.getData().get(i).getReceiving() + "");//联系人
                            map.put("pictureUrl", info.getData().get(i).getPictureUrl() + "");//图片地址
                            map.put("uname", info.getData().get(i).getUname() + "");//创建者
                            map.put("describee", info.getData().get(i).getDescribee() + "");//商品备注
                            map.put("expressnumber", info.getData().get(i).getExpressnumber() + "");//快递单号
                            map.put("criteria", info.getData().get(i).getCriteria() + "");//审核说明
                            map.put("remarke", info.getData().get(i).getRemarke() + "");//审核说明
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, "获取订单详情失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "获取订单详情失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsInfo> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(OrderDetailsActivity.this, "获取订单详情失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateOrder(int i, final String message, final String error) {
        dialog.show();
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("oid", getIntent().getStringExtra("order_id"));
        params.put("usid", pref.getInt("id", 0) + "");
        params.put("operator", pref.getString("username", ""));
        params.put("ostatus", i + "");
        params.put("datechanged", getTimeByMinute(0));
        params.put("criteria", audit_explain.getText() + "");
        Call<Common> call = RetrofitUtil.getInstance(API.URL).updateOrder(params);
        call.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Common info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        Toast.makeText(OrderDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, info.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(OrderDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deliverGoods() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("oid", getIntent().getStringExtra("order_id"));
        params.put("uid", getIntent().getStringExtra("uid"));
        params.put("operator", pref.getString("username", ""));
        params.put("classify", getIntent().getStringExtra("classify"));
        params.put("shipper", pref.getInt("id", 0) + "");
        params.put("inventime", getTimeByMinute(0));
        params.put("expressnumber", express_id.getText() + "");
        Call<Common> call = RetrofitUtil.getInstance(API.URL).deliverGoods(params);
        call.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Common info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        Toast.makeText(OrderDetailsActivity.this, "发货成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, "发货失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "发货失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(OrderDetailsActivity.this, "发货失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        pref = getSharedPreferences("login", MODE_PRIVATE);
        view = View.inflate(this, R.layout.order_details_header, null);
        order_shopInfo = (MyListView) findViewById(R.id.order_shopInfo);
        layout_audit = (LinearLayout) findViewById(R.id.layout_audit);
        layout_cancel = (LinearLayout) findViewById(R.id.layout_cancel);
        title = (TextView) findViewById(R.id.title);
        audit_yes = (Button) findViewById(R.id.audit_yes);
        audit_no = (Button) findViewById(R.id.audit_no);
        btn_express = (Button) findViewById(R.id.btn_express);
        order_cancel = (Button) findViewById(R.id.order_cancel);
        audit_yes.setOnClickListener(this);
        audit_no.setOnClickListener(this);
        btn_express.setOnClickListener(this);
        order_cancel.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        express_id = (EditText) findViewById(R.id.express_id);
        audit_explain = (EditText) findViewById(R.id.audit_explain);
        layout_express = (LinearLayout) findViewById(R.id.layout_express);
        express = (LinearLayout) findViewById(R.id.express);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        if (getIntent().getStringExtra("title").equals("审核列表")) {
            title.setText("订单审核");
            if (pref.getString("menuNode", "").indexOf("40102") >= 0) {
                layout_audit.setVisibility(View.VISIBLE);
            } else {
                layout_audit.setVisibility(View.GONE);
            }
            express.setVisibility(View.GONE);
        } else if (getIntent().getStringExtra("title").equals("发货列表")) {
            title.setText("订单发货");
            layout_audit.setVisibility(View.GONE);
            if (pref.getString("menuNode", "").indexOf("40202") >= 0) {
                express.setVisibility(View.VISIBLE);
            } else {
                express.setVisibility(View.GONE);
            }
        } else if (getIntent().getStringExtra("title").equals("出库记录")) {
            title.setText("出库详情");
            layout_audit.setVisibility(View.GONE);
            express.setVisibility(View.GONE);
        } else if (getIntent().getStringExtra("title").equals("我的订单")) {
            title.setText("订单详情");
            layout_audit.setVisibility(View.GONE);
            express.setVisibility(View.GONE);
            if (getIntent().getStringExtra("dvalue").equals("待审核")) {
                layout_cancel.setVisibility(View.VISIBLE);
            } else {
                layout_cancel.setVisibility(View.GONE);
            }
        } else {
            title.setText("订单详情");
            layout_audit.setVisibility(View.GONE);
            express.setVisibility(View.GONE);
        }
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audit_yes:
                updateOrder(1, "审核成功！", "审核失败！");
                break;
            case R.id.audit_no:
                updateOrder(2, "审核成功！", "审核失败！");
                break;
            case R.id.btn_express:
                if ((express_id.getText() + "").equals("") && layout_express.getVisibility() == View.VISIBLE) {
                    Toast.makeText(OrderDetailsActivity.this, "快递单号不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    deliverGoods();
                }
                break;
            case R.id.order_cancel:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确认取消该订单？")    //对话框显示内容
                        //设置按钮
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                updateOrder(5, "取消成功！", "取消失败！");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                break;
        }
    }

    //返回当前时间-day
    public static String getTimeByMinute(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
}
