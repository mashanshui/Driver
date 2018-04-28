package com.shenhesoft.driver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.LoadConditionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mashanshui
 * @date 2018/4/25
 * @desc TODO
 */
public class LoadConditionAdapter extends RecyclerView.Adapter<LoadConditionAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<LoadConditionBean.DataBean> mOrderBeanList;
    private View view;

    public LoadConditionAdapter(Context context, List<LoadConditionBean.DataBean> mOrderBeanList) {
        this.inflater = LayoutInflater.from(context);
        this.mOrderBeanList = mOrderBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.recycle_item_road_condition_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoadConditionBean.DataBean loadConditionBean = mOrderBeanList.get(position);
        holder.tvMessage.setText(loadConditionBean.getDriverMsg());
        holder.tvTime.setText(loadConditionBean.getCreateTime());
        holder.tvUsername.setText(loadConditionBean.getDriverName());
    }

    @Override
    public int getItemCount() {
        return mOrderBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
