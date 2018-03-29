package com.shenhesoft.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author 张明星
 * @date 2017/12/4
 * @desc 定位界面查询项目实体类
 */

public class UserinfoBean implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String name;

    //用户头像
    @SerializedName("userIcon")
    private String userIcon;

    //订单id
    @SerializedName("orderId")
    private String orderId;

    //总运单
    @SerializedName("orderNum")
    private String orderNum;

    //总收入
    @SerializedName("sumIncome")
    private String sumIncome;

    @SerializedName("informationId")
    private String informationId;

    @SerializedName("rongCloudToken")
    private String rongCloudToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSumIncome() {
        return sumIncome;
    }

    public void setSumIncome(String sumIncome) {
        this.sumIncome = sumIncome;
    }

    public String getRongCloudToken() {
        return rongCloudToken;
    }

    public void setRongCloudToken(String rongCloudToken) {
        this.rongCloudToken = rongCloudToken;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
