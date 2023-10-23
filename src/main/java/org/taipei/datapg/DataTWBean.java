package org.taipei.datapg;

import java.text.SimpleDateFormat;

public class DataTWBean {
    //車輛停等
    private String deviId;//裝置Id，AIBox 設備編號
    private String eventId;//事件Id，DeviId+ EventId 唯一 Key
    private String cameraId;//攝影機Id
    private String regionId;//車道Id
    private String regionName;//車道名稱
    private Long dataTimestamp;//時間，Timestamp 格式
    private String dataTime;//資料時間，YYYY-MM-DD hh:mm:ss
    private int vehicleParkedCount;//車輛停等數量
    private int vehiclePassingCount;//車輛通過數量
    private Float averageLengthParkedVehicles;//平均車輛停等之長度
    private int percentageVehiclesParked;//車輛停等百分比

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

    public int getVehicleParkedCount() {
        return vehicleParkedCount;
    }

    public void setVehicleParkedCount(int vehicleParkedCount) {
        this.vehicleParkedCount = vehicleParkedCount;
    }

    public int getVehiclePassingCount() {
        return vehiclePassingCount;
    }

    public void setVehiclePassingCount(int vehiclePassingCount) {
        this.vehiclePassingCount = vehiclePassingCount;
    }

    public Float getAverageLengthParkedVehicles() {
        return averageLengthParkedVehicles;
    }

    public void setAverageLengthParkedVehicles(Float averageLengthParkedVehicles) {
        this.averageLengthParkedVehicles = averageLengthParkedVehicles;
    }

    public int getPercentageVehiclesParked() {
        return percentageVehiclesParked;
    }

    public void setPercentageVehiclesParked(int percentageVehiclesParked) {
        this.percentageVehiclesParked = percentageVehiclesParked;
    }
}
