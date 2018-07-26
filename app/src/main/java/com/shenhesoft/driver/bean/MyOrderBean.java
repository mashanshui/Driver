package com.shenhesoft.driver.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

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
     * arriveAddress : 1
     * arriveFreightSite : 
     * arriveFreightYrad : 
     * arrivePlace : 新疆,乌鲁木齐,新市区
     * arriveredImg : 917909.jpg
     * branchGroupName : 新疆秦龙矿业有限公司
     * branchId : 1
     * cancelDate : null
     * cancelReason : 
     * cancelReasonDetail : 
     * carPlateNumber : 测试车辆
     * carType : 1
     * cargoName : 1
     * carrierVehicleId : 998056
     * carrierVehicleName : 1
     * carteamId : 0
     * containerNumber1 : 
     * containerNumber1Id : 
     * containerNumber2 : 
     * containerNumber2Id : 
     * containerOneReceiptNet : 9
     * containerOneSendNet : 9
     * containerTwoReceiptNet : 0
     * containerTwoSendNet : 0
     * createDate : 2018-04-21 09:12:58
     * creatorId : 1
     * deductionPrice : 1
     * deductionRate : 1
     * deleteFlag : 0
     * deleteName : 
     * deleteReason : 
     * deleteTime : null
     * deletorId : 0
     * distributionCargoPlace : 1
     * distributionCargoPlaceId : 126
     * distributionCargoSite : 1  1
     * distributionCargoSiteId : 477
     * driverId : 998056
     * driverName : 1
     * driverPhone : 17775322135
     * editDate : null
     * exceptionId : 0
     * exceptionReoportId : 0
     * exceptionReoportName : 
     * exceptionReoportReason : 
     * exceptionStatus : 0
     * exceptionTime : null
     * id : 777
     * isCancel : 0
     * orderCode : QLWY987499399863074816
     * orderImg : 113810.jpg
     * orderOrigin : 1
     * pickupPlace : 新疆,乌鲁木齐,新市区
     * pickupPlaceAddress : 1
     * pieceNumber : 0
     * placeNowId : 0
     * projectCode : QL20180421091242
     * projectDistributionId : 362
     * projectId : 351
     * projectType : 1
     * receiptCompany : 1
     * receiptCompanyId : 66
     * receiptGross : 10
     * receiptTare : 1
     * receiptTestIndicators : 
     * receipterDate : 2018-04-21 09:13:29
     * receiveCompanyPhone : 
     * remark : 
     * sendCompany : 1
     * sendCompanyId : 66
     * sendCompanyPhone : 
     * sendGross : 10
     * sendTare : 1
     * shortBargeCost : 1
     * specifications : 1
     * status : 5
     * stepSelect : 汽运
     * stepSelectCode : 2
     * subsidy : 0
     * takeCargoPlaceId : 126
     * takeCargoSiteId : 477
     * takeCargoSiteName : 1  1
     * takeCarogoPlaceName : 1
     * takeDeliveryDate : 2018-04-21 09:13:11
     * testIndicators : 0
     * transportType : 0
     * type : 2
     * updateDate : 2018-04-21 09:13:29
     * userDispatchId : 1
     * userDispatchName : admin
     * valuationUnitType : 1
     */

    private String arriveAddress;
    private String arriveFreightSite;
    private String arriveFreightYrad;
    private String arrivePlace;
    private String arriveredImg;
    private String branchGroupName;
    private String branchId;
    private String cancelDate;
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
    private String deleteTime;
    private String deletorId;
    private String distributionCargoPlace;
    private String distributionCargoPlaceId;
    private String distributionCargoSite;
    private String distributionCargoSiteId;
    private String driverId;
    private String driverName;
    private String driverPhone;
    private String editDate;
    private String exceptionId;
    private String exceptionReoportId;
    private String exceptionReoportName;
    private String exceptionReoportReason;
    private String exceptionStatus;
    private String exceptionTime;
    private String id;
    private String isCancel;
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
    private String shouldMoney;
    /**
     * cashAmount : -1
     * cashPayType : 转账
     * checkingAuditDate : 2018-04-21 14:18:45
     * deductionPrice : 1
     * deductionRate : 1
     * freightChargeAmount : -1
     * packTruckDegree : 1
     * packTruckNum : 1
     * receiveUserName : 1
     * settleAuditDate : null
     * shPackId : 987576308194082816
     * shortBargeCost : 1
     * shortOrderFinances : [{"buckleFigure":0,"fShortBargeCost":1,"fSubsidy":0,"orderCode":"QLWY987498485504802816","orderCreateDate":"2018-04-21 09:09:20","shouldPayFigure":1}]
     * status : 6
     * suppliesAmount : 0
     * suppliesExecuteDate : null
     * suppliesReceiveType :
     */

    private String cashAmount;
    private String cashPayType;
    private String checkingAuditDate;
    private String freightChargeAmount;
    private String packTruckDegree;
    private String packTruckNum;
    private String receiveUserName;
    private String settleAuditDate;
    private String shPackId;
    private String suppliesAmount;
    private String suppliesExecuteDate;
    private String suppliesReceiveType;

    private List<ShortOrderFinancesBean> shortOrderFinances;

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

    public String getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(String shouldMoney) {
        this.shouldMoney = shouldMoney;
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

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
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

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
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

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
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

    public String getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(String exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
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

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCashPayType() {
        return cashPayType;
    }

    public void setCashPayType(String cashPayType) {
        this.cashPayType = cashPayType;
    }

    public String getCheckingAuditDate() {
        return checkingAuditDate;
    }

    public void setCheckingAuditDate(String checkingAuditDate) {
        this.checkingAuditDate = checkingAuditDate;
    }


    public String getFreightChargeAmount() {
        return freightChargeAmount;
    }

    public void setFreightChargeAmount(String freightChargeAmount) {
        this.freightChargeAmount = freightChargeAmount;
    }

    public String getPackTruckDegree() {
        return packTruckDegree;
    }

    public void setPackTruckDegree(String packTruckDegree) {
        this.packTruckDegree = packTruckDegree;
    }

    public String getPackTruckNum() {
        return packTruckNum;
    }

    public void setPackTruckNum(String packTruckNum) {
        this.packTruckNum = packTruckNum;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getSettleAuditDate() {
        return settleAuditDate;
    }

    public void setSettleAuditDate(String settleAuditDate) {
        this.settleAuditDate = settleAuditDate;
    }

    public String getShPackId() {
        return shPackId;
    }

    public void setShPackId(String shPackId) {
        this.shPackId = shPackId;
    }

    public String getSuppliesAmount() {
        return suppliesAmount;
    }

    public void setSuppliesAmount(String suppliesAmount) {
        this.suppliesAmount = suppliesAmount;
    }

    public String getSuppliesExecuteDate() {
        return suppliesExecuteDate;
    }

    public void setSuppliesExecuteDate(String suppliesExecuteDate) {
        this.suppliesExecuteDate = suppliesExecuteDate;
    }

    public String getSuppliesReceiveType() {
        return suppliesReceiveType;
    }

    public void setSuppliesReceiveType(String suppliesReceiveType) {
        this.suppliesReceiveType = suppliesReceiveType;
    }

    public List<ShortOrderFinancesBean> getShortOrderFinances() {
        return shortOrderFinances;
    }

    public void setShortOrderFinances(List<ShortOrderFinancesBean> shortOrderFinances) {
        this.shortOrderFinances = shortOrderFinances;
    }

    public static class ShortOrderFinancesBean implements Serializable{
        /**
         * buckleFigure : 0
         * fShortBargeCost : 1
         * fSubsidy : 0
         * orderCode : QLWY987498485504802816
         * orderCreateDate : 2018-04-21 09:09:20
         * shouldPayFigure : 1
         */

        private String buckleFigure;
        private String fShortBargeCost;
        private String fSubsidy;
        @SerializedName("orderCode")
        private String orderCodeX;
        private String orderCreateDate;
        private String shouldPayFigure;

        public String getBuckleFigure() {
            return buckleFigure;
        }

        public void setBuckleFigure(String buckleFigure) {
            this.buckleFigure = buckleFigure;
        }

        public String getFShortBargeCost() {
            return fShortBargeCost;
        }

        public void setFShortBargeCost(String fShortBargeCost) {
            this.fShortBargeCost = fShortBargeCost;
        }

        public String getFSubsidy() {
            return fSubsidy;
        }

        public void setFSubsidy(String fSubsidy) {
            this.fSubsidy = fSubsidy;
        }

        public String getOrderCodeX() {
            return orderCodeX;
        }

        public void setOrderCodeX(String orderCodeX) {
            this.orderCodeX = orderCodeX;
        }

        public String getOrderCreateDate() {
            return orderCreateDate;
        }

        public void setOrderCreateDate(String orderCreateDate) {
            this.orderCreateDate = orderCreateDate;
        }

        public String getShouldPayFigure() {
            return shouldPayFigure;
        }

        public void setShouldPayFigure(String shouldPayFigure) {
            this.shouldPayFigure = shouldPayFigure;
        }
    }
}