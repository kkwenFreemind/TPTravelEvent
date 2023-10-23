package org.taipei.datapg;

public class DatapgActiveBean {
    String aimpg_path;
    String datapg_path;
    String fileType_Aimpg_txt;
    String fileType_Aimpg_getting;
    String fileType_Aimpg_txt_p;
    String fileType_Data_tmp;
    String fileType_Data_txt;

    public String getAimpg_path() {
        return aimpg_path;
    }

    public void setAimpg_path(String aimpg_path) {
        this.aimpg_path = aimpg_path;
    }

    public String getDatapg_path() {
        return datapg_path;
    }

    public void setDatapg_path(String datapg_path) {
        this.datapg_path = datapg_path;
    }

    public String getFileType_Aimpg_txt() {
        return fileType_Aimpg_txt;
    }

    public void setFileType_Aimpg_txt(String fileType_Aimpg_txt) {
        this.fileType_Aimpg_txt = fileType_Aimpg_txt;
    }

    public String getFileType_Aimpg_getting() {
        return fileType_Aimpg_getting;
    }

    public void setFileType_Aimpg_getting(String fileType_Aimpg_getting) {
        this.fileType_Aimpg_getting = fileType_Aimpg_getting;
    }

    public String getFileType_Aimpg_txt_p() {
        return fileType_Aimpg_txt_p;
    }

    public void setFileType_Aimpg_txt_p(String fileType_Aimpg_txt_p) {
        this.fileType_Aimpg_txt_p = fileType_Aimpg_txt_p;
    }

    public String getFileType_Data_tmp() {
        return fileType_Data_tmp;
    }

    public void setFileType_Data_tmp(String fileType_Data_tmp) {
        this.fileType_Data_tmp = fileType_Data_tmp;
    }

    public String getFileType_Data_txt() {
        return fileType_Data_txt;
    }

    public void setFileType_Data_txt(String fileType_Data_txt) {
        this.fileType_Data_txt = fileType_Data_txt;
    }

    public DatapgActiveBean(String aimpg_path, String datapg_path, String fileType_Aimpg_txt, String fileType_Aimpg_getting, String fileType_Aimpg_txt_p, String fileType_Data_tmp, String fileType_Data_txt) {
        this.aimpg_path = aimpg_path;
        this.datapg_path = datapg_path;
        this.fileType_Aimpg_txt = fileType_Aimpg_txt;
        this.fileType_Aimpg_getting = fileType_Aimpg_getting;
        this.fileType_Aimpg_txt_p = fileType_Aimpg_txt_p;
        this.fileType_Data_tmp = fileType_Data_tmp;
        this.fileType_Data_txt = fileType_Data_txt;
    }

}
