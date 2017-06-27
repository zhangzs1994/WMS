package com.ycsx.www.wms.presenter;

import android.os.Handler;

import com.ycsx.www.wms.biz.IUserInfoSub;
import com.ycsx.www.wms.biz.UserInfoSub;
import com.ycsx.www.wms.biz.SubmitListener;
import com.ycsx.www.wms.view.IPsersonInfoView;

/**
 * Created by ZZS_PC on 2017/5/12.
 */
public class PersonInfoPresenter {
    private IUserInfoSub userInfoSub;
    private IPsersonInfoView iPsersonInfoView;
    private Handler handler = new Handler();

    public PersonInfoPresenter(IPsersonInfoView iPsersonInfoView) {
        this.userInfoSub = new UserInfoSub();
        this.iPsersonInfoView = iPsersonInfoView;
    }

    public void submit() {
        iPsersonInfoView.showDialog();
        userInfoSub.submit(iPsersonInfoView.getUserInfo(), new SubmitListener() {
                    @Override
                    public void submitSuccess(final String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iPsersonInfoView.dismissDialog();
                                iPsersonInfoView.SubmitSuccess(message);
                            }
                        });
                    }

                    @Override
                    public void submitFailed(final String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iPsersonInfoView.dismissDialog();
                                iPsersonInfoView.SubmitFailed(message);
                            }
                        });
                    }
                });
    }
}
