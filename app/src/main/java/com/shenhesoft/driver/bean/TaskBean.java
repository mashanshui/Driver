package com.shenhesoft.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhangzhenrui on 2018/1/15.
 */
//    bean.setmProjectNum("J12345678");
//            bean.setmTakeCity("中国人民共和国");
//            bean.setmArriCity("美利坚合众国");
//            bean.setmTakeCompany("测试吹牛逼公司");
//            bean.setmArriCompany("傻逼美国公司");
//            bean.setmProjectName("手机");
//            bean.setmProjectPrice("30元");
//            bean.setmLossPrice("300元");
//            bean.setmProjectRect("1x2");
//            bean.setmLossProportion("0.3%");

public class TaskBean implements Serializable {

    //除了订单id没别的数据
    boolean nodata;

    //订单id
    @SerializedName(value = "orderId", alternate = "id")
    private String orderId;

    //项目分配id
    @SerializedName("projectDistributionId")
    private String pro_dist_id;

    //项目编号
    @SerializedName("projectCode")
    private String mProjectNum;

    //调度员手机号
    @SerializedName("dispatchPhone")
    private String dispatchPhone;


    //发货货场
    @SerializedName("takeCarogoPlaceName")
    private String sendyard;

    //发货货位
    @SerializedName("takeCargoSiteName")
    private String sendsite;

    //到货货场
    @SerializedName("distributionCargoPlace")
    private String receiptyard;

    //到货货位
    @SerializedName("distributionCargoSite")
    private String receiptsite;


    //发货城市
    @SerializedName("sendCity")
    private String sendCity;

    //收货城市
    @SerializedName("receiptCity")
    private String receiptCity;

    //取货地
    @SerializedName("pickupPlace")
    private String mTakeCity;

    //取货地址
    @SerializedName("pickupPlaceAddress")
    private String pickupPlaceAddress;


    //运抵地
    @SerializedName("arrivePlace")
    private String mArriCity;

    //运抵地址
    @SerializedName("arriveAddress")
    private String arriveAddress;


    //发送公司
    @SerializedName("sendCompany")
    private String mTakeCompany;

    //收货单位
    @SerializedName("receiptCompany")
    private String mArriCompany;

    //货物品名
    @SerializedName("cargoName")
    private String mProjectName;

    //运输费用
    @SerializedName("shortBargeCost")
    private String mProjectPrice;

    //补贴
    @SerializedName("subsidy")
    private String subsidy;

    //扣损单价
    @SerializedName("deductionPrice")
    private String mLossPrice;

    //扣损系数
    @SerializedName("deductionRate")
    private String mLossProportion;

    //扣损方式
    @SerializedName("xxx")
    private String lossWay;

    //计量方式
    @SerializedName("valuationUnitType")
    private String valuationUnitType;

    //规格
    @SerializedName("cargoSpecifications")
    private String mProjectRect;

    //执行状态
    @SerializedName("status")
    private String status;

    @SerializedName("projectType")
    private String projectType; //项目类型


    @SerializedName("payment")
    private String payWay;  //付款方式

    @SerializedName("containerNumber1")
    private String containerNumber1;   //集装箱1的名字


    @SerializedName("containerNumber2")
    private String containerNumber2;   //集装箱2的名字

    @SerializedName("receiptTare")
    private double receiptTare;        //到货载重

    public String getContainerNumber1() {
        return containerNumber1;
    }

    public void setContainerNumber1(String containerNumber1) {
        this.containerNumber1 = containerNumber1;
    }

    public double getReceiptTare() {
        return receiptTare;
    }

    public void setReceiptTare(double receiptTare) {
        this.receiptTare = receiptTare;
    }

    public String getContainerNumber2() {
        return containerNumber2;
    }

    public void setContainerNumber2(String containerNumber2) {
        this.containerNumber2 = containerNumber2;
    }

    public String getValuationUnitType() {
        return valuationUnitType;
    }

    public void setValuationUnitType(String valuationUnitType) {
        this.valuationUnitType = valuationUnitType;
    }

    public boolean isNodata() {
        return nodata;
    }

    public void setNodata(boolean nodata) {
        this.nodata = nodata;
    }


    public String getSendyard() {
        return sendyard;
    }

    public void setSendyard(String sendyard) {
        this.sendyard = sendyard;
    }

    public String getSendsite() {
        return sendsite;
    }

    public void setSendsite(String sendsite) {
        this.sendsite = sendsite;
    }

    public String getReceiptyard() {
        return receiptyard;
    }

    public void setReceiptyard(String receiptyard) {
        this.receiptyard = receiptyard;
    }

    public String getReceiptsite() {
        return receiptsite;
    }

    public void setReceiptsite(String receiptsite) {
        this.receiptsite = receiptsite;
    }

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    public String getReceiptCity() {
        return receiptCity;
    }

    public void setReceiptCity(String receiptCity) {
        this.receiptCity = receiptCity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDispatchPhone() {
        return dispatchPhone;
    }

    public void setDispatchPhone(String dispatchPhone) {
        this.dispatchPhone = dispatchPhone;
    }

    public String getPro_dist_id() {
        return pro_dist_id;
    }

    public void setPro_dist_id(String pro_dist_id) {
        this.pro_dist_id = pro_dist_id;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getPickupPlaceAddress() {
        return pickupPlaceAddress;
    }

    public void setPickupPlaceAddress(String pickupPlaceAddress) {
        this.pickupPlaceAddress = pickupPlaceAddress;
    }

    public String getArriveAddress() {
        return arriveAddress;
    }

    public String getLossWay() {
        return lossWay;
    }

    public void setLossWay(String lossWay) {
        this.lossWay = lossWay;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }


    public void setArriveAddress(String arriveAddress) {
        this.arriveAddress = arriveAddress;
    }

    public String getProjectNum() {
        return mProjectNum;
    }

    public void setProjectNum(String projectNum) {
        mProjectNum = projectNum;
    }

    public String getTakeCity() {
        return mTakeCity;
    }

    public void setTakeCity(String takeCity) {
        mTakeCity = takeCity;
    }

    public String getArriCity() {
        return mArriCity;
    }

    public void setArriCity(String arriCity) {
        mArriCity = arriCity;
    }

    public String getTakeCompany() {
        return mTakeCompany;
    }

    public void setTakeCompany(String takeCompany) {
        mTakeCompany = takeCompany;
    }

    public String getArriCompany() {
        return mArriCompany;
    }

    public void setArriCompany(String arriCompany) {
        mArriCompany = arriCompany;
    }

    public String getProjectName() {
        return mProjectName;
    }

    public void setProjectName(String projectName) {
        mProjectName = projectName;
    }

    public String getProjectPrice() {
        return mProjectPrice;
    }

    public void setProjectPrice(String projectPrice) {
        mProjectPrice = projectPrice;
    }

    public String getLossPrice() {
        return mLossPrice;
    }

    public void setLossPrice(String lossPrice) {
        mLossPrice = lossPrice;
    }

    public String getLossProportion() {
        return mLossProportion;
    }

    public void setLossProportion(String lossProportion) {
        mLossProportion = lossProportion;
    }

    public String getProjectRect() {
        return mProjectRect;
    }

    public void setProjectRect(String projectRect) {
        mProjectRect = projectRect;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getmProjectNum() {
        return mProjectNum;
    }

    public void setmProjectNum(String mProjectNum) {
        this.mProjectNum = mProjectNum;
    }
}
