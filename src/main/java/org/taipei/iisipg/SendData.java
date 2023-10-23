package org.taipei.iisipg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.HttpClientUtils;

import java.net.HttpURLConnection;

public class SendData {
    Logger logger = LogManager.getLogger(getClass());

    private final String url;
    private final String data;

    public SendData(String url, String data) {
        this.url = url;
        this.data = data;
    }

    public boolean doSendJsonData() {
        logger.info("SendJsonData新增新連線");
        //建立連線
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        HttpURLConnection conn = httpClientUtils.callPOST(url, data);
        //解析連線狀態並取出資料
        JSONObject dataObj = httpClientUtils.callGetRespData(conn);
        if (dataObj != null && dataObj.get("code").equals(0)) {
            logger.info("SendJsonData连接成功，json: " + dataObj);
            return true;
        } else {
            logger.error("SendJsonData连接失敗，送出json: " + data);
            logger.error("SendJsonData连接失敗，" + url + "回應json: " + dataObj);
            return false;
        }

    }
}
