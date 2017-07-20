package com.ycsx.www.wms.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.AutoTextViewAdapter;
import com.ycsx.www.wms.adapter.LogRecyclerAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.LogInfo;
import com.ycsx.www.wms.bean.LoginInfo;
import com.ycsx.www.wms.bean.UserInfo;
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

public class OpLogActivity extends BaseActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private PullRecyclerView recyclerView;
    private LogRecyclerAdapter adapter;
    private List<Map<String, Object>> list = new ArrayList();
    private List<UserInfo> listName = new ArrayList();
    private int startRecord = 0;//开始条数
    private int pageRecords = 10;//显示条数
    private LinearLayout shop_query, layout_query, layout_pop;
    private int i = 0;//0：查询全部；1：按分类查询；2：按商品名查询
    private AutoCompleteTextView user_name;
    private AutoTextViewAdapter autoTextViewAdapter;
    private String username;
    private SharedPreferences pref;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_op_log);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        username = pref.getString("username", "");
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        initData();
        initView();
        likeByName();
        autoTextViewAdapter = new AutoTextViewAdapter(listName, this);
        user_name.setAdapter(autoTextViewAdapter);
        user_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView likeName = (TextView) view;
                user_name.setText(likeName.getText());
                user_name.setSelection(user_name.getText().length());
                username = user_name.getText().toString();
                startRecord = 0;
                list = new ArrayList();
                initData();
                adapter = new LogRecyclerAdapter(OpLogActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        layout_query = (LinearLayout) findViewById(R.id.layout_query);
        layout_pop = (LinearLayout) findViewById(R.id.layout_pop);
        user_name = (AutoCompleteTextView) findViewById(R.id.user_name);
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
        adapter = new LogRecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        if(pref.getString("menuNode","").indexOf("802")>=0){
            layout_pop.setVisibility(View.VISIBLE);
        }else {
            layout_pop.setVisibility(View.GONE);
        }
    }

    private void likeByName() {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        params.put("str", "");
        Call<LoginInfo> call = RetrofitUtil.getInstance(API.URL).likeByName(params);
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                if (response.isSuccessful()) {
                    LoginInfo user = response.body();
                    if (("10200").equals(user.getStatus())) {
                        for (int i = 0; i < user.getData().size(); i++) {
                            UserInfo info = new UserInfo();
                            info.setId(user.getData().get(i).getId());//用户ID
                            info.setUserName(user.getData().get(i).getUsername() + "");//用户名
                            info.setName(user.getData().get(i).getName() + "");//姓名
                            listName.add(info);
                        }
                    } else if (("10365").equals(user.getStatus())) {
                        Toast.makeText(OpLogActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OpLogActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OpLogActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Toast.makeText(OpLogActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        dialog.show();
        Map<String, Object> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        params.put("username", username);
        Log.e("username", "=== " + username);
        params.put("currentPage", Integer.parseInt(startRecord + ""));
        Log.e("startRecord", "=== " + startRecord);
        params.put("pageRecords", Integer.parseInt(pageRecords + ""));
        Log.e("pageRecords", "=== " + pageRecords);
        Call<LogInfo> call = RetrofitUtil.getInstance(API.URL).queryLogByName(params);
        call.enqueue(new Callback<LogInfo>() {
            @Override
            public void onResponse(Call<LogInfo> call, Response<LogInfo> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    LogInfo user = response.body();
                    if (("10200").equals(user.getStatus())) {
                        for (int i = 0; i < user.getData().size(); i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("id", user.getData().get(i).getId() + "");//日志ID
                            map.put("datetime", user.getData().get(i).getDatetime() + "");//操作时间
                            map.put("operator", user.getData().get(i).getOperator() + "");//操作人
                            map.put("event", user.getData().get(i).getEvent() + "");//入库时间
                            map.put("content", user.getData().get(i).getContent() + "");//出库时间
                            list.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    } else if (("10365").equals(user.getStatus())) {
                        Toast.makeText(OpLogActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("getStatus==", user.getStatus());
                        Log.e("getMessage==", user.getMessage());
                        Toast.makeText(OpLogActivity.this, "访问失败1！", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("code", "==" + response.code());
                    Toast.makeText(OpLogActivity.this, "访问失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogInfo> call, Throwable t) {
                dialog.dismiss();
                Log.e("getMessage", "==" + t.getMessage());
                Toast.makeText(OpLogActivity.this, "访问失败3！", Toast.LENGTH_SHORT).show();
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
                adapter = new LogRecyclerAdapter(OpLogActivity.this, list);
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
                    Toast.makeText(OpLogActivity.this, "已经没有更多了！", Toast.LENGTH_SHORT).show();
                } else {
                    startRecord = startRecord + pageRecords;
                    initData();
                }
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }
}
