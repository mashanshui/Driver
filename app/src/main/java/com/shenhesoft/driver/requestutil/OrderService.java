package com.shenhesoft.driver.requestutil;


import com.shenhesoft.driver.bean.CurrentStatusBean;
import com.shenhesoft.driver.bean.MyOrderBean;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者：Tornado
 * 创作日期：2017/12/22.
 * 描述：业务相关的请求接口
 */

public interface OrderService {


//    /**
//     * 火运-承运车信息提交
//     *
//     * @param params
//     * @return
//     */
//    @POST("trainOrder/saveTbTrainOrderAdmitCarNum.do")
//    Observable<RequestResults> saveTbTrainorderCarNum(@Body Map<String, Object> params);


    /**
     * 添加汽车短驳》发运信息
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/saveDriverTbOrderForwardInfoApp.do")
    Observable<RequestResults> addMotorFayxxForm(@Body Map<String, Object> params);

    /**
     * 发货上传/到货通知/卸货上传信息获取
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/viewDriverTbOrderByStatusApp.do")
    Observable<RequestResultsList<TaskBean>> getMotorinfo(@Body Map<String, Object> params);

    /**
     * 通过司机id获取当前接受的运单的状态
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverLogin/viewStatusByDriverId.do")
    Observable<RequestResults<CurrentStatusBean>> getCurrentMotorinfo(@Body Map<String, Object> params);

    /**
     * 待对账/待结算/已结算信息获取，和发货上传/到货通知/卸货上传使用相同的接口，但接受返回值返回的bean用的不一样
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/viewDriverTbOrderByStatusApp.do")
    Observable<RequestResultsList<MyOrderBean>> getOrderinfo(@Body Map<String, Object> params);

    /**
     * 添加汽车短驳》到货信息
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/saveDriverTbOrderReceipterApp.do")
    Observable<RequestResults> addMotorArriveForm(@Body Map<String, Object> params);


    /**
     * 添加汽车短驳》到货通知
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/saveDirverTbOrderArriveApp.do")
    Observable<RequestResults> saveMotorArriveForm(@Body Map<String, Object> params);


    /**
     * 任务列表
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/listWaitReceiveTbProjectByUserIdApp.do")
    Observable<RequestResultsList<TaskBean>> getTasklistForm(@Body Map<String, Object> params);


    /**
     * 任务执行中
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/listRunningTbOrderByUserIdApp.do")
    Observable<RequestResultsList<TaskBean>> getTaskloadinglist(@Body Map<String, Object> params);


    /**
     * 任务列表-已取消
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/listIsCancelTbOrderByUserIdApp.do")
    Observable<RequestResultsList<TaskBean>> getTaskCanclelist(@Body Map<String, Object> params);

    /**
     * 任务列表-申请接单
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/applyReceiveTbOrderDriver.do")
    Observable<RequestResults> sendRequestOrder(@Body Map<String, Object> params);


    /**
     * 任务列表-申请接单
     *
     * @param params 相关参数
     * @return
     */
    @POST("driverOrder/saveCancelReasonTbOrderApp.do")
    Observable<RequestResults> CancleOrder(@Body Map<String, Object> params);

}

