package org.taipei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.taipei.pgutils.FileUtils;
import org.taipei.pgutils.HttpClientUtils;
import org.taipei.utils.FileUtil;
import org.taipei.utils.HostInfoUtil;

import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ChkServiceFlag {

    static Logger logger = LogManager.getLogger(ChkServiceFlag.class);

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        String configFile = "." + File.separator + "config.properties";

        try {

            while(true) {

                properties.load(new FileInputStream(configFile));
                String mastername = properties.getProperty("master", "127.0.0.1");
                String masterserviceUrl = properties.getProperty("masterchkservice");

                //取主機IP
                HostInfoUtil hostInfoUtil = new HostInfoUtil();
                String Hostname = hostInfoUtil.getHostname();

                System.out.println("My Host Name :" + Hostname);
                System.out.println("Master Name :" + mastername);
                System.out.println("Master Check Service Url :" + masterserviceUrl);

                boolean master_flag = false;

                if (mastername.equalsIgnoreCase(Hostname)) {
                    logger.info("I am Master Host");
                    File file = new File("IISIFLAG.log");
                    if (!file.exists()) {
                        logger.info("I am Master Host, file.createNewFile()");
                        file.createNewFile();
                    }
                } else {

                    //檢測對方url api是否存在
                    HttpClientUtils httpClientUtils = new HttpClientUtils();
                    HttpURLConnection conn = httpClientUtils.callPOST(masterserviceUrl, "{\"username\":\"root\"}");
                    int status=0;
                    try {
                        status = conn.getResponseCode();
                    }catch(Exception ex){
                        //ex.printStackTrace();
                    }
                    logger.info("connect結束");

                    FileUtil fileUtil = new FileUtil();
                     if (status == 200) {

                        File file = new File("IISIFLAG.log");
                        if (file.exists()) {
                            logger.info("I am Slave Host,file.exists(),刪除IISILAG.log");
                            file.delete();
                        }

                    } else {

                        File file = new File("IISIFLAG.log");
                        if (!file.exists()) {
                            logger.info("I am Slave Host, !file.exists(),建立IISILAG.log");
                            file.createNewFile();
                        }
                    }
                }
                TimeUnit.MILLISECONDS.sleep(5000);
            }


        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
