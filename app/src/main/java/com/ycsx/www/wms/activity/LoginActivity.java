package com.ycsx.www.wms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.presenter.UserLoginPresenter;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.view.IUserLoginView;

public class LoginActivity extends BaseActivity implements IUserLoginView {
    private EditText userName, userPassword;
    private Button login, clear;
    private CheckBox remember_pwd, automatic_login;
    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_login);
        initView();
        initCheckBox();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.clear();
            }
        });
    }

    public void initCheckBox() {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        remember_pwd.setChecked(pref.getBoolean("remember_pwd", false));
//        automatic_login.setChecked(pref.getBoolean("automatic_login", false));
        if (remember_pwd.isChecked()) {
            userName.setText(pref.getString("username", ""));
            userPassword.setText(pref.getString("userpwd", ""));
        }
//        自动登陆
//        if (automatic_login.isChecked()) {
//            userLoginPresenter.login();
//        }
    }

    public void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        remember_pwd = (CheckBox) findViewById(R.id.remember_pwd);
//        automatic_login = (CheckBox) findViewById(R.id.automatic_login);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
    }

    @Override
    public UserInfo getUserInfo() {
        return new UserInfo(userName.getText().toString(), userPassword.getText().toString());
    }

    @Override
    public void clearUserName() {
        userName.setText("");
    }

    @Override
    public void clearUserPassword() {
        userPassword.setText("");
    }

    @Override
    public void LoginSuccess(UserInfo user) {
        SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
        editor.putString("username", user.getUserName());
        editor.putString("userpwd", user.getUserPassword());
        editor.putString("flag", user.getFlag());
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("superior", user.getSuperior());
        editor.putString("status", user.getStatus());
        if (remember_pwd.isChecked()) {
            editor.putBoolean("remember_pwd", true);
        } else {
            editor.putBoolean("remember_pwd", false);
        }
//        if (automatic_login.isChecked()) {
//            editor.putBoolean("automatic_login", true);
//        } else {
//            editor.putBoolean("automatic_login", false);
//        }
        editor.commit();
        Toast.makeText(LoginActivity.this, user.getUserName() + "登陆成功！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MajorActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginFailed(String errorMsg) {
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
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
