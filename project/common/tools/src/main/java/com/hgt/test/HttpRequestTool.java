package com.hgt.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestTool {
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param urlStr 发送请求的 URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String urlStr, String param) throws Exception {
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        /**
         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
         */
        connection.setDoOutput(true);
        /**
         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
         */
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "UTF-8");
        out.write(param); //post的关键所在！
        // remember to clean up
        out.flush();
        out.close();
        /**
         * 这样就可以发送一个看起来象这样的POST：
         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:
         * text/plain Content-type: application/x-www-form-urlencoded
         * Content-length: 99 username=bob password=someword
         */
        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        String sCurrentLine;
        String sTotalString;
        sCurrentLine = "";
        sTotalString = "";
        InputStream l_urlStream = connection.getInputStream();
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                l_urlStream, "UTF-8"));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString += sCurrentLine;
        }
        l_urlStream.close();
        l_reader.close();
        return sTotalString;
    }

    public static void main(String[] args) throws Exception {
        String urlOrigin = "http://localhost:8080";
        String urlPostfix = "/send/message";
        String paramString = "message=f**k";
        sendPost(urlOrigin + urlPostfix, paramString);
    }
}