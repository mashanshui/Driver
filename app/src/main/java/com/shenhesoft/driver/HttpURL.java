package com.shenhesoft.driver;

/**
 * 作者：Tornado
 * 创作日期：2017/7/13.
 * 描述：APP网络请求 接口
 */

public class HttpURL {
    //测试IP 石吕飞
//    public static final String IP = "http://192.168.2.105:8088/logistics-carmanage-web";
//    public static final String IP = "http://192.168.2.182:8088/logistics-carmanage-web";
//    //测试IP
    public static final String IP = "http://139.196.100.149:8081/logistics-carmanage-web";
//    public static final String IP = "http://139.196.100.149:8081/logistics-display";

    //服务器
    private static final String SERVER = "/app/";

    //请求的base URL
    public static final String POST_URL = IP + SERVER;

    //登陆
    public static final String LOGIN = "driverLogin/driverTbLoginUserLogin.do";

}
