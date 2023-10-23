package org.taipei.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class FileUtil {

    Logger logger = LogManager.getLogger(getClass());

    public FileUtil(){}

    public boolean renameFile(String fromFile, String toName) {
        boolean isMoved =false;
        try {
            File fileToMove = new File(fromFile);
            isMoved = fileToMove.renameTo(new File(toName));
            if (!isMoved) {
                logger.error(toName+" rename result:"+isMoved);
            }
        } catch (Exception ex) {
            logger.error(ex);
            return false;
        }
        return isMoved;
    }
}
