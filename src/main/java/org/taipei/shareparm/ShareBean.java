package org.taipei.shareparm;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ShareBean {

    public void loadSysConfParameters() {

        Properties prop = new Properties();
        FileInputStream inp = null;
        try {

            String prop_file_path = ShareParm.PARM_ServletContext_PROP_FILE_PATH;
            if (prop_file_path == null || prop_file_path.equals("")) {
                prop_file_path = ShareParm.PARM_DEFAULT_PROP_FILE_PATH;
                System.out.println(ShareParm.PARM_DEFAULT_PROP_FILE_PATH);
            }
            inp = new FileInputStream(prop_file_path);

            prop.load(inp);
            Enumeration settings = prop.keys();
            while (settings.hasMoreElements()) {
                String setting = (String) settings.nextElement();
                System.getProperties().put(setting, prop.get(setting));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                inp.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
