package org.taipei.poolingpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class PoolingpgActive extends TimerTask {
    Logger logger = LogManager.getLogger(getClass());

    private Date begin_date;
    private int intervaltime;
    private String polling_path;
    private String FileType_Pooling_tmp;
    private String FileType_Pooling_txt;

    public PoolingpgActive(Date begin_date, int intervaltime, String polling_path, String fileType_Pooling_tmp, String fileType_Pooling_txt) {
        this.begin_date = begin_date;
        this.intervaltime = intervaltime;
        this.polling_path = polling_path;
        this.FileType_Pooling_tmp = fileType_Pooling_tmp;
        this.FileType_Pooling_txt = fileType_Pooling_txt;
    }


    @Override
    public void run() {
        try {
            logger.info("PoolingPG 運作開始");
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
            logger.info("Begin Date:" + sdFormat.format(begin_date) + ", Begin GetTime:" + begin_date.getTime());

            Date end_date = new Date(begin_date.getTime() + intervaltime * 1000);
            logger.info("End Date: " + sdFormat.format(end_date) + ", End GetTime: " + end_date.getTime());

            //建立 .tmp 檔案
            String FileName = "Pooling_" + sdFormat.format(begin_date) + FileType_Pooling_tmp;
            //資料彙整
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", "");
            jsonObject.put("DataDttmStart", begin_date.getTime());
            jsonObject.put("DataDttmEnd", end_date.getTime());
            logger.info("發送json: " + jsonObject);
            //檔案建立
            FileUtils fileUtils = new FileUtils();
            boolean doFileWrite = fileUtils.doFileWrite(polling_path, FileName, jsonObject, FileType_Pooling_tmp);
            if (!doFileWrite) {
                logger.error(FileName + " 寫入失敗");
                return;
            } else {
                logger.info(FileName + " 寫入成功");
            }
            boolean doFileRenameTmpToTxt = fileUtils.doFileRename(polling_path + File.separator + FileName, FileType_Pooling_tmp, FileType_Pooling_txt);
            if (!doFileRenameTmpToTxt) {
                logger.error(FileName.replace(FileType_Pooling_tmp, FileType_Pooling_txt) + " 改檔名失敗");
            } else {
                logger.info(FileName.replace(FileType_Pooling_tmp, FileType_Pooling_txt) + " 改檔名成功");
            }
            logger.info("PoolingPG 運作結束");
        } catch (Exception ex) {
            logger.error(ex);
        }
    }


}
