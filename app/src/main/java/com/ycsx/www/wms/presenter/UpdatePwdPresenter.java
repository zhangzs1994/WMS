package com.ycsx.www.wms.presenter;

import android.os.Handler;

import com.ycsx.www.wms.biz.IUpdatePwd;
import com.ycsx.www.wms.biz.SubmitListener;
import com.ycsx.www.wms.biz.UpdatePwd;
import com.ycsx.www.wms.view.IUpdatePwdView;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class UpdatePwdPresenter {
    private IUpdatePwd updatePwd;
    private IUpdatePwdView iUpdatePwdView;
    private Handler handler = new Handler();

    public UpdatePwdPresenter(IUpdatePwdView iUpdatePwdView) {
        this.updatePwd = new UpdatePwd();
        this.iUpdatePwdView = iUpdatePwdView;
    }

    public void submit() {
        iUpdatePwdView.showDialog();
        updatePwd.submit(iUpdatePwdView.getUserInfo(), iUpdatePwdView.getNewPwd(),
                iUpdatePwdView.getSubmitPwd(), new SubmitListener() {
                    @Override
                    public void submitSuccess(final String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iUpdatePwdView.dismissDialog();
                                iUpdatePwdView.SubmitSuccess(message);
                            }
                        });
                    }

                    @Override
                    public void submitFailed(final String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iUpdatePwdView.dismissDialog();
                                iUpdatePwdView.SubmitFailed(message);
                            }
                        });
                    }
                });
    }
}
