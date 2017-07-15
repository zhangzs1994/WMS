package com.ycsx.www.wms.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.ycsx.www.wms.bean.InitInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void init() {
        initData();
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        Call<InitInfo> call = RetrofitUtil.getInstance(API.URL1).initInfo(params);
        call.enqueue(new Callback<InitInfo>() {
            @Override
            public void onResponse(Call<InitInfo> call, Response<InitInfo> response) {
                if (response.isSuccessful()) {
                    InitInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            API.image = convertStrToArray(info.getData().get(i).getAppAdvert() + "");
                            API.images = convertStrToArray1(info.getData().get(i).getAppAdvert() + "");
                        }
                    } else {
                        Toast.makeText(BaseActivity.this, "获取初始化信息失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BaseActivity.this, "获取初始化信息失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InitInfo> call, Throwable t) {
                Toast.makeText(BaseActivity.this, "获取初始化信息失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String[] convertStrToArray(String str) {
        //Log.e("image", "" + strArray.length);
        if (!str.contains(",")) {
            API.image = new String[1];
            API.image[0] = str;
        } else {
            API.image = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return API.image;
    }

    public List convertStrToArray1(String str) {
        //Log.e("image", "" + strArray.length);
        if (!str.contains(",")) {
            API.images = new ArrayList();
            API.images.add(str);
        } else {
            API.images = Arrays.asList(str.split(",")); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return API.images;
    }

    /**
     * 设置Android6.0的权限申请
     */
    public void checkPermissions(String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(BaseActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                //Android 6.0申请权限
                ActivityCompat.requestPermissions(this, permissions, 1);
            } else {
                Log.i("Permission", "权限申请ok");
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
