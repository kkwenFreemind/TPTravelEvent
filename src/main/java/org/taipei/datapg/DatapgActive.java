package org.taipei.datapg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.taipei.pgutils.FileUtils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

public class DatapgActive {
    Logger logger = LogManager.getLogger(getClass());
    private String aimpg_fileName;
    private String aimpg_path;
    private String datapg_path;
    private String datapg_path_succ;
    private String FileType_Aimpg_txt;
    private String FileType_Aimpg_getting;
    private String FileType_Aimpg_txt_p;
    private String FileType_Data_tmp;
    private String FileType_Data_txt;

    public DatapgActive(String aimpg_fileName, DatapgActiveBean datapgActiveBean, String datapg_path_succ) {
        this.aimpg_fileName = aimpg_fileName;
        this.aimpg_path = datapgActiveBean.getAimpg_path();
        this.datapg_path = datapgActiveBean.getDatapg_path();
        this.datapg_path_succ = datapg_path_succ;
        this.FileType_Aimpg_txt = datapgActiveBean.getFileType_Aimpg_txt();
        this.FileType_Aimpg_getting = datapgActiveBean.getFileType_Aimpg_getting();
        this.FileType_Aimpg_txt_p = datapgActiveBean.getFileType_Aimpg_txt_p();
        this.FileType_Data_tmp = datapgActiveBean.getFileType_Data_tmp();
        this.FileType_Data_txt = datapgActiveBean.getFileType_Data_txt();
    }
    private JSONObject removeJSONKeys(JSONObject in, String[] keys) {
        if (in != null) {
            for (String key : keys) in.remove(key);
            return in;
        } else {
            return null;
        }
    }

    private JSONObject convertJSON(JSONObject in) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (in != null) {
            if (in.has("DataTimestamp")) {
                in.put("DataTime", sdf.format(new Date(in.getLong("DataTimestamp") * 1000)));
                in.remove("DataTimestamp");
            }
            if ("2".equals(in.getString("EventType"))) {
                String[] keys = {"VehiTotCnt", "CarCnt", "BusCnt", "TruckCnt", "MidCnt", "MotorcycleCnt", "BicycleCnt", "PedestrianCnt", "AverageVelocity", "OcupyRate", "LSRName", "TrafficDirection", "LSRType"};
                in = removeJSONKeys(in, keys);
                if (in.has("EventStartDttm")) {
                    in.put("EventStartDt", sdf.format(new Date(in.getLong("EventStartDttm") * 1000)));
                    in.remove("EventStartDttm");
                }
                if (in.has("EventgEndDttm")) {
                    in.put("EventgEndDt", sdf.format(new Date(in.getLong("EventgEndDttm") * 1000)));
                    in.remove("EventgEndDttm");
                }
                return in;
            } else {
                String[] keys = {"ObjectId", "VehicleType", "PlateNo", "OcupyRate", "EventTime", "EventStartDttm", "EventgEndDttm", "StartPhoto", "EndPhoto"};
                in = removeJSONKeys(in, keys);
                return in;
            }
        }
        logger.info("convertJSON() input is Null !");
        return null;
    }

    public void DoDatapgActive() {
        try {
            if (aimpg_path == null || aimpg_fileName == null) {
                logger.error("aimpg_path: "+aimpg_path);
                logger.error("aimpg_fileName: "+aimpg_fileName);
                return;
            }
            final String filePath = aimpg_path + File.separator + aimpg_fileName;
            FileUtils fileUtils = new FileUtils();
            //**path:/AIM**
            //修改 .tmp 改成 .getting
            boolean doFileRenameTmpToGetting = fileUtils.doFileRename(filePath, FileType_Aimpg_txt, FileType_Aimpg_getting);
            //讀取 .getting 檔案
            if (!doFileRenameTmpToGetting) {
                logger.error(filePath + "改檔名失敗");
                return;
            }else {
                logger.info(filePath.replace(FileType_Aimpg_txt,FileType_Aimpg_getting) + " 改檔名成功");
            }
            final String gettingFilePath = filePath.replace(FileType_Aimpg_txt, FileType_Aimpg_getting);
            JSONObject jsonObject = fileUtils.doFileRead(gettingFilePath);

            //call format data
            JSONArray arrayData = jsonObject.getJSONArray("RawData");
//            System.out.println(arrayData.length());
            int total = arrayData.length();
            String numberTotal = String.format("%05d", total);
            if (total > 0) {
                IntStream.range(0, total).forEach(arrayidx -> {
                    JSONObject data = (JSONObject) arrayData.get(arrayidx);
                    String number = String.format("%05d", arrayidx + 1);
                    logger.info("總筆數: " + numberTotal + " ,該筆: " + number);

                    //**Topath:/DATA**
                    //建立 .tmp
                    final String[] fileNameList = aimpg_fileName.split("\\.");
                    final String new_fileName_top = fileNameList[0].replace("Aim", "Data") + "_" + numberTotal + "_" + number;
                    final String new_Extension = ("." + fileNameList[1]).replace(FileType_Aimpg_txt, FileType_Data_tmp);
                    final String new_fileName = new_fileName_top + new_Extension;
                    boolean doFileWrite = fileUtils.doFileWrite(datapg_path, new_fileName, convertJSON(data), FileType_Data_tmp);
                    if (!doFileWrite) {
                        logger.error(new_fileName + " 寫入失敗");
                        return;
                    } else {
                        logger.info(new_fileName + " 寫入成功");
                    }
                    //修改 .tmp 改成 .txt
                    final String new_filePath_tmp = datapg_path + File.separator + new_fileName.replace(FileType_Aimpg_txt, FileType_Data_tmp);
                    boolean doFileRenameTmpToTxt = fileUtils.doFileRename(new_filePath_tmp, FileType_Data_tmp, FileType_Data_txt);
                    if (!doFileRenameTmpToTxt) {
                        logger.error(new_filePath_tmp.replace(FileType_Data_tmp, FileType_Data_txt) + "改檔名失敗");
                        return;
                    } else {
                        logger.info(new_filePath_tmp.replace(FileType_Data_tmp, FileType_Data_txt) + " 改檔名成功");
                    }
                    //**path:/AIM**
                    //修改 .getting 改成 .txt.p
                    if (arrayidx + 1 != total) {
                        return;
                    }
                    boolean doFileRenameTxtP = fileUtils.doFileRename(gettingFilePath, FileType_Aimpg_getting, FileType_Aimpg_txt_p);
                    if (!doFileRenameTxtP) {
                        logger.error(gettingFilePath.replace(FileType_Aimpg_getting, FileType_Aimpg_txt_p) + "改檔名失敗");
                        return;
                    } else {
                        logger.info(gettingFilePath.replace(FileType_Aimpg_getting, FileType_Aimpg_txt_p) + " 改檔名成功");
                    }
                    //搬移 .txt.p
                    //**Topath:/DATASUCC**
                    boolean doMovefile = fileUtils.doMovefile(aimpg_path, datapg_path_succ, aimpg_fileName.replace(FileType_Aimpg_txt, FileType_Aimpg_txt_p));
                    if (!doMovefile) {
                        logger.error(gettingFilePath.replace(FileType_Aimpg_getting, FileType_Aimpg_txt_p) + "搬移失敗");
                        return;
                    } else {
                        logger.info(gettingFilePath.replace(FileType_Aimpg_getting, FileType_Aimpg_txt_p) + " 搬移成功");
                    }

                });
            } else {
                logger.info(gettingFilePath + " 無data值");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

}
