package com.shenhesoft.driver.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jph.takephoto.model.TResult;
import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.Event;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.Base64Utils;
import com.shenhesoft.driver.utils.EventBusUtils;
import com.shenhesoft.driver.utils.IToast;
import com.shenhesoft.driver.utils.TakePhotoActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 作者：Tornado
 * 创作日期：2017/12/27.
 * 描述：汽车短驳》》添加到货信息
 */

public class AddArrivexxActivity extends TakePhotoActivity {


    @BindView(R.id.tv_address)
    TextView tvaddress;             //取货地址

    @BindView(R.id.tv_goods_place)
    TextView tvgoodsplace;             //货场

    @BindView(R.id.tv_goods_location)
    TextView tvgoodslocation;             //货位

    @BindView(R.id.fayxx_et_fahpz)
    EditText etFahpz;             //到货皮重

    @BindView(R.id.fayxx_et_fahmz)
    EditText etFahmz;             //到货毛重

    @BindView(R.id.fayxx_et_fahjz)
    EditText etFahjz;       //散装发货净重

    @BindView(R.id.fayxx_et_fahjz1)
    EditText etFahjz1;             //集装箱发货净重1

    @BindView(R.id.fayxx_et_fahjz2)
    EditText etFahjz2;             //集装箱发货净重2

    @BindView(R.id.fayxx_et_huayzb)
    EditText etHuayzb;             //化验指标

    @BindView(R.id.fayxx_tv_yundsc)
    TextView tvYundsc;             //运单上传

    @BindView(R.id.containerNumber1)
    TextView mContainerNumber1;  //集装箱1的名字

    @BindView(R.id.containerNumber2)
    TextView mContainerNumber2;   //集装箱2的名字

    @BindView(R.id.layout_fayxx)
    LinearLayout mLayoutFayxx;
    @BindView(R.id.layout_fayxx1)
    LinearLayout mLayoutFayxx1;
    @BindView(R.id.layout_fayxx2)
    LinearLayout mLayoutFayxx2;

    private String imgUrl;//图片地址

    private String orderid;//订单id

    private String projectType;  //0 集装箱 1 散装
    private int pageNo = 1;
    private boolean isnodata;
    private int receiptTare;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_arrivexx;
    }

    @Override
    protected void initTitle() {
        setTitle("到货信息");
        setRightText("完成 ", v -> {
            commintData();
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        imgUrl = result.getImage().getCompressPath();
        //result.getImage().getOriginalPath()  获取图片真实路径
        tvYundsc.setText("已上传");
    }

    @OnClick({R.id.fayxx_tv_yundsc})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.fayxx_tv_yundsc:
                ShowPicCheck();
                break;
            default:
                break;
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
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
//            tvaddress.setText(bean.getArriCity() + bean.getArriveAddress());
//        } else {
//            getOrderStatus();
//        }
        getOrderStatus();
    }


    /**
     * 提交数据 请求接口
     */

    private void commintData() {
        String fhpz = etFahpz.getText().toString().trim();
        String fhmz = etFahmz.getText().toString().trim();
        String fhjz = etFahjz.getText().toString().trim();
        String fhjz1 = etFahjz1.getText().toString().trim();
        String fhjz2 = etFahjz2.getText().toString().trim();
        String huayzb = etHuayzb.getText().toString().trim();
        String imgSubmit = "";

//        String imgtype = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
//        if (imgtype.equals("jpg")) {
//            imgtype = "jpeg";
//        }
        try {
            imgSubmit = "data:image/jpeg;base64," + Base64Utils.encodeFile(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!inputverification(fhpz, fhmz,fhjz, fhjz1, fhjz2, huayzb, imgSubmit)) {
            return;
        }

        Map<String, Object> params = ApiRetrofit.getInstance().submitMotorArrivexxParams(orderid, txtCastDecimal(fhpz)
                , txtCastDecimal(fhmz),txtCastDecimal(fhjz), txtCastDecimal(fhjz1), txtCastDecimal(fhjz2), huayzb, imgSubmit, projectType);


        Observable<RequestResults> observable = HttpManager.getInstance()
                .getOrderService().addMotorArriveForm(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    EventBusUtils.sendStickyEvent(new Event(EventBusUtils.EventCode.B, "5"));
                    IToast.showShort("到货信息提交成功");
                    finish();

                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }


    /**
     * 获取数据接口
     */

    private void getOrderStatus() {

        Map<String, Object> params = ApiRetrofit.getInstance().getOrderStatusParams(pageNo + "", "5");

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
                    receiptTare = taskBean.getReceiptTare();
                    tvgoodsplace.setText(taskBean.getReceiptyard());
                    tvgoodslocation.setText(taskBean.getReceiptsite());
                    tvaddress.setText((taskBean.getArriCity() + taskBean.getArriveAddress()));
                    orderid = taskBean.getOrderId();
                    //修改集装箱和散堆装不同的显示ui
                    projectType = taskBean.getProjectType();
                    if ("1".equals(projectType)) {
                        mLayoutFayxx.setVisibility(View.VISIBLE);
                        mLayoutFayxx1.setVisibility(View.GONE);
                        mLayoutFayxx2.setVisibility(View.GONE);
                    } else {
                        mLayoutFayxx.setVisibility(View.GONE);
                        mLayoutFayxx1.setVisibility(View.VISIBLE);
                        mLayoutFayxx2.setVisibility(View.VISIBLE);
                        if (!TextUtils.isEmpty(taskBean.getContainerNumber1())) {
                            mContainerNumber1.setText(taskBean.getContainerNumber1());
                        }
                        if (!TextUtils.isEmpty(taskBean.getContainerNumber2())) {
                            mContainerNumber2.setText(taskBean.getContainerNumber2());
                        }
                    }
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

    //输入验证...
    private boolean inputverification( String fhpz, String fhmz,String fhjz,
                                      String fhjz1, String fhjz2, String huayzb, String imgUrl) {
        if (fhpz.isEmpty()) {
            IToast.showShort("请填入收货皮重");
            return false;
        }
        if (fhmz.isEmpty()) {
            IToast.showShort("请填入收货毛重");
            return false;
        }
        if (imgUrl.isEmpty()) {
            IToast.showShort("请上传运单照片");
            return false;
        }
        /**
         * 根据集装箱和散堆装判断数据是否正确
         */
        double fhmzNum = txtCastDecimal(fhmz);
        double fhpzNmu = txtCastDecimal(fhpz);
        if ("1".equals(projectType)) {
            if (fhjz.isEmpty()) {
                IToast.showShort("请填入收货净重");
                return false;
            }
            double fhjzNum = txtCastDecimal(fhjz);
            if (fhmzNum - fhpzNmu != fhjzNum) {
                IToast.showShort("重量填写错误");
                return false;
            }
        } else {
            if (fhjz1.isEmpty()) {
                IToast.showShort("请填入第1个集装箱收货净重");
                return false;
            }
            if (fhjz2.isEmpty() && !TextUtils.isEmpty(mContainerNumber2.getText().toString())) {
                IToast.showShort("请填入第2个集装箱收货净重");
                return false;
            }
            double fhjz1Num = txtCastDecimal(fhjz1);
            double fhjz2Num = txtCastDecimal(fhjz2);
            if (fhmzNum - fhpzNmu != fhjz1Num + fhjz2Num) {
                IToast.showShort("重量填写错误");
                return false;
            }
        }
        return true;
    }

    private double txtCastDecimal(String txt) {
        try {
            return Double.parseDouble(txt);
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
