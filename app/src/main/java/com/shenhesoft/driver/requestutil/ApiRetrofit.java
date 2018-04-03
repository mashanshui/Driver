package com.shenhesoft.driver.requestutil;


import android.util.Log;

import com.shenhesoft.driver.AppConstant;
import com.shenhesoft.driver.application.MyApplication;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.cache.SharedPref;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张继淮
 * @date 2017/11/27
 * @description Api管理
 */

public class ApiRetrofit {

    private String TAG = "requestParams:";

    private static ApiRetrofit mInstance;

    private ApiRetrofit() {
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                mInstance = new ApiRetrofit();
            }
        }
        return mInstance;
    }

    /**
     * 登陆
     *
     * @param name
     * @param password
     * @return
     */
    public Map<String, Object> login(String name, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", name);
        params.put("password", password);
        ShowLog(params);
        return params;
    }

    /**
     * 定位上传
     *
     * @param lat 纬度
     * @param lon 精度
     * @return
     */
    public Map<String, Object> updateLocation(String lat, String lon, String position, String remark) {
        Map<String, Object> params = new HashMap<>();
//        {carId: "AKJK547货位", lon: "116.398258", lat: "39.9146", position: "北京西站", remark: "备注"}
        params.put("updateUserId", getUserID());
        params.put("carId", getUserID());
        params.put("lon", lon);
        params.put("lat", lat);
        params.put("position", position);
        params.put("remark", remark);
        ShowLog(params);
        return params;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Map<String, Object> getUserinfo() {
        Map<String, Object> params = new HashMap<>();
        params.put("informationId", getinformationID());
        ShowLog(params);
        return params;
    }

    /**
     * 获取验证码
     *
     * @param phoneNum
     * @return
     */
    public Map<String, Object> getCode(String phoneNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("phoneNum", phoneNum);
        ShowLog(params);
        return params;
    }

    /**
     * 保存新手机号
     *
     * @param checkedCode      验证码
     * @param oldPhoneNum      原手机号码
     * @param newPasswordAgain 新号码
     * @return
     */
    public Map<String, Object> saveNewPhone(String checkedCode, String oldPhoneNum, String newPasswordAgain) {
        Map<String, Object> params = new HashMap<>();
//        params.put("userId", getUserID());
        params.put("checkedCode", checkedCode);
        params.put("oldPhoneNum", oldPhoneNum);
        params.put("newPhoneNum", newPasswordAgain);
        ShowLog(params);
        return params;
    }

    /**
     * 保存新密码
     * userId 用户id
     * oldPassword  原密码
     * newPassword 新密码
     * newPasswordAgain 新重复密码
     *
//     * @param phoneNum         手机号码
//     * @param checkedCode      验证码
     * @param newPassword      新密码
     * @param newPasswordAgain 新重复密码
     * @return
     */
    public Map<String, Object> saveNewPassWord(String oldPassword, String newPassword, String newPasswordAgain) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("oldPassword", oldPassword);
        params.put("newPassword", newPassword);
        params.put("newPasswordAgain", newPasswordAgain);
        ShowLog(params);
        return params;
    }


    /**
     * 修改头像
     *
     * @param img
     * @return
     */
    public Map<String, Object> updateHeader(String img) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("img", img);
        ShowLog(params);
        return params;
    }


    /**
     * 汽车短驳 提交等待发运 发运信息Map参数
     *
     * @return
     */
    public Map<String, Object> submitMotorFayxxParams(String orderId, double pz, double mz,double jz, double jz1
            , double jz2, String huayzb, String img, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("orderId", orderId);
        params.put("sendTare", pz);
        params.put("sendGross", mz);
        if (type.equals("0")) {
            params.put("containerOneSendNet", jz1);
            params.put("containerTwoSendNet", jz2);
        } else {
            params.put("containerOneSendNet", jz);
//            params.put("containerTwoSendNet", 0);
        }
        params.put("testIndicators", huayzb);
        params.put("img", img);
        ShowLog(params);
        return params;
    }


    /**
     * 汽车短驳 保存到货通知信息 发运信息Map参数
     *
     * @return
     */
    public Map<String, Object> submitMotorArrivexxParams(String orderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("orderId", orderId);
        ShowLog(params);
        return params;
    }


    /**
     * 通过运单状态获取运单信息
     *
     * @return
     */
    public Map<String, Object> getOrderStatusParams(String pageNum, String orderStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("pageNum", pageNum);
        params.put("orderStatus", orderStatus);
        ShowLog(params);
        return params;
    }

    /**
     * 获取司机接受运单的当前状态
     *
     * @return
     */
    public Map<String, Object> getCurrentStatusParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        ShowLog(params);
        return params;
    }

    /**
     * 汽车短驳 提交等待发运 发运信息Map参数
     *
     * @return
     */
    public Map<String, Object> submitMotorArrivexxParams(String orderId, double pz, double mz, double jz, double jz1
            , double jz2, String huayzb, String img, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("orderId", orderId);
        params.put("receiptTare", pz);
        params.put("receiptGross", mz);
        if (type.equals("0")) {
            params.put("containerOneReceiptNet", jz1);
            params.put("containerTwoReceiptNet", jz2);
        } else {
            params.put("containerOneReceiptNet", jz);
//            params.put("containerTwoSendNet", 0);
        }
        params.put("testIndicators", huayzb);
        params.put("img", img);
        ShowLog(params);
        return params;
    }


    /**
     * 汽车短驳 保存到货通知信息 发运信息Map参数
     *
     * @return
     */
    public Map<String, Object> getMotorListParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        ShowLog(params);
        return params;
    }


    /**
     * 汽车短驳 申请接单 发运信息Map参数
     *
     * @return
     */
    public Map<String, Object> getsendRequestParams(String pro_dist_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("projectDistributionId", pro_dist_id);
        ShowLog(params);
        return params;
    }


    /**
     * 汽车短驳 申请接单 发运信息Map参数
     * userId 用户id
     * orderId 运单id
     * cancelReason 取消原因
     * cancelReasonDetail 取消详细原因
     *
     * @return
     */
    public Map<String, Object> sendCancleParams(String orderId, String cancelReason, String cancelReasonDetail) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserID());
        params.put("orderId", orderId);
        params.put("cancelReason", cancelReason);
        params.put("cancelReasonDetail", cancelReasonDetail);
        ShowLog(params);
        return params;
    }


    /**
     * 获取用户ID
     *
     * @return
     */
    public static String getUserID() {
        return SharedPref.getInstance(MyApplication.getContext()).getString(AppConstant.Userinfo, "");
    }

    /**
     * 获取用户信息ID
     *
     * @return
     */
    public static String getinformationID() {
        return AppUtil.getUserinfo().getInformationId() + "";
    }

    private void ShowLog(Map<String, Object> params) {
        Log.e(TAG, params.toString());
    }
}
