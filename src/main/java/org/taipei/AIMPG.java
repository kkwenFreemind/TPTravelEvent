package org.taipei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.aimpg.AimpgActive;
import org.taipei.aimpg.AimpgActiveBean;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class AIMPG {
    static Logger logger = LogManager.getLogger(AIMPG.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        final String configFile = "." + File.separator + "config.properties";
        try {
            properties.load(new FileInputStream(configFile));
            final String polling_path = properties.getProperty("Pooling_path");
            final String Aimpg_path_succ = properties.getProperty("Aimpg_path_succ");
            final String aimpg_path = properties.getProperty("Aimpg_path");
            final String FileType_Pooling_txt = properties.getProperty("FileType_Pooling_txt");
            final String FileType_Pooling_getting = properties.getProperty("FileType_Pooling_getting");
            final String FileType_Pooling_txt_p = properties.getProperty("FileType_Pooling_txt_p");
            final String FileType_Aimpg_tmp = properties.getProperty("FileType_Aimpg_tmp");
            final String FileType_Aimpg_txt = properties.getProperty("FileType_Aimpg_txt");
            final String pooling_url = properties.getProperty("Pooling_URL");
            final int chkTime = Integer.parseInt(properties.getProperty("Aimpg_CHKTime", String.valueOf(1000)));

            final String token_url = properties.getProperty("Token_URL");
            final String username = properties.getProperty("Username");
            final String password = properties.getProperty("Password");

            AimpgActiveBean aimpgActiveBean = new AimpgActiveBean(polling_path, aimpg_path, FileType_Pooling_txt, FileType_Pooling_getting, FileType_Pooling_txt_p, FileType_Aimpg_tmp, FileType_Aimpg_txt);
            while (true) {
                //抓取檔案
                File all_file = new File(polling_path);
                if (!all_file.isDirectory()) {
                    System.out.println("sur_path: " + polling_path + "，找不到資料夾路徑。");
                    TimeUnit.MILLISECONDS.sleep(chkTime);
                }else {
                    File[] files = all_file.listFiles((File dir, String name) -> name.toLowerCase().endsWith(FileType_Pooling_txt));
//                logger.info("polling_path: " + polling_path + "，符合 " + FileType_Pooling_txt + " 的檔案數量: " + Objects.requireNonNull(files).length);
                    if (Objects.requireNonNull(files).length != 0) {
                        for (File file : Objects.requireNonNull(files)) {
                            long long_date_init = new Date().getTime();
                            String fileName = file.getName();
                            logger.info("AIMPG 運作開始");
                            logger.info("檔案名稱: " + fileName);
                            AimpgActive aimpgActive = new AimpgActive(fileName, aimpgActiveBean, pooling_url, Aimpg_path_succ, token_url, username, password);
                            aimpgActive.DoAimpgActive();
                            logger.info("AIMPG 運作結束");
                            long long_date_end = new Date().getTime();
                            long long_difference = long_date_end - long_date_init;
                            if (long_difference < chkTime) {
                                TimeUnit.MILLISECONDS.sleep(chkTime - long_difference);
                            }
                        }
                    } else {
                        logger.warn("AIMPG 資料夾無資料，時間將延遲 "+chkTime+" ms");
                        TimeUnit.MILLISECONDS.sleep(chkTime);
                    }
                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
