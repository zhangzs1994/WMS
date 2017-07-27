package com.ycsx.www.wms.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.ShopClassifyAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopClassifyActivity extends BaseActivity {
    private ListView listView;
    private Intent intent;
    private List<Map<String, Object>> list;
    private ShopClassifyAdapter adapter;
    private LinearLayout classify_add;
    private PopupWindow popupWindow;
    private TextView cnki_info;
    private EditText name;
    private Button cnki, cancel, confirm;
    private SharedPreferences pref;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_shop_classify);
        list = new ArrayList();
        initView();
        queryDropdown();
        adapter = new ShopClassifyAdapter(list, this);
        listView.setAdapter(adapter);
        classify_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new PopupWindow(ShopClassifyActivity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                View view = LayoutInflater.from(ShopClassifyActivity.this).inflate(R.layout.classify_add_popup, null);
                name = (EditText) view.findViewById(R.id.name);
                cnki_info = (TextView) view.findViewById(R.id.cnki_info);
                cnki = (Button) view.findViewById(R.id.cnki);
                cancel = (Button) view.findViewById(R.id.cancel);
                confirm = (Button) view.findViewById(R.id.confirm);
                popupWindow.setContentView(view);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        cnki_info.setText("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                cnki.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkValue();
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
                        if (cnki_info.getText().toString().equals("该名称可用")) {
                            addDropdownValue();
                        } else if (cnki_info.getText().toString().equals("分类已存在")) {
                            Toast.makeText(ShopClassifyActivity.this, "该名称不可用，请重新输入！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ShopClassifyActivity.this, "请先查重！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        pref = getSharedPreferences("login", MODE_PRIVATE);
        if (pref.getString("menuNode", "").indexOf("20102") >= 0) {
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ShopClassifyActivity.this);
                    builder.setMessage("确认删除该分类？")    //对话框显示内容
                            //设置按钮
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("colName", "goodsCategory");
                                    params.put("code", list.get(position).get("code") + "");
                                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                                    params.put("operator", pref.getString("username", ""));
                                    Call<Common> call = RetrofitUtil.getInstance(API.URL).deleteDropdown(params);
                                    call.enqueue(new Callback<Common>() {
                                        @Override
                                        public void onResponse(Call<Common> call, Response<Common> response) {
                                            if (response.isSuccessful()) {
                                                Common info = response.body();
                                                if (("10200").equals(info.getStatus())) {
                                                    Toast.makeText(ShopClassifyActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                    list.remove(position);
                                                    adapter.notifyDataSetChanged();
                                                } else {
                                                    Log.e("getStatus==", info.getStatus());
                                                    Toast.makeText(ShopClassifyActivity.this, "删除失败1！", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(ShopClassifyActivity.this, "删除失败2！", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Common> call, Throwable t) {
                                            Toast.makeText(ShopClassifyActivity.this, "删除失败3！", Toast.LENGTH_SHORT).show();
                                        }

                                    });
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
                    return false;
                }
            });
        }
    }

    private void queryDropdown() {
        Map<String, String> params = new HashMap<>();
        params.put("colName", "goodsCategory");
        Call<CategoryInfo> call = RetrofitUtil.getInstance(API.URL).queryDropdown(params);
        call.enqueue(new Callback<CategoryInfo>() {
            @Override
            public void onResponse(Call<CategoryInfo> call, Response<CategoryInfo> response) {
                if (response.isSuccessful()) {
                    CategoryInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("value", info.getData().get(i).getValue() + "");
                            map.put("code", info.getData().get(i).getCode() + "");
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ShopClassifyActivity.this, "获取商品类别失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopClassifyActivity.this, "获取商品类别失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(ShopClassifyActivity.this, "获取商品类别失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void checkValue() {
        if (name.getText().toString().equals("")) {
            Toast.makeText(ShopClassifyActivity.this, "请输入名称！", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("colName", "goodsCategory");
            params.put("value", name.getText().toString());
            Call<Common> call = RetrofitUtil.getInstance(API.URL).checkValue(params);
            call.enqueue(new Callback<Common>() {
                @Override
                public void onResponse(Call<Common> call, Response<Common> response) {
                    if (response.isSuccessful()) {
                        Common info = response.body();
                        if (("10200").equals(info.getStatus())) {
                            cnki_info.setText("该名称可用");
                        } else if (("10366").equals(info.getStatus())) {
                            cnki_info.setText(info.getMessage());
                        } else {
                            Toast.makeText(ShopClassifyActivity.this, "检查失败1！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ShopClassifyActivity.this, "检查失败2！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Common> call, Throwable t) {
                    Toast.makeText(ShopClassifyActivity.this, "检查失败3！", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    private void addDropdownValue() {
        Map<String, String> params = new HashMap<>();
        params.put("colName", "goodsCategory");
        params.put("value", name.getText().toString());
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        params.put("operator", pref.getString("username", ""));
        Call<Common> call = RetrofitUtil.getInstance(API.URL).addDropdownValue(params);
        call.enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.isSuccessful()) {
                    Common info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        Toast.makeText(ShopClassifyActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                        list = new ArrayList();
                        queryDropdown();
                        adapter = new ShopClassifyAdapter(list, ShopClassifyActivity.this);
                        listView.setAdapter(adapter);
                        popupWindow.dismiss();
                    } else {
                        Log.e("getStatus==", info.getStatus());
                        Toast.makeText(ShopClassifyActivity.this, "添加失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopClassifyActivity.this, "添加失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                Toast.makeText(ShopClassifyActivity.this, "添加失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        pref=getSharedPreferences("login",MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.listView);
        classify_add = (LinearLayout) findViewById(R.id.classify_add);
        if (pref.getString("menuNode", "").indexOf("20101") < 0) {
            classify_add.setVisibility(View.INVISIBLE);
        } else {
            classify_add.setVisibility(View.VISIBLE);
        }
    }
}
