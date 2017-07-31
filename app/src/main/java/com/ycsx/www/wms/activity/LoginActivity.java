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
import com.ycsx.www.wms.bean.InitInfo;
import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.common.API;
import com.ycsx.www.wms.presenter.UserLoginPresenter;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.util.RetrofitUtil;
import com.ycsx.www.wms.util.UpdateAppUtils;
import com.ycsx.www.wms.view.IUserLoginView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements IUserLoginView {
    private EditText userName, userPassword;
    private Button login, clear;
    private CheckBox remember_pwd;
    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);
    private LoadingDialog dialog;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_login);
        initData();
        UpdateAppUtils.UpdateApp(this, API.Version_no, API.versionName, API.versionInfo,
              API.downLoadUrl, API.forced == 1 ? true : false, false);
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initCheckBox() {
        remember_pwd.setChecked(pref.getBoolean("remember_pwd", false));
        if (remember_pwd.isChecked()) {
            userName.setText(pref.getString("username", ""));
            userName.setSelection(userName.getText().length());
            userPassword.setText(pref.getString("userpwd", ""));
        }
    }

    public void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        remember_pwd = (CheckBox) findViewById(R.id.remember_pwd);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        editor = pref.edit();
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
        editor.putString("username", user.getUserName());
        editor.putString("userpwd", user.getUserPassword());
        editor.putInt("flag", user.getFlag());
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("superior", user.getSuperior());
        editor.putInt("status", user.getStatus());
        editor.putString("subordinate", user.getSubordinate());
        editor.putString("flagValue", user.getFlagValue());
        editor.putString("menuNode", user.getMenuNode());
        if (remember_pwd.isChecked()) {
            editor.putBoolean("remember_pwd", true);
        } else {
            editor.putBoolean("remember_pwd", false);
        }
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

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("authorizationCode", API.authorizationCode);
        Call<InitInfo> call = RetrofitUtil.getInstance(API.URL).initInfo(params);
        call.enqueue(new Callback<InitInfo>() {
            @Override
            public void onResponse(Call<InitInfo> call, Response<InitInfo> response) {
                if (response.isSuccessful()) {
                    InitInfo info = response.body();
                    if (("10200").equals(info.getStatus())) {
                        for (int i = 0; i < info.getData().size(); i++) {
                            API.image = convertStrToArray(info.getData().get(i).getAppAdvert() + "");
                            if (!info.getData().get(i).getAppAdvert().equals("")) {
                                API.images = convertStrToArray1(info.getData().get(i).getAppAdvert() + "");
                            } else {
                                API.images = new ArrayList();
                                API.images.add("暂无图片！");
                            }
                            API.Version_no = info.getData().get(i).getVersion();
                            API.downLoadUrl = info.getData().get(i).getDownLoadUrl();
                            API.versionName = info.getData().get(i).getVersionName();
                            API.versionInfo = info.getData().get(i).getVersionInfo();
                            API.forced = info.getData().get(i).getForced();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "获取初始化信息失败1！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "获取初始化信息失败2！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InitInfo> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "获取初始化信息失败3！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String[] convertStrToArray(String str) {
        //Log.e("image", "" + strArray.length);
        if (!str.contains(",")) {
            API.image = new String[1];
            API.image[0] = str;
        } else {
            API.image = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return API.image;
    }

    public List convertStrToArray1(String str) {
        //Log.e("image", "" + strArray.length);
        if (!str.contains(",")) {
            API.images = new ArrayList();
            API.images.add(str);
        } else {
            API.images = Arrays.asList(str.split(",")); //拆分字符为"," ,然后把结果交给数组strArray
        }
        return API.images;
    }

}
