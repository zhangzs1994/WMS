package com.ycsx.www.wms.biz;

import com.ycsx.www.wms.bean.Common;
import com.ycsx.www.wms.bean.UserInfo;
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
public class UpdatePwd implements IUpdatePwd {
    @Override
    public void submit(UserInfo userInfo, final String newPwd, final String submitPwd, final SubmitListener submitListener) {
        if (userInfo.getUserPassword().equals("") || newPwd.equals("") || submitPwd.equals("")) {
            submitListener.submitFailed("账号或密码不能为空！");
        } else if (!newPwd.equals(submitPwd)) {
            submitListener.submitFailed("两次输入密码不一致！");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("authorizationCode", API.authorizationCode);
            params.put("id", userInfo.getId());
            params.put("oldpwd", userInfo.getUserPassword());
            params.put("newpwd", newPwd);
            params.put("operator", userInfo.getUserName());
            Call<Common> call = RetrofitUtil.getInstance(API.URL).setUpdatePwd(params);
            call.enqueue(new Callback<Common>() {
                @Override
                public void onResponse(Call<Common> call, Response<Common> response) {
                    if (response.isSuccessful()) {
                        Common data = response.body();
                        if (("10200").equals(data.getStatus())) {
                            //修改密码功能实现
                            submitListener.submitSuccess("修改成功,请重新登陆！");
                        } else {
                            submitListener.submitFailed("原账号密码不正确，请重新输入！");
                        }
                    } else {
                        submitListener.submitFailed("提交失败！");
                    }
                }

                @Override
                public void onFailure(Call<Common> call, Throwable t) {
                    submitListener.submitFailed("无法提交！");
                }
            });
        }
    }
}
