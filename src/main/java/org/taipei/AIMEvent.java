package org.taipei;

import org.taipei.chksvrtask.DoAIMobileFile;
import org.taipei.utils.HostInfoUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

public class AIMEvent {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();


        String configFile = "." + File.separator + "config.properties";
        try {
            properties.load(new FileInputStream(configFile));
            String s_intervaltime = properties.getProperty("intervaltime", "5");
            int i_intervaltime = Integer.parseInt(s_intervaltime);

            String masterIp = properties.getProperty("master", "127.0.0.1");
            //String slaveIp = properties.getProperty("slave");
            String masterserviceUrl = properties.getProperty("masterchkservice");

            //取主機IP
            HostInfoUtil hostInfoUtil = new HostInfoUtil();
            String IPAddress = hostInfoUtil.getAddress();


            String FILEPATH = properties.getProperty("FILEPATH");

            System.out.println("My Host IP :" + IPAddress);
            System.out.println("Master IP :" + masterIp);
            System.out.println("Master Check Service Url :" + masterserviceUrl);

            boolean master_flag = false;

            if (masterIp.equalsIgnoreCase(IPAddress)) {
                master_flag = true;
            }
//            DoAIMobileAPI doAIMobileAPI = new DoAIMobileAPI(FILEPATH);
//            doAIMobileAPI.CallAIMAPI();
//            while (true) {
            DoAIMobileFile doAIMobileFile = new DoAIMobileFile(new Date(), i_intervaltime, FILEPATH);
            doAIMobileFile.AIM_JOB();
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
