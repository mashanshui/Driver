package com.shenhesoft.driver.activity.user;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.IToast;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class UploadLoadActivity extends BaseActivity {


    @BindView(R.id.et_upload_load)
    TextInputEditText etUploadLoad;

    @Override
    protected void initTitle() {
        setTitle("填写路况信息");
        setRightText("上传", v -> {
            upload();
        });
    }

    private void upload() {
        if (TextUtils.isEmpty(etUploadLoad.getText().toString().trim())) {
            return;
        }
        Map<String, Object> params = ApiRetrofit.getInstance().uploadLoadCondition(etUploadLoad.getText().toString().trim());

        Observable<RequestResults> observable = HttpManager.getInstance()
                .getOrderService().uploadLoadCondition(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mcontext,
                data -> {
                    if (data.getState() != 201) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    IToast.showShort("上传路况信息成功");
                    finish();

                });
        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_load;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }
}
