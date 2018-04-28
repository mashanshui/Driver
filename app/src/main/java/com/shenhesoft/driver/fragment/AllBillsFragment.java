package com.shenhesoft.driver.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.activity.task.WayBillDetailActivity;
import com.shenhesoft.driver.adapter.AllBillsAdapter;
import com.shenhesoft.driver.base.BaseFragment;
import com.shenhesoft.driver.bean.MyOrderBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.IToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllBillsFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private static final String KEY_MOTOR_STATUS = "key_status";
    @BindView(R.id.list)
    RecyclerView mRecycler;
    @BindView(R.id.myrefreshLayout)
    BGARefreshLayout mMyrefreshLayout;
    Unbinder unbinder;

    private AllBillsAdapter mAdapter;
    private String requestStatus;
    private List<MyOrderBean> list;
    private List<MyOrderBean> refreshList;
    private boolean isLoadMore;
    private int pageNo = 1;

    public AllBillsFragment() {
        // Required empty public constructor
    }

    /**
     * 创建一个AllBillsFragment实例
     *
     * @param status 加载不同列表数据的标识值
     * @return
     */
    public static AllBillsFragment newInstance(String status) {
        AllBillsFragment mf = new AllBillsFragment();
        Bundle b = new Bundle();
        b.putString(KEY_MOTOR_STATUS, status);
        mf.setArguments(b);
        return mf;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + requestStatus);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        requestStatus = getArguments().getString(KEY_MOTOR_STATUS);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new AllBillsAdapter(mContext, list,requestStatus);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, position) -> {
            startActivity(new Intent(mContext, WayBillDetailActivity.class).putExtra("myOrderBean", list.get(position)));
        });

        mMyrefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder meiTuanRefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mMyrefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);

        getOrderStatus();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_all_bills;
    }


    /**
     * 获取数据接口
     */

    private void getOrderStatus() {
        if (requestStatus == null) {
            IToast.showShort("界面异常，请返回重新进入！");
            return;
        }
        Map<String, Object> params = ApiRetrofit.getInstance().getOrderStatusParams(pageNo + "", requestStatus);

        Observable<RequestResultsList<MyOrderBean>> observable = HttpManager.getInstance()
                .getOrderService().getOrderinfo(params);

        HttpObserver<RequestResultsList<MyOrderBean>> observer = new HttpObserver<>(mContext,
                data -> {
                    if (data.getState() != 1) {
                        return;
                    }
                    //修改到货通知为三级地址，例：（新疆,巴音郭楞,和静县）第三个地址
//                    String address = data.getObj().getTakeCity();
//                    String[] temp = address.split(",");
                    refreshList = data.getObj();
                    /**
                     * 下拉刷新时数据是从头开始，因此需要setData
                     * 上拉加载时数据是往后叠加的，所以需要addData
                     * 如果上拉加载时没有加载到数据，pageNo页面数需要保持不变，减1
                     */
                    //上拉加载后有数据
                    if (isLoadMore && !refreshList.isEmpty()) {
                        list.addAll(refreshList);
                        isLoadMore = false;
                        mAdapter.notifyDataSetChanged();
                    }
                    //上拉加载后没有数据
                    else if (isLoadMore && refreshList.isEmpty()) {
                        pageNo--;
                        isLoadMore = false;
                    }
                    //下拉刷新后有数据
                    else if (!isLoadMore && !refreshList.isEmpty()) {
                        if (!list.isEmpty()) {
                            list.clear();
                        }
                        list.addAll(refreshList);
                        mAdapter.notifyDataSetChanged();
                    }
                    //下拉刷新后没有数据了
                    else if (!isLoadMore && refreshList.isEmpty()) {
                        if (!list.isEmpty()) {
                            list.clear();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
        observer.setRefreshLayout(mMyrefreshLayout);
        HttpManager.getInstance().statrPostTask(observable, observer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isLoadMore = false;
        /**
         * 下拉刷新时页面从头开始加载
         */
        pageNo = 1;
        getOrderStatus();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isLoadMore = true;
        /**
         * 上拉加载时每次加载页面加1
         */
        pageNo++;
        getOrderStatus();
        return true;
    }

}
