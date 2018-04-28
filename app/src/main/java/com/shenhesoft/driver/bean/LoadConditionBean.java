package com.shenhesoft.driver.bean;

import java.util.List;

/**
 * @author mashanshui
 * @date 2018/4/25
 * @desc TODO
 */
public class LoadConditionBean {
    /**
     * data : [{"driverId":"998059","createTime":"2018-04-25 10:19:44","driverMsg":"hgf ghfgfhb","driverName":"2","remark":"","id":"988965756056109056"},{"driverId":"998059","createTime":"2018-04-25 10:19:31","driverMsg":"asfasfdsf","driverName":"2","remark":"","id":"988965698317320192"},{"driverId":"998059","createTime":"2018-04-25 10:17:54","driverMsg":"sd","driverName":"2","remark":"","id":"988965290685497344"},{"driverId":"1","createTime":"2018-04-24 19:36:41","driverMsg":"冬至路与黄山路交叉口，交通拥堵","driverName":"司机老wang","remark":"","id":"988744945969922048"},{"driverId":"1","createTime":"2018-04-24 19:35:35","driverMsg":"长丰南路与肥西路交叉口，交通拥堵","driverName":"司机老张","remark":"","id":"988744668852256768"}]
     * recordsTotal : 5
     * recordsFiltered : 5
     */

    private int recordsTotal;
    private int recordsFiltered;
    private List<DataBean> data;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * driverId : 998059
         * createTime : 2018-04-25 10:19:44
         * driverMsg : hgf ghfgfhb
         * driverName : 2
         * remark :
         * id : 988965756056109056
         */

        private String driverId;
        private String createTime;
        private String driverMsg;
        private String driverName;
        private String remark;
        private String id;

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDriverMsg() {
            return driverMsg;
        }

        public void setDriverMsg(String driverMsg) {
            this.driverMsg = driverMsg;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
