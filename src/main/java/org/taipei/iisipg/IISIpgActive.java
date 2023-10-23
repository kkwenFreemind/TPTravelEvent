package org.taipei.iisipg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.FileUtils;
import org.taipei.utils.FileUtil;

import java.io.File;

public class IISIpgActive {
    Logger logger = LogManager.getLogger(getClass());
    private String datapg_fileName;
    private String datapg_path;
    private String iisipg_path;
    private String iisipg_path_succ;
    private String FileType_Data_txt;
    private String FileType_Datapg_getting;
    private String FileType_Datapg_txt_p;
    private String FileType_IISI_tmp;
    private String FileType_IISI_txt;
    private String IISIpg_TEURL;
    private String IISIpg_TPURL;

    public IISIpgActive(String datapg_fileName, IISIpgActiveBean iisipgActiveBean, String iisipg_TEURL, String iisipg_TPURL, String iisipg_path_succ) {
        this.datapg_fileName = datapg_fileName;
        this.datapg_path = iisipgActiveBean.getDatapg_path();
        this.iisipg_path = iisipgActiveBean.getIisipg_path();
        this.iisipg_path_succ = iisipg_path_succ;
        this.FileType_Data_txt = iisipgActiveBean.getFileType_Data_txt();
        this.FileType_Datapg_getting = iisipgActiveBean.getFileType_Datapg_getting();
        this.FileType_Datapg_txt_p = iisipgActiveBean.getFileType_Datapg_txt_p();
        this.FileType_IISI_tmp = iisipgActiveBean.getFileType_IISI_tmp();
        this.FileType_IISI_txt = iisipgActiveBean.getFileType_IISI_txt();
        this.IISIpg_TEURL = iisipg_TEURL;
        this.IISIpg_TPURL = iisipg_TPURL;
    }


    public void DoIISIpgActive() {
        try {
            if (datapg_path == null || datapg_fileName == null) {
                logger.error("datapg_path: " + datapg_path);
                logger.error("datapg_fileName: " + datapg_fileName);
                return;
            }
            final String filePath = datapg_path + File.separator + datapg_fileName;
            FileUtils fileUtils = new FileUtils();
            //**path:/DATA**
            //修改 .tmp 改成 .getting
            boolean doFileRenameTmpToGetting = fileUtils.doFileRename(filePath, FileType_Data_txt, FileType_Datapg_getting);
            //讀取 .getting 檔案
            if (!doFileRenameTmpToGetting) {
                logger.error(filePath + "改檔名失敗");
                return;
            } else {
                logger.info(filePath.replace(FileType_Data_txt, FileType_Datapg_getting) + " 改檔名成功");
            }
            final String gettingFilePath = filePath.replace(FileType_Data_txt, FileType_Datapg_getting);
            JSONObject jsonObject = fileUtils.doFileRead(gettingFilePath);
//            System.out.println(jsonObject.toString());

            //call api iisi
            boolean result = false;
            if (jsonObject != null && ChkFile_IISIFLAG()) {
                logger.info("找到IISIFLAG.log，新增新連線");
                String url;
                if (jsonObject.get("EventType") == "2") {
                    url = IISIpg_TEURL;
                } else {
                    url = IISIpg_TPURL;
                }
                SendData sendData = new SendData(url, jsonObject.toString());
                result = sendData.doSendJsonData();
            } else {
                logger.info("未找到IISIFLAG.log，不新增新連線");
            }
            if(!result){
                return;
            }
            //**Topath:/IISI**
            //建立 .tmp
            final String new_fileName = datapg_fileName.replace(FileType_Data_txt, FileType_IISI_tmp).replace("Data", "IISI");
            boolean doFileWrite = fileUtils.doFileWrite(iisipg_path, new_fileName, jsonObject, FileType_IISI_tmp);

            //修改 .tmp 改成 .txt
            if (!doFileWrite) {
                logger.error(new_fileName + " 寫入失敗");
                return;
            } else {
                logger.info(new_fileName + " 寫入成功");
            }
            final String new_filePath_tmp = iisipg_path + File.separator + new_fileName.replace(FileType_Data_txt, FileType_IISI_tmp);
            boolean doFileRenameTmpToTxt = fileUtils.doFileRename(new_filePath_tmp, FileType_IISI_tmp, FileType_IISI_txt);
            if (!doFileRenameTmpToTxt) {
                logger.error(new_filePath_tmp.replace(FileType_IISI_tmp, FileType_IISI_txt) + "改檔名失敗");
                return;
            } else {
                logger.info(new_filePath_tmp.replace(FileType_IISI_tmp, FileType_IISI_txt) + " 改檔名成功");
            }
            //**path:/DATA**
            //修改 .getting 改成 .txt.p
            boolean doFileRenameTxtP = fileUtils.doFileRename(gettingFilePath, FileType_Datapg_getting, FileType_Datapg_txt_p);
            if (!doFileRenameTxtP) {
                logger.error(gettingFilePath.replace(FileType_Datapg_getting, FileType_Datapg_txt_p) + "改檔名失敗");
                return;
            } else {
                logger.info(gettingFilePath.replace(FileType_Datapg_getting, FileType_Datapg_txt_p) + " 改檔名成功");
            }

            //搬移 .txt.p
            //**Topath:/IISISUCC**
            boolean doMovefile = fileUtils.doMovefile(datapg_path, iisipg_path_succ, datapg_fileName.replace(FileType_Data_txt, FileType_Datapg_txt_p));
            if (!doMovefile) {
                logger.error(gettingFilePath.replace(FileType_Datapg_getting, FileType_Datapg_txt_p) + "搬移失敗");
                return;
            } else {
                logger.info(gettingFilePath.replace(FileType_Datapg_getting, FileType_Datapg_txt_p) + " 搬移成功");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }


    private boolean ChkFile_IISIFLAG() {
        return new FileUtils().chkFile( "."+File.separator+"IISIFLAG.log", ".log");
    }
}
