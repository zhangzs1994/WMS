package com.ycsx.www.wms.activity;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.RolesListViewAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.FlagCountInfo;
import com.ycsx.www.wms.bean.RolesInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RolesActivity extends BaseActivity {
    public List<String> group;
    List<List<String>> list;
    List<String> listChild;
    ExpandableListView mExpandableListView;
    RolesListViewAdapter mRolesListViewAdapter;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_roles);
        group = new ArrayList<>();
        list = new ArrayList<>();
        getRoles();
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        mRolesListViewAdapter = new RolesListViewAdapter(group, list, this);
        mExpandableListView.setAdapter(mRolesListViewAdapter);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

    //获取子分类
    private List<List<String>> getFlagName(final int code) {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        params.put("code", code + "");
        Call<FlagCountInfo> call = RetrofitUtil.getInstance(API.URL).getFlagCount(params);
        call.enqueue(new Callback<FlagCountInfo>() {
            @Override
            public void onResponse(Call<FlagCountInfo> call, Response<FlagCountInfo> response) {
                if (response.isSuccessful()) {
                    FlagCountInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        listChild = new ArrayList<>();
                        for (int i = 0; i < info.getData().size(); i++) {
                            listChild.add(info.getData().get(i).getUsername());
                        }
                        list.add(listChild);
                    } else {
                        Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlagCountInfo> call, Throwable t) {
                Log.e("访问失败返回信息：", t.getMessage());
                Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }

    //获取父分类
    private List<String> getRoles() {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        Call<RolesInfo> call = RetrofitUtil.getInstance(API.URL).queryRoles(params);
        call.enqueue(new Callback<RolesInfo>() {
            @Override
            public void onResponse(Call<RolesInfo> call, Response<RolesInfo> response) {
                if (response.isSuccessful()) {
                    RolesInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            group.add(info.getData().get(i).getValue());
                            Log.e("group", info.getData().get(i).getCode()+"");
                            try {
                                Thread.sleep(30);
                                getFlagName(info.getData().get(i).getCode());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RolesInfo> call, Throwable t) {
                Log.e("访问失败返回信息：", t.getMessage());
                Toast.makeText(RolesActivity.this, "请求数据失败！", Toast.LENGTH_SHORT).show();
            }
        });
        return group;
    }

    public void back(View view) {
        finish();
    }
}
