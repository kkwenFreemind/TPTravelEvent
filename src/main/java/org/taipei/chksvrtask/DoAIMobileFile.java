package org.taipei.chksvrtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoAIMobileFile {
    Logger logger = LogManager.getLogger(getClass());

    private Date begin_date;
    private int interval_sec;
    private String FILEPATH;

    public DoAIMobileFile(Date date, int interval_sec,String FILEPATH) {
        this.begin_date = date;
        this.interval_sec = interval_sec;
        this.FILEPATH = FILEPATH;
    }

    public void AIM_JOB() {

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp begin_ts = new Timestamp(begin_date.getTime());
        System.out.println(begin_ts.getTime());
        System.out.println(sdFormat.format(begin_date));
        logger.info("Master AIM_JOB Begin Date:" + sdFormat.format(begin_date) + "," + begin_ts.getTime());

        Date end_date = new Date(begin_date.getTime() + interval_sec * 1000);
        Timestamp end_ts = new Timestamp(end_date.getTime());
        System.out.println(end_date.getTime());
        logger.info("Master AIM_JOB End   Date:" + sdFormat.format(end_date) + "," + end_ts.getTime());

        String FileName = sdFormat.format(begin_date) + "_" + sdFormat.format(end_date) + ".request";
        try {
            FileWriter fw = new FileWriter(FILEPATH + FileName);
            fw.write("test," + begin_ts.getTime() + "," + end_ts.getTime());
            fw.flush();
            fw.close();
        } catch (Exception ex) {

        }

    }

}
