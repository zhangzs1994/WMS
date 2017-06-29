package com.ycsx.www.wms.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.ShopRecyclerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.ShopInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.recycler.MyDecoration;
import com.ycsx.www.wms.recycler.PullBaseView;
import com.ycsx.www.wms.recycler.PullRecyclerView;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopQueryActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private Spinner spinner;
    private String[] spinnerChild = {"全部", "0", "1", "2"};
    private List<String> spinnerChild1 = new ArrayList<>();
    private PullRecyclerView recyclerView;
    private ShopRecyclerAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 5;//显示条数
    private ArrayAdapter<String> arrayAdapter;
    private LinearLayout layout_query;
    private PopupWindow popupWindow;
    private int i = 0;//0：查询全部；1：按分类查询；2：按商品名查询
    private String category;//类别
    private Call<ShopInfo> call;
    private EditText shop_name;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_shop_query);
        initData(i);
        initView();
        queryDropdown();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = arrayAdapter.getItem(position).toString();
                startRecord = 0;
                list = new ArrayList();
                if (category.equals("全部")) {
                    i = 0;
                } else {
                    category = category.substring(0, spinnerChild1.get(position).indexOf(":"));
                    i = 1;
                }
                initData(i);
                adapter = new ShopRecyclerAdapter(ShopQueryActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layout_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
    }

    private void queryDropdown() {
        spinnerChild1.add("全部");
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
                            spinnerChild1.add(info.getData().get(i).getCode() + ":" + info.getData().get(i).getValue());
                        }
                        arrayAdapter = new ArrayAdapter<String>(ShopQueryActivity.this, R.layout.spinner_item, spinnerChild1);
                        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner.setAdapter(arrayAdapter);
                    } else {
                        Toast.makeText(ShopQueryActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShopQueryActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(ShopQueryActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void back(View view) {
        finish();
    }


    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
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
        adapter = new ShopRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void showPopupWindow(View parent) {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(parent.getHeight());
        View view = LayoutInflater.from(this).inflate(R.layout.shop_popup, null);
        LinearLayout layout_query = (LinearLayout) view.findViewById(R.id.layout_query);
        shop_name= (EditText) view.findViewById(R.id.shop_name);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        //popupWindow.showAtLocation(parent, Gravity.TOP,0,0);
        popupWindow.showAsDropDown(parent, 0, 0);
        layout_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                startRecord = 0;
                list = new ArrayList();
                i = 2;
                initData(i);
                adapter = new ShopRecyclerAdapter(ShopQueryActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void initData(int i) {
        final Map<String, String> params = new HashMap<>();
        params.put("startRecord", startRecord + "");
        params.put("pageRecords", pageRecords + "");
        if (i == 1) {
            params.put("category", category);
            call = RetrofitUtil.getInstance(API.URL).getGoodsByCategory(params);
        } else if (i == 2) {
            params.put("name", shop_name.getText().toString());
            call = RetrofitUtil.getInstance(API.URL).getLikeGoods(params);
        } else {
            call = RetrofitUtil.getInstance(API.URL).getGoodsList(params);
        }
        call.enqueue(new Callback<ShopInfo>() {
            @Override
            public void onResponse(Call<ShopInfo> call, Response<ShopInfo> response) {
                if (response.isSuccessful()) {
                    ShopInfo user = response.body();
                    if (("10200").equals(user.getStatus())) {
                        for (int i = 0; i < user.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("name", user.getData().get(i).getName() + "");//商品名称
                            map.put("category", user.getData().get(i).getCategory() + "");//商品类目
                            map.put("instockTime", user.getData().get(i).getInstockTime() + "");//入库时间
                            map.put("outstockTime", user.getData().get(i).getOutstockTime() + "");//出库时间
                            map.put("stock", user.getData().get(i).getStock() + "");//库存
                            map.put("price", user.getData().get(i).getPrice() + "");//价格
                            map.put("spec", user.getData().get(i).getSpec() + "");//规格
                            map.put("manufactureTime", user.getData().get(i).getManufactureTime() + "");//生产日期
                            map.put("qualityTime", user.getData().get(i).getQualityTime() + "");//保质期
                            map.put("describ", user.getData().get(i).getDescrib() + "");//商品描述
                            map.put("transactor", user.getData().get(i).getTransactor() + "");//经办人
                            map.put("goodsStatus", user.getData().get(i).getGoodsStatus() + "");//商品状态
                            map.put("acceptedGoods", user.getData().get(i).getAcceptedGoods() + "");//检验商品
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(user.getStatus())) {
                        Toast.makeText(ShopQueryActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShopQueryActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("code", "=="+response.code());
                    Toast.makeText(ShopQueryActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopInfo> call, Throwable t) {
                Toast.makeText(ShopQueryActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
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
                adapter = new ShopRecyclerAdapter(ShopQueryActivity.this, list);
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
                if (list.size() < 5) {
                    Toast.makeText(ShopQueryActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData(i);
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
