package com.ycsx.www.wms.biz;

import android.util.Log;

import com.ycsx.www.wms.bean.LoginInfo;
import com.ycsx.www.wms.bean.UserInfo;
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
public class UserLogin implements IUserLogin{
    @Override
    public void login(final UserInfo userInfo, final LoginListener loginListener) {
//        UserInfo user = new UserInfo();
//        loginListener.loginSuccess(user);
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
                    Log.e("TAG", login.getStatus() );
                    if (("10200").equals(login.getStatus())) {
                        UserInfo user = new UserInfo();
                        user.setUserName(userInfo.getUserName());
                        user.setUserPassword(userInfo.getUserPassword());
                        user.setName(login.getData().get(0).getName());
                        user.setFlag(login.getData().get(0).getFlag());
                        Log.e("TAG==", login.getData().get(0).getFlag()+"");
                        user.setId(login.getData().get(0).getId());
                        user.setStatus(login.getData().get(0).getStatus());
                        user.setSuperior(login.getData().get(0).getSuperior());
                        if(login.getData().get(0).getSex().equals("1")){
                            user.setSex("男");
                        }else{
                            user.setSex("女");
                        }
                        user.setAge(login.getData().get(0).getAge());
                        user.setPhone(login.getData().get(0).getPhone());
                        user.setEmail(login.getData().get(0).getMail());
                        user.setSubordinate(login.getData().get(0).getSubordinate());
                        loginListener.loginSuccess(user);
                    } else {
                        if (!userInfo.getUserName().equals("") && !userInfo.getUserPassword().equals("")) {
                            loginListener.loginFailed("账号或密码错误！");
                        }
                    }
                } else {
                    Log.e("访问失败信息：", response.code()+"");
                    loginListener.loginFailed("登陆失败！");
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Log.e("访问失败信息：", t.getMessage()+"");
                loginListener.loginFailed("无法访问！");
            }
        });
    }
}
