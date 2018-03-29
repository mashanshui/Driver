package com.shenhesoft.driver.activity.me;

import android.os.Bundle;
import android.widget.ExpandableListView;


import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.PDetailsChildItem;
import com.shenhesoft.driver.bean.PDetailsRootItem;
import com.shenhesoft.driver.bean.PersoninfoBean;
import com.shenhesoft.driver.bean.UserinfoBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.IToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @author zmx
 * @date 2017/12/6
 * @desc 项目详情
 */

public class ProjectDetailsActivity extends BaseActivity {

    @BindView(R.id.details_ex_listview)
    ExpandableListView exListview;

    @Override
    public int getLayoutId() {
        //return R.layout.activity_project_details;
        return R.layout.activity_project_details_new;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        submit();
    }

    @Override
    protected void initTitle() {
        setTitle("车辆信息");
    }


    private List<PDetailsRootItem> createListData(PersoninfoBean bean) {
        List<PDetailsRootItem> rootItems = new ArrayList<>();


        PDetailsRootItem item1 = new PDetailsRootItem("车辆信息", R.mipmap.take_goods);
        List<PDetailsChildItem> childItems1 = new ArrayList<>();
        childItems1.add(new PDetailsChildItem("车牌号：", bean.getPlateNumber()));
        childItems1.add(new PDetailsChildItem("品牌：", bean.getBrand()));
        childItems1.add(new PDetailsChildItem("型号：", bean.getModel()));
        childItems1.add(new PDetailsChildItem("车型：", bean.getMotorcycleType()));
        childItems1.add(new PDetailsChildItem("身份证号：", bean.getIdCardNumber()));
        childItems1.add(new PDetailsChildItem("车辆照片：", "", bean.getCarPhoto1(), bean.getCarPhoto2()));
        childItems1.add(new PDetailsChildItem("购置时间：", bean.getBuyDate()));
        childItems1.add(new PDetailsChildItem("保险时间：", bean.getInsuranceBeginDate() + " 一 " + bean.getInsuranceEndDate()));
        childItems1.add(new PDetailsChildItem("保险资料：", "", bean.getInsuranceFilePhoto1(), bean.getInsuranceFilePhoto2()));

        item1.setChildItems(childItems1);

        rootItems.add(item1);

        return rootItems;
    }


    /***
     * 提交请求
     */
    private void submit() {
        Observable<RequestResults<PersoninfoBean>> observable = HttpManager.getInstance().getUserService()
                .getuserinfo(ApiRetrofit.getInstance().getUserinfo());

        HttpObserver<RequestResults<PersoninfoBean>> observer = new HttpObserver<>(this,

                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }

                    PDetailsAdapter adapter = new PDetailsAdapter(createListData(data.getObj()), mcontext);
                    exListview.setGroupIndicator(null);
                    exListview.setAdapter(adapter);
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        exListview.expandGroup(i);
                    }


                });

        HttpManager.getInstance().statrPostTask(observable, observer);
    }
}
