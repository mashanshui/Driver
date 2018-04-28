package com.shenhesoft.driver.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.adapter.TitlePagerAdapter;
import com.shenhesoft.driver.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaybillFragment extends Fragment {
    public final static String BILL_STATUS_ALL = "0"; //全部
    public final static String BILL_STATUS_TO_RECONCILED = "4"; //待对账
    public final static String BILL_STATUS_TO_SETTLED = "6"; //待结算
    public final static String BILL_STATUS_AlREADY_SETTLED = "7"; //已结算
    @BindView(R.id.tab_mybills)
    PagerSlidingTabStrip tab;
    @BindView(R.id.vp_mybills)
    ViewPager viewPager;
    @BindView(R.id.title)
    TextView titleTv;
    //    String[] titles = new String[]{"全部", "待对账", "待结算","已结算"};
//    private ArrayList<Fragment> fragmentList = new ArrayList<>(4);
    String[] titles = new String[]{"待对账", "待结算", "已结算"};
    private ArrayList<Fragment> fragmentList = new ArrayList<>(2);

    public WaybillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waybill, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        titleTv.setText("我的运单");
    }

    private void initData() {
//        if (fragmentList.size() < 4) {
//            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_ALL));
//            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_TO_RECONCILED));
//            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_TO_SETTLED));
//            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_AlREADY_SETTLED));
//        }
        if (fragmentList.size() < 3) {
            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_TO_RECONCILED));
            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_TO_SETTLED));
            fragmentList.add(AllBillsFragment.newInstance(BILL_STATUS_AlREADY_SETTLED));
        }
        TitlePagerAdapter pagerAdapter = new TitlePagerAdapter(getChildFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tab.setViewPager(viewPager);
        setTabsValue();//设置
    }

    private void initListener() {

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
//        tab.setMsgToast(2, true);
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
