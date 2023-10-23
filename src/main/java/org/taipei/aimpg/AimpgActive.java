package org.taipei.aimpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.FileUtils;

import java.io.File;

public class AimpgActive {
    Logger logger = LogManager.getLogger(getClass());

    private final String pooling_fileName;
    private final String pooling_path;
    private final String aimpg_path;
    private final String FileType_Pooling_txt;
    private final String FileType_Pooling_getting;
    private final String FileType_Pooling_txt_p;
    private final String FileType_Aimpg_tmp;
    private final String FileType_Aimpg_txt;
    private final String pooling_url;
    private final String aimpg_path_succ;
    private final String token_url;
    private final String username;
    private final String password;

    public AimpgActive(String pooling_fileName, AimpgActiveBean aimpgActiveBean, String pooling_url, String aimpg_path_succ, String token_url, String username, String password) {
        this.FileType_Pooling_txt = aimpgActiveBean.getFileType_Pooling_txt();
        this.FileType_Pooling_getting = aimpgActiveBean.getFileType_Pooling_getting();
        this.FileType_Pooling_txt_p = aimpgActiveBean.getFileType_Pooling_txt_p();
        this.FileType_Aimpg_tmp = aimpgActiveBean.getFileType_Aimpg_tmp();
        this.FileType_Aimpg_txt = aimpgActiveBean.getFileType_Aimpg_txt();
        this.pooling_fileName = pooling_fileName;
        this.pooling_path = aimpgActiveBean.getPooling_path();
        this.aimpg_path = aimpgActiveBean.getAimpg_path();
        this.pooling_url = pooling_url;
        this.aimpg_path_succ = aimpg_path_succ;
        this.token_url = token_url;
        this.username = username;
        this.password = password;

    }

    public void DoAimpgActive() {
        try {
            if (pooling_path == null || pooling_fileName == null) {
                logger.error("pooling_path: " + pooling_path);
                logger.error("pooling_fileName: " + pooling_fileName);
                return;
            }
            final String filePath = pooling_path + File.separator + pooling_fileName;
            FileUtils fileUtils = new FileUtils();
            //**path:/Pooling**
            //修改 .tmp 改成 .getting
            boolean doFileRenameTmpToGetting = fileUtils.doFileRename(filePath, FileType_Pooling_txt, FileType_Pooling_getting);
            //讀取 .getting 檔案
            if (!doFileRenameTmpToGetting) {
                logger.error(filePath + "改檔名失敗");
                return;
            } else {
                logger.info(filePath.replace(FileType_Pooling_txt, FileType_Pooling_getting) + " 改檔名成功");
            }
            final String gettingFilePath = filePath.replace(FileType_Pooling_txt, FileType_Pooling_getting);
            JSONObject jsonObject = fileUtils.doFileRead(gettingFilePath);

            //call http client 取 token
            GetToken getToken = new GetToken(username, password, token_url);
            String token = getToken.doGetToken();
            if (token == null || jsonObject == null) {
                return;
            }
            //取得 token 後放入 json 內
            jsonObject.put("token", token);
            logger.info("File Data Change: " + jsonObject);

            //call http client 取 data
            logger.info("GetData新增新連線");
            GetData getdata = new GetData(pooling_url, String.valueOf(jsonObject));
            JSONObject dataObj = getdata.doGetJsonData();
            logger.info("Getdata: " + dataObj);
            if (dataObj == null) {
                return;
            }

            //**Topath:/AIM**
            //建立 .tmp
            final String new_fileName = pooling_fileName.replace(FileType_Pooling_txt, FileType_Aimpg_tmp).replace("Pooling", "Aim");
            boolean doFileWrite = fileUtils.doFileWrite(aimpg_path, new_fileName, dataObj, FileType_Aimpg_tmp);

            //修改 .tmp 改成 .txt
            if (!doFileWrite) {
                logger.error(new_fileName + " 寫入失敗");
                return;
            } else {
                logger.info(new_fileName + " 寫入成功");
            }
            final String new_filePath_tmp = aimpg_path + File.separator + new_fileName.replace(FileType_Pooling_txt, FileType_Aimpg_tmp);
            boolean doFileRenameTmpToTxt = fileUtils.doFileRename(new_filePath_tmp, FileType_Aimpg_tmp, FileType_Aimpg_txt);
            if (!doFileRenameTmpToTxt) {
                logger.error(new_filePath_tmp.replace(FileType_Aimpg_tmp, FileType_Aimpg_txt) + " 改檔名失敗");
                return;
            } else {
                logger.info(new_filePath_tmp.replace(FileType_Aimpg_tmp, FileType_Aimpg_txt) + " 改檔名成功");
            }
            //**path:/Pooling**
            //修改 .getting 改成 .txt.p
            boolean doFileRenameTxtP = fileUtils.doFileRename(gettingFilePath, FileType_Pooling_getting, FileType_Pooling_txt_p);
            if (!doFileRenameTxtP) {
                logger.error(pooling_fileName.replace(FileType_Pooling_txt, FileType_Pooling_txt_p) + " 改檔名失敗");
                return;
            } else {
                logger.info(pooling_fileName.replace(FileType_Pooling_txt, FileType_Pooling_txt_p) + " 改檔名成功");
            }
            //搬移 .txt.p
            //**Topath:/AIMSUCC**
            boolean doMovefile = fileUtils.doMovefile(pooling_path, aimpg_path_succ, pooling_fileName.replace(FileType_Pooling_txt, FileType_Pooling_txt_p));
            if (!doMovefile) {
                logger.error(pooling_fileName.replace(FileType_Pooling_txt, FileType_Pooling_txt_p) + " 搬移失敗");
            } else {
                logger.info(pooling_fileName.replace(FileType_Pooling_txt, FileType_Pooling_txt_p) + " 搬移成功");
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }



}
