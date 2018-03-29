package com.shenhesoft.driver.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;


/**
 * 作者：Tornado
 * 创作日期：2017/7/20.
 * 描述：
 */

public class CommonAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>
        implements View.OnClickListener {
    private ProgressDialog loadingdialog;
    protected String type;
    protected List<T> data;
    protected Context context;
    private int itemLayoutId;
    protected OnItemClickListener itemClickListener;

    /**
     * item 单击事件
     */
    public interface OnItemClickListener {
        void OnClick(int position);
    }

    public void ShowDailog() {
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(context);
        }
        loadingdialog.setMessage("正在提交申请");
        loadingdialog.show();

    }

    public void ShowDailog(String msg) {
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(context);
        }
        loadingdialog.setMessage(msg);
        loadingdialog.show();

    }

    public ProgressDialog getLoadingdialog() {
        return loadingdialog;
    }


    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CommonAdapter(List<T> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public CommonAdapter(List<T> data, Context context, String type) {
        this.data = data;
        this.context = context;
        this.type = type;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (itemLayoutId == -1) {
            throw new RuntimeException("请在onCreateViewHolder中调用setItemLayoutId()方法设置item布局");
        }
        return RecyclerViewHolder.getViewHolder(parent.getContext(), parent, itemLayoutId);
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.OnClick((Integer) v.getTag());
        }
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    public void setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
