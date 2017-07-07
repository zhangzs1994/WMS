package com.ycsx.www.wms.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZS_PC on 2017/5/24.
 */
public class BaseActivity extends AutoLayoutActivity {
    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        init();
    }

    public void init(){
    }

    /**
     * 设置Android6.0的权限申请
     */
    public void checkPermissions(String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(BaseActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                //Android 6.0申请权限
                ActivityCompat.requestPermissions(this,permissions,1);
            }else{
                Log.i("Permission","权限申请ok");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
        }
    }

    //将Activity添加到list中
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    //关闭某个Activity
    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.finish();
    }

    //关闭所有Activity
    public static void removeAllActivity() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}
