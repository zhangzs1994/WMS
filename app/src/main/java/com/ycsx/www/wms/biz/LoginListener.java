package com.ycsx.www.wms.biz;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public interface LoginListener {
    void loginSuccess(UserInfo user);
    void loginFailed(String errorMsg);
}
