package com.shenhesoft.driver.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.UserinfoBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.IToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * @author 张继淮
 * @date 2017/12/4
 * @desc 忘记密码
 */

public class ForgotPasswordActivity extends BaseActivity {
    /**
     * 手机号
     */
    @BindView(R.id.et_phone)
    EditText etPhone;

    /**
     * 新密码
     */
    @BindView(R.id.et_new_pwd)
    EditText etPassWord;


    /**
     * 确认新密码
     */
    @BindView(R.id.et_check_pwd)
    EditText etCheckPassWord;


    @Override
    protected void initTitle() {
        setTitle("忘记密码");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


    @OnClick({R.id.btn_sumit})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btn_getcode:
                getCode(etPhone.getText().toString());
                break;
            case R.id.btn_sumit:
                submit(etPhone.getText().toString(),
                        etPassWord.getText().toString(), etCheckPassWord.getText().toString());
                break;
            default:
                break;
        }

    }


    /***
     * 获取验证码
     */
    private void getCode(String phoneNumber) {
        Observable<RequestResults> observable = HttpManager.getInstance().getUserService()
                .getCode(ApiRetrofit.getInstance().getCode(phoneNumber));

        HttpObserver<RequestResults<UserinfoBean>> observer = new HttpObserver<>(this,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("验证码发送成功");

                });

        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    /***
     * 重置密码
     */
    private void submit(String oldPassword, String newPassword, String newPasswordAgain) {
        Observable<RequestResults> observable = HttpManager.getInstance().getUserService()
                .saveNewPassWord(ApiRetrofit.getInstance().saveNewPassWord(oldPassword, newPassword, newPasswordAgain));

        HttpObserver<RequestResults<RequestResults>> observer = new HttpObserver<>(this,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }

                    IToast.showShort("密码重置成功");
                    finish();
                });

        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_password;
    }


}
