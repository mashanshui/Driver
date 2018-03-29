package com.shenhesoft.driver.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.activity.me.MyCenterActivity;
import com.shenhesoft.driver.activity.me.ProjectDetailsActivity;
import com.shenhesoft.driver.activity.user.SetActivity;
import com.shenhesoft.driver.utils.AppUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.layout_persion_center)
    LinearLayout persionCenterLayout;
    @BindView(R.id.layout_wallet)
    LinearLayout walletLayout;
    @BindView(R.id.layout_message)
    LinearLayout messageLayout;
    @BindView(R.id.layout_car_info)
    LinearLayout carInfoLayout;
    @BindView(R.id.layout_company)
    LinearLayout companyLayout;
    @BindView(R.id.layout_setting)
    LinearLayout settingLayout;

    @BindView(R.id.billnum_tv)
    TextView billnum_tv;

    @BindView(R.id.income_tv)
    TextView income_tv;

    @BindView(R.id.username_iv)
    TextView username_iv;

    @BindView(R.id.usericon_iv)
    ImageView usericon;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        titleTv.setText("我的");
    }

    private void initData() {
        if (!TextUtils.isEmpty(AppUtil.getUserinfo().getUserIcon())) {
            Picasso.with(getContext()).load(AppUtil.getUserinfo().getUserIcon()).placeholder(R.mipmap.app_icon).into(usericon);
        }

        billnum_tv.setText(AppUtil.getUserinfo().getOrderNum());

        income_tv.setText(AppUtil.getUserinfo().getSumIncome());

        username_iv.setText(AppUtil.getUserinfo().getName());
    }

    private void initListener() {
        persionCenterLayout.setOnClickListener(this);
        walletLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        carInfoLayout.setOnClickListener(this);
        companyLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_persion_center:
                Intent intent = new Intent(getContext(), MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_wallet:

                break;
            case R.id.layout_message:
                break;
            case R.id.layout_car_info:
                Intent intent1 = new Intent(getContext(), ProjectDetailsActivity.class);
                startActivity(intent1);
                break;
            case R.id.layout_company:
                break;
            case R.id.layout_setting:
                Intent intent2 = new Intent(getContext(), SetActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
