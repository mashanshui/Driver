package com.shenhesoft.driver.activity.task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.adapter.TitlePagerAdapter;
import com.shenhesoft.driver.base.BaseActivity;
import com.shenhesoft.driver.fragment.TaskALLFragment;
import com.shenhesoft.driver.view.PagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * 抵达卸货地点
 */
public class TaskActivity extends BaseActivity {

    @BindView(R.id.tab_mybills)
    PagerSlidingTabStrip tab;
    @BindView(R.id.vp_mybills)
    ViewPager viewPager;
    String[] titles = new String[]{"平台任务", "执行中", "已取消"};

    private ArrayList<Fragment> fragmentList = new ArrayList<>(3);

    @Override
    protected void initTitle() {
        setTitle("任务平台");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        fragmentList.add(TaskALLFragment.newInstance(titles[0]));
        fragmentList.add(TaskALLFragment.newInstance(titles[1]));
        fragmentList.add(TaskALLFragment.newInstance(titles[2]));
        TitlePagerAdapter pagerAdapter = new TitlePagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(1);
        tab.setViewPager(viewPager);
        setTabsValue();//设置

    }

    @Override
    protected void initView() {

    }


    private void setTabsValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置Tab底部选中的指示器Indicator的高度
        tab.setIndicatorHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab底部选中的指示器 Indicator的颜色
        tab.setIndicatorColorResource(R.color.orange);
        //设置指示器Indicatorin是否跟文本一样宽，默认false
        tab.setIndicatorinFollowerTv(false);
        //设置小红点提示，item从0开始计算，true为显示，false为隐藏
        tab.setMsgToast(2, true);
        //设置红点滑动到当前页面自动消失,默认为true
        tab.setMsgToastPager(true);
        //设置Tab标题文字的颜色
        //tab.setTextColor(R.color.***);
        // 设置Tab标题文字的大小
        tab.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, dm));
        // 设置选中的Tab文字的颜色
        tab.setSelectedTextColorResource(R.color.orange);
        //设置Tab底部分割线的高度
        tab.setUnderlineHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, dm));
        //设置Tab底部分割线的颜色
        //tab.setUnderlineColorResource(R.color.colorGray);
        // 设置点击某个Tab时的背景色,设置为0时取消背景色tabs.setTabBackground(0);
//        tab.setTabBackground(R.drawable.bg_tab);
        tab.setTabBackground(0);
        // 设置Tab是自动填充满屏幕的
        tab.setShouldExpand(true);
    }
}
