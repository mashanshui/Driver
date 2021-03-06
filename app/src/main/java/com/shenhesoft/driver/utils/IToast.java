package com.shenhesoft.driver.utils;

import android.widget.Toast;

import com.shenhesoft.driver.application.MyApplication;


/**
 * 作者：Tornado
 * 创作日期：2017/8/9.
 * 描述：
 */

public class IToast {

//    public static void showShort(String msg) {
//        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
//    }
//
//    public static void showLong(String msg) {
//        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
//    }
    private static Toast toast;

    public static void showShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void showLong(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
