package com.ycsx.www.wms.biz;

import android.util.Log;

import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/5/27.
 */
public class UserInfoSub implements IUserInfoSub {
    @Override
    public void submit(com.ycsx.www.wms.bean.UserInfo userInfo, final SubmitListener submitListener) {
        //POST请求的表单参数
        Map<String, Object> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        params.put("username", userInfo.getUserName());
        params.put("userpwd", userInfo.getUserPassword());
        params.put("name", userInfo.getName());
        params.put("age", userInfo.getAge());
        params.put("phone", userInfo.getPhone());
        params.put("mail", userInfo.getEmail());
        params.put("sex", userInfo.getSex());
        params.put("status", userInfo.getStatus());
        params.put("id", userInfo.getId());
        params.put("flag", userInfo.getFlag());
        params.put("superior", userInfo.getSuperior());
        params.put("subordinate", userInfo.getSubordinate());
        //调用Retrofit网络请求
        Call<Common> call = RetrofitUtil.getInstance(API.URL).setPersonInfo(params);
        call.enqueue(new Callback<Common>() {
            //请求成功
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                //返回成功
                if (response.isSuccessful()) {
                    Common data = response.body();
                    Log.e("TAG===", data.getStatus() );
                    if (("10200").equals(data.getStatus())) {
                        //个人信息设置功能实现
                        submitListener.submitSuccess("提交成功");
                    } else {
                        submitListener.submitFailed("提交失败！");
                    }
                } else {
                    submitListener.submitFailed("提交失败！");
                }
            }
            //请求失败
            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                submitListener.submitFailed("无法提交！");
            }
        });
    }
}
