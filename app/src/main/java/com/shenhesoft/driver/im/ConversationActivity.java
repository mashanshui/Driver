package com.shenhesoft.driver.im;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.shenhesoft.driver.R;
import com.shenhesoft.driver.base.BaseActivity;

/**
 * @author 张继淮
 * @date 2018/1/18
 * @desc TODO
 */

public class ConversationActivity extends BaseActivity {
    String title;

    @Override
    protected void initTitle() {
        title = getIntent().getData().getQueryParameter("title");
        if (title == null) {
            title = "";
        }
        setTitle(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.conversation;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

}