package com.shenhesoft.driver.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hanks.htextview.rainbow.RainbowTextView;
import com.shenhesoft.driver.AppConstant;
import com.shenhesoft.driver.R;
import com.shenhesoft.driver.activity.AddArrivexxActivity;
import com.shenhesoft.driver.activity.AddFayxxActivity;
import com.shenhesoft.driver.activity.ArrivePlaceActivity;
import com.shenhesoft.driver.activity.SplashActivity;
import com.shenhesoft.driver.activity.task.TaskActivity;
import com.shenhesoft.driver.base.BaseFragment;
import com.shenhesoft.driver.bean.CurrentStatusBean;
import com.shenhesoft.driver.bean.Event;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.DialogUtil;
import com.shenhesoft.driver.utils.EventBusUtils;
import com.shenhesoft.driver.utils.IToast;
import com.shenhesoft.driver.utils.cache.SharedPref;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.task_layout)
    RelativeLayout taskLayout;
    @BindView(R.id.layout_upload_sendgoods)
    RelativeLayout sendgoodsLayout;
    @BindView(R.id.layout_notice_goodsarrive)
    RelativeLayout goodsarriveLayout;
    @BindView(R.id.layout_upload_downgoods)
    RelativeLayout unloadingLayout;
    @BindView(R.id.message_remind)
    TextView mMessageRemind;
    Unbinder unbinder;

    private int receiptTare=0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void receiveStickyEvent(Event event) {
        if (event.getCode() == EventBusUtils.EventCode.A) {
//            String data = (String) event.getData();
//            updateStatus(data);
            getCurrentStatus();
        } else if (event.getCode() == EventBusUtils.EventCode.B) {
            getCurrentStatus();
        }
    }

    // case "1":
//         mMessageRemind.setText("您的申请已发送，正在审核中，请耐心等候！");
//                break;
//            case "2":
//                    mMessageRemind.setText("您的申请被取消，请重新接单或联系调度员！");
//                break;
//            case "3":
//                    mMessageRemind.setText("您已接单成功！请到xxxxxxxx取货");
//                break;
//            case "4":
//                    mMessageRemind.setText("您的运单被取消，请重新接单或联系调度员！");
//                break;
//            case "5":
//                    mMessageRemind.setText("发货信息已上传，祝您一路平安！");
//                break;
//            case "6":
//                    mMessageRemind.setText("发货信息未上传，请及时上传，状态异常！");
//                break;
//            case "7":
//                    mMessageRemind.setText("已通知物流员，请耐心等候卸货！");
//                break;
//            case "8":
//                    mMessageRemind.setText("请前往XXX货场XXX货位卸货！");
//                break;
//            case "9":
//                    mMessageRemind.setText("到货信息已上传，祝您一路平安！");
//                break;
//            case "10":
//                    mMessageRemind.setText("到货信息未上传，请及时上传，状态异常！");
//                break;
    private void updateStatus(String status) {

        switch (status) {
            case "0":
                setStatus("欢迎来到司机助手!", status);
                break;
            case "1":
                setStatus("您的申请已发送，正在审核中，请耐心等候！", status);
                break;
            case "2":
                setStatus("您已接单成功！请取货", status);
                break;
            case "3":
                setStatus("发货信息已上传，祝您一路平安！", status);
                break;
            case "4":
                setStatus("已通知物流员，请耐心等候卸货！", status);
                break;
            case "5":
                if (receiptTare == 0) {
                    setStatus("请前往卸货！", status);
                } else {
                    setStatus("欢迎来到司机助手!", status);
                }
                break;
            default:
                break;
        }
    }

    private void setStatus(String message, String status) {
        if (mMessageRemind != null) {
            mMessageRemind.setText(message);
//            saveStatus(status);
        }
    }

    private void saveStatus(String status) {
        SharedPref.getInstance(mContext).putString(AppConstant.OrderStatus, status);
    }

    private String getStatus() {
        return SharedPref.getInstance(mContext).getString(AppConstant.OrderStatus, "");
    }

    @Override
    protected void init() {
        initView();
        initData();
        initListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取更新运单状态
        getCurrentStatus();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    private void initView() {
        titleTv.setText("司机APP");
        mMessageRemind.setSelected(true);
    }

    private void initData() {
    }

    private void initListener() {
        taskLayout.setOnClickListener(this);
        sendgoodsLayout.setOnClickListener(this);
        goodsarriveLayout.setOnClickListener(this);
        unloadingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_layout:
                //任务平台
                Intent intent3 = new Intent(mContext, TaskActivity.class);
                mContext.startActivity(intent3);
                break;
            case R.id.layout_upload_sendgoods:
                //发货上传
                CheckOrder();
                getOrderStatus(1);
//                Intent intent = new Intent(mContext, AddFayxxActivity.class);
//                mContext.startActivity(intent);
                break;
            case R.id.layout_notice_goodsarrive:
                //到货通知
                CheckOrder();
                getOrderStatus(3);
//                Intent intent1 = new Intent(mContext, ArrivePlaceActivity.class);
//                mContext.startActivity(intent1);
                break;
            case R.id.layout_upload_downgoods:
                //卸货上传
                CheckOrder();
                getOrderStatus(5);
//                Intent intent2 = new Intent(mContext, AddArrivexxActivity.class);
//                mContext.startActivity(intent2);
//                RongIM.getInstance().startPrivateChat(getContext(), "Q1", "我是标题");
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否接单
     */
    private void CheckOrder() {
//        if (!TextUtils.isEmpty(AppUtil.getUserinfo().getOrderId())) {
//            if (!AppUtil.HasTask(AppUtil.getUserinfo().getOrderId())) {
//                TaskBean bean = new TaskBean();
//                bean.setOrderId(AppUtil.getUserinfo().getOrderId());
//                bean.setNodata(true);
//                AppUtil.setMyTask(bean);
//            }
//        }
    }


    private void getOrderStatus(int status) {

        Map<String, Object> params = ApiRetrofit.getInstance().getOrderStatusParams("1", String.valueOf(status));

        Observable<RequestResultsList<TaskBean>> observable = HttpManager.getInstance()
                .getOrderService().getMotorinfo(params);

        HttpObserver<RequestResultsList<TaskBean>> observer = new HttpObserver<>(mContext, DialogUtil.createLoading(getContext()),
                data -> {
                    if (data.getState() != 1) {
                        switch (status) {
                            case 1:
                                IToast.showShort("没有等待运输的货物");
                                break;
                            case 3:
                                IToast.showShort("没有运输中的货物");
                                break;
                            case 5:
                                IToast.showShort("没有卸货信息");
                                break;
                            default:
                                break;

                        }
                        return;
                    }
                    switch (status) {
                        case 1:
                            Intent intent = new Intent(mContext, AddFayxxActivity.class);
                            mContext.startActivity(intent);
                            break;
                        case 3:
                            Intent intent1 = new Intent(mContext, ArrivePlaceActivity.class);
                            mContext.startActivity(intent1);
                            break;
                        case 5:
                            TaskBean taskBean = data.getObj().get(0);
                            receiptTare = taskBean.getReceiptTare();
                            if (receiptTare == 0) {
                                Intent intent2 = new Intent(mContext, AddArrivexxActivity.class);
                                mContext.startActivity(intent2);
                            } else {
                                IToast.showShort("没有卸货信息");
                            }
                            break;
                        default:
                            break;
                    }
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

    /**
     * 从网络获取运单状态
     */
    private void getCurrentStatus() {

        Map<String, Object> params = ApiRetrofit.getInstance().getCurrentStatusParams();

        Observable<RequestResults<CurrentStatusBean>> observable = HttpManager.getInstance()
                .getOrderService().getCurrentMotorinfo(params);

        HttpObserver<RequestResults<CurrentStatusBean>> observer = new HttpObserver<>(mContext,
                data -> {
                    if (data.getState() != 1) {
                        return;
                    }
                    if (data.getObj() != null) {
                        int status = data.getObj().getStatus();
                        if (status == 5) {
                            receiptTare = data.getObj().getReceiptTare();
                        }
                        updateStatus(String.valueOf(status));
                    } else {
                        updateStatus("0");
                    }
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
