package com.ycsx.www.wms.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ycsx.www.wms.R;
import com.ycsx.www.wms.base.BaseActivity;
import com.ycsx.www.wms.bean.UserInfo;
import com.ycsx.www.wms.presenter.PersonInfoPresenter;
import com.ycsx.www.wms.util.LoadingDialog;
import com.ycsx.www.wms.view.IPsersonInfoView;

public class UserInfoUpdateActivity extends BaseActivity implements IPsersonInfoView {
    private EditText userName, userAge, userPhone, userEmail;
    private RadioGroup userSex;
    private RadioButton sexBoy, sexGirl;
    private Button submit;
    private PersonInfoPresenter personInfoPresenter = new PersonInfoPresenter(this);
    private LoadingDialog dialog;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_person_info_up);
        initView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfoPresenter.submit();
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void initView() {
        userName = (EditText) findViewById(R.id.userName);
        userAge = (EditText) findViewById(R.id.userAge);
        userPhone = (EditText) findViewById(R.id.userPhone);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userSex = (RadioGroup) findViewById(R.id.userSex);
        sexBoy = (RadioButton) findViewById(R.id.sexBoy);
        sexGirl = (RadioButton) findViewById(R.id.sexGirl);
        userName.setText(getIntent().getStringExtra("userName"));
        userAge.setText(getIntent().getStringExtra("userAge"));
        userPhone.setText(getIntent().getStringExtra("userPhone"));
        userEmail.setText(getIntent().getStringExtra("userEmail"));
        if (getIntent().getStringExtra("userSex").equals("女")) {
            sexBoy.setChecked(false);
            sexGirl.setChecked(true);
        } else {
            sexBoy.setChecked(true);
            sexGirl.setChecked(false);
        }
        submit = (Button) findViewById(R.id.submit);
        dialog = new LoadingDialog(this, R.style.CustomDialog);
    }

    @Override
    public String getUserSex() {
        if (R.id.sexBoy == userSex.getCheckedRadioButtonId()) {
            return "1";//男
        } else {
            return "2";//女
        }
    }

    @Override
    public UserInfo getUserInfo() {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        String status = pref.getInt("status", 0)+"";
        if (status.equals("在职")) {
            status = "1";
        } else {
            status = "2";
        }
        return new UserInfo(pref.getString("username", ""), pref.getString("userpwd", ""),
                pref.getInt("flag", 0), userName.getText().toString(), userAge.getText().toString(),
                userPhone.getText().toString(), userEmail.getText().toString(), getUserSex(),
                Integer.parseInt(status), pref.getInt("id", 0), pref.getString("superior", ""),
                pref.getString("subordinate",""));
    }

    @Override
    public void SubmitSuccess(String message) {
        Toast.makeText(UserInfoUpdateActivity.this, message, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, UserInfoActivity.class);
//        startActivity(intent);
        finish();
    }

    @Override
    public void SubmitFailed(String errorMsg) {
        Toast.makeText(UserInfoUpdateActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
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
