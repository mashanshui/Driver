package com.shenhesoft.driver.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：Tornado
 * 创作日期：2017/7/12.
 * 描述：通用的ViewHolder 减少重复代码
 */

public class ViewHolder {


    public static <T extends View> T get(View view, int id) {
        SparseArray viewHolder = (SparseArray) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray();
            view.setTag(viewHolder);
        }
        View childView = (View) viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public static void setText(View view, int id, String content) {
        TextView text = get(view, id);
        text.setText(content);
    }

}
