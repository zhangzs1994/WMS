package com.ycsx.www.wms.biz;

import com.ycsx.www.wms.bean.UserInfo;

/**
 * Created by ZZS_PC on 2017/5/27.
 */
public interface IUserInfoSub {
    void submit(UserInfo userInfo, SubmitListener submitListener);
}
