package com.shenhesoft.driver.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者：Tornado
 * 创作日期：2017/7/17.
 * 描述：RecyclerView的通用ViewHolder
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views; //viewItem的控件集合
    private Context context;

    private RecyclerViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    public static RecyclerViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    public static RecyclerViewHolder getViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    /**
     * 获取itemView的控件
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView文本
     */
    public RecyclerViewHolder setText(int textId, String content) {
        TextView tvTxt = getView(textId);
        tvTxt.setText(content);
        return this;
    }

    /**
     * 设置TextView文本
     */
    public RecyclerViewHolder setText(int textId, String content, int color) {
        TextView tvTxt = getView(textId);
        tvTxt.setText(content);
        tvTxt.setTextColor(color);
        return this;
    }

    /**
     * 设置ImageView加载本地图片
     */
    public RecyclerViewHolder setImageResource(int imgId, int drawableId) {
        ImageView img = getView(imgId);
        img.setImageResource(drawableId);
        return this;
    }

//    /**
//     * 设置SimpleDraweeView加载Url
//     */
//    public RecyclerViewHolder setSimpleDraweeViewUrl(int viewId, String url) {
//        SimpleDraweeView sdv = getView(R.id.item_news_list_img);
//        sdv.setImageURI(url);
//        return this;
//    }


}
