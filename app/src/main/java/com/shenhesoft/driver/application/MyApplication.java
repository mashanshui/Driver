package com.shenhesoft.driver.application;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.Event;
import com.shenhesoft.driver.utils.EventBusUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * @author 张继淮
 * @date 2017/9/27
 * @description MyApplication
 */

public class MyApplication extends MultiDexApplication {
    private static final String TAG = "MyApplication";
    public static final boolean IS_DEBUG = true;
    public static final String BUGLY_APPID = "fbc78fc3a9";
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        context = this;
//        //注册Im
        initRongCloud();
        //注册网络请求
        //debug下检测漏洞
//        if (IS_DEBUG) {
//            LeakCanary.install(this);
//        } else {
//            //Bugly
//            CrashReport.initCrashReport(getApplicationContext(), BUGLY_APPID, false);
//        }
        initUmengPush();
    }

    private void initUmengPush() {
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "c8f03d9333fa9c0d3f2bee7235b8b481");
        MiPushRegistar.register(context, "2882303761517763271", "5401776392271");
        HuaWeiRegister.register(context);
        PushAgent mPushAgent = PushAgent.getInstance(context);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                Log.e(TAG, "onSuccess: ========================="+deviceToken );
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG, "onFailure: -------------------------" +s+s1);
            }
        });

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {

            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            @Override
            public Notification getNotification(Context context, UMessage msg) {
                for (Map.Entry entry : msg.extra.entrySet()) {
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    Log.e(TAG, "getNotification: "+key+value );
                    if ("status".equals(key)) {
                        EventBusUtils.sendStickyEvent(new Event(EventBusUtils.EventCode.A, value));
                    }
                }
                return super.getNotification(context, msg);
            }
        };

        mPushAgent.setMessageHandler(messageHandler);
    }


    private void initRongCloud() {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
//            if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext())))
//                RongIM.setOnReceiveMessageListener((message, i) -> {
//                    return false;
//                });
        }

    }
//        try {
//            RongIMClient.registerMessageType(DeleteContactMessage.class);
//        } catch (AnnotationNotFoundException e) {
//            e.printStackTrace();
//        }


    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    @Nullable
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
