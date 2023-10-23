package org.taipei.datapg;

import java.text.SimpleDateFormat;

public class DataTEBean {
    //交通事件
    private String deviId;//裝置Id，AIBox 設備編號
    private String eventId;//事件Id，DeviId+ EventId 唯一 Key
    private String cameraId;//攝影機Id
    private String regionId;//車道Id
    private String regionName;//車道名稱
    private String objectId;//物件Id
    private Long dataTimestamp;//時間，Timestamp 格式
    private String dataTime;//資料時間，YYYY-MM-DD hh:mm:ss
    private String eventType;//資料主類別，2:Parking detect
    private String eventSubType;//資料次類別
    private String vehicleType;//交通工具類別
    private String plateNo;//車牌號碼
    private int eventTime;//事件時間
    private Long eventStartDt;//事件起始日期時間，YYYY-MM-DD hh:mm:ss
    private Long eventEndDt;//事件截止日期時間，YYYY-MM-DD hh:mm:ss
    private String startPhoto;//事件開始照片URL
    private String endPhoto;//事件結束照片URL

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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public Long getEventStartDt() {
        return eventStartDt;
    }

    public void setEventStartDt(Long eventStartDt) {
        this.eventStartDt = eventStartDt;
    }

    public Long getEventEndDt() {
        return eventEndDt;
    }

    public void setEventEndDt(Long eventEndDt) {
        this.eventEndDt = eventEndDt;
    }

    public String getStartPhoto() {
        return startPhoto;
    }

    public void setStartPhoto(String startPhoto) {
        this.startPhoto = startPhoto;
    }

    public String getEndPhoto() {
        return endPhoto;
    }

    public void setEndPhoto(String endPhoto) {
        this.endPhoto = endPhoto;
    }

    public DataTEBean(String deviId, String eventId, String cameraId, String regionId, String regionName, String objectId, Long dataTimestamp, String eventType, String eventSubType, String vehicleType, String plateNo, int eventTime, Long eventStartDt, Long eventEndDt, String startPhoto, String endPhoto) {
        this.deviId = deviId;
        this.eventId = eventId;
        this.cameraId = cameraId;
        this.regionId = regionId;
        this.regionName = regionName;
        this.objectId = objectId;
        this.dataTimestamp = dataTimestamp;
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.vehicleType = vehicleType;
        this.plateNo = plateNo;
        this.eventTime = eventTime;
        this.eventStartDt = eventStartDt;
        this.eventEndDt = eventEndDt;
        this.startPhoto = startPhoto;
        this.endPhoto = endPhoto;
    }
}
