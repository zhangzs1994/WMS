package com.ycsx.www.wms.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.ShopAddAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.OrderShop;
import com.ycsx.www.wms.bean.ShopInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.recycler.MyDecoration;
import com.ycsx.www.wms.recycler.PullBaseView;
import com.ycsx.www.wms.recycler.PullRecyclerView;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;
import com.ycsx.www.wms.zxing.android.CaptureActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopAddActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private Spinner spinner;
    private List<String> spinnerValue = new ArrayList<>();
    private List<String> spinnerCode = new ArrayList<>();
    private PullRecyclerView recyclerView;
    private ShopAddAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 10;//显示条数
    private ArrayAdapter<String> arrayAdapter;
    private LinearLayout shop_query, layout_query, layout_pop;
    private PopupWindow popupWindow;
    private int i = 0;//0：查询全部；1：按分类查询；2：按商品名查询
    private String category;//类别
    private Call<ShopInfo> call;
    private EditText shop_name;
    public final static int SCANNING_REQUEST_CODE = 1;
    private ImageView zxing;
    private String[] permissions = {Manifest.permission.CAMERA};
    private SharedPreferences pref;
    private int count = 0;
    private BadgeView badgeView;
    private TextView text_finish;
    private Button btn_finish;
    private LinearLayout shop_card;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_shop_add);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        initData(i);
        initView();
        queryDropdown();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = spinnerValue.get(position).toString();
                startRecord = 0;
                list = new ArrayList();
                if (category.equals("全部")) {
                    i = 0;
                } else {
                    for (int i = 0; i < spinnerValue.size(); i++) {
                        if (spinnerValue.get(i).toString().equals(spinnerValue.get(position).toString())) {
                            category = spinnerCode.get(i - 1).toString();
                        }
                    }
                    i = 1;
                }
                initData(i);
                adapter = new ShopAddAdapter(new ShopAddAdapter.ImageInterface() {
                    @Override
                    public void onclick(View view, int position) {
                        imageSetOnClick(view, position);
                    }
                }, ShopAddActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        shop_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void queryDropdown() {
        spinnerValue.add("全部");
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
                            spinnerValue.add(info.getData().get(i).getValue() + "");
                            spinnerCode.add(info.getData().get(i).getCode() + "");
                        }
                        arrayAdapter = new ArrayAdapter<String>(ShopAddActivity.this, R.layout.spinner_item, spinnerValue);
                        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner.setAdapter(arrayAdapter);
                    } else {
                        Log.e("getStatus==", info.getStatus());
                        Toast.makeText(ShopAddActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopAddActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(ShopAddActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void back(View view) {
        Intent intent = new Intent(this, SaleOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SaleOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
        startActivity(intent);
        finish();
    }

    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        shop_query = (LinearLayout) findViewById(R.id.shop_query);
        layout_query = (LinearLayout) findViewById(R.id.layout_query);
        layout_pop = (LinearLayout) findViewById(R.id.layout_pop);
        shop_name = (EditText) findViewById(R.id.shop_name);
        zxing = (ImageView) findViewById(R.id.zxing);
        recyclerView = (PullRecyclerView) findViewById(R.id.pullRecyclerView);
        layout_query = (LinearLayout) findViewById(R.id.layout_query);
        //设置水平布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置下拉刷新监听
        recyclerView.setOnHeaderRefreshListener(this);
        //设置上拉加载监听
        recyclerView.setOnFooterRefreshListener(this);
        //设置自定义分割线
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        //适配器
        adapter = new ShopAddAdapter(new ShopAddAdapter.ImageInterface() {
            @Override
            public void onclick(View view, int position) {
                imageSetOnClick(view, position);
            }
        }, this, list);
        recyclerView.setAdapter(adapter);
        shop_card = (LinearLayout) findViewById(R.id.shop_card);
        badgeView = new BadgeView(this, shop_card);
        badgeView.setBackgroundResource(R.drawable.update_round);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
        badgeView.setBadgeMargin(5);
        badgeView.setTextSize(12);
        shop_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopAddActivity.this, MyOrderShopActivity.class);
                startActivity(intent);
            }
        });
        text_finish = (TextView) findViewById(R.id.text_finish);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        select();
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopAddActivity.this, SaleOrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
                startActivity(intent);
            }
        });
    }

    private void imageSetOnClick(View v, final int position) {
        popupWindow = new PopupWindow(ShopAddActivity.this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        View view = LayoutInflater.from(ShopAddActivity.this).inflate(R.layout.shop_add_popup, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(list.get(position).get("name").toString());
        final TextView stock = (TextView) view.findViewById(R.id.stock);
        stock.setText(list.get(position).get("nondefectiveNum").toString());
        final EditText num = (EditText) view.findViewById(R.id.num);
        num.setText("1");
        num.setSelection(num.length());
        final EditText price = (EditText) view.findViewById(R.id.price);
        price.setText(list.get(position).get("price").toString());
        final TextView all_price = (TextView) view.findViewById(R.id.all_price);
        if (num.getText().toString().equals("")) {
            num.setText("1");
        }
        all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                * Double.parseDouble(price.getText() + "")) + "");
        final EditText describe = (EditText) view.findViewById(R.id.describe);
        LinearLayout lessen = (LinearLayout) view.findViewById(R.id.lessen);
        LinearLayout add = (LinearLayout) view.findViewById(R.id.add);
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
                        Toast.makeText(ShopAddActivity.this, "输入数量大于库存数量，请重新输入！", Toast.LENGTH_SHORT).show();
                        num.setText(stock.getText() + "");
                        num.setSelection(num.length());
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
                if((price.getText()+"").equals("")){
                    all_price.setText("0.00");
                }else{
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
                    num.setSelection(num.length());
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
                if (Integer.parseInt(num.getText() + "") > 1) {
                    num.setText((Integer.parseInt(num.getText() + "") - 1) + "");
                    num.setSelection(num.length());
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                } else {
                    num.setText("1");
                    num.setSelection(num.length());
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
                    num.setSelection(num.length());
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                }
                if (Integer.parseInt(num.getText() + "") < Integer.parseInt(stock.getText() + "")) {
                    num.setText((Integer.parseInt(num.getText() + "") + 1) + "");
                    num.setSelection(num.length());
                    all_price.setText(new DecimalFormat("######0.00").format((Integer.parseInt(num.getText() + ""))
                            * Double.parseDouble(price.getText() + "")) + "");
                } else {
                    num.setText(Integer.parseInt(stock.getText() + "") + "");
                    num.setSelection(num.length());
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
                    Toast.makeText(ShopAddActivity.this, "请输入单价！", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("name", list.get(position).get("name").toString());
                    Log.e("id", list.get(position).get("id").toString());
                    pref = getSharedPreferences("login", MODE_PRIVATE);
                    Map<String, String> params = new HashMap<>();
                    params.put("uid", pref.getInt("id", 0) + "");
                    params.put("pid", list.get(position).get("id").toString());
                    params.put("pname", list.get(position).get("name").toString());
                    params.put("num", num.getText() + "");
                    params.put("price", new DecimalFormat("######0.00").format(Double.parseDouble(price.getText() + ""))+"");
                    params.put("iocost", new DecimalFormat("######0.00").format(Double.parseDouble(all_price.getText() + ""))+"");
                    params.put("describee", describe.getText()+"");
                    Call<Common> call = RetrofitUtil.getInstance(API.URL).addMyorder(params);
                    call.enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if (response.isSuccessful()) {
                                Common info = response.body();
                                if (("10200").equals(info.getStatus())) {
                                    Toast.makeText(ShopAddActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                                    select();
                                } else {
                                    Log.e("getStatus==", info.getStatus());
                                    Toast.makeText(ShopAddActivity.this, "添加失败1！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.e("code==", response.code() + "");
                                Toast.makeText(ShopAddActivity.this, "添加失败2！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            Toast.makeText(ShopAddActivity.this, "添加失败3！", Toast.LENGTH_SHORT).show();
                        }

                    });
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void select() {
        pref = getSharedPreferences("login", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("uid", pref.getInt("id", 0) + "");
        Call<OrderShop> call1 = RetrofitUtil.getInstance(API.URL).selectMyorde(params);
        call1.enqueue(new Callback<OrderShop>() {
            @Override
            public void onResponse(Call<OrderShop> call, Response<OrderShop> response) {
                if (response.isSuccessful()) {
                    OrderShop info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        count = info.getData().size();
                    } else if (("10365").equals(info.getStatus())) {
                        count = 0;
                    } else {
                        Toast.makeText(ShopAddActivity.this, "查询失败1！", Toast.LENGTH_SHORT).show();
                    }
                    if (count > 0) {
                        badgeView.setText(count + "");
                        badgeView.show();
                        text_finish.setVisibility(View.GONE);
                        btn_finish.setVisibility(View.VISIBLE);
                    } else {
                        badgeView.hide();
                        text_finish.setVisibility(View.VISIBLE);
                        btn_finish.setVisibility(View.GONE);
                    }
                    Log.e("count", "==" + count);
                } else {
                    Toast.makeText(ShopAddActivity.this, "查询失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderShop> call, Throwable t) {
                Log.e("getMessage", "===" + t.getMessage());
                Toast.makeText(ShopAddActivity.this, "查询失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPopupWindow() {
        if (layout_pop.getVisibility() == View.VISIBLE) {
            layout_pop.setVisibility(View.GONE);
        } else {
            layout_pop.setVisibility(View.VISIBLE);
        }
        layout_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_pop.setVisibility(View.GONE);
                startRecord = 0;
                list = new ArrayList();
                i = 2;
                initData(i);
                adapter = new ShopAddAdapter(new ShopAddAdapter.ImageInterface() {
                    @Override
                    public void onclick(View view, int position) {
                        imageSetOnClick(view, position);
                    }
                }, ShopAddActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        });
        zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions(permissions);
                Intent intent = new Intent(ShopAddActivity.this, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNING_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNING_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final Bundle bundle = data.getExtras();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            shop_name.setText(data.getStringExtra("codedContent"));
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    private void initData(int i) {
        dialog.show();
        final Map<String, String> params = new HashMap<>();
        params.put("startRecord", startRecord + "");
        params.put("pageRecords", pageRecords + "");
        if (i == 1) {
            Log.e("category", "===" + category);
            params.put("category", category);
            call = RetrofitUtil.getInstance(API.URL).getGoodsByCategory(params);
        } else if (i == 2) {
            params.put("value", shop_name.getText().toString());
            call = RetrofitUtil.getInstance(API.URL).getGoodsListLikeValue(params);
        } else {
            call = RetrofitUtil.getInstance(API.URL).getGoodsList(params);
        }
        call.enqueue(new Callback<ShopInfo>() {
            @Override
            public void onResponse(Call<ShopInfo> call, Response<ShopInfo> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    ShopInfo user = response.body();
                    if (("10200").equals(user.getStatus())) {
                        for (int i = 0; i < user.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("id", user.getData().get(i).getId() + "");//商品ID
                            map.put("name", user.getData().get(i).getName() + "");//商品名称
                            map.put("category", user.getData().get(i).getCategory() + "");//商品类目
                            map.put("instockTime", user.getData().get(i).getInstockTime() + "");//入库时间
                            map.put("outstockTime", user.getData().get(i).getOutstockTime() + "");//出库时间
                            map.put("stock", user.getData().get(i).getStock() + "");//库存
                            map.put("price", user.getData().get(i).getRetailPrice());//价格
                            map.put("spec", user.getData().get(i).getSpec() + "");//规格
                            map.put("manufactureTime", user.getData().get(i).getManufactureTime() + "");//生产日期
                            map.put("qualityTime", user.getData().get(i).getQualityTime() + "");//保质期
                            map.put("describ", user.getData().get(i).getDescrib() + "");//商品描述
                            map.put("transactor", user.getData().get(i).getTransactor() + "");//经办人
                            map.put("goodsStatus", user.getData().get(i).getGoodsStatus() + "");//商品状态
                            map.put("nondefectiveNum", user.getData().get(i).getNondefectiveNum() + "");//检验商品
                            map.put("pictureUrl", user.getData().get(i).getPictureUrl() + "");//商品图片地址
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(user.getStatus())) {
                        Toast.makeText(ShopAddActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShopAddActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopAddActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopInfo> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ShopAddActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //下拉刷新数据
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //初始化加载数据
                startRecord = 0;
                list = new ArrayList();
                initData(i);
                adapter = new ShopAddAdapter(new ShopAddAdapter.ImageInterface() {
                    @Override
                    public void onclick(View view, int position) {
                        imageSetOnClick(view, position);
                    }
                }, ShopAddActivity.this, list);
                recyclerView.setAdapter(adapter);
                recyclerView.onHeaderRefreshComplete();
            }
        }, 2000);
    }

    //上拉加载数据
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (list.size() < 10) {
                    Toast.makeText(ShopAddActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData(i);
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
