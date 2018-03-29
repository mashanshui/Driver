package com.shenhesoft.driver.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shenhesoft.driver.AppConstant;
import com.shenhesoft.driver.R;
import com.shenhesoft.driver.activity.LoginActivity;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.utils.cache.SharedPref;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 张继淮
 * @date 2018/1/18
 * @desc TODO
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.btn_exits)
    Button btn_exits;

    @Override
    protected void initTitle() {
        setTitle("设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_exits, R.id.tv_resetphone, R.id.tv_resetpwd})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exits:
                //保存用户输入的用户名和密码，方便下一次自动登陆
                SharedPref.getInstance(SetActivity.this).putString(AppConstant.UserName, "");
                SharedPref.getInstance(SetActivity.this).putString(AppConstant.UserPassword, "");
                startActivity(new Intent(SetActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_resetphone:
                startActivity(new Intent(SetActivity.this, ChangePhoneActivity.class));
                break;
            case R.id.tv_resetpwd:
                startActivity(new Intent(SetActivity.this, ForgotPasswordActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void initView() {

    }
}
