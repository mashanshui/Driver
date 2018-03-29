package com.shenhesoft.driver.bean;

/**
 * @author 张继淮
 * @date 2018/2/1
 * @desc TODO
 */

public class UpdatalocationBean {


    public UpdatalocationBean(String carId, String lon, String lat, String position, String remark) {
        this.carId = carId;
        this.lon = lon;
        this.lat = lat;
        this.position = position;
        this.remark = remark;
    }

    /**
     * carId : AKJK547货位
     * lon : 116.398258
     * lat : 39.9146
     * position : 北京西站
     * remark : 备注
     */



    private String carId;
    private String lon;
    private String lat;
    private String position;
    private String remark;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
