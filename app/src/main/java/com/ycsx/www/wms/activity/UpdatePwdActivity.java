package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.presenter.UpdatePwdPresenter;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.view.IUpdatePwdView;

public class UpdatePwdActivity extends BaseActivity implements IUpdatePwdView {
    private EditText oldPwd, newPwd, submitPwd;
    private TextView userName;
    private Button submit;
    private UpdatePwdPresenter updatePwdPresenter = new UpdatePwdPresenter(this);
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_update_pwd);
        initView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePwdPresenter.submit();
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        userName = (TextView) findViewById(R.id.userName);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        userName.setText(pref.getString("username", ""));
        oldPwd = (EditText) findViewById(R.id.oldPwd);
        newPwd = (EditText) findViewById(R.id.newPwd);
        submitPwd = (EditText) findViewById(R.id.submitPwd);
        submit = (Button) findViewById(R.id.submit);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
    }

    @Override
    public UserInfo getUserInfo() {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        return new UserInfo(pref.getInt("id", 0), oldPwd.getText().toString(),pref.getString("username",""));
    }

    @Override
    public String getNewPwd() {
        return newPwd.getText().toString();
    }

    @Override
    public String getSubmitPwd() {
        return submitPwd.getText().toString();
    }

    @Override
    public void SubmitSuccess(String message) {
        Toast.makeText(UpdatePwdActivity.this, message, Toast.LENGTH_SHORT).show();
        removeAllActivity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void SubmitFailed(String errorMsg) {
        Toast.makeText(UpdatePwdActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void dismissDialog() {
        dialog.dismiss();
    }
}
