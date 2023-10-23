package org.taipei.pgutils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtils {
    Logger logger = LogManager.getLogger(getClass());

    public HttpURLConnection callPOST(String httpurl, String text) {
        HttpURLConnection conn = null;
        try {
            // 创建url资源
            URL url = new URL(httpurl);
            // 建立http连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(3000);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
            byte[] data = (text).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));

            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write((text).getBytes());
            out.flush();
            out.close();
//            System.out.println(conn.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return conn;
    }

    public JSONObject callGetRespData(HttpURLConnection conn) {
        // 请求返回的状态
        try {
            logger.info("連線狀態:" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
//                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                reader.close();
                if(conn!=null) {
                    conn.disconnect();
                }
                System.out.println(String.valueOf(response));
                return new JSONObject(String.valueOf(response));
            } else {
                logger.error("连接失敗，狀態: "+conn.getResponseMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
