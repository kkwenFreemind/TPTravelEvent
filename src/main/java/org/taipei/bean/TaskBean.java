package org.taipei.bean;

public class TaskBean {
    private String masterchkserviceUrl = "";
    private String FILEPATH = "";
    private int interval_sec = 0;


    public String getMasterchkserviceUrl() {
        return masterchkserviceUrl;
    }

    public void setMasterchkserviceUrl(String masterchkserviceUrl) {
        this.masterchkserviceUrl = masterchkserviceUrl;
    }

    public String getFILEPATH() {
        return FILEPATH;
    }

    public void setFILEPATH(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public int getInterval_sec() {
        return interval_sec;
    }

    public void setInterval_sec(int interval_sec) {
        this.interval_sec = interval_sec;
    }

    @Override
    public String toString() {
        return "taskBean{" +
                "masterchkserviceUrl='" + masterchkserviceUrl + '\'' +
                ", FILEPATH='" + FILEPATH + '\'' +
                ", interval_sec=" + interval_sec +
                '}';
    }

}
