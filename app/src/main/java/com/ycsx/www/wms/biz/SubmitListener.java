package com.ycsx.www.wms.biz;

/**
 * Created by ZZS_PC on 2017/5/27.
 */
public interface SubmitListener {
    void submitSuccess(String message);
    void submitFailed(String message);
}
