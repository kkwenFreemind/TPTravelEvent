package org.taipei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.datapg.DatapgActive;
import org.taipei.datapg.DatapgActiveBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DATAPG {
    static Logger logger = LogManager.getLogger(DATAPG.class);
    public static void main(String[] args) {
        Properties properties = new Properties();
        final String configFile = "." + File.separator + "config.properties";
        try {
            properties.load(new FileInputStream(configFile));
            final String aimpg_path = properties.getProperty("Aimpg_path");
            final String datapg_path =  properties.getProperty("Datapg_path");
            final String datapg_path_succ =  properties.getProperty("Datapg_path_succ");
            final String FileType_Aimpg_txt = properties.getProperty("FileType_Aimpg_txt");
            final String FileType_Aimpg_getting = properties.getProperty("FileType_Aimpg_getting");
            final String FileType_Aimpg_txt_p = properties.getProperty("FileType_Aimpg_txt_p");
            final String FileType_Data_tmp = properties.getProperty("FileType_Data_tmp");
            final String FileType_Data_txt = properties.getProperty("FileType_Data_txt");
            final int chkTime = Integer.parseInt(properties.getProperty("Datapg_CHKTime", String.valueOf(1000)));

            DatapgActiveBean datapgActiveBean = new DatapgActiveBean(aimpg_path,datapg_path,FileType_Aimpg_txt,FileType_Aimpg_getting,FileType_Aimpg_txt_p,FileType_Data_tmp,FileType_Data_txt);
            while (true) {
                //抓取檔案
                File all_file = new File(aimpg_path);
                if (!all_file.isDirectory()) {
                    System.out.println("sur_path: " + aimpg_path + "，找不到資料夾路徑。");
                    TimeUnit.MILLISECONDS.sleep(chkTime);
                }else {
                    File[] files = all_file.listFiles((File dir, String name) -> name.toLowerCase().endsWith(FileType_Aimpg_txt));
//                logger.info("aimpg_path: " + aimpg_path + "，符合 " + FileType_Aimpg_txt + " 的檔案數量: " + Objects.requireNonNull(files).length);
                    if (Objects.requireNonNull(files).length != 0) {
                        for (File file : Objects.requireNonNull(files)) {
                            long long_date_init = new Date().getTime();
                            String fileName = file.getName();
                            logger.info("DATAPG 運作開始");
                            logger.info("檔案名稱: " + fileName);
                            DatapgActive datapgActive = new DatapgActive(fileName, datapgActiveBean, datapg_path_succ);
                            datapgActive.DoDatapgActive();
                            logger.info("DATAPG 運作結束");
                            long long_date_end = new Date().getTime();
                            long long_difference = long_date_end - long_date_init;
                            if (long_difference < chkTime) {
                                TimeUnit.MILLISECONDS.sleep(chkTime - long_difference);
                            }
                        }
                    } else {
                        logger.warn("DATAPG 資料夾無資料，時間將延遲 "+chkTime+" ms");
                        TimeUnit.MILLISECONDS.sleep(chkTime);
                    }
                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
