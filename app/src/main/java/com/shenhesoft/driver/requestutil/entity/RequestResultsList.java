package com.shenhesoft.driver.requestutil.entity;


import java.util.List;

/**
 * @author 张继淮
 * @date 2017/9/28
 * @description 网络请求返回类型类
 */

public class RequestResultsList<T> {
    private int state;
    private String msg;
    private List<T> obj;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getObj() {
        return obj;
    }

    public void setObj(List<T> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Response{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }
}
