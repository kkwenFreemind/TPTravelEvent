package org.taipei.aimpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.HttpClientUtils;

import java.net.HttpURLConnection;

public class GetData {
    Logger logger = LogManager.getLogger(getClass());

    private final String pooling_url;
    private final String sendMsg;

    public GetData(String pooling_url, String sendMsg) {
        this.pooling_url = pooling_url;
        this.sendMsg = sendMsg;
    }

    public JSONObject doGetJsonData() {
        logger.info("GetJsonData新增新連線");
        //建立連線
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        HttpURLConnection conn = httpClientUtils.callPOST(pooling_url, sendMsg);
        //解析連線狀態並取出資料
        JSONObject dataObj = httpClientUtils.callGetRespData(conn);
        logger.error("dataObj: " + dataObj);
        if (dataObj != null && dataObj.getString("Success").equals("true")) {
            return dataObj;
        } else {
            logger.error("GetJsonData连接失敗，Message: " + dataObj.getString("Message"));
            return null;
        }

    }

}
