package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.adapter.JuniorAchiAdapter;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.SuperiorInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JuniorAchiActivity extends BaseActivity {
    private ListView junior_list;
    private Intent intent;
    private List<Map<String,String>> list = new ArrayList();
    private JuniorAchiAdapter achiAdapter;
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_junior_achi);
        initView();
        initData();
        achiAdapter = new JuniorAchiAdapter(list, this);
        junior_list.setAdapter(achiAdapter);
        junior_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(JuniorAchiActivity.this, MineAchiActivity.class);
                intent.putExtra("title", list.get(position).get("username")+"的业绩");
                intent.putExtra("id", list.get(position).get("id").toString());
                startActivity(intent);
            }
        });
    }

    private void initData() {
        dialog.show();
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        params.put("username", pref.getString("username",""));
        Call<SuperiorInfo> call = RetrofitUtil.getInstance(API.URL).querySuperior(params);
        call.enqueue(new Callback<SuperiorInfo>() {
            @Override
            public void onResponse(Call<SuperiorInfo> call, Response<SuperiorInfo> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    SuperiorInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            Map<String,String> map=new HashMap<String, String>();
                            map.put("id",info.getData().get(i).getId()+"");
                            map.put("username",info.getData().get(i).getUsername()+"");
                            map.put("name",info.getData().get(i).getName()+"");
                            list.add(map);
                        }
                        achiAdapter.notifyDataSetChanged();
                    }else if(("10666").equals(info.getStatus())){
                        Toast.makeText(JuniorAchiActivity.this, "无下属信息！", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.e("code", "=="+info.getStatus());
                        Toast.makeText(JuniorAchiActivity.this, "获取下属信息失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(JuniorAchiActivity.this, "获取下属信息失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuperiorInfo> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(JuniorAchiActivity.this, "获取下属信息失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        junior_list = (ListView) findViewById(R.id.junior_list);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
    }
}
