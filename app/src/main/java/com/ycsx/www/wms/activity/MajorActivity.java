package com.ycsx.www.wms.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.fragment.HomePageFragment;
import com.ycsx.www.wms.fragment.MineFragment;

public class MajorActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private ImageView homeImage,censusImage,mineImage;
    private TextView homeText,censusText,mineText;
    private FragmentManager fragmentManager;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences pref;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_major);
        initView();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, new HomePageFragment(), "").commit();
        setBackground();
        homeImage.setBackgroundResource(R.drawable.major_home_blue);
        homeText.setTextColor(getResources().getColor(R.color.colorBlue));
    }

    private void initView() {
        homeImage = (ImageView) findViewById(R.id.homeImage);
        censusImage = (ImageView) findViewById(R.id.censusImage);
        mineImage = (ImageView) findViewById(R.id.mineImage);
        homeText = (TextView) findViewById(R.id.homeText);
        censusText = (TextView) findViewById(R.id.censusText);
        mineText = (TextView) findViewById(R.id.mineText);
        layout1= (LinearLayout) findViewById(R.id.bottomLayout1);
        layout2= (LinearLayout) findViewById(R.id.bottomLayout2);
        layout3= (LinearLayout) findViewById(R.id.bottomLayout3);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
    }

    public void setBackground(){
        homeImage.setBackgroundResource(R.drawable.major_home);
        censusImage.setBackgroundResource(R.drawable.major_census);
        mineImage.setBackgroundResource(R.drawable.major_mine);
        homeText.setTextColor(getResources().getColor(R.color.fontColor));
        censusText.setTextColor(getResources().getColor(R.color.fontColor));
        mineText.setTextColor(getResources().getColor(R.color.fontColor));
    }

    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction();
        pref = getSharedPreferences("login", MODE_PRIVATE);
        switch (v.getId()) {
            case R.id.bottomLayout1:
                setBackground();
                homeImage.setBackgroundResource(R.drawable.major_home_blue);
                homeText.setTextColor(getResources().getColor(R.color.colorBlue));
                fragmentTransaction.replace(R.id.fl_container, new HomePageFragment(), "").commit();
                break;
            case R.id.bottomLayout2:
                setBackground();
                censusImage.setBackgroundResource(R.drawable.major_census_blue);
                censusText.setTextColor(getResources().getColor(R.color.colorBlue));
                if (pref.getString("menuNode", "").indexOf("801") < 0) {
                    Toast.makeText(this, "您的权限不足，如有疑问，请联系管理员！", Toast.LENGTH_SHORT).show();
                } else {
//                  fragmentTransaction.replace(R.id.fl_container, new CensusFragment(), "").commit();
                    Toast.makeText(MajorActivity.this, "该功能暂未开放，敬请期待！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bottomLayout3:
                setBackground();
                //检查权限
                checkPermissions(permissions);
                mineImage.setBackgroundResource(R.drawable.major_mine_blue);
                mineText.setTextColor(getResources().getColor(R.color.colorBlue));
                fragmentTransaction.replace(R.id.fl_container, new MineFragment(), "").commit();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
