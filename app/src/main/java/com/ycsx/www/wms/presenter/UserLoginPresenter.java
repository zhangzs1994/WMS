package com.ycsx.www.wms.presenter;

import android.os.Handler;

import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.biz.IUserLogin;
import com.ycsx.www.wms.biz.LoginListener;
import com.ycsx.www.wms.biz.UserLogin;
import com.ycsx.www.wms.view.IUserLoginView;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UserLoginPresenter {
    private IUserLogin userLogin;
    private IUserLoginView iUserLoginView;
    private Handler handler=new Handler();

    public UserLoginPresenter(IUserLoginView iUserLoginView) {
        this.userLogin = new UserLogin();
        this.iUserLoginView = iUserLoginView;
    }

    public void login(){
        iUserLoginView.showDialog();
        userLogin.login(iUserLoginView.getUserInfo(), new LoginListener() {
            @Override
            public void loginSuccess(final UserInfo user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.dismissDialog();
                        iUserLoginView.LoginSuccess(user);
                    }
                });
            }

            @Override
            public void loginFailed(final String errorMsg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserLoginView.dismissDialog();
                        iUserLoginView.LoginFailed(errorMsg);
                    }
                });
            }
        });
    }

    public void clear(){
        iUserLoginView.clearUserName();
        iUserLoginView.clearUserPassword();
    }
}
