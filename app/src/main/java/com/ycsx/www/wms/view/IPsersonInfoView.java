package com.ycsx.www.wms.view;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public interface IPsersonInfoView {
    String getUserSex();
    UserInfo getUserInfo();
    void SubmitSuccess(String message);
    void SubmitFailed(String errorMsg);
    void showDialog();
    void dismissDialog();
}
