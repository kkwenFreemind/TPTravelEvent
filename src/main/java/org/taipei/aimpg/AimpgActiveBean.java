package org.taipei.aimpg;

public class AimpgActiveBean {
    private String pooling_path;
    private String aimpg_path;
    private String fileType_Pooling_txt;
    private String fileType_Pooling_getting;
    private String fileType_Pooling_txt_p;
    private String fileType_Aimpg_tmp;
    private String fileType_Aimpg_txt;


    public String getPooling_path() {
        return pooling_path;
    }

    public void setPooling_path(String pooling_path) {
        this.pooling_path = pooling_path;
    }

    public String getAimpg_path() {
        return aimpg_path;
    }

    public void setAimpg_path(String aimpg_path) {
        this.aimpg_path = aimpg_path;
    }

    public String getFileType_Pooling_txt() {
        return fileType_Pooling_txt;
    }

    public void setFileType_Pooling_txt(String fileType_Pooling_txt) {
        this.fileType_Pooling_txt = fileType_Pooling_txt;
    }

    public String getFileType_Pooling_getting() {
        return fileType_Pooling_getting;
    }

    public void setFileType_Pooling_getting(String fileType_Pooling_getting) {
        this.fileType_Pooling_getting = fileType_Pooling_getting;
    }

    public String getFileType_Pooling_txt_p() {
        return fileType_Pooling_txt_p;
    }

    public void setFileType_Pooling_txt_p(String fileType_Pooling_txt_p) {
        this.fileType_Pooling_txt_p = fileType_Pooling_txt_p;
    }

    public String getFileType_Aimpg_tmp() {
        return fileType_Aimpg_tmp;
    }

    public void setFileType_Aimpg_tmp(String fileType_Aimpg_tmp) {
        this.fileType_Aimpg_tmp = fileType_Aimpg_tmp;
    }

    public String getFileType_Aimpg_txt() {
        return fileType_Aimpg_txt;
    }

    public void setFileType_Aimpg_txt(String fileType_Aimpg_txt) {
        this.fileType_Aimpg_txt = fileType_Aimpg_txt;
    }


    public AimpgActiveBean(String pooling_path, String aimpg_path, String fileType_Pooling_txt, String fileType_Pooling_getting, String fileType_Pooling_txt_p, String fileType_Aimpg_tmp, String fileType_Aimpg_txt) {
        this.pooling_path = pooling_path;
        this.aimpg_path = aimpg_path;
        this.fileType_Pooling_txt = fileType_Pooling_txt;
        this.fileType_Pooling_getting = fileType_Pooling_getting;
        this.fileType_Pooling_txt_p = fileType_Pooling_txt_p;
        this.fileType_Aimpg_tmp = fileType_Aimpg_tmp;
        this.fileType_Aimpg_txt = fileType_Aimpg_txt;
    }
}
