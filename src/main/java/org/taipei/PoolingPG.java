package org.taipei;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.poolingpg.PoolingpgActive;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class PoolingPG {
    static Logger logger = LogManager.getLogger(PoolingPG.class);
    public static void main(String[] args) {
        Properties properties = new Properties();
        final String configFile = "." + File.separator + "config.properties";
        try {
            properties.load(new FileInputStream(configFile));
            String polling_path = properties.getProperty("Pooling_path", "."+ File.separator +"APTGW"+ File.separator +"Data"+ File.separator +"Pooling");
            String intervaltime = properties.getProperty("intervaltime", "60");
            int i_intervaltime = parseInt(intervaltime);
            String fileType_Pooling_tmp = properties.getProperty("FileType_Pooling_tmp");
            String fileType_Pooling_txt = properties.getProperty("FileType_Pooling_txt");

            //noinspection InfiniteLoopStatement
            while (true) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.MILLISECOND, 0);
                Date begin_date = c.getTime();
                Timer timer = new Timer();
                timer.schedule(new PoolingpgActive(begin_date, i_intervaltime, polling_path,fileType_Pooling_tmp,fileType_Pooling_txt), 0);
                String ss_milli = getCurrentTime().substring(20, 23);
                //非同步轉同步 TimeUnit.MILLISECONDS.sleep
                TimeUnit.MILLISECONDS.sleep(1000 * i_intervaltime - Integer.parseInt(ss_milli));
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(date);
    }
}
