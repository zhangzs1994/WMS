package com.ycsx.www.wms.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.MyOrderRecyclerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.CategoryInfo;
import com.ycsx.www.wms.bean.OrderInfo;
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

public class MyOrderListActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private PullRecyclerView recyclerView;
    private MyOrderRecyclerAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 10;//显示条数
    private TextView title;
    private Spinner spinner;
    private List<String> spinnerValue = new ArrayList<>();
    private List<String> spinnerCode = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private String status;
    private int i=0;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_submit_list);
        initData(i);
        initView();
        queryDropdown();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerValue.get(position).toString().equals("全部")) {
                    i=0;
                    status = "";
                } else {
                    i=1;
                    for (int i = 0; i < spinnerValue.size(); i++) {
                        if (spinnerValue.get(i).toString().equals(spinnerValue.get(position).toString())) {
                            status = spinnerCode.get(i - 1).toString();
                        }
                    }
                }
                startRecord = 0;
                list = new ArrayList();
                initData(i);
                adapter = new MyOrderRecyclerAdapter(MyOrderListActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status = "";
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void queryDropdown() {
        spinnerValue.add("全部");
        Map<String, String> params = new HashMap<>();
        params.put("colName", "order2");
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
                        arrayAdapter = new ArrayAdapter<String>(MyOrderListActivity.this, R.layout.spinner_item, spinnerValue);
                        arrayAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
                        spinner.setAdapter(arrayAdapter);
                    } else {
                        Toast.makeText(MyOrderListActivity.this, "获取状态失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyOrderListActivity.this, "获取状态失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryInfo> call, Throwable t) {
                Toast.makeText(MyOrderListActivity.this, "获取状态失败3！", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initView() {
        recyclerView = (PullRecyclerView) findViewById(R.id.pullRecyclerView);
        //设置水平布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置下拉刷新监听
        recyclerView.setOnHeaderRefreshListener(this);
        //设置上拉加载监听
        recyclerView.setOnFooterRefreshListener(this);
        //设置自定义分割线
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        //适配器
        adapter = new MyOrderRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        title = (TextView) findViewById(R.id.title);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void initData(int i) {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("uid", pref.getInt("id", 0) + "");
        if (i == 1) {
            params.put("ostatus", status + "");
        }
        params.put("startRecord", startRecord + "");
        params.put("pageRecords", pageRecords + "");
        Call<OrderInfo> call = RetrofitUtil.getInstance(API.URL).selectOrderh1(params);
        call.enqueue(new Callback<OrderInfo>() {
            @Override
            public void onResponse(Call<OrderInfo> call, Response<OrderInfo> response) {
                if (response.isSuccessful()) {
                    OrderInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("oid", info.getData().get(i).getOid() + "");//订单id
                            map.put("usid", info.getData().get(i).getUsid() + "");//审核人
                            map.put("ostatus", info.getData().get(i).getOstatus() + "");//订单状态:0,待审核;1,已审核;2,未通过
                            map.put("dvalue", info.getData().get(i).getDvalue() + "");//订单状态值
                            map.put("value", info.getData().get(i).getValue() + "");//订单分类值
                            map.put("uid", info.getData().get(i).getUid() + "");//用户id
                            map.put("octime", info.getData().get(i).getOctime() + "");//创建时间
                            map.put("ouaddress", info.getData().get(i).getOuaddress() + "");//收货地址
                            map.put("uname", info.getData().get(i).getUname() + "");//用户姓名
                            map.put("ocost", info.getData().get(i).getOcost());//订单金额
                            map.put("criteria", info.getData().get(i).getCriteria() + "");//审核原因
                            map.put("datechanged", info.getData().get(i).getDatechanged() + "");//最后修改日期
                            map.put("expressnumber", info.getData().get(i).getExpressnumber() + "");//快递单号
                            map.put("classify", info.getData().get(i).getClassify() + "");//订单分类
                            map.put("title", title.getText() + "");//标题
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(info.getStatus())) {
                        Toast.makeText(MyOrderListActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyOrderListActivity.this, "获取订单失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyOrderListActivity.this, "获取订单失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderInfo> call, Throwable t) {
                Toast.makeText(MyOrderListActivity.this, "获取订单失败3！", Toast.LENGTH_SHORT).show();
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
                adapter = new MyOrderRecyclerAdapter(MyOrderListActivity.this, list);
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
                    Toast.makeText(MyOrderListActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData(i);
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
