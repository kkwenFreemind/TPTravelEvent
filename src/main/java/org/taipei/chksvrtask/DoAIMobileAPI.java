package org.taipei.chksvrtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.taipei.utils.FileUtil;

import java.io.File;


public class DoAIMobileAPI {

    Logger logger = LogManager.getLogger(getClass());

    private final String FILEPATH;

    public DoAIMobileAPI(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public void CallAIMAPI() {

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                File file = null;
                File[] paths;
                file = new File(FILEPATH);
                logger.info("FILEPATH:" + FILEPATH);
                if (file != null && file.isDirectory()) {     // 是一個目錄
                    // 列出目錄中的全部內容

                    paths = file.listFiles();

                    if (paths != null) {
                        for (File path : paths) {
                            System.out.println(path);
                            if (path.getName().endsWith(".request")) {
                                logger.info("列出目錄中附檔名為.request全部檔案");
                                logger.info("File:" + path.getName());

                                // do something and move to another path

                                FileUtil fileUtil = new FileUtil();
                                String fromFile = FILEPATH + path.getName();
                                String toName = FILEPATH + path.getName() + ".p";
                                fileUtil.renameFile(fromFile, toName);

                            }
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }


    }
}
