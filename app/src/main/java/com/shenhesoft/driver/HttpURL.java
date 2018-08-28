package com.shenhesoft.driver;

/**
 * 作者：Tornado
 * 创作日期：2017/7/13.
 * 描述：APP网络请求 接口
 */

public class HttpURL {
    public static final String MIP = "http://139.196.100.149:8081";
    //    public static final String MIP = "http://192.168.2.230:8088";
//    //外网IP
    public static final String IP = "http://139.196.100.149:8081/logistics-carmanage-web";
//    public static final String IP = "http://192.168.1.117:7012/logistics-carmanage-web";

    //服务器
    private static final String SERVER = "/app/";

    //请求的base URL
    public static final String POST_URL = IP + SERVER;

    //登陆
    public static final String LOGIN = "driverLogin/driverTbLoginUserLogin.do";

}
