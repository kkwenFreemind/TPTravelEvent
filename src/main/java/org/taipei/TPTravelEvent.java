package org.taipei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.taipei.chksvrtask.MainTask;
import org.taipei.utils.HostInfoUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class TPTravelEvent {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        String configFile = "." + File.separator + "config.properties";
        try {
            System.out.println(new FileInputStream(configFile));
            properties.load(new FileInputStream(configFile));
            //每幾秒做一次
            String s_intervaltime = properties.getProperty("intervaltime", "5");
            int i_intervaltime = Integer.parseInt(s_intervaltime);

            //masterIp:172.21.38.58
            String masterIp = properties.getProperty("master", "127.0.0.1");

            String masterserviceUrl = properties.getProperty("masterchkservice");

            HostInfoUtil hostInfoUtil = new HostInfoUtil();
            String IPAddress = hostInfoUtil.getAddress();

            String FILEPATH=properties.getProperty("FILEPATH");

            System.out.println("My Host IP :" + IPAddress);
            System.out.println("Master IP :" + masterIp);
            System.out.println("Master Check Service Url :" + masterserviceUrl);

            boolean master_flag = false;

            if (masterIp.equalsIgnoreCase(IPAddress)) {
                master_flag = true;
            }

            SpringApplication.run(TPTravelEvent.class, args);
            new MainTask(i_intervaltime, master_flag, masterserviceUrl,FILEPATH);


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
