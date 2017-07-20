package com.ycsx.www.wms.application;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.ycsx.www.wms.bean.InitInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/7/20.
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        Call<InitInfo> call = RetrofitUtil.getInstance(API.URL).initInfo(params);
        call.enqueue(new Callback<InitInfo>() {
            @Override
            public void onResponse(Call<InitInfo> call, Response<InitInfo> response) {
                if (response.isSuccessful()) {
                    InitInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            API.image = convertStrToArray(info.getData().get(i).getAppAdvert() + "");
                            if (!info.getData().get(i).getAppAdvert().equals("")) {
                                API.images = convertStrToArray1(info.getData().get(i).getAppAdvert() + "");
                            } else {
                                API.images = new ArrayList();
                                API.images.add("暂无图片！");
                            }
                            API.Version_no = info.getData().get(i).getVersion();
                        }
                        Log.e("images", "==="+API.images.size());
                    } else {
                        Toast.makeText(App.this, "获取初始化信息失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(App.this, "获取初始化信息失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InitInfo> call, Throwable t) {
                Toast.makeText(App.this, "获取初始化信息失败3！", Toast.LENGTH_SHORT).show();
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
}
