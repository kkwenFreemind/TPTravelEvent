package org.taipei.shareparm;

public class ShareParm {
    public ShareParm() {
        new ShareBean().loadSysConfParameters();
    }

    public static String PARM_ServletContext_PROP_FILE_PATH = "";
    public static String PARM_DEFAULT_PROP_FILE_PATH = "/opt/jboss/epay.properties";

    public String PARM_XSMS_URL = System.getProperty("PARM_XSMS_URL");
    public String PARM_XSMS_UID = System.getProperty("PARM_XSMS_UID");
    public String PARM_XSMS_PWD = System.getProperty("PARM_XSMS_PWD");
    public String PARM_XSMS_MDN = System.getProperty("PARM_XSMS_MDN");
    public String PARM_XSMS_CALLBACK = System.getProperty("PARM_XSMS_CALLBACK");
}
