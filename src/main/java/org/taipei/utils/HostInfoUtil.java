package org.taipei.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.util.Enumeration;

public class HostInfoUtil {

    Logger logger = LogManager.getLogger(getClass());


    public String getHostname() {

        try {
            String localIp = InetAddress.getLocalHost().getHostName();
            return localIp;
        } catch (UnknownHostException une) {
        }
        return null;
    }

    public String getAddress() {
        InetAddress localIp;
        try {
            localIp = InetAddress.getLocalHost();
            String ip = localIp.getHostAddress();
            return ip;
        } catch (UnknownHostException une) {
        }
        return null;
    }

    public static void main(String agrs[]) throws SocketException, UnknownHostException {
        String localIp = InetAddress.getLocalHost().getHostName();
        System.out.println(localIp);
//
//        Enumeration e = NetworkInterface.getNetworkInterfaces();
//        while(e.hasMoreElements())
//        {
//            NetworkInterface n = (NetworkInterface) e.nextElement();
//            Enumeration ee = n.getInetAddresses();
//            while (ee.hasMoreElements())
//            {
//                InetAddress i = (InetAddress) ee.nextElement();
//                System.out.println(i.getHostName().);
//            }
//        }
    }

}
