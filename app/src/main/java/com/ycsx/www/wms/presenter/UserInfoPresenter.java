package com.ycsx.www.wms.presenter;

import android.os.Handler;

import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.biz.IUserInfo;
import com.ycsx.www.wms.biz.LoginListener;
import com.ycsx.www.wms.view.IUserLoginView;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UserInfoPresenter {
    private IUserInfo userInfo;
    private IUserLoginView iUserLoginView;
    private Handler handler=new Handler();

    public UserInfoPresenter(IUserLoginView iUserLoginView) {
        this.userInfo = new com.ycsx.www.wms.biz.UserInfo();
        this.iUserLoginView = iUserLoginView;
    }

    public void getUserInfo(){
        iUserLoginView.showDialog();
        userInfo.getUserInfo(iUserLoginView.getUserInfo(), new LoginListener() {
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
