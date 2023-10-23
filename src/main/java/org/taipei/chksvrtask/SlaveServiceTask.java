package org.taipei.chksvrtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.taipei.bean.TaskBean;

import java.net.URI;
import java.util.TimerTask;

public class SlaveServiceTask extends TimerTask {

    Logger logger = LogManager.getLogger(getClass());
    TaskBean task = new TaskBean();
    public SlaveServiceTask(int intSec, String FILEPATH, String masterchkserviceUrl) {
        task.setInterval_sec(intSec);
        task.setFILEPATH(FILEPATH);
        task.setMasterchkserviceUrl(masterchkserviceUrl);
    }

    @Override
    public void run() {
        try {
            URI uri = new URI(task.getMasterchkserviceUrl());
            RestTemplate restTemplate = new RestTemplate();
            logger.info("connect發送");
            //檢測對方url api是否存在
            String content = restTemplate.getForObject(uri, String.class);
            logger.info("connect結束");
            if ("ok".equalsIgnoreCase(content)) {
                //表示master ok
                logger.info("Master Service is :" + content);
            } else {
                //表示master 不ok
                logger.info("Master Service is DOWN");
                //定時呼叫華研API
                //DoAIMobile doAIMobile = new DoAIMobile();
                //doAIMobile.AIMJOB();

            }

        } catch (Exception ex) {
            logger.error(ex);
        }
    }
}
