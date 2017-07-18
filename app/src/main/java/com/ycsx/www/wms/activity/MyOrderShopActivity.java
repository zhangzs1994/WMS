package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.MyOrderListAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.DecimalFormat;
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
    private List<Map<String, Object>> list = new ArrayList<>();
    private PopupWindow popupWindow;
    private SharedPreferences pref;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_my_order_shop);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        initList();
        initView();
        adapter = new MyOrderListAdapter(list, this, new MyOrderListAdapter.UpdateMyShop() {
            @Override
            public void onclick(View v, int position) {
                updateMyShop(v, position);
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MyOrderShopActivity.this,MyShopDetaisActivity.class);
                intent.putExtra("uid",list.get(position).get("uid")+"");
                intent.putExtra("pid",list.get(position).get("pid")+"");
                startActivity(intent);
            }
        });
    }

    private void updateMyShop(View v, final int position) {
        popupWindow = new PopupWindow(MyOrderShopActivity.this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        View view = LayoutInflater.from(MyOrderShopActivity.this).inflate(R.layout.shop_add_popup, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(list.get(position).get("pname") + "");
        final TextView stock = (TextView) view.findViewById(R.id.stock);
        stock.setText(list.get(position).get("stock") + "");
        final EditText num = (EditText) view.findViewById(R.id.num);
        num.setText(list.get(position).get("num") + "");
        num.setSelection(num.length());
        final EditText price = (EditText) view.findViewById(R.id.price);
        price.setText(new DecimalFormat("######0.00").format(Double.parseDouble(list.get(position).get("price") + "")));
        final TextView all_price = (TextView) view.findViewById(R.id.all_price);
        all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                * Double.parseDouble(price.getText() + "")) + "");
        final EditText describe = (EditText) view.findViewById(R.id.describe);
        describe.setText(list.get(position).get("describe") + "");
        TextView lessen = (TextView) view.findViewById(R.id.lessen);
        TextView add = (TextView) view.findViewById(R.id.add);
        final Button cancel = (Button) view.findViewById(R.id.cancel);
        Button confirm = (Button) view.findViewById(R.id.confirm);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!num.getText().toString().equals("")) {
                    if (Integer.parseInt(num.getText() + "") <= Integer.parseInt(stock.getText() + "")) {
                        all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                                * Double.parseDouble(price.getText() + "")) + "");
                    } else {
                        Toast.makeText(MyOrderShopActivity.this, "输入数量大于库存数量，请重新输入！", Toast.LENGTH_SHORT).show();
                        num.setText(stock.getText() + "");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((price.getText() + "").equals("")) {
                    all_price.setText("0.00");
                } else {
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        lessen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.getText().toString().equals("")) {
                    num.setText("1");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
                if (Integer.parseInt(num.getText() + "") > 1) {
                    num.setText((Integer.parseInt(num.getText() + "") - 1) + "");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                } else {
                    num.setText("1");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.getText().toString().equals("")) {
                    num.setText("1");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
                if (Integer.parseInt(num.getText() + "") < Integer.parseInt(stock.getText() + "")) {
                    num.setText((Integer.parseInt(num.getText() + "") + 1) + "");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                } else {
                    num.setText(Integer.parseInt(stock.getText() + "") + "");
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (price.getText().toString().equals("") || all_price.getText().toString().equals("0.00")) {
                    Toast.makeText(MyOrderShopActivity.this, "请输入单价！", Toast.LENGTH_SHORT).show();
                } else {
                    pref = getSharedPreferences("login", MODE_PRIVATE);
                    Map<String, String> params = new HashMap<>();
                    params.put("uid", pref.getInt("id", 0) + "");
                    params.put("pid", list.get(position).get("pid") + "");
                    params.put("pname", list.get(position).get("pname") + "");
                    params.put("num", num.getText() + "");
                    params.put("price", new DecimalFormat("######0.00").format(Double.parseDouble(price.getText() + "")) + "");
                    params.put("iocost", new DecimalFormat("######0.00").format(Double.parseDouble(all_price.getText() + "")) + "");
                    params.put("describee", describe.getText() + "");
                    Call<Common> call = RetrofitUtil.getInstance(API.URL).addMyorder(params);
                    call.enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if (response.isSuccessful()) {
                                Common info = response.body();
                                if (("10200").equals(info.getStatus())) {
                                    Toast.makeText(MyOrderShopActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MyOrderShopActivity.this, MyOrderShopActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.e("getStatus==", info.getStatus());
                                    Toast.makeText(MyOrderShopActivity.this, "修改失败1！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.e("code==", response.code() + "");
                                Toast.makeText(MyOrderShopActivity.this, "修改失败2！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            Toast.makeText(MyOrderShopActivity.this, "修改失败3！", Toast.LENGTH_SHORT).show();
                        }

                    });
                    popupWindow.dismiss();
                }
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(this, ShopAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
        startActivity(intent);
        finish();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
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
                if (response.isSuccessful()) {
                    OrderShop info = response.body();
                    dialog.dismiss();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("uid", info.getData().get(i).getUid() + "");
                            map.put("pid", info.getData().get(i).getPid() + "");
                            map.put("price", info.getData().get(i).getPrice());
                            map.put("pname", info.getData().get(i).getPname() + "");
                            map.put("num", info.getData().get(i).getNum() + "");
                            map.put("pictureUrl", info.getData().get(i).getPictureUrl() + "");
                            map.put("stock", info.getData().get(i).getNondefectiveNum() + "");
                            map.put("describe", info.getData().get(i).getDescribee() + "");
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(info.getStatus())) {
                        Toast.makeText(MyOrderShopActivity.this, "无已选商品！", Toast.LENGTH_SHORT).show();
                    } else {
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
