package com.shenhesoft.driver.activity.task;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.bean.TaskBean;
import com.shenhesoft.driver.requestutil.ApiRetrofit;
import com.shenhesoft.driver.requestutil.HttpManager;
import com.shenhesoft.driver.requestutil.HttpObserver;
import com.shenhesoft.driver.requestutil.entity.RequestResults;
import com.shenhesoft.driver.requestutil.entity.RequestResultsList;
import com.shenhesoft.driver.utils.AppUtil;
import com.shenhesoft.driver.utils.IToast;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by zmx on 2018/1/15.
 */


public class TaskAdapter extends Adapter<TaskAdapter.ViewHolder> {
    private List<TaskBean> taskBeans;
    private LayoutInflater inflater;
    private cancelClick mCancelClick;
    private String type;
    private Context mContext;

    public TaskAdapter(Context context, List<TaskBean> taskBeans, cancelClick cancelClick, String type) {
        this.taskBeans = taskBeans;
        this.inflater = LayoutInflater.from(context);
        this.mCancelClick = cancelClick;
        this.type = type;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_task, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TaskBean bean = taskBeans.get(position);
        holder.mTvProjectNum.setText(bean.getProjectNum());
        holder.mTvTakeCity.setText(bean.getTakeCity());
        holder.mTvArriCity.setText(bean.getArriCity());
        holder.mTvTakeCompany.setText(bean.getTakeCompany());
        holder.mTvArriCompany.setText(bean.getArriCompany());
        holder.mTvProjectPrice.setText(bean.getProjectPrice());
        holder.mTvProjectlossPrice.setText(bean.getLossPrice());
        holder.mTvProjectName.setText(bean.getProjectName());
        holder.mTvLossProportion.setText(bean.getLossProportion());
        holder.mTvProjectRect.setText(bean.getProjectRect());

        String statuse = "";
        /** 1:等待调度
         2:等待发运
         3:在途运载
         4:货位引导
         5:等待回单
         6:等待确认
         7:已完成*/

        if (bean.getStatus().equals("1")) {
            statuse = "等待调度";
        } else if (bean.getStatus().equals("2")) {
            statuse = "等待发运";
        } else if (bean.getStatus().equals("3")) {
            statuse = "在途运载";
        } else if (bean.getStatus().equals("4")) {
            statuse = "货位引导";
        } else if (bean.getStatus().equals("5")) {
            statuse = "等待回单";
        } else if (bean.getStatus().equals("6")) {
            statuse = "等待确认";
        } else if (bean.getStatus().equals("7")) {
            statuse = "已完成";
        } else {
            statuse = "未接单";
        }

        holder.mTvStatus.setText(statuse);

        holder.mIvContanct.setOnClickListener(v -> {
            AppUtil.call(mContext, bean.getDispatchPhone());
        });

        //type 平台任务 执行中  已取消
        if (type.equals("平台任务")) {
            holder.mTvCancel.setText("申请接单");
            holder.mTvCancel.setBackgroundResource(R.drawable.btn_tv_background);
        } else if (type.equals("执行中")) {
            holder.mTvCancel.setText("取消执行");
            holder.mTvCancel.setBackgroundResource(R.drawable.btn_tv_background_red);
        } else {
            holder.mTvCancel.setText("已取消");
            holder.mTvCancel.setBackgroundResource(R.drawable.btn_tv_background_gray);
        }

        holder.mTvCancel.setOnClickListener(v -> {

            if (type.equals("平台任务")) {
                mCancelClick.onClick(v, position, 0);
            } else if (type.equals("执行中")) {
                mCancelClick.onClick(v, position, 1);
            } else {
                mCancelClick.onClick(v, position, 2);
            }


        });

        holder.mClRoot.setOnClickListener(v -> {

            mCancelClick.onClick(v, position, 10);
        });


    }


    @Override
    public int getItemCount() {
        return taskBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mClRoot;
        private TextView mTvProjectNum;
        private TextView mTvTakeCity;
        private TextView mTvArriCity;
        private TextView mTvTakeCompany;
        private TextView mTvArriCompany;
        private TextView mTvProjectName;
        private TextView mTvProjectPrice;
        private TextView mTvProjectlossPrice;
        private TextView mTvLossProportion;
        private TextView mTvProjectRect;
        private TextView mTvCancel;
        private TextView mTvStatus;
        private ImageView mIvContanct;


        private ViewHolder(View itemView) {
            super(itemView);
            mTvProjectNum = itemView.findViewById(R.id.tv_project_num);
            mTvTakeCity = itemView.findViewById(R.id.tv_take_city);
            mTvArriCity = itemView.findViewById(R.id.tv_arrived_city);
            mTvTakeCompany = itemView.findViewById(R.id.tv_take_company);
            mTvArriCompany = itemView.findViewById(R.id.tv_arrived_company);
            mTvProjectName = itemView.findViewById(R.id.tv_goods_name);
            mTvProjectPrice = itemView.findViewById(R.id.tv_transpot_price);
            mTvProjectlossPrice = itemView.findViewById(R.id.tv_loss_price);
            mTvLossProportion = itemView.findViewById(R.id.tv_loss_proportion);
            mTvProjectRect = itemView.findViewById(R.id.tv_good_spec);
            mTvCancel = itemView.findViewById(R.id.cancel_execution);
            mTvStatus = itemView.findViewById(R.id.execution_status);
            mIvContanct = itemView.findViewById(R.id.img_contact_person);
            mClRoot = itemView.findViewById(R.id.item_root);

        }
    }

    public interface cancelClick {
        void onClick(View view, int position, int type);
    }


}
