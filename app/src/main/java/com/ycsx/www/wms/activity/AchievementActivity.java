package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;

public class AchievementActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout mine_query,junior_query;
    private Intent intent;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_achievement);
        initView();
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        mine_query = (LinearLayout) findViewById(R.id.mine_query);
        junior_query = (LinearLayout) findViewById(R.id.junior_query);
        mine_query.setOnClickListener(this);
        junior_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        switch (v.getId()){
            case R.id.mine_query:
                intent = new Intent(this, MineAchiActivity.class);
                intent.putExtra("title","个人业绩");
                intent.putExtra("id",pref.getInt("id",0)+"");
                startActivity(intent);
                break;
            case R.id.junior_query:
                intent = new Intent(this, JuniorAchiActivity.class);
                intent.putExtra("id",pref.getInt("id",0)+"");
                startActivity(intent);
                break;
        }
    }
}
