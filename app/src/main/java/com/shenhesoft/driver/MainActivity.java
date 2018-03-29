package com.shenhesoft.driver;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenhesoft.driver.application.MyApplication;
import com.shenhesoft.driver.bean.UpdatalocationBean;
import com.shenhesoft.driver.fragment.ChatFragment;
import com.shenhesoft.driver.fragment.HomeFragment;
import com.shenhesoft.driver.fragment.MineFragment;
import com.shenhesoft.driver.fragment.WaybillFragment;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.service.DriverService;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.DeviceValueUtil;
import com.shenhesoft.driver.utils.IToast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.OnReceiveMessageListener;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 使用LinearLayout+TextView实现底部导航栏
 * 通过点击layout切换fragment实现
 * 优点:实现原理简单,逻辑清晰,可以自定义角标并显示数量
 * 缺点:由于没加ViewPager,所以不能左右滑动切换
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tab_home_icon)
    ImageView tabHomeIcon;
    @BindView(R.id.tab_home_badge)
    ImageView tabHomeBadge;
    @BindView(R.id.tab_home_title)
    TextView tabHomeTitle;
    @BindView(R.id.tab_home_layout)
    LinearLayout tabHomeLayout;
    @BindView(R.id.tab_scene_icon)
    ImageView tabSceneIcon;
    @BindView(R.id.tab_scene_badge)
    ImageView tabSceneBadge;
    @BindView(R.id.tab_scene_title)
    TextView tabSceneTitle;
    @BindView(R.id.tab_scene_layout)
    LinearLayout tabSceneLayout;
    @BindView(R.id.tab_find_icon)
    ImageView tabFindIcon;
    @BindView(R.id.tab_find_badge)
    ImageView tabFindBadge;
    @BindView(R.id.tab_find_title)
    TextView tabFindTitle;
    @BindView(R.id.tab_find_layout)
    LinearLayout tabFindLayout;
    @BindView(R.id.tab_me_icon)
    ImageView tabMeIcon;
    @BindView(R.id.tab_me_badge)
    ImageView tabMeBadge;
    @BindView(R.id.tab_me_title)
    TextView tabMeTitle;
    @BindView(R.id.tab_me_layout)
    LinearLayout tabMeLayout;
    @BindView(R.id.tab_layout)
    LinearLayout tabLayout;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;

    ArrayList<Fragment> tabFragments = new ArrayList<>(4);
    FragmentManager fm = null;
    private int currentSelectedTab = -1;
    public static final int POSITION_HOME = 0;
    public static final int POSITION_SCENE = 1;
    public static final int POSITION_FOUND = 2;
    public static final int POSITION_PERSONAL = 3;

    private ConversationListFragment mConversationListFragment = null;

    private Conversation.ConversationType[] mConversationsTypes = null;
    private Intent driverService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        driverService = new Intent(this, DriverService.class);
        startService(driverService);
        connect(AppUtil.getUserinfo().getRongCloudToken());
        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, mConversationsTypes);

    }

    @OnClick({R.id.tab_home_layout, R.id.tab_scene_layout, R.id.tab_find_layout, R.id.tab_me_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home_layout:
                setTabByPosition(POSITION_HOME);
                break;
            case R.id.tab_scene_layout:
                setTabByPosition(POSITION_SCENE);
                break;
            case R.id.tab_find_layout:
                setTabByPosition(POSITION_FOUND);
                break;
            case R.id.tab_me_layout:
                setTabByPosition(POSITION_PERSONAL);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragments();
        initTabView();
        setDefaultTab();
    }


    private void initFragments() {
        fm = this.getSupportFragmentManager();
        tabFragments.add(new HomeFragment());
        tabFragments.add(initConversationList());
        tabFragments.add(new WaybillFragment());
        tabFragments.add(new MineFragment());
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            mConversationListFragment = new ConversationListFragment();
            mConversationListFragment.setAdapter(new ConversationListAdapter(RongContext.getInstance()));
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "fals") //设置私聊会话是否聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP
            };
            mConversationListFragment.setUri(uri);
            return mConversationListFragment;
        } else {
            return mConversationListFragment;
        }

    }


    /**
     * 初始化底部tabView
     * 主要是处理系统底部虚拟按键的遮盖问题
     */
    private void initTabView() {
        int navBarHeight = AppUtil.getNavigationBarHeight(this);
        Log.e(TAG, "navBarHeight : " + navBarHeight);
        navBarHeight = 0;
        //ToastUtils.show(this,"navBarHeight : " + navBarHeight);

        //int screenHeight = DeviceValueUtils.getScreenHeightPx(this);
        int tabLayoutHeight = DeviceValueUtil.getRealityHeightPx(this, Constants.DESIGN_TABLAYOUT_HEIGHT);
        /*Math.round(screenHeight * Constants.DESIGN_TABLAYOUT_HEIGHT
                / (float) Constants.DESIGN_HEIGHT);*/
        RelativeLayout.LayoutParams tabLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, tabLayoutHeight);
        tabLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        tabLayoutParams.setMargins(0, 0, 0, navBarHeight);
        tabLayout.setLayoutParams(tabLayoutParams);
        Log.d(TAG, "tabLayoutHeight : " + tabLayoutHeight);

    }


    private void setDefaultTab() {
        if (currentSelectedTab == -1) {
            setTabByPosition(POSITION_HOME);
        } else {
            setTabByPosition(currentSelectedTab);
        }
    }

    /**
     * 选择tab,显示tab
     */
    public void setTabByPosition(int currentPosition) {
//        Log.d(TAG, "setTabByPosition  ---  currentPosition : " + currentPosition);
        clearTabSelected();
        onTabSelected(currentPosition);
    }

    /**
     * 清除之前选择的tab的图标颜色和字体颜色
     */
    private void clearTabSelected() {
        switch (currentSelectedTab) {
            case POSITION_HOME:
                tabHomeIcon.setSelected(false);
                tabHomeTitle.setSelected(false);
                break;
            case POSITION_SCENE:
                tabSceneIcon.setSelected(false);
                tabSceneTitle.setSelected(false);
                break;
            case POSITION_FOUND:
                tabFindIcon.setSelected(false);
                tabFindTitle.setSelected(false);
                break;
            case POSITION_PERSONAL:
                tabMeIcon.setSelected(false);
                tabMeTitle.setSelected(false);
                break;
            default:
                break;
        }
    }

    /**
     * 当相应tab被点击时调用
     *
     * @param position
     */
    private void onTabSelected(int position) {
//        Log.d(TAG, "onTabSelected  --- position : " + position);
        currentSelectedTab = position;
        //refresh view
        refreshView(position);
        //refresh content
        refreshFragment(position);
    }

    /**
     * 点击相应tab后,相应tab显示绿色图标和字体颜色
     *
     * @param position
     */
    private void refreshView(int position) {
//        Log.d(TAG, "refreshView  --- position : " + position);
        switch (position) {
            case POSITION_HOME:
                tabHomeIcon.setSelected(true);
                tabHomeTitle.setSelected(true);
                break;
            case POSITION_SCENE:
                tabSceneIcon.setSelected(true);
                tabSceneTitle.setSelected(true);
                break;
            case POSITION_FOUND:
                tabFindIcon.setSelected(true);
                tabFindTitle.setSelected(true);
                break;
            case POSITION_PERSONAL:
                tabMeIcon.setSelected(true);
                tabMeTitle.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * 点击相应tab后,显示相应fragment,并传递所需参数
     *
     * @param position
     */
    private void refreshFragment(int position) {
//        Log.d(TAG, "refreshFragment  --- position : " + position);
        if (tabFragments != null) {
            if (position < tabFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = tabFragments.get(position);
//                Log.d(TAG, "fragment.isAdded() : " + fragment.isAdded());
                ft.replace(R.id.content_layout, fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }


    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link #init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "Token 错误");
                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

                    if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {
                        RongIM.setOnReceiveMessageListener((message, i) -> {
                            return false;
                        });
                    }

                    UserInfo userInfo = new UserInfo("S" + AppUtil.getUserinfo().getId(), AppUtil.getUserinfo().getName(),
                            Uri.parse(AppUtil.getUserinfo().getUserIcon()));
                    /**
                     * 设置当前用户信息，
                     *
                     * @param userInfo 当前用户信息
                     */
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    /**
                     * 设置消息体内是否携带用户信息。
                     *
                     * @param state 是否携带用户信息，true 携带，false 不携带。
                     */
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(driverService);
    }
}