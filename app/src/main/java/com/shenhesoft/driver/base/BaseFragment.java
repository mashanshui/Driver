package com.shenhesoft.driver.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.Event;
import com.shenhesoft.driver.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;


/**
 * 作者：Tornado
 * 创作日期：2017/7/21.
 * 描述：
 */

public abstract class BaseFragment extends Fragment {

    private ProgressDialog loadingdialog;
    public String TAG = getClass().getSimpleName();
    protected View view;
    protected boolean isFirstLoad = true; //是否是首次加载数据
    protected Dialog loadingDialog;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "======onCreate======");
        mContext = getContext();
        view = View.inflate(mContext, getContentViewLayoutID(), null);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isRegisterEventBus()) {
            EventBusUtils.register(this);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setRootTitle(String title) {
        TextView tvRight = v(R.id.title);
        tvRight.setText(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return view;
    }

    public void ShowDailog() {
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(mContext);
        }
        loadingdialog.setMessage("正在提交申请");
        loadingdialog.show();

    }

    public void ShowDailog(String msg) {
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(mContext);
        }
        loadingdialog.setMessage(msg);
        loadingdialog.show();

    }

    public ProgressDialog getLoadingdialog() {
        return loadingdialog;
    }


    public <T extends View> T v(int id) {
        View childView = view.findViewById(id);
        return (T) childView;
    }

    /**
     * 初始化view
     */
    protected abstract void init();


    /**
     * 获取页面除topBar外的layoutID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    public void receiveEvent(Event event) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    public void receiveStickyEvent(Event event) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //在销毁视图的时候把父控件remove一下，不然重新加载的时候会异常导致奔溃，提示should remove parent view
        ((ViewGroup) view.getParent()).removeView(view);   //移除  否则onCreateView会报错
        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this);
        }
    }
}
