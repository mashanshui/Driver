package com.shenhesoft.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.MyOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 马山水
 * @date 2018/3/12
 * @desc TODO
 */

public class AllBillsAdapter extends RecyclerView.Adapter<AllBillsAdapter.ViewHolder> {
    private static final String TAG = "AllBillsAdapter";
    private Context mContext;
    private LayoutInflater inflater;
    private ViewHolder mViewHolder;
    private View view;
    private String type;
    private List<MyOrderBean> mOrderBeanList;

    public AllBillsAdapter(Context context, List<MyOrderBean> orderBeanList,String type) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        mOrderBeanList = orderBeanList;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mViewHolder = holder;
        MyOrderBean bean = mOrderBeanList.get(position);
        String status = String.valueOf(bean.getStatus());
        Log.e(TAG, "onBindViewHolder: "+status );
        switch (status) {
            case "5":
                setText2();
                holder.mTvProjectName.setText(bean.getProjectCode());
                holder.mTextItem1.setText(bean.getOrderCode());
                holder.mTextItem2.setText(bean.getCreateDate());
                holder.mTextItem3.setText(bean.getCargoName());
                holder.mTextItem4.setText(bean.getSendCompany());
                holder.mTextItem5.setText(bean.getReceiptCompany());
                holder.mTextItem6.setText(bean.getShortBargeCost());
                break;
            case "6":
                setText1();
                holder.mTvProjectName.setText(bean.getProjectCode());
                holder.mTextItem1.setText(bean.getShPackId());
                holder.mTextItem2.setText(bean.getCargoName());
                holder.mTextItem3.setText(bean.getSendCompany());
                holder.mTextItem4.setText(bean.getReceiptCompany());
                holder.mTextItem5.setText(bean.getPackTruckDegree());
                holder.mTextItem6.setText(bean.getPackTruckNum());
                break;
            case "7":
                setText3();
                holder.mTvProjectName.setText(bean.getProjectCode());
                holder.mTextItem1.setText(bean.getShPackId());
                holder.mTextItem2.setText(bean.getCargoName());
                holder.mTextItem3.setText(bean.getSendCompany());
                holder.mTextItem4.setText(bean.getReceiptCompany());
                holder.mTextItem5.setText(bean.getPackTruckDegree());
                holder.mTextItem6.setText(bean.getPackTruckNum());
                holder.mTextItem7.setText("");
                holder.mTextItem8.setText(bean.getFreightChargeAmount());
                break;
            default:
                break;
        }

        if (mOnItemClickListener != null) {
            view.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, position));
        }
    }

    @Override
    public int getItemCount() {
        return mOrderBeanList.size();
    }

    private void setText1() {
        mViewHolder.mText1.setText("打包编号");
        mViewHolder.mText2.setText("货物品名");
        mViewHolder.mText3.setText("发货单位");
        mViewHolder.mText4.setText("收货单位");
        mViewHolder.mText5.setText("包内车次");
        mViewHolder.mText6.setText("包内车辆");
        mViewHolder.mLlText1.setVisibility(View.GONE);
        mViewHolder.mLlText2.setVisibility(View.GONE);
    }

    private void setText2() {
        mViewHolder.mText1.setText("运单编号");
        mViewHolder.mText2.setText("运输时间");
        mViewHolder.mText3.setText("货物品名");
        mViewHolder.mText4.setText("发货单位");
        mViewHolder.mText5.setText("收货单位");
        mViewHolder.mText6.setText("核计金额");
        mViewHolder.mLlText1.setVisibility(View.GONE);
        mViewHolder.mLlText2.setVisibility(View.GONE);
    }

    private void setText3() {
        mViewHolder.mText1.setText("打包编号");
        mViewHolder.mText2.setText("货物品名");
        mViewHolder.mText3.setText("发货单位");
        mViewHolder.mText4.setText("收货单位");
        mViewHolder.mText5.setText("包内车次");
        mViewHolder.mText6.setText("包内车辆");
        mViewHolder.mText7.setText("本车运输");
        mViewHolder.mText8.setText("结算金额");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        @BindView(R.id.ll_project_name)
        LinearLayout mLlProjectName;
        @BindView(R.id.text_1)
        TextView mText1;
        @BindView(R.id.text_item_1)
        TextView mTextItem1;
        @BindView(R.id.text_2)
        TextView mText2;
        @BindView(R.id.text_item_2)
        TextView mTextItem2;
        @BindView(R.id.text_3)
        TextView mText3;
        @BindView(R.id.text_item_3)
        TextView mTextItem3;
        @BindView(R.id.text_4)
        TextView mText4;
        @BindView(R.id.text_item_4)
        TextView mTextItem4;
        @BindView(R.id.text_5)
        TextView mText5;
        @BindView(R.id.text_item_5)
        TextView mTextItem5;
        @BindView(R.id.text_6)
        TextView mText6;
        @BindView(R.id.text_item_6)
        TextView mTextItem6;
        @BindView(R.id.text_7)
        TextView mText7;
        @BindView(R.id.text_item_7)
        TextView mTextItem7;
        @BindView(R.id.text_8)
        TextView mText8;
        @BindView(R.id.text_item_8)
        TextView mTextItem8;
        @BindView(R.id.ll_text_1)
        LinearLayout mLlText1;
        @BindView(R.id.ll_text_2)
        LinearLayout mLlText2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
