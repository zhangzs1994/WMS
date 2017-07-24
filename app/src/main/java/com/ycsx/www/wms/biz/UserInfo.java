package com.ycsx.www.wms.biz;

import com.ycsx.www.wms.bean.LoginInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.util.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UserInfo implements IUserInfo {
    @Override
    public void getUserInfo(final com.ycsx.www.wms.bean.UserInfo userInfo, final LoginListener loginListener) {
        if (userInfo.getUserName().equals("") || userInfo.getUserPassword().equals("")) {
            loginListener.loginFailed("账号或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", userInfo.getUserName());
        params.put("userpwd", userInfo.getUserPassword());
        Call<LoginInfo> call = RetrofitUtil.getInstance(API.URL).getUserInfo(params);
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                if (response.isSuccessful()) {
                    LoginInfo login = response.body();
                    if (("10200").equals(login.getStatus())) {
                        com.ycsx.www.wms.bean.UserInfo user = new com.ycsx.www.wms.bean.UserInfo();
                        user.setUserName(userInfo.getUserName());
                        user.setUserPassword(userInfo.getUserPassword());
                        user.setName(login.getData().get(0).getName());
                        user.setSex(login.getData().get(0).getSexValue());
                        user.setAge(login.getData().get(0).getAge().toString());
                        user.setPhone(login.getData().get(0).getPhone());
                        user.setEmail(login.getData().get(0).getMail());
                        user.setSuperior(login.getData().get(0).getSuperior());
                        loginListener.loginSuccess(user);
                    } else {
                        if (!userInfo.getUserName().equals("") && !userInfo.getUserPassword().equals("")) {
                            loginListener.loginFailed("账号或密码错误！");
                        }
                    }
                } else {
                    loginListener.loginFailed("登陆失败！");
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                loginListener.loginFailed("无法访问！");
            }
        });
    }
}
