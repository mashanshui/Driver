package com.shenhesoft.driver.activity.me;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.PDetailsChildItem;
import com.shenhesoft.driver.bean.PDetailsRootItem;
import com.shenhesoft.driver.bean.PersoninfoBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.utils.IToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * @author zmx
 * @date 2017/12/6
 * @desc 个人中心
 */

public class MyCenterActivity extends BaseActivity {

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
        setTitle("个人中心");
    }


    private List<PDetailsRootItem> createListData(PersoninfoBean bean) {
        List<PDetailsRootItem> rootItems = new ArrayList<>();

        String sex=bean.getSex()==1? "男":"女";

        String driversex=bean.getSex()==1? "男":"女";

        PDetailsRootItem item1 = new PDetailsRootItem("车主信息", R.mipmap.take_goods);
        List<PDetailsChildItem> childItems1 = new ArrayList<>();
        childItems1.add(new PDetailsChildItem("姓名", bean.getName()));
        childItems1.add(new PDetailsChildItem("出生年月", bean.getBirthday()));
        childItems1.add(new PDetailsChildItem("性别", sex));
        childItems1.add(new PDetailsChildItem("绑定手机", bean.getPhone()));
        childItems1.add(new PDetailsChildItem("住址", bean.getAddress()));
        childItems1.add(new PDetailsChildItem("银行卡号", bean.getBankCardNumber()));
        childItems1.add(new PDetailsChildItem("身份证号", bean.getIdCardNumber()));
        childItems1.add(new PDetailsChildItem("身份证照片", "", bean.getIdCardImgFront(), bean.getIdCardImgBack()));

        item1.setChildItems(childItems1);

        rootItems.add(item1);

        PDetailsRootItem item2 = new PDetailsRootItem("驾驶员信息", R.mipmap.take_goods);
        List<PDetailsChildItem> childItems2 = new ArrayList<>();
        childItems2.add(new PDetailsChildItem("姓名", bean.getDriverName()));
        childItems2.add(new PDetailsChildItem("出生年月", bean.getDriverBirthday()));
        childItems2.add(new PDetailsChildItem("性别", driversex));
        childItems2.add(new PDetailsChildItem("绑定手机", bean.getDriverPhone()));
        childItems2.add(new PDetailsChildItem("住址", bean.getDriverAddress()));
        childItems2.add(new PDetailsChildItem("银行卡号", bean.getDriverBankCardNumber()));
        childItems2.add(new PDetailsChildItem("身份证号", bean.getDriverIdCardNumber()));
        childItems2.add(new PDetailsChildItem("身份证照片", "", bean.getDriverIdCardImgFront(), bean.getDriverIdCardImgBack()));
        childItems2.add(new PDetailsChildItem("驾驶证照片", "", bean.getDriverLicenceImgFront(), bean.getDriverLicenceImgBack()));

        item2.setChildItems(childItems2);

        rootItems.add(item2);

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
