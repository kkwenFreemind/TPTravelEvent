package org.taipei.pgutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.*;

public class FileUtils {
    Logger logger = LogManager.getLogger(getClass());

    //共用 確認檔案
    public boolean chkFile(String data_path, String fileType) {
        File file = new File(data_path);
        if (file.exists() && file.getName().endsWith(fileType)) {
            return true;
        }
        logger.error("[Error] find not data_path: " + data_path);
        return false;
    }

    //讀取檔案資料
    public JSONObject doFileRead(String data_path) {
        JSONObject obj = null;
        try {
            FileReader fr = new FileReader(data_path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder text = new StringBuilder();
            while (br.ready()) {
                text.append(br.readLine());
            }
            fr.close();
            obj = new JSONObject(String.valueOf(text));

//            System.out.println(obj.keys().next());
//            System.out.println(((JSONObject) obj.get("data")).get("a"));
//            System.out.println(((JSONObject) obj.get("data")).length());
//            JSONObject data = obj.getJSONObject("data");
//            String a = data.getString("a");
//            String b = data.getString("b");
//            String c = data.getString("c");
//            System.out.println(a + "," + b + "," + c);
////            JSONArray jsonArray =  obj.getJSONArray(data.names().join(""));
//
//            System.out.println(data.names());

//            for (String key : data.keySet()) {
//                String value = data.getString(key);
//                System.out.println(key + "  " + value);
//            }

        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
            e.printStackTrace();
            logger.error("[Error] doFileRead FileNotFoundException: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("[Error] doFileRead IOException: " + e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[Error] doFileRead Exception: " + e);
        }
        logger.info("Read Data: " + obj);
        return obj;
    }


    public boolean doFileRename(String data_path, String fileType, String newfileType) {
        String toName = data_path.replace(fileType, newfileType);
        File file = new File(data_path);
        File file2 = new File(toName);
//        System.out.println(data_path);
//        System.out.println(toName);
        try {
            if (file.exists()) {
                if (file2.exists()) throw new java.io.IOException(toName + " file exists");
                return file.renameTo(file2);
            } else {
                throw new java.io.IOException(data_path + " file not exists");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("[Error] doFileRename Exception: " + ex);
            return false;
        }
    }

    //檢查資料夾，沒有的話自動建立資料夾路徑
    private boolean chkFolder(String des_path) {
        File folder = new File(des_path);
        if (!folder.exists() && !folder.isDirectory()) {
            try {
                if (folder.mkdirs()) {
                    chkFolder(des_path);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("[Error] chkFolder mkdir folder: " + ex);
            }

        }
        return true;
    }

    //寫入新檔案
    public boolean doFileWrite(String des_path, String des_fileName, JSONObject newFileData, String fileType) {
        try {
            File folder = new File(des_path);
            boolean chkFolder = chkFolder(des_path);
            if (chkFolder && folder.isDirectory()) {
                FileWriter fw = new FileWriter(des_path + File.separator + des_fileName);
                fw.write(String.valueOf(newFileData));
                fw.flush();
                fw.close();
                return chkFile(des_path + File.separator + des_fileName, fileType);
            }
            logger.error("[Error] find not folder: " + des_path);
            return false;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            logger.error("[Error] FileNotFoundException: " + ex);
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("[Error] IOException: " + ex);
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("[Error] Exception: " + ex);
            return false;
        }
    }


    public boolean doMovefile(String sur_path, String des_path, String sur_fileName) {
        final String filepath = sur_path + File.separator + sur_fileName;
        final String newfilepath = des_path + File.separator + sur_fileName;
        if (chkFolder(des_path)) {
            File file = new File(filepath);
            return file.renameTo(new File(newfilepath));
        }
        return false;

    }

    public void doDefaultTxt(String data_path, String nowType) {
        doFileRename(data_path,nowType,".txt");
    }

}
