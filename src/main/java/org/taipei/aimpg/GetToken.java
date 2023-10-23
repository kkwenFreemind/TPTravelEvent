package org.taipei.aimpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.taipei.pgutils.HttpClientUtils;

import java.net.HttpURLConnection;

public class GetToken {
    Logger logger = LogManager.getLogger(getClass());

    private final String userid;
    private final String password;
    private final String token_url;

    public GetToken(String userid, String password, String token_url) {
        this.userid = userid;
        this.password = password;
        this.token_url = token_url;
    }

    public String doGetToken(){
        JSONObject tokenObject = new JSONObject();
        tokenObject.put("UserId", userid);
        tokenObject.put("Password", password);
        logger.info("GetToken新增新連線");
        //建立連線
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        HttpURLConnection tokenConn = httpClientUtils.callPOST(token_url, tokenObject.toString());
        //解析連線狀態並取出資料
        JSONObject tokenObj = httpClientUtils.callGetRespData(tokenConn);
        if (tokenObj.getString("Success").equals("true")) {
            JSONObject data = (JSONObject) tokenObj.get("Data");
            return String.valueOf(data.get("Token"));
        } else {
            logger.error("GetToken连接失敗，Message: " + tokenObj.getString("Message"));
            return null;
        }

    }

}
