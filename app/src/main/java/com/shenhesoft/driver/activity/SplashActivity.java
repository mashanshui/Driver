package com.shenhesoft.driver.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.shenhesoft.driver.AppConstant;
import com.shenhesoft.driver.Constants;
import com.shenhesoft.driver.MainActivity;
import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.UserinfoBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.IToast;
import com.shenhesoft.driver.utils.cache.SharedPref;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "SplashActivity";
    private int CALL_PHONE_REQUEST_CODE = 1001;
    private String userName;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                String value = bun.getString(key);
                Log.e(TAG, "onCreate: "+key+value );
            }

        }
        initView();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initView() {
        checkPermission();
    }

    /***
     * 检查权限
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //定位的权限
            String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION};
            if (EasyPermissions.hasPermissions(SplashActivity.this, mPermissionList)) {
                startLogin();
            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(this, "需要定位权限", CALL_PHONE_REQUEST_CODE, mPermissionList);
            }

        } else {
            //6.0以下，不需要授权
            startLogin();
        }

    }

    private void startLogin() {
        userName = SharedPref.getInstance(SplashActivity.this).getString(AppConstant.UserName, "");
        userPassword = SharedPref.getInstance(SplashActivity.this).getString(AppConstant.UserPassword, "");
        if (!TextUtils.isEmpty(userName) || !TextUtils.isEmpty(userPassword)) {
            submit(userName, userPassword);
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }


    /***
     * 提交请求-登陆
     */
    private void submit(String username, String password) {
        Observable<RequestResults<UserinfoBean>> observable = HttpManager.getInstance().getUserService()
                .userLogin(ApiRetrofit.getInstance().login(username, password));

        HttpObserver<RequestResults<UserinfoBean>> observer = new HttpObserver<>(this,

                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                    Log.e("userId", "submit: " + String.valueOf(data.getObj().getId()));
                    SharedPref.getInstance(SplashActivity.this).putString(AppConstant.Userinfo, String.valueOf(data.getObj().getId()));
                    SharedPref.getInstance(SplashActivity.this).putString(AppConstant.rongCloudToken, String.valueOf(data.getObj().getRongCloudToken()));

                    //设置用户别名(用于推送)
                    PushAgent mPushAgent = PushAgent.getInstance(SplashActivity.this);
                    mPushAgent.addAlias(String.valueOf(data.getObj().getId()), Constants.UMENG_PUSH_ALIAS_TYPE, (isSuccess, message) -> {
                        Log.e(TAG, "submit: "+data.getObj().getId()+isSuccess+message );
                    });

                    AppUtil.setUserinfo(data.getObj());

                    //                    postEvent(new BaseEvent(AppConstant.LOGIN, data.getObj()));
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                },
                throwable -> {
                    IToast.showShort("登陆失败");
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                });

        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startLogin();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        startLogin();
    }
}
