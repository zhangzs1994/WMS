package com.ycsx.www.wms.activity;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.OutStockRecyclerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.OutStockInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.recycler.MyDecoration;
import com.ycsx.www.wms.recycler.PullBaseView;
import com.ycsx.www.wms.recycler.PullRecyclerView;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutStockActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener{
    private PullRecyclerView recyclerView;
    private OutStockRecyclerAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 5;//显示条数
    private LinearLayout outStock_query;
    private PopupWindow popupWindow;
    private TextView startData, endData;
    private View view;
    private int i = 0;
    private LinearLayout layout_query;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_out_stock);
        initData(i);
        initView();
        outStock_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        outStock_query = (LinearLayout) findViewById(R.id.outStock_query);
        view = LayoutInflater.from(this).inflate(R.layout.stock_popup, null);
        layout_query = (LinearLayout) view.findViewById(R.id.layout_query);
        startData = (TextView) view.findViewById(R.id.startData);
        endData = (TextView) view.findViewById(R.id.endData);
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
        adapter = new OutStockRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void showPopupWindow(View parent) {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(parent.getHeight());
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        //popupWindow.showAtLocation(parent, Gravity.TOP,0,0);
        popupWindow.showAsDropDown(parent, 0, 0);
        final Calendar c = Calendar.getInstance();
        startData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(OutStockActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        startData.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                if(!endData.getText().toString().equals("")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(endData.getText().toString());
                        dialog.getDatePicker().setMaxDate(date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    dialog.getDatePicker().setMaxDate((new Date()).getTime());
                }
                dialog.show();
            }
        });
        endData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(OutStockActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        endData.setText(DateFormat.format("yyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                if(!startData.getText().toString().equals("")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(startData.getText().toString());
                        dialog.getDatePicker().setMinDate(date.getTime());
                        dialog.getDatePicker().setMaxDate((new Date()).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    dialog.getDatePicker().setMaxDate((new Date()).getTime());
                }
                dialog.show();
            }
        });
        layout_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startData.getText().equals("")){
                    Toast.makeText(OutStockActivity.this, "请选择开始日期！", Toast.LENGTH_SHORT).show();
                }else if(endData.getText().equals("")){
                    Toast.makeText(OutStockActivity.this, "请选择结束日期！", Toast.LENGTH_SHORT).show();
                }else {
                    popupWindow.dismiss();
                    i = 1;
                    startRecord = 0;
                    list = new ArrayList();
                    initData(i);
                    adapter = new OutStockRecyclerAdapter(OutStockActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    private void initData(int i) {
        Map<String, String> params = new HashMap<>();
        if(i==1){
            params.put("starttime", startData.getText() + "");
            params.put("endtime", endData.getText() + "");
        }
        params.put("startRecord", startRecord + "");
        params.put("pageRecords", pageRecords + "");
        Call<OutStockInfo> call = RetrofitUtil.getInstance(API.URL).getinvenBypage(params);
        call.enqueue(new Callback<OutStockInfo>() {
            @Override
            public void onResponse(Call<OutStockInfo> call, Response<OutStockInfo> response) {
                if (response.isSuccessful()) {
                    OutStockInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                            for (int i = 0; i < info.getData().size(); i++) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("name", info.getData().get(i).getName() + "");//商品名称
                                map.put("category", info.getData().get(i).getCategory() + "");//商品类目
                                map.put("instockTime", info.getData().get(i).getInstockTime() + "");//入库时间
                                map.put("outstockTime", info.getData().get(i).getOutstockTime() + "");//出库时间
                                map.put("stock", info.getData().get(i).getStock() + "");//库存
                                map.put("price", info.getData().get(i).getPrice());//价格
                                map.put("spec", info.getData().get(i).getSpec() + "");//规格
                                map.put("manufactureTime", info.getData().get(i).getManufactureTime() + "");//生产日期
                                map.put("qualityTime", info.getData().get(i).getQualityTime() + "");//保质期
                                map.put("describ", info.getData().get(i).getDescrib() + "");//商品描述
                                map.put("transactor", info.getData().get(i).getTransactor() + "");//经办人
                                map.put("goodsStatus", info.getData().get(i).getGoodsStatus() + "");//商品状态
                                map.put("acceptedGoods", info.getData().get(i).getAcceptedGoods() + "");//检验商品
                                map.put("freightamount", info.getData().get(i).getFreightamount() + "");//出库件数
                                map.put("iocost", info.getData().get(i).getIocost());//出库金额
                                Log.e("getInventime", info.getData().get(i).getInventime() );
                                map.put("inventime", info.getData().get(i).getInventime() + "");//出库时间
                                list.add(map);
                            }
                            adapter.notifyDataSetChanged();
                    }else if(("10365").equals(info.getStatus())){
                        Toast.makeText(OutStockActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(OutStockActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OutStockActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OutStockInfo> call, Throwable t) {
                Toast.makeText(OutStockActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
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
                adapter = new OutStockRecyclerAdapter(OutStockActivity.this, list);
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
                    Toast.makeText(OutStockActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData(i);
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
