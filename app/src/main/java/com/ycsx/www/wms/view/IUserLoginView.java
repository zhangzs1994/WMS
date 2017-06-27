package com.ycsx.www.wms.view;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public interface IUserLoginView {
    UserInfo getUserInfo();
    void clearUserName();
    void clearUserPassword();
    void LoginSuccess(UserInfo user);
    void LoginFailed(String errorMsg);
    void showDialog();
    void dismissDialog();
}
