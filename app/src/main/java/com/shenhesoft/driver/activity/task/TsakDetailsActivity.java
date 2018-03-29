package com.shenhesoft.driver.activity.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.IToast;


import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 作者：Tornado
 * 创作日期：2018/1/18.
 * 描述：
 */

public class TsakDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.task_details_tv_wuliu)
    TextView tvWuliu;
    @BindView(R.id.task_details_tv_sendcity)
    TextView tvSendCity;
    @BindView(R.id.task_details_tv_arriccity)
    TextView tvArricCity;

    @BindView(R.id.task_details_tvxiangmbh)
    TextView tvXiangmbh;   //项目编号
    @BindView(R.id.details_tv_xiangmlx)
    TextView tvXiangmlx;   //项目类型
    @BindView(R.id.details_tv_huowmc)
    TextView tvHuowmc;   //货物名称
    @BindView(R.id.details_tv_jiliangfs)
    TextView tvJilfs;   //计量方式
    @BindView(R.id.details_tv_fukfs)
    TextView tvFukfs;   //付款方式
    @BindView(R.id.details_tv_fahdw)
    TextView tvFahdw;   //发货单位
    @BindView(R.id.details_tv_quhd)
    TextView tvQuhd;   //取货地
    @BindView(R.id.details_tv_quhdz)
    TextView tvQuhdz;   //取货地址
    @BindView(R.id.details_tv_shouhdw)
    TextView tvShouhdw;   //收货单位
    @BindView(R.id.details_tv_yundd)
    TextView tvYundd;   //运抵地
    @BindView(R.id.details_tv_yunddz)
    TextView tvYunddz;   //运抵地址
    @BindView(R.id.details_tv_yunsfy)
    TextView tvYunsfy;   //运输费用
    @BindView(R.id.details_tv_butie)
    TextView tvBut;   //补贴
    @BindView(R.id.details_tv_kousxs)
    TextView tvKousxs;   //扣损系数
    @BindView(R.id.details_tv_koudj)
    TextView tvKousdj;   //扣损单价
    @BindView(R.id.details_tv_kousfs)
    TextView tvKousunfs;   //扣损方式
    @BindView(R.id.details_tv_beizhu)
    TextView tvBeiz;   //任务备注

    @BindView(R.id.root_item_tv_right)
    TextView tvStatus;   //任务备注

    private TaskBean mTaskBean;


    @Override
    protected void initTitle() {
        setTitle("任务详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_details;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mTaskBean = (TaskBean) getIntent().getSerializableExtra("taskdetail");
        if (mTaskBean == null) {
            IToast.showShort("详情为空");
            finish();
            return;
        }

        String status;
        if (mTaskBean.getStatus().equals("1")) {
            status = "等待调度";
        } else if (mTaskBean.getStatus().equals("2")) {
            status = "等待发运";
        } else if (mTaskBean.getStatus().equals("3")) {
            status = "在途运载";
        } else if (mTaskBean.getStatus().equals("4")) {
            status = "货位引导";
        } else if (mTaskBean.getStatus().equals("5")) {
            status = "等待回单";
        } else if (mTaskBean.getStatus().equals("6")) {
            status = "等待确认";
        } else if (mTaskBean.getStatus().equals("7")) {
            status = "已完成";
        } else {
            status = "未接单";
        }

        if (mTaskBean.getProjectType().equals("0")) {
            mTaskBean.setProjectType("集装箱");
        } else {
            mTaskBean.setProjectType("散堆装");
        }

        if (mTaskBean.getValuationUnitType().equals("0")) {
            mTaskBean.setValuationUnitType("吨");
        } else {
            mTaskBean.setValuationUnitType("位");
        }

        if (mTaskBean.getPayWay().equals("0")) {
            mTaskBean.setPayWay("日结");
        } else if (mTaskBean.getPayWay().equals("1")) {
            mTaskBean.setPayWay("周结");
        } else {
            mTaskBean.setPayWay("月结");
        }


        tvSendCity.setText(mTaskBean.getSendCity());
        tvArricCity.setText(mTaskBean.getArriCity());
        tvStatus.setText(status);
        tvXiangmbh.setText(mTaskBean.getProjectNum());
        tvXiangmlx.setText(mTaskBean.getProjectType());
        tvHuowmc.setText(mTaskBean.getProjectName());
        if (mTaskBean.getValuationUnitType().equals("0")) {
            tvJilfs.setText("吨");
        } else {
            tvJilfs.setText("件");
        }
        tvFukfs.setText(mTaskBean.getPayWay());
        tvFahdw.setText(mTaskBean.getTakeCompany());
        tvQuhd.setText(mTaskBean.getTakeCity());
        tvQuhdz.setText(mTaskBean.getPickupPlaceAddress());
        tvShouhdw.setText(mTaskBean.getArriCompany());
        tvYundd.setText(mTaskBean.getArriCity());
        tvYunddz.setText(mTaskBean.getArriveAddress());
        tvYunsfy.setText(mTaskBean.getProjectPrice());
        tvBut.setText(mTaskBean.getSubsidy());
        tvKousxs.setText(mTaskBean.getLossProportion());
        tvKousdj.setText(mTaskBean.getLossPrice());
        tvKousunfs.setText(mTaskBean.getLossWay());
        tvBeiz.setText("暂无备注");

    }

    @Override
    protected void initView() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_details_img_phone:
                callOrSendSms(0);
                break;
            case R.id.task_details_img_sms:
                callOrSendSms(1);
                break;
            case R.id.task_details_tv_apply:
                sendRequestOrder(mTaskBean.getPro_dist_id());
                break;
            default:
                break;
        }
    }

    /**
     * 打电话 or 发短信
     *
     * @param flag
     */
    private void callOrSendSms(int flag) {
        if (flag == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:17610919129"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:17610919129"));
            intent.putExtra("sms_body", "Hi Tornado!");
            startActivity(intent);
        }
    }


    /***
     * 接单申请
     * @param pro_dist_id  项目分配id
     */
    private void sendRequestOrder(String pro_dist_id) {

        Map<String, Object> params = ApiRetrofit.getInstance().getsendRequestParams(pro_dist_id);

        Observable<RequestResults> observable;

        observable = HttpManager.getInstance()
                .getOrderService().sendRequestOrder(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(this,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("接单申请成功");
//                    AppUtil.setMyTask(mTaskBean);

                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

}
