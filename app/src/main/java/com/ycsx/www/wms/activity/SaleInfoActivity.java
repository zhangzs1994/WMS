package com.ycsx.www.wms.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.OrderRecyclerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.OrderInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.recycler.MyDecoration;
import com.ycsx.www.wms.recycler.PullBaseView;
import com.ycsx.www.wms.recycler.PullRecyclerView;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleInfoActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private PullRecyclerView recyclerView;
    private OrderRecyclerAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 10;//显示条数
    private TextView title;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_order_list);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        initData();
        initView();
    }

    public void back(View view) {
        finish();
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
        adapter = new OrderRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        title= (TextView) findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));
    }

    private void initData() {
        dialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("uid",getIntent().getStringExtra("uid"));
        params.put("ostatus", "1");
        params.put("startRecord", startRecord + "");
        params.put("pageRecords", pageRecords + "");
        Call<OrderInfo> call = RetrofitUtil.getInstance(API.URL).selectOrderh1(params);
        call.enqueue(new Callback<OrderInfo>() {
            @Override
            public void onResponse(Call<OrderInfo> call, Response<OrderInfo> response) {
                if (response.isSuccessful()) {
                    OrderInfo info = response.body();
                    dialog.dismiss();
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
                            map.put("title", title.getText()+"");//标题
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    }else if(("10365").equals(info.getStatus())){
                        Toast.makeText(SaleInfoActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SaleInfoActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SaleInfoActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderInfo> call, Throwable t) {
                Toast.makeText(SaleInfoActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
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
                initData();
                adapter = new OrderRecyclerAdapter(SaleInfoActivity.this, list);
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
                    Toast.makeText(SaleInfoActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData();
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
