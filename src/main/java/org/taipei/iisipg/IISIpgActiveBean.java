package org.taipei.iisipg;

public class IISIpgActiveBean {
    private String datapg_path;
    private String iisipg_path;
    private String FileType_Data_txt;
    private String FileType_Datapg_getting;
    private String FileType_Datapg_txt_p;
    private String FileType_IISI_tmp;
    private String FileType_IISI_txt;


    public String getDatapg_path() {
        return datapg_path;
    }

    public void setDatapg_path(String datapg_path) {
        this.datapg_path = datapg_path;
    }

    public String getIisipg_path() {
        return iisipg_path;
    }

    public void setIisipg_path(String iisipg_path) {
        this.iisipg_path = iisipg_path;
    }

    public String getFileType_Data_txt() {
        return FileType_Data_txt;
    }

    public void setFileType_Data_txt(String fileType_Data_txt) {
        FileType_Data_txt = fileType_Data_txt;
    }

    public String getFileType_Datapg_getting() {
        return FileType_Datapg_getting;
    }

    public void setFileType_Datapg_getting(String fileType_Datapg_getting) {
        FileType_Datapg_getting = fileType_Datapg_getting;
    }

    public String getFileType_Datapg_txt_p() {
        return FileType_Datapg_txt_p;
    }

    public void setFileType_Datapg_txt_p(String fileType_Datapg_txt_p) {
        FileType_Datapg_txt_p = fileType_Datapg_txt_p;
    }

    public String getFileType_IISI_tmp() {
        return FileType_IISI_tmp;
    }

    public void setFileType_IISI_tmp(String fileType_IISI_tmp) {
        FileType_IISI_tmp = fileType_IISI_tmp;
    }

    public String getFileType_IISI_txt() {
        return FileType_IISI_txt;
    }

    public void setFileType_IISI_txt(String fileType_IISI_txt) {
        FileType_IISI_txt = fileType_IISI_txt;
    }

    public IISIpgActiveBean(String datapg_path, String iisipg_path, String fileType_Data_txt, String fileType_Datapg_getting, String fileType_Datapg_txt_p, String fileType_IISI_tmp, String fileType_IISI_txt) {
        this.datapg_path = datapg_path;
        this.iisipg_path = iisipg_path;
        FileType_Data_txt = fileType_Data_txt;
        FileType_Datapg_getting = fileType_Datapg_getting;
        FileType_Datapg_txt_p = fileType_Datapg_txt_p;
        FileType_IISI_tmp = fileType_IISI_tmp;
        FileType_IISI_txt = fileType_IISI_txt;
    }
}
