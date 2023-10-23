package org.taipei.chksvrtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainTask {
    Logger logger = LogManager.getLogger(getClass());

    public MainTask(int intSec, boolean masterflag, String masterchkserviceUrl, String FILEPATH) {

        SleepTime(intSec);

        if (masterflag) {
            logger.info("Master API: xxxx,  start:" + getCurrentTime());
            MasterServiceTimer(intSec, FILEPATH);
        } else {
            logger.info("Slave: xxxx,  start:" + getCurrentTime());
            SlaveServiceTimer(intSec, FILEPATH, masterchkserviceUrl);
        }
    }

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(date);
    }

    public void SleepTime(int intSec) {
        //校對時間，從整秒開始執行
        String ss_sec = getCurrentTime().substring(17, 19);
        String ss_milli = getCurrentTime().substring(20, 23);
//        float sleep_sec_time = ((60 - Float.parseFloat(ss_sec + "." + ss_milli)) % intSec);
        float sleep_sec_time = ((60 - Float.parseFloat(ss_sec)) % intSec);
        logger.info("SleepTime: " + sleep_sec_time);
        try {
            Thread.sleep((long) (1000 * (sleep_sec_time)));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }


    public void MasterServiceTimer(int intSec, String FILEPATH) {

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Timer timer = new Timer();
                timer.schedule(new MasterServiceTask(intSec, FILEPATH), 0);
                String ss_milli = getCurrentTime().substring(20, 23);
                TimeUnit.MILLISECONDS.sleep(1000 * intSec - Integer.parseInt(ss_milli));
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex);
            }
        }
    }


    public void SlaveServiceTimer(int intSec, String FILEPATH, String masterchkserviceUrl) {

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Timer timer = new Timer();
                timer.schedule(new SlaveServiceTask(intSec, FILEPATH, masterchkserviceUrl), 0);
                String ss_milli = getCurrentTime().substring(20, 23);
                TimeUnit.MILLISECONDS.sleep(1000 * intSec - Integer.parseInt(ss_milli));
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex);
            }
        }
    }
}
