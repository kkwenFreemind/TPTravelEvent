package org.taipei.chksvrtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.bean.TaskBean;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class MasterServiceTask extends TimerTask {

    Logger logger = LogManager.getLogger(getClass());
    TaskBean task = new TaskBean();
    public MasterServiceTask(int intSec, String FILEPATH){
        task.setInterval_sec(intSec);
        task.setFILEPATH(FILEPATH);
    }

    public void run() {
        try {
            //定時呼叫英研API
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MILLISECOND,  0 );
            Date my_date = c.getTime();

            logger.info("API Service Start time：" + my_date);

            DoAIMobileFile doAIMobile = new DoAIMobileFile(my_date,task.getInterval_sec(),task.getFILEPATH());
            doAIMobile.AIM_JOB();
            //Thread.sleep(1000 * 15);
        }catch(Exception ex){
            logger.info(ex);
        }
    }
}
