package com.ycsx.www.wms.view;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public interface IUpdatePwdView {
    UserInfo getUserInfo();
    String getNewPwd();
    String getSubmitPwd();
    void SubmitSuccess(String message);
    void SubmitFailed(String errorMsg);
    void showDialog();
    void dismissDialog();
}
