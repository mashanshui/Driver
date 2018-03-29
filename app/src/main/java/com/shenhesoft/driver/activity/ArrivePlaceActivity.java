package com.shenhesoft.driver.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.IToast;

import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;


/**
 * 抵达卸货地点
 */
public class ArrivePlaceActivity extends BaseActivity {

    private String orderid;//订单id

    @BindView(R.id.arrive_et_quhd)
    TextView etQuhd; //取货地

    @BindView(R.id.arrive_et_quhdz)
    TextView etQuhdz; //取货地址

    @BindView(R.id.arrive_et_yundd)
    TextView etYundd; //运抵地

    @BindView(R.id.arrive_et_yunddz)
    TextView etYunddz; //运抵地址

    private boolean isnodata;
    private int pageNo = 1;

    @Override
    protected void initTitle() {
        setTitle("抵达卸货地点");
        setRightText("确认", v -> {
            commintData();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_arrive_place;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        TaskBean bean = AppUtil.getMyTask();
//        if (bean == null) {
//            IToast.showShort("您还没有接单哦");
//            finish();
//            return;
//        }
//
//        orderid = bean.getOrderId();
//        isnodata = bean.isNodata();
//        if (TextUtils.isEmpty(orderid)) {
//            IToast.showShort("当前订单号为空");
//            finish();
//        }
//
//        if (!isnodata) {
//            etQuhd.setText(bean.getTakeCity());
//            etQuhdz.setText(bean.getPickupPlaceAddress());
//            etYundd.setText(bean.getArriCity());
//            etYunddz.setText(bean.getArriveAddress());
//        } else {
//            getOrderStatus();
//        }
        getOrderStatus();
    }

    @Override
    protected void initView() {

    }

    /**
     * 提交数据 请求接口
     */

    private void commintData() {

        Map<String, Object> params = ApiRetrofit.getInstance().submitMotorArrivexxParams(orderid);

        Observable<RequestResults> observable = HttpManager.getInstance()
                .getOrderService().saveMotorArriveForm(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("保存到货通知信息成功！");
                    finish();

                });
        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    /**
     * 获取数据接口
     */

    private void getOrderStatus() {

        Map<String, Object> params = ApiRetrofit.getInstance().getOrderStatusParams(pageNo + "", "3");

        Observable<RequestResultsList<TaskBean>> observable = HttpManager.getInstance()
                .getOrderService().getMotorinfo(params);

        HttpObserver<RequestResultsList<TaskBean>> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
//                    if (isnodata) {
//                        AppUtil.setMyTask(data.getObj());
//                    }
                    TaskBean taskBean = data.getObj().get(0);
                    //修改到货通知为三级地址，例：（新疆,巴音郭楞,和静县）第三个地址
                    String address = taskBean.getTakeCity();
                    String[] temp = address.split(",");
                    etQuhd.setText(temp[temp.length - 1]);
                    etQuhdz.setText(taskBean.getPickupPlaceAddress());
                    etYundd.setText(taskBean.getArriCity());
                    etYunddz.setText(taskBean.getArriveAddress());
                    orderid = taskBean.getOrderId();
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

}
