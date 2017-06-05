package com.feelcolor.website.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceAgentRel {
    private String deviceId;

    private String deviceName;

    private Integer agentId;

    private Date bindTime;

    private String deviceType;

    private Integer groupareaId;

    private Integer provinceId;

    private Integer cityId;

    private Integer countyId;

    private String detailaddress;

    private String tradeId;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer pedestrianvolume;

    private BigDecimal floorspace;

    private String customerType;

    private String fixposition;

    private Date createtime;

    private Date lastupdatetime;

    private String rzbh;

    private Date crmSyncTime;

    private String deviceFrozenStatus;

    private Integer screenInstallId;

    private String screenInstallName;

    private String screenInstallJobNum;

    private String screenInstallMobileNum;

    private Integer operatorUserId;

    private String operatorName;

    private String operatorJobNum;

    private String operatorMobileNum;

    private Date brushTime;

    private String agentDockName;

    private String agentWechat;

    private String agentDockMobile;

    private Date lastEditTime;

    private String importRemark;

    private Integer completeStatus;

    private String deviceVersion;

    private Date lastHeartDatetime;

    private Integer modelShopFlag;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Integer getGroupareaId() {
        return groupareaId;
    }

    public void setGroupareaId(Integer groupareaId) {
        this.groupareaId = groupareaId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress == null ? null : detailaddress.trim();
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getPedestrianvolume() {
        return pedestrianvolume;
    }

    public void setPedestrianvolume(Integer pedestrianvolume) {
        this.pedestrianvolume = pedestrianvolume;
    }

    public BigDecimal getFloorspace() {
        return floorspace;
    }

    public void setFloorspace(BigDecimal floorspace) {
        this.floorspace = floorspace;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getFixposition() {
        return fixposition;
    }

    public void setFixposition(String fixposition) {
        this.fixposition = fixposition == null ? null : fixposition.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getRzbh() {
        return rzbh;
    }

    public void setRzbh(String rzbh) {
        this.rzbh = rzbh == null ? null : rzbh.trim();
    }

    public Date getCrmSyncTime() {
        return crmSyncTime;
    }

    public void setCrmSyncTime(Date crmSyncTime) {
        this.crmSyncTime = crmSyncTime;
    }

    public String getDeviceFrozenStatus() {
        return deviceFrozenStatus;
    }

    public void setDeviceFrozenStatus(String deviceFrozenStatus) {
        this.deviceFrozenStatus = deviceFrozenStatus == null ? null : deviceFrozenStatus.trim();
    }

    public Integer getScreenInstallId() {
        return screenInstallId;
    }

    public void setScreenInstallId(Integer screenInstallId) {
        this.screenInstallId = screenInstallId;
    }

    public String getScreenInstallName() {
        return screenInstallName;
    }

    public void setScreenInstallName(String screenInstallName) {
        this.screenInstallName = screenInstallName == null ? null : screenInstallName.trim();
    }

    public String getScreenInstallJobNum() {
        return screenInstallJobNum;
    }

    public void setScreenInstallJobNum(String screenInstallJobNum) {
        this.screenInstallJobNum = screenInstallJobNum == null ? null : screenInstallJobNum.trim();
    }

    public String getScreenInstallMobileNum() {
        return screenInstallMobileNum;
    }

    public void setScreenInstallMobileNum(String screenInstallMobileNum) {
        this.screenInstallMobileNum = screenInstallMobileNum == null ? null : screenInstallMobileNum.trim();
    }

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatorJobNum() {
        return operatorJobNum;
    }

    public void setOperatorJobNum(String operatorJobNum) {
        this.operatorJobNum = operatorJobNum == null ? null : operatorJobNum.trim();
    }

    public String getOperatorMobileNum() {
        return operatorMobileNum;
    }

    public void setOperatorMobileNum(String operatorMobileNum) {
        this.operatorMobileNum = operatorMobileNum == null ? null : operatorMobileNum.trim();
    }

    public Date getBrushTime() {
        return brushTime;
    }

    public void setBrushTime(Date brushTime) {
        this.brushTime = brushTime;
    }

    public String getAgentDockName() {
        return agentDockName;
    }

    public void setAgentDockName(String agentDockName) {
        this.agentDockName = agentDockName == null ? null : agentDockName.trim();
    }

    public String getAgentWechat() {
        return agentWechat;
    }

    public void setAgentWechat(String agentWechat) {
        this.agentWechat = agentWechat == null ? null : agentWechat.trim();
    }

    public String getAgentDockMobile() {
        return agentDockMobile;
    }

    public void setAgentDockMobile(String agentDockMobile) {
        this.agentDockMobile = agentDockMobile == null ? null : agentDockMobile.trim();
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getImportRemark() {
        return importRemark;
    }

    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark == null ? null : importRemark.trim();
    }

    public Integer getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(Integer completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion == null ? null : deviceVersion.trim();
    }

    public Date getLastHeartDatetime() {
        return lastHeartDatetime;
    }

    public void setLastHeartDatetime(Date lastHeartDatetime) {
        this.lastHeartDatetime = lastHeartDatetime;
    }

    public Integer getModelShopFlag() {
        return modelShopFlag;
    }

    public void setModelShopFlag(Integer modelShopFlag) {
        this.modelShopFlag = modelShopFlag;
    }
}