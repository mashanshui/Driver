package com.shenhesoft.driver.activity.task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.fragment.TaskALLFragment;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.IToast;

import java.util.Map;

import io.reactivex.Observable;


/**
 * Created by zhangzhenrui on 2018/1/15.
 */


public class CancelDialog extends DialogFragment implements View.OnClickListener {

    private static final String KEY_MOTOR_STATUS = "key_status";

    private LinearLayout mReasonList;
    private ConstraintLayout mDetails;
    private TextView mEtReason;
    private EditText mEtDetaisl;
    private TextView mTvOK;
    private TextView mTvCancel;
    private String Reason = "";
    private String requestStatus;
    String aa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cancel, container, false);
        mReasonList = (LinearLayout) view.findViewById(R.id.visiable_content);
        mDetails = (ConstraintLayout) view.findViewById(R.id.gone_content);
        mEtReason = (TextView) view.findViewById(R.id.et_reason);
        mEtDetaisl = (EditText) view.findViewById(R.id.et_details);
        mTvOK = (TextView) view.findViewById(R.id.tv_ok);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mEtReason.setOnClickListener(this);
        mTvOK.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        view.findViewById(R.id.choose_reason).setOnClickListener(this);
        view.findViewById(R.id.reason1).setOnClickListener(this);
        view.findViewById(R.id.reason2).setOnClickListener(this);
        view.findViewById(R.id.reason3).setOnClickListener(this);
        view.findViewById(R.id.reason4).setOnClickListener(this);
        view.findViewById(R.id.reason5).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestStatus = getArguments().getString(KEY_MOTOR_STATUS);
        aa = getTag();
    }

    /**
     * 创建一个MotorAllProjectFragment实例
     *
     * @param status 加载不同列表数据的标识值
     * @return
     */
    public static CancelDialog newInstance(String status) {
        CancelDialog mf = new CancelDialog();
        Bundle b = new Bundle();
        b.putString(KEY_MOTOR_STATUS, status);
        mf.setArguments(b);
        return mf;
    }

    private void ShowDialog() {
        // 创建数据
        final String[] items = new String[]{"车辆故障", "私人问题", "运价问题", "误接运单", "不想拉了", "其他原因填写"};
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 设置参数
        builder.setTitle("取消原因")
                .setItems(items, (dialog, which) ->
                        {
                            Reason = items[which];
                            mEtReason.setText(Reason);
                        }
                );
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reason1:
                mReasonList.setVisibility(View.GONE);
                mDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.reason2:
                mReasonList.setVisibility(View.GONE);
                mDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.reason3:
                mReasonList.setVisibility(View.GONE);
                mDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.reason4:
                mReasonList.setVisibility(View.GONE);
                mDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.reason5:
                mReasonList.setVisibility(View.GONE);
                mDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.et_reason:
                ShowDialog();
//                mReasonList.setVisibility(View.VISIBLE);
//                mDetails.setVisibility(View.GONE);
                break;
            case R.id.choose_reason:
//                ShowDialog();
                break;
            case R.id.tv_ok:
                sendCancleOrder(aa, Reason, mEtDetaisl.getText().toString());
                break;
            case R.id.tv_cancel:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    private void sendCancleOrder(String orderId, String reason, String detail) {

        Map<String, Object> params = ApiRetrofit.getInstance().sendCancleParams(orderId, reason, detail);

        Observable<RequestResults> observable;

        observable = HttpManager.getInstance()
                .getOrderService().CancleOrder(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(getContext(),
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("订单已取消");
                    this.dismiss();
                });

        HttpManager.getInstance().statrPostTask(observable, observer);

    }
}
