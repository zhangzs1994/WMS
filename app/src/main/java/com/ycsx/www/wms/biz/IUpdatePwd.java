package com.ycsx.www.wms.biz;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/27.
 */
public interface IUpdatePwd {
    void submit(UserInfo userInfo, String newPwd, String submitPwd, SubmitListener submitListener);
}
