package com.shenhesoft.driver.requestutil;


import com.shenhesoft.driver.HttpURL;
import com.shenhesoft.driver.bean.PersoninfoBean;
import com.shenhesoft.driver.bean.UserinfoBean;
import com.shenhesoft.driver.requestutil.entity.RequestResults;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者：张明星
 * 创作日期：2017/10/27
 * 描述：用户相关的请求接口
 */

public interface UserService {


    /**
     * 定位上传
     *
     * @return
     */
    @POST("http://139.196.100.149:8081/logistics-manage-web/api/mapPoint")
    Observable<RequestResults> updatelocation(@Body Map<String, Object> params);

    /**
     * 用户登录
     *
     * @return
     */
    @POST(HttpURL.LOGIN)
    Observable<RequestResults<UserinfoBean>> userLogin(@Body Map<String, Object> params);

    /**
     * 获取验证码
     *
     * @return
     */
    @POST("driverLogin/viewVerificationByregisterPhoneApp.do")
    Observable<RequestResults> getCode(@Body Map<String, Object> params);


    /**
     * 保存新手机号
     *
     * @return
     */
    @POST("driverLogin/updateTbLoginUserPhoneApp.do")
    Observable<RequestResults> saveNewPhone(@Body Map<String, Object> params);


    /**
     * 保存新密码
     *
     * @return
     */
    @POST("driverLogin/updateTbLoginUserPasswordApp.do")
    Observable<RequestResults> saveNewPassWord(@Body Map<String, Object> params);


    /**
     * 获取个人信息
     *
     * @return
     */
    @POST("driverLogin/viewTbUserInformationApp.do")
    Observable<RequestResults<PersoninfoBean>> getuserinfo(@Body Map<String, Object> params);


    /**
     * 修改头像
     *
     * @return
     */
    @POST("driverLogin/changeTbSystmUserIconApp.do")
    Observable<RequestResults> updataDriverheader(@Body Map<String, Object> params);


    /**
     * 车主信息
     *
     * @return
     */
    @POST("driverLogin/viewTbUserInformationApp.do")
    Observable<RequestResults<UserinfoBean>> getDriverinfo(@Body Map<String, Object> params);


//    /**
//     * 发送验证码
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(HttpURL.SMS_SEND)
//    Observable<CommonResult> sendsms(@Field("data") String data);
//
//    /**
//     * 验证验证码
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(HttpURL.CHECK_SMS_CODE)
//    Observable<CommonResult> checkSms(@Field("data") String data);
//

}
