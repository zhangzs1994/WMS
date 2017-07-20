package com.ycsx.www.wms.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;
import com.ycsx.www.wms.R;
import com.ycsx.www.wms.activity.LoginActivity;
import com.ycsx.www.wms.activity.RolesActivity;
import com.ycsx.www.wms.activity.UpdatePwdActivity;
import com.ycsx.www.wms.activity.UserInfoActivity;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.UpdateAppUtils;

/**
 * Created by ZZS_PC on 2017/6/6.
 */
public class MineFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layout_update, userInfo, updatePwd, authority, checkUpdate, quit, quit_user, quit_all;
    private BadgeView badgeView;
    private int Forced = 0;// 1：强制更新   0：不是
    private String url = "https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk";//APK地址
    private String Version_name = "仓管1.1";//版本名称
    private String info = "仓管系统版本更新！";  //更新说明
    private boolean state = false;//更新状态
    private View view;
    private Intent intent;
    private TextView userName, userLevel;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        try {
            PackageManager pm = getActivity().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
            //Log.e("TAG", "旧版本" + pi.versionCode);
            //如果新版本大于旧版本，就显示更新小红点
            if (API.Version_no > pi.versionCode) {
                initBadgeView();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initView();
        return view;
    }


    public void initView() {
        pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userInfo = (LinearLayout) view.findViewById(R.id.userInfo);
        updatePwd = (LinearLayout) view.findViewById(R.id.updatePwd);
        authority = (LinearLayout) view.findViewById(R.id.authority);
        checkUpdate = (LinearLayout) view.findViewById(R.id.checkUpdate);
        userName = (TextView) view.findViewById(R.id.userName);
        userLevel = (TextView) view.findViewById(R.id.userLevel);
        userName.setText("账号："+pref.getString("username", ""));
        userLevel.setText("级别："+pref.getString("flagValue", ""));
        quit = (LinearLayout) view.findViewById(R.id.quit);
        userInfo.setOnClickListener(this);
        updatePwd.setOnClickListener(this);
        authority.setOnClickListener(this);
        checkUpdate.setOnClickListener(this);
        quit.setOnClickListener(this);
    }

    public void initBadgeView() {
        //更新小红点
        layout_update = (LinearLayout) view.findViewById(R.id.layout_update);
        badgeView = new BadgeView(getActivity(), layout_update);
        badgeView.setBackgroundResource(R.drawable.update_round);
        badgeView.setBadgeMargin(5);
        badgeView.setTextSize(7);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
        badgeView.show();
        state = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userInfo:
                intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.updatePwd:
                intent = new Intent(getActivity(), UpdatePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.authority:
                intent = new Intent(getActivity(), RolesActivity.class);
                startActivity(intent);
                break;
            case R.id.checkUpdate:
                if (state) {
                    //如果状态为false就不隐藏badgeView，不然报空指针异常
                    badgeView.hide();
                    state = false;
                }
                UpdateAppUtils.UpdateApp(getActivity(), API.Version_no, Version_name, info,
                        url, Forced == 1 ? true : false, true);
                break;
            case R.id.quit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = View.inflate(getActivity(), R.layout.quit_item, null);
                builder .setCancelable(true) // 设置点击对话框外部区域，关闭对话框，默认
                        .setView(view);
                quit_user = (LinearLayout) view.findViewById(R.id.quit_user);
                quit_all = (LinearLayout) view.findViewById(R.id.quit_all);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                quit_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        new BaseActivity().removeAllActivity();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                quit_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        new BaseActivity().removeAllActivity();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                break;
        }
    }
}
