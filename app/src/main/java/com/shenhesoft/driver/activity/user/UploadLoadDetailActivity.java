package com.shenhesoft.driver.activity.user;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.adapter.LoadConditionAdapter;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.bean.LoadConditionBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observable;

public class UploadLoadDetailActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.recycle_list)
    RecyclerView recycleList;
    @BindView(R.id.myrefreshLayout)
    BGARefreshLayout mMyrefreshLayout;

    private LoadConditionAdapter conditionAdapter;
    private List<LoadConditionBean.DataBean> dataBeanList;
    private List<LoadConditionBean.DataBean> refreshList;
    private int length = 10;
    private int start = 0;
    private boolean isLoadMore;

    @Override
    protected void initTitle() {
        setTitle("路况信息");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_load_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        dataBeanList = new ArrayList<>();
        conditionAdapter = new LoadConditionAdapter(mcontext, dataBeanList);
        recycleList.setLayoutManager(new LinearLayoutManager(mcontext));
        recycleList.setAdapter(conditionAdapter);
        recycleList.addItemDecoration(new DividerItemDecoration(mcontext, DividerItemDecoration.VERTICAL));        mMyrefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder meiTuanRefreshViewHolder = new BGANormalRefreshViewHolder(mcontext, true);
        mMyrefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);
        getLoadCondition();
    }

    @Override
    protected void initView() {

    }

    private void getLoadCondition() {
        Map<String, Object> params = ApiRetrofit.getInstance().getLoadCondition(start);

        Observable<RequestResults<LoadConditionBean>> observable = HttpManager.getInstance()
                .getOrderService().getLoadCondition(params);

        HttpObserver<RequestResults<LoadConditionBean>> observer = new HttpObserver<>(mcontext,
                data -> {
                    refreshList = data.getObj().getData();
                    /**
                     * 下拉刷新时数据是从头开始，因此需要setData
                     * 上拉加载时数据是往后叠加的，所以需要addData
                     * 如果上拉加载时没有加载到数据，pageNo页面数需要保持不变，减1
                     */
                    //上拉加载后有数据
                    if (isLoadMore && !refreshList.isEmpty()) {
                        dataBeanList.addAll(refreshList);
                        isLoadMore = false;
                        conditionAdapter.notifyDataSetChanged();
                    }
                    //上拉加载后没有数据
                    else if (isLoadMore && refreshList.isEmpty()) {
                        start = start - length;
                        isLoadMore = false;
                    }
                    //下拉刷新后有数据
                    else if (!isLoadMore && !refreshList.isEmpty()) {
                        if (!dataBeanList.isEmpty()) {
                            dataBeanList.clear();
                        }
                        dataBeanList.addAll(refreshList);
                        conditionAdapter.notifyDataSetChanged();
                    }
                    //下拉刷新后没有数据了
                    else if (!isLoadMore && refreshList.isEmpty()) {
                        if (!dataBeanList.isEmpty()) {
                            dataBeanList.clear();
                        }
                        conditionAdapter.notifyDataSetChanged();
                    }
                    Log.e(TAG, "getLoadCondition: " + start);
                });
        observer.setRefreshLayout(mMyrefreshLayout);
        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isLoadMore = false;
        start = 0;
        getLoadCondition();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isLoadMore = true;
        start = start + length;
        getLoadCondition();
        return true;
    }

}
