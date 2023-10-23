package org.taipei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.iisipg.IISIpgActive;
import org.taipei.iisipg.IISIpgActiveBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class IISIPG {
    static Logger logger = LogManager.getLogger(IISIPG.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        final String configFile = "." + File.separator + "config.properties";
        try {
            properties.load(new FileInputStream(configFile));
            final String datapg_path = properties.getProperty("Datapg_path");
            final String iisipg_path_succ = properties.getProperty("IISIpg_path_succ");
            final String iisipg_path = properties.getProperty("IISIpg_path");
            final String FileType_Data_txt = properties.getProperty("FileType_Pooling_txt");
            final String FileType_Datapg_getting = properties.getProperty("FileType_Datapg_getting");
            final String FileType_Datapg_txt_p = properties.getProperty("FileType_Datapg_txt_p");
            final String FileType_IISI_tmp = properties.getProperty("FileType_IISI_tmp");
            final String FileType_IISI_txt = properties.getProperty("FileType_IISI_txt");
            final String iisipg_TEURL = properties.getProperty("IISIpg_TEURL");
            final String iisipg_TPURL = properties.getProperty("IISIpg_TPURL");
            final int chkTime = Integer.parseInt(properties.getProperty("IISIpg_CHKTime", String.valueOf(1000)));

            IISIpgActiveBean iisipgActiveBean = new IISIpgActiveBean(datapg_path, iisipg_path, FileType_Data_txt, FileType_Datapg_getting, FileType_Datapg_txt_p, FileType_IISI_tmp, FileType_IISI_txt);
            while (true) {
                //抓取檔案
                File all_file = new File(datapg_path);
                if (!all_file.isDirectory()) {
                    System.out.println("sur_path: " + datapg_path + "，找不到資料夾路徑。");
                    TimeUnit.MILLISECONDS.sleep(chkTime);
                }else {
                    File[] files = all_file.listFiles((File dir, String name) -> name.toLowerCase().endsWith(FileType_Data_txt));
//                logger.info("polling_path: " + polling_path + "，符合 " + FileType_Pooling_txt + " 的檔案數量: " + Objects.requireNonNull(files).length);
                    if (Objects.requireNonNull(files).length != 0) {
                        for (File file : Objects.requireNonNull(files)) {
                            long long_date_init = new Date().getTime();
                            String fileName = file.getName();
                            logger.info("IISIPG 運作開始");
                            logger.info("檔案名稱: " + fileName);
                            IISIpgActive iisipgActive = new IISIpgActive(fileName, iisipgActiveBean, iisipg_TEURL, iisipg_TPURL, iisipg_path_succ);
                            iisipgActive.DoIISIpgActive();
                            logger.info("IISIPG 運作結束");
                            long long_date_end = new Date().getTime();
                            long long_difference = long_date_end - long_date_init;
                            if (long_difference < chkTime) {
                                TimeUnit.MILLISECONDS.sleep(chkTime - long_difference);
                            }
                        }
                    } else {
                        logger.warn("IISIPG 資料夾無資料，時間將延遲 "+chkTime+" ms");
                        TimeUnit.MILLISECONDS.sleep(chkTime);
                    }
                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
