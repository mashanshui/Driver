package com.shenhesoft.driver.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.Event;
import com.shenhesoft.driver.utils.ActivityManager;
import com.shenhesoft.driver.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/31.
 */

public abstract class BaseActivity extends FragmentActivity {


    public String TAG = getClass().getSimpleName();
    public static Activity mactivity;
    public static Context mcontext;

    private ProgressDialog loadingdialog;

    @BindView(R.id.title_right)
    TextView mTvRight;

    @BindView(R.id.activity_title)
    TextView tv_title;

    @BindView(R.id.btn_back)
    RelativeLayout btn_back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() <= 0) {
            Log.d(TAG, "请先设置布局！");
            return;
        }
        setContentView(getLayoutId());
        ActivityManager.addActivity(this);
        ButterKnife.bind(this);
        mactivity = this;
        mcontext = this;
        initView();
        initData(savedInstanceState);
        initTitle();
        setBtn_back();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isRegisterEventBus()) {
            EventBusUtils.register(this);
        }
    }

    protected abstract void initTitle();


    private void setBtn_back() {
        if (btn_back != null) {
            btn_back.setOnClickListener(v -> finish());
        }
    }


    public void setTitle(String title) {
        if (tv_title != null && !TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        } else {
            tv_title.setText("");
        }
    }

    protected void setRightText(String text, View.OnClickListener onClickListener) {
        if (mTvRight != null) {
            mTvRight.setText(text);
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setOnClickListener(onClickListener);
        }
    }

    public void ShowDailog() {
        if (!isLiving(mactivity)) {
            return;
        }
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(mcontext);
        }
        loadingdialog.setMessage("正在提交申请");
        loadingdialog.show();
    }

    public void ShowDailog(String msg) {
        if (!isLiving(mactivity)) {
            return;
        }
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(mcontext);
        }
        loadingdialog.setMessage(msg);
        loadingdialog.show();

    }


    /**
     * 初始化布局
     */
    protected abstract int getLayoutId();


    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();


    public ProgressDialog getLoadingdialog() {
        return loadingdialog;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this);
        }
    }

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

    public static <T extends View> T v(int id) {
        View childView = mactivity.findViewById(id);
        return (T) childView;
    }

    public static <T extends View> T v(View view, int id) {
        View childView = view.findViewById(id);
        return (T) childView;
    }

    public static <T extends View> T v(Activity activity, int id) {
        View childView = activity.findViewById(id);
        return (T) childView;
    }


    /**
     * 判断activity是否存活
     */
    private static boolean isLiving(Activity activity) {

        if (activity == null) {
            Log.d("wisely", "activity == null");
            return false;
        }

        return true;
    }
}

