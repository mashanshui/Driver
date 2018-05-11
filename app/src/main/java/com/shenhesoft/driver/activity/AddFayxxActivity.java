package com.shenhesoft.driver.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jph.takephoto.model.TResult;
import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.Base64Utils;
import com.shenhesoft.driver.utils.IToast;
import com.shenhesoft.driver.utils.TakePhotoActivity;

import java.math.BigDecimal;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 作者：Tornado
 * 创作日期：2017/12/27.
 * 描述：汽车短驳》》添加发运信息
 */

public class AddFayxxActivity extends TakePhotoActivity {
    private static final String TAG = "AddFayxxActivity";

    @BindView(R.id.tv_address)
    TextView tvaddress;             //取货地址

    @BindView(R.id.tv_goods_place)
    TextView tvgoodsplace;             //货场

    @BindView(R.id.tv_goods_location)
    TextView tvgoodslocation;             //货位

    @BindView(R.id.fayxx_et_fahpz)
    EditText etFahpz;             //发货皮重

    @BindView(R.id.fayxx_et_fahmz)
    EditText etFahmz;             //发货毛重

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_fayxx;
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
    protected void initTitle() {
        setTitle("发运信息提交");
        setRightText("完成", v -> {
//            Updataheader();
            commintData();
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

//        TaskBean bean = AppUtil.getMyTask();
//
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
//            return;
//        }
//        if (!isnodata) {
//            tvaddress.setText(bean.getPickupPlaceAddress());
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
        try {
            imgSubmit = "data:image/jpeg;base64," + Base64Utils.encodeFile(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!inputverification(fhpz, fhmz, fhjz, fhjz1, fhjz2, huayzb, imgSubmit)) {
            return;
        }

        Map<String, Object> params = ApiRetrofit.getInstance().submitMotorFayxxParams(orderid, txtCastDecimal(fhpz)
                , txtCastDecimal(fhmz),txtCastDecimal(fhjz), txtCastDecimal(fhjz1), txtCastDecimal(fhjz2), huayzb, imgSubmit, projectType);


        Observable<RequestResults> observable = HttpManager.getInstance()
                .getOrderService().addMotorFayxxForm(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("发运信息提交成功");
                    finish();
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

    /**
     * 获取数据接口
     */

    private void getOrderStatus() {

        Map<String, Object> params = ApiRetrofit.getInstance().getOrderStatusParams(pageNo + "", "1");

        Observable<RequestResultsList<TaskBean>> observable = HttpManager.getInstance()
                .getOrderService().getMotorinfo(params);

        HttpObserver<RequestResultsList<TaskBean>> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
            /*        if (isnodata) {
                        AppUtil.setMyTask(data.getObj());
                    }*/
                    TaskBean taskBean = data.getObj().get(0);
                    orderid = taskBean.getOrderId();
                    //修改bug货场货位提取错误
//                    tvgoodsplace.setText(taskBean.getReceiptyard());
//                    tvgoodslocation.setText(taskBean.getReceiptsite());
                    tvgoodsplace.setText(taskBean.getSendyard());
                    tvgoodslocation.setText(taskBean.getSendsite());
                    tvaddress.setText(taskBean.getTakeCity() + taskBean.getPickupPlaceAddress());

                    //修改集装箱和散堆装不同的显示ui
                    projectType = taskBean.getProjectType();
                    if (projectType.equals("1")) {
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


    /**
     * 修改头像接口
     */

    private void Updataheader() {
        String imgSubmit = "";
        try {
            imgSubmit = "data:image/jpeg;base64," + Base64Utils.encodeFile(imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> params = ApiRetrofit.getInstance().updateHeader(imgSubmit);

        Observable<RequestResults> observable = HttpManager.getInstance()
                .getUserService().updataDriverheader(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }

    //输入验证...
    private boolean inputverification(String fhpz, String fhmz,String fhjz,
                                      String fhjz1, String fhjz2, String huayzb, String imgUrl) {
        if (fhpz.isEmpty()) {
            IToast.showShort("请填入发货皮重");
            return false;
        }
        if (fhmz.isEmpty()) {
            IToast.showShort("请填入发货毛重");
            return false;
        }
        if (huayzb.isEmpty()) {
            IToast.showShort("请填入化验指标");
            return false;
        }
        if (imgUrl.isEmpty()) {
            IToast.showShort("请上传运单照片");
            return false;
        }
        /**
         * 根据集装箱和散堆装判断数据是否正确
         */
        BigDecimal fhmzNum = new BigDecimal(fhmz);
        BigDecimal fhpzNmu = new BigDecimal(fhpz);
        if (projectType.equals("1")) {
            if (fhjz.isEmpty()) {
                IToast.showShort("请填入发货净重");
                return false;
            }
            double fhjzNum = txtCastDecimal(fhjz);
            if (fhmzNum.subtract(fhpzNmu).doubleValue() != fhjzNum) {
                IToast.showShort("重量填写错误");
                return false;
            }
        } else {
            if (fhjz1.isEmpty()) {
                IToast.showShort("请填入第1个集装箱发货净重");
                return false;
            }
            if (fhjz2.isEmpty() && !TextUtils.isEmpty(mContainerNumber2.getText().toString())) {
                IToast.showShort("请填入第2个集装箱发货净重");
                return false;
            }
            BigDecimal fhjz1Num = new BigDecimal(fhjz1);
            BigDecimal fhjz2Num = new BigDecimal(fhjz2);
            if (fhmzNum.subtract(fhpzNmu).doubleValue() != fhjz1Num.add(fhjz2Num).doubleValue()) {
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
