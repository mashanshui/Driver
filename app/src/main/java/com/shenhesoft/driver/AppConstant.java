package com.shenhesoft.driver;

/**
 * @author 张继淮
 * @date 2017/10/10
 * @description 全局常量
 */

public class AppConstant {
    public static final String UserName = "username";
    public static final String UserPassword = "userpassword";
    //网络请求TAG 1001起
    public static final String Userinfo = "userinfo";

    //运单的当前状态
    public static final String OrderStatus = "status";

    //网络请求TAG 1001起
    public static final String rongCloudToken = "rongCloudToken";

    public static final String API_BASE_URL = "http://192.168.2.192:8082/app/";
    //EventTag  101起
    public static final int SHOW_MENU = 101;

    //EventTag  调度驳回
    public static final int REJECT_ORDER = 102;

    //EventTag  确认到货异常
    public static final int ARRIVE_UNUSUAL = 103;

    //EventTag  101起
    public static final int LOGIN = 200;

    //网络请求TAG 1001起
    public static final int API_LOGIN = 1001;

    //网络请求Url
    public static final String URL_LOGIN = "login/systemUserDoLogin.do";
    //FragmentTag
//    public static final String TAG_FRAGMENT = "tag_fragment";
//    public static final int FRAGMENT_WAIT_DISPATCH = 10001;
//    public static final int FRAGMENT_DISMISSED = 10002;

}
