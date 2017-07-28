package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.presenter.UserInfoPresenter;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.view.IUserLoginView;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener,IUserLoginView{
    private Intent intent;
    private TextView update_userInfo,userName,userSex,userAge,userPhone,userEmail,userSuperior;
    private UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_person_info);
        initView();
    }

    @Override
    protected void onStart() {
        userInfoPresenter.getUserInfo();
        super.onStart();
    }

    private void initView() {
        update_userInfo= (TextView) findViewById(R.id.update_userInfo);
        userName= (TextView) findViewById(R.id.userName);
        userSex= (TextView) findViewById(R.id.userSex);
        userAge= (TextView) findViewById(R.id.userAge);
        userPhone= (TextView) findViewById(R.id.userPhone);
        userEmail= (TextView) findViewById(R.id.userEmail);
        userSuperior= (TextView) findViewById(R.id.userSuperior);
        update_userInfo.setOnClickListener(this);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_userInfo:
                intent = new Intent(this, UserInfoUpdateActivity.class);
                intent.putExtra("userName",userName.getText().toString());
                intent.putExtra("userSex",userSex.getText().toString());
                intent.putExtra("userAge",userAge.getText().toString());
                intent.putExtra("userPhone",userPhone.getText().toString());
                intent.putExtra("userEmail",userEmail.getText().toString());
                startActivity(intent);
                break;
        }
    }

    public void back(View view){
        finish();
    }

    @Override
    public UserInfo getUserInfo() {
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
        return new UserInfo(pref.getString("username",""),pref.getString("userpwd",""));
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearUserPassword() {

    }

    @Override
    public void LoginSuccess(UserInfo user) {
        userName.setText(user.getName());
        userSex.setText(user.getSex());
        userAge.setText(user.getAge()+"");
        userPhone.setText(user.getPhone());
        userEmail.setText(user.getEmail());
        userSuperior.setText(user.getSuperior());
    }

    @Override
    public void LoginFailed(String errorMsg) {
        Toast.makeText(UserInfoActivity.this, "获取个人信息失败！", Toast.LENGTH_SHORT).show();
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
