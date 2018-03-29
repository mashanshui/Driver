package com.shenhesoft.driver.bean;

import java.io.Serializable;

/**
 * @author 马山水
 * @date 2018/3/19
 * @desc TODO
 * 这个bean是获取订单信息返回使用的，之前有一个TaskBean是“发货上传，到货通知，卸货上传”使用的
 * 现在扩展我的运单模块使用了同一个接口，但是返回的数据增加，我这里新建了一个bean，但是以前的TaskBean还在使用
 * 防止搞混，这里写个注释，就是同一个接口返回的值使用了两个不同的bean接收
 */

public class MyOrderBean implements Serializable {

    /**
     * arriveAddress : G218国道（巴伦台镇北120公里处）
     * arriveFreightSite :
     * arriveFreightYrad :
     * arrivePlace : 新疆巴音郭楞和静县
     * arriveredImg : 220420.png
     * branchGroupName : 新疆秦龙矿业有限公司
     * branchId : 1
     * cancelDate : null
     * cancelReason :
     * cancelReasonDetail :
     * carPlateNumber : 这是车牌号
     * carType : 这是车型
     * cargoName : 铁精粉
     * carrierVehicleId : 997995
     * carrierVehicleName : 这是姓名
     * carteamId : 0
     * containerNumber1 : 231
     * containerNumber1Id :
     * containerNumber2 :
     * containerNumber2Id :
     * containerOneReceiptNet : 10
     * containerOneSendNet : 10
     * containerTwoReceiptNet : 0
     * containerTwoSendNet : 0
     * createDate : 2018-03-05 11:24:20
     * creatorId : 1
     * deductionPrice : 1
     * deductionRate : 10
     * deleteFlag : 0
     * deleteName :
     * deleteReason :
     * deleteTime : null
     * deletorId : 0
     * distributionCargoPlace : 敦德货场
     * distributionCargoPlaceId : 73
     * distributionCargoSite : 敦德货场  敦德货场
     * distributionCargoSiteId : 250
     * driverId : 997995
     * driverName : 这是姓名
     * driverPhone : 18355187482
     * editDate : null
     * exceptionId : 0
     * exceptionReoportId : 0
     * exceptionReoportName :
     * exceptionReoportReason :
     * exceptionStatus : 0
     * exceptionTime : null
     * id : 198
     * isCancel : 0
     * orderCode : QLWY970500229998575616
     * orderImg : 938397.png
     * orderOrigin : 1
     * pickupPlace : 新疆巴音郭楞和静县
     * pickupPlaceAddress : G218国道（巴伦台镇北120公里处）
     * pieceNumber : 0
     * placeNowId : 0
     * projectCode : QL20180305111158
     * projectDistributionId : 129
     * projectId : 155
     * projectType : 0
     * receiptCompany : 巴州敦德矿业有限公司
     * receiptCompanyId : 30
     * receiptGross : 10
     * receiptTare : 0
     * receiptTestIndicators :
     * receipterDate : 2018-03-05 11:25:19
     * receiveCompanyPhone :
     * remark :
     * sendCompany : 巴州敦德矿业有限公司
     * sendCompanyId : 30
     * sendCompanyPhone :
     * sendGross : 10
     * sendTare : 0
     * shortBargeCost : 10
     * specifications : 粉末
     * status : 5
     * stepSelect : 汽运
     * stepSelectCode : 2
     * subsidy : 0
     * takeCargoPlaceId : 73
     * takeCargoSiteId : 250
     * takeCargoSiteName : 敦德货场  敦德货场
     * takeCarogoPlaceName : 敦德货场
     * takeDeliveryDate : 2018-03-05 11:24:43
     * testIndicators : 1
     * transportType : 0
     * type : 1
     * updateDate : 2018-03-05 11:25:19
     * userDispatchId : 1
     * userDispatchName : admin
     * valuationUnitType : 0
     */

    private String arriveAddress;
    private String arriveFreightSite;
    private String arriveFreightYrad;
    private String arrivePlace;
    private String arriveredImg;
    private String branchGroupName;
    private String branchId;
    private Object cancelDate;
    private String cancelReason;
    private String cancelReasonDetail;
    private String carPlateNumber;
    private String carType;
    private String cargoName;
    private String carrierVehicleId;
    private String carrierVehicleName;
    private String carteamId;
    private String containerNumber1;
    private String containerNumber1Id;
    private String containerNumber2;
    private String containerNumber2Id;
    private String containerOneReceiptNet;
    private String containerOneSendNet;
    private String containerTwoReceiptNet;
    private String containerTwoSendNet;
    private String createDate;
    private String creatorId;
    private String deductionPrice;
    private String deductionRate;
    private String deleteFlag;
    private String deleteName;
    private String deleteReason;
    private Object deleteTime;
    private String deletorId;
    private String distributionCargoPlace;
    private String distributionCargoPlaceId;
    private String distributionCargoSite;
    private String distributionCargoSiteId;
    private String driverId;
    private String driverName;
    private String driverPhone;
    private Object editDate;
    private String exceptionId;
    private String exceptionReoportId;
    private String exceptionReoportName;
    private String exceptionReoportReason;
    private String exceptionStatus;
    private Object exceptionTime;
    private int id;
    private int isCancel;
    private String orderCode;
    private String orderImg;
    private String orderOrigin;
    private String pickupPlace;
    private String pickupPlaceAddress;
    private String pieceNumber;
    private String placeNowId;
    private String projectCode;
    private String projectDistributionId;
    private String projectId;
    private String projectType;
    private String receiptCompany;
    private String receiptCompanyId;
    private String receiptGross;
    private String receiptTare;
    private String receiptTestIndicators;
    private String receipterDate;
    private String receiveCompanyPhone;
    private String remark;
    private String sendCompany;
    private String sendCompanyId;
    private String sendCompanyPhone;
    private String sendGross;
    private String sendTare;
    private String shortBargeCost;
    private String specifications;
    private String status;
    private String stepSelect;
    private String stepSelectCode;
    private String subsidy;
    private String takeCargoPlaceId;
    private String takeCargoSiteId;
    private String takeCargoSiteName;
    private String takeCarogoPlaceName;
    private String takeDeliveryDate;
    private String testIndicators;
    private String transportType;
    private String type;
    private String updateDate;
    private String userDispatchId;
    private String userDispatchName;
    private String valuationUnitType;

    public String getArriveAddress() {
        return arriveAddress;
    }

    public void setArriveAddress(String arriveAddress) {
        this.arriveAddress = arriveAddress;
    }

    public String getArriveFreightSite() {
        return arriveFreightSite;
    }

    public void setArriveFreightSite(String arriveFreightSite) {
        this.arriveFreightSite = arriveFreightSite;
    }

    public String getArriveFreightYrad() {
        return arriveFreightYrad;
    }

    public void setArriveFreightYrad(String arriveFreightYrad) {
        this.arriveFreightYrad = arriveFreightYrad;
    }

    public String getArrivePlace() {
        return arrivePlace;
    }

    public void setArrivePlace(String arrivePlace) {
        this.arrivePlace = arrivePlace;
    }

    public String getArriveredImg() {
        return arriveredImg;
    }

    public void setArriveredImg(String arriveredImg) {
        this.arriveredImg = arriveredImg;
    }

    public String getBranchGroupName() {
        return branchGroupName;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Object getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Object cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelReasonDetail() {
        return cancelReasonDetail;
    }

    public void setCancelReasonDetail(String cancelReasonDetail) {
        this.cancelReasonDetail = cancelReasonDetail;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCarrierVehicleId() {
        return carrierVehicleId;
    }

    public void setCarrierVehicleId(String carrierVehicleId) {
        this.carrierVehicleId = carrierVehicleId;
    }

    public String getCarrierVehicleName() {
        return carrierVehicleName;
    }

    public void setCarrierVehicleName(String carrierVehicleName) {
        this.carrierVehicleName = carrierVehicleName;
    }

    public String getCarteamId() {
        return carteamId;
    }

    public void setCarteamId(String carteamId) {
        this.carteamId = carteamId;
    }

    public String getContainerNumber1() {
        return containerNumber1;
    }

    public void setContainerNumber1(String containerNumber1) {
        this.containerNumber1 = containerNumber1;
    }

    public String getContainerNumber1Id() {
        return containerNumber1Id;
    }

    public void setContainerNumber1Id(String containerNumber1Id) {
        this.containerNumber1Id = containerNumber1Id;
    }

    public String getContainerNumber2() {
        return containerNumber2;
    }

    public void setContainerNumber2(String containerNumber2) {
        this.containerNumber2 = containerNumber2;
    }

    public String getContainerNumber2Id() {
        return containerNumber2Id;
    }

    public void setContainerNumber2Id(String containerNumber2Id) {
        this.containerNumber2Id = containerNumber2Id;
    }

    public String getContainerOneReceiptNet() {
        return containerOneReceiptNet;
    }

    public void setContainerOneReceiptNet(String containerOneReceiptNet) {
        this.containerOneReceiptNet = containerOneReceiptNet;
    }

    public String getContainerOneSendNet() {
        return containerOneSendNet;
    }

    public void setContainerOneSendNet(String containerOneSendNet) {
        this.containerOneSendNet = containerOneSendNet;
    }

    public String getContainerTwoReceiptNet() {
        return containerTwoReceiptNet;
    }

    public void setContainerTwoReceiptNet(String containerTwoReceiptNet) {
        this.containerTwoReceiptNet = containerTwoReceiptNet;
    }

    public String getContainerTwoSendNet() {
        return containerTwoSendNet;
    }

    public void setContainerTwoSendNet(String containerTwoSendNet) {
        this.containerTwoSendNet = containerTwoSendNet;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDeductionPrice() {
        return deductionPrice;
    }

    public void setDeductionPrice(String deductionPrice) {
        this.deductionPrice = deductionPrice;
    }

    public String getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        this.deductionRate = deductionRate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Object getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Object deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeletorId() {
        return deletorId;
    }

    public void setDeletorId(String deletorId) {
        this.deletorId = deletorId;
    }

    public String getDistributionCargoPlace() {
        return distributionCargoPlace;
    }

    public void setDistributionCargoPlace(String distributionCargoPlace) {
        this.distributionCargoPlace = distributionCargoPlace;
    }

    public String getDistributionCargoPlaceId() {
        return distributionCargoPlaceId;
    }

    public void setDistributionCargoPlaceId(String distributionCargoPlaceId) {
        this.distributionCargoPlaceId = distributionCargoPlaceId;
    }

    public String getDistributionCargoSite() {
        return distributionCargoSite;
    }

    public void setDistributionCargoSite(String distributionCargoSite) {
        this.distributionCargoSite = distributionCargoSite;
    }

    public String getDistributionCargoSiteId() {
        return distributionCargoSiteId;
    }

    public void setDistributionCargoSiteId(String distributionCargoSiteId) {
        this.distributionCargoSiteId = distributionCargoSiteId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Object getEditDate() {
        return editDate;
    }

    public void setEditDate(Object editDate) {
        this.editDate = editDate;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getExceptionReoportId() {
        return exceptionReoportId;
    }

    public void setExceptionReoportId(String exceptionReoportId) {
        this.exceptionReoportId = exceptionReoportId;
    }

    public String getExceptionReoportName() {
        return exceptionReoportName;
    }

    public void setExceptionReoportName(String exceptionReoportName) {
        this.exceptionReoportName = exceptionReoportName;
    }

    public String getExceptionReoportReason() {
        return exceptionReoportReason;
    }

    public void setExceptionReoportReason(String exceptionReoportReason) {
        this.exceptionReoportReason = exceptionReoportReason;
    }

    public String getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public Object getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(Object exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderImg() {
        return orderImg;
    }

    public void setOrderImg(String orderImg) {
        this.orderImg = orderImg;
    }

    public String getOrderOrigin() {
        return orderOrigin;
    }

    public void setOrderOrigin(String orderOrigin) {
        this.orderOrigin = orderOrigin;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getPickupPlaceAddress() {
        return pickupPlaceAddress;
    }

    public void setPickupPlaceAddress(String pickupPlaceAddress) {
        this.pickupPlaceAddress = pickupPlaceAddress;
    }

    public String getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(String pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public String getPlaceNowId() {
        return placeNowId;
    }

    public void setPlaceNowId(String placeNowId) {
        this.placeNowId = placeNowId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectDistributionId() {
        return projectDistributionId;
    }

    public void setProjectDistributionId(String projectDistributionId) {
        this.projectDistributionId = projectDistributionId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getReceiptCompany() {
        return receiptCompany;
    }

    public void setReceiptCompany(String receiptCompany) {
        this.receiptCompany = receiptCompany;
    }

    public String getReceiptCompanyId() {
        return receiptCompanyId;
    }

    public void setReceiptCompanyId(String receiptCompanyId) {
        this.receiptCompanyId = receiptCompanyId;
    }

    public String getReceiptGross() {
        return receiptGross;
    }

    public void setReceiptGross(String receiptGross) {
        this.receiptGross = receiptGross;
    }

    public String getReceiptTare() {
        return receiptTare;
    }

    public void setReceiptTare(String receiptTare) {
        this.receiptTare = receiptTare;
    }

    public String getReceiptTestIndicators() {
        return receiptTestIndicators;
    }

    public void setReceiptTestIndicators(String receiptTestIndicators) {
        this.receiptTestIndicators = receiptTestIndicators;
    }

    public String getReceipterDate() {
        return receipterDate;
    }

    public void setReceipterDate(String receipterDate) {
        this.receipterDate = receipterDate;
    }

    public String getReceiveCompanyPhone() {
        return receiveCompanyPhone;
    }

    public void setReceiveCompanyPhone(String receiveCompanyPhone) {
        this.receiveCompanyPhone = receiveCompanyPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendCompany() {
        return sendCompany;
    }

    public void setSendCompany(String sendCompany) {
        this.sendCompany = sendCompany;
    }

    public String getSendCompanyId() {
        return sendCompanyId;
    }

    public void setSendCompanyId(String sendCompanyId) {
        this.sendCompanyId = sendCompanyId;
    }

    public String getSendCompanyPhone() {
        return sendCompanyPhone;
    }

    public void setSendCompanyPhone(String sendCompanyPhone) {
        this.sendCompanyPhone = sendCompanyPhone;
    }

    public String getSendGross() {
        return sendGross;
    }

    public void setSendGross(String sendGross) {
        this.sendGross = sendGross;
    }

    public String getSendTare() {
        return sendTare;
    }

    public void setSendTare(String sendTare) {
        this.sendTare = sendTare;
    }

    public String getShortBargeCost() {
        return shortBargeCost;
    }

    public void setShortBargeCost(String shortBargeCost) {
        this.shortBargeCost = shortBargeCost;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStepSelect() {
        return stepSelect;
    }

    public void setStepSelect(String stepSelect) {
        this.stepSelect = stepSelect;
    }

    public String getStepSelectCode() {
        return stepSelectCode;
    }

    public void setStepSelectCode(String stepSelectCode) {
        this.stepSelectCode = stepSelectCode;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getTakeCargoPlaceId() {
        return takeCargoPlaceId;
    }

    public void setTakeCargoPlaceId(String takeCargoPlaceId) {
        this.takeCargoPlaceId = takeCargoPlaceId;
    }

    public String getTakeCargoSiteId() {
        return takeCargoSiteId;
    }

    public void setTakeCargoSiteId(String takeCargoSiteId) {
        this.takeCargoSiteId = takeCargoSiteId;
    }

    public String getTakeCargoSiteName() {
        return takeCargoSiteName;
    }

    public void setTakeCargoSiteName(String takeCargoSiteName) {
        this.takeCargoSiteName = takeCargoSiteName;
    }

    public String getTakeCarogoPlaceName() {
        return takeCarogoPlaceName;
    }

    public void setTakeCarogoPlaceName(String takeCarogoPlaceName) {
        this.takeCarogoPlaceName = takeCarogoPlaceName;
    }

    public String getTakeDeliveryDate() {
        return takeDeliveryDate;
    }

    public void setTakeDeliveryDate(String takeDeliveryDate) {
        this.takeDeliveryDate = takeDeliveryDate;
    }

    public String getTestIndicators() {
        return testIndicators;
    }

    public void setTestIndicators(String testIndicators) {
        this.testIndicators = testIndicators;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserDispatchId() {
        return userDispatchId;
    }

    public void setUserDispatchId(String userDispatchId) {
        this.userDispatchId = userDispatchId;
    }

    public String getUserDispatchName() {
        return userDispatchName;
    }

    public void setUserDispatchName(String userDispatchName) {
        this.userDispatchName = userDispatchName;
    }

    public String getValuationUnitType() {
        return valuationUnitType;
    }

    public void setValuationUnitType(String valuationUnitType) {
        this.valuationUnitType = valuationUnitType;
    }
}
