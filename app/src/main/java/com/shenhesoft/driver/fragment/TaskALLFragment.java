package com.shenhesoft.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.activity.task.CancelDialog;
import com.shenhesoft.driver.activity.task.TaskAdapter;
import com.shenhesoft.driver.activity.task.TsakDetailsActivity;
import com.shenhesoft.driver.base.BaseFragment;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
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
 * 任务列表
 */
public class TaskALLFragment extends BaseFragment implements TaskAdapter.cancelClick, BGARefreshLayout.BGARefreshLayoutDelegate {


    private static final String KEY_MOTOR_STATUS = "key_status";

    @BindView(R.id.list)
    RecyclerView mRecycler;
    @BindView(R.id.myrefreshLayout)
    BGARefreshLayout mMyrefreshLayout;
    Unbinder unbinder;

    private TaskAdapter adapter;
    private List<TaskBean> beans;

    private String requestStatus;

    @Override
    protected void init() {
        requestStatus = getArguments().getString(KEY_MOTOR_STATUS);
        beans = new ArrayList<>();
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mMyrefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder meiTuanRefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mMyrefreshLayout.setRefreshViewHolder(meiTuanRefreshViewHolder);
        commitData();
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_taskall_list;
    }


    @Override
    public void onClick(View view, int position, int type) {
        if (type == 1) {
            CancelDialog.newInstance(beans.get(position).getPro_dist_id())
                    .show(getChildFragmentManager(), beans.get(position).getOrderId());
        } else if (type == 0) {
            sendRequestOrder(beans.get(position).getPro_dist_id(), position);
        } else if (type == 2) {

        } else if (type == 10 && requestStatus.equals("平台任务")) {
            Intent intent = new Intent(getContext(), TsakDetailsActivity.class);
            intent.putExtra("taskdetail", beans.get(position));
            startActivity(intent);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //每次进入“执行中”页面时都刷新
        requestStatus = getArguments().getString(KEY_MOTOR_STATUS);
        if (isVisibleToUser && requestStatus.equals("执行中")) {
            commitData();
        }
    }


    /**
     * 创建一个MotorAllProjectFragment实例
     *
     * @param status 加载不同列表数据的标识值
     * @return
     */
    public static TaskALLFragment newInstance(String status) {
        TaskALLFragment mf = new TaskALLFragment();
        Bundle b = new Bundle();
        b.putString(KEY_MOTOR_STATUS, status);
        mf.setArguments(b);
        return mf;
    }

    /**
     * 提交数据 请求接口
     */

    private void commitData() {

        Map<String, Object> params = ApiRetrofit.getInstance().getMotorListParams();

        Observable<RequestResultsList<TaskBean>> observable;
        if (requestStatus.equals("平台任务")) {
            observable = HttpManager.getInstance()
                    .getOrderService().getTasklistForm(params);
        } else if (requestStatus.equals("执行中")) {
            observable = HttpManager.getInstance()
                    .getOrderService().getTaskloadinglist(params);
        } else {
            observable = HttpManager.getInstance()
                    .getOrderService().getTaskCanclelist(params);
        }

        HttpObserver<RequestResultsList<TaskBean>> observer = new HttpObserver<>(mContext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
                    beans = data.getObj();

                    adapter = new TaskAdapter(mContext, beans, this, requestStatus);
                    mRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                });
        observer.setRefreshLayout(mMyrefreshLayout);
        HttpManager.getInstance().statrPostTask(observable, observer);

    }


    /***
     * 接单申请
     * @param pro_dist_id  项目分配id
     * @param mposition    列表选择下标
     */
    private void sendRequestOrder(String pro_dist_id, int mposition) {

        Map<String, Object> params = ApiRetrofit.getInstance().getsendRequestParams(pro_dist_id);

        Observable<RequestResults> observable;

        observable = HttpManager.getInstance()
                .getOrderService().sendRequestOrder(params);

        HttpObserver<RequestResults> observer = new HttpObserver<>(mContext,
                data -> {
                    if (data.getState() != 1) {
                        IToast.showShort(data.getMsg());
                        return;
                    }
//                    AppUtil.setMyTask(beans.get(mposition));
                    IToast.showShort("接单申请成功");
                });
        HttpManager.getInstance().statrPostTask(observable, observer);

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        commitData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
