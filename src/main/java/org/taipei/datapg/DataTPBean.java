package org.taipei.datapg;

import java.text.SimpleDateFormat;

public class DataTPBean {
    //交通參數
    private String deviId;//裝置Id，AIBox 設備編號
    private String eventId;//事件Id，DeviId+ EventId 唯一 Key
    private String cameraId;//攝影機Id
    private String regionId;//車道Id
    private String regionName;//車道名稱
    private Long dataTimestamp;//時間，Timestamp 格式
    private String dataTime;//資料時間，YYYY-MM-DD hh:mm:ss
    private String eventType;//資料主類別，1:Traffic statistics、5:Speed and occupancy、6:Traffic flow tatistics
    private String eventSubType;//資料次類別
    private int vehiTotCnt;//交通工具總數量
    private int carCnt;//car 數量
    private int busCnt;//bus 數量
    private int truckCnt;//truck 數量
    private int midCnt;//mid 數量
    private int motorcycleCnt;//motorcycle 數量
    private int bicycleCnt;//bicycle 數量
    private int pedestrianCnt;//pedestrian 數量(行人)
    private Float averageVelocity;//平均速率
    private String lsrName;//複合型名稱
    private String trafficDirection;//車行方向
    private String lsrType;//複合型定義

    public String getDeviId() {
        return deviId;
    }

    public void setDeviId(String deviId) {
        this.deviId = deviId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(Long dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

    public String getDataTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dataTimestamp);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public void setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType;
    }

    public int getVehiTotCnt() {
        return vehiTotCnt;
    }

    public void setVehiTotCnt(int vehiTotCnt) {
        this.vehiTotCnt = vehiTotCnt;
    }

    public int getCarCnt() {
        return carCnt;
    }

    public void setCarCnt(int carCnt) {
        this.carCnt = carCnt;
    }

    public int getBusCnt() {
        return busCnt;
    }

    public void setBusCnt(int busCnt) {
        this.busCnt = busCnt;
    }

    public int getTruckCnt() {
        return truckCnt;
    }

    public void setTruckCnt(int truckCnt) {
        this.truckCnt = truckCnt;
    }

    public int getMidCnt() {
        return midCnt;
    }

    public void setMidCnt(int midCnt) {
        this.midCnt = midCnt;
    }

    public int getMotorcycleCnt() {
        return motorcycleCnt;
    }

    public void setMotorcycleCnt(int motorcycleCnt) {
        this.motorcycleCnt = motorcycleCnt;
    }

    public int getBicycleCnt() {
        return bicycleCnt;
    }

    public void setBicycleCnt(int bicycleCnt) {
        this.bicycleCnt = bicycleCnt;
    }

    public int getPedestrianCnt() {
        return pedestrianCnt;
    }

    public void setPedestrianCnt(int pedestrianCnt) {
        this.pedestrianCnt = pedestrianCnt;
    }

    public Float getAverageVelocity() {
        return averageVelocity;
    }

    public void setAverageVelocity(Float averageVelocity) {
        this.averageVelocity = averageVelocity;
    }

    public String getLsrName() {
        return lsrName;
    }

    public void setLsrName(String lsrName) {
        this.lsrName = lsrName;
    }

    public String getTrafficDirection() {
        return trafficDirection;
    }

    public void setTrafficDirection(String trafficDirection) {
        this.trafficDirection = trafficDirection;
    }

    public String getLsrType() {
        return lsrType;
    }

    public void setLsrType(String lsrType) {
        this.lsrType = lsrType;
    }

    public DataTPBean(String deviId, String eventId, String cameraId, String regionId, String regionName, Long dataTimestamp, String eventType, String eventSubType, int vehiTotCnt, int carCnt, int busCnt, int truckCnt, int midCnt, int motorcycleCnt, int bicycleCnt, int pedestrianCnt, Float averageVelocity, String lsrName, String trafficDirection, String lsrType) {
        this.deviId = deviId;
        this.eventId = eventId;
        this.cameraId = cameraId;
        this.regionId = regionId;
        this.regionName = regionName;
        this.dataTimestamp = dataTimestamp;
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.vehiTotCnt = vehiTotCnt;
        this.carCnt = carCnt;
        this.busCnt = busCnt;
        this.truckCnt = truckCnt;
        this.midCnt = midCnt;
        this.motorcycleCnt = motorcycleCnt;
        this.bicycleCnt = bicycleCnt;
        this.pedestrianCnt = pedestrianCnt;
        this.averageVelocity = averageVelocity;
        this.lsrName = lsrName;
        this.trafficDirection = trafficDirection;
        this.lsrType = lsrType;
    }
}
