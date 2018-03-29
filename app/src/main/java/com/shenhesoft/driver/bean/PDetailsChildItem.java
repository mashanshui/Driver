package com.shenhesoft.driver.bean;

/**
 * 作者：
 * 创作日期：2018/1/2.
 * 描述：项目详情 子级Item
 */

public class PDetailsChildItem {

    private String cName;  //item名称
    private String cValue; //item值

    private String img1;  //img1名称
    private String img2; //img2

    public PDetailsChildItem() {
    }

    public PDetailsChildItem(String cName, String cValue, String img1, String img2) {
        this.cName = cName;
        this.cValue = cValue;
        this.img1 = img1;
        this.img2 = img2;
    }

    public PDetailsChildItem(String cName, String cValue) {
        this.cName = cName;
        this.cValue = cValue;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcValue() {
        return cValue;
    }

    public void setcValue(String cValue) {
        this.cValue = cValue;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }
}
