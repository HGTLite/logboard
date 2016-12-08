package com.hgt.msg;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hgt.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供通过HTTP协议获取内容的方法 <br/>
 * 所有提供方法中的params参数在内部不会进行自动的url encode，如果提交参数需要进行url encode，请调用方法自行处理
 *
 * @author lushuifa
 * @Description: HTTP请求代理工具
 * @date 2016年11月18日 上午11:21:05
 */
public class HttpUtil {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 支持的Http method
     */
    private static enum HttpMethod {
        POST, DELETE, GET, PUT, HEAD;
    }

    ;

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String invokeUrl(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String encoding, HttpMethod method) {
        //构造请求参数字符串
        StringBuilder paramsStr = null;
        if (params != null) {
            paramsStr = new StringBuilder();
            Set<Map.Entry> entries = params.entrySet();
            for (Map.Entry entry : entries) {
                String value = (entry.getValue() != null) ? (String.valueOf(entry.getValue())) : "";
                paramsStr.append(entry.getKey() + "=" + value + "&");
            }
            //只有POST方法才能通过OutputStream(即form的形式)提交参数
            if (method != HttpMethod.POST) {
                url += "?" + paramsStr.toString();
            }
        }

        URL uUrl = null;
        HttpURLConnection conn = null;
        BufferedWriter out = null;
        BufferedReader in = null;
        try {
            //创建和初始化连接
            uUrl = new URL(url);
            conn = (HttpURLConnection) uUrl.openConnection();
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestMethod(method.toString());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            //设置读取超时时间
            conn.setReadTimeout(readTimeout);
            //指定请求header参数
            if (headers != null && headers.size() > 0) {
                Set<String> headerSet = headers.keySet();
                for (String key : headerSet) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }

            if (paramsStr != null && method == HttpMethod.POST) {
                //发送请求参数
                out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encoding));
                out.write(paramsStr.toString());
                out.flush();
            }

            //接收返回结果
            StringBuilder result = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            if (in != null) {
                String line = "";
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("调用接口[" + url + "]失败！请求URL：" + url + "，参数：" + params, e);
            //处理错误流，提高http连接被重用的几率
            try {
                byte[] buf = new byte[100];
                InputStream es = conn.getErrorStream();
                if (es != null) {
                    while (es.read(buf) > 0) {
                        ;
                    }
                    es.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //关闭连接
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * POST方法提交Http请求，语义为“增加” <br/>
     * 注意：Http方法中只有POST方法才能使用body来提交内容
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String post(String url, Map params, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, null, connectTimeout, readTimeout, charset, HttpMethod.POST);
    }

    /**
     * POST方法提交Http请求，语义为“增加” <br/>
     * 注意：Http方法中只有POST方法才能使用body来提交内容
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param headers        请求头参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String post(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, headers, connectTimeout, readTimeout, charset, HttpMethod.POST);
    }

    /**
     * GET方法提交Http请求，语义为“查询”
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String get(String url, Map params, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, null, connectTimeout, readTimeout, charset, HttpMethod.GET);
    }

    /**
     * GET方法提交Http请求，语义为“查询”
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param headers        请求头参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String get(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, headers, connectTimeout, readTimeout, charset, HttpMethod.GET);
    }

    /**
     * PUT方法提交Http请求，语义为“更改” <br/>
     * 注意：PUT方法也是使用url提交参数内容而非body，所以参数最大长度收到服务器端实现的限制，Resin大概是8K
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String put(String url, Map params, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, null, connectTimeout, readTimeout, charset, HttpMethod.PUT);
    }

    /**
     * PUT方法提交Http请求，语义为“更改” <br/>
     * 注意：PUT方法也是使用url提交参数内容而非body，所以参数最大长度收到服务器端实现的限制，Resin大概是8K
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param headers        请求头参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String put(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, headers, connectTimeout, readTimeout, charset, HttpMethod.PUT);
    }

    /**
     * DELETE方法提交Http请求，语义为“删除”
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String delete(String url, Map params, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, null, connectTimeout, readTimeout, charset, HttpMethod.DELETE);
    }

    /**
     * DELETE方法提交Http请求，语义为“删除”
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param headers        请求头参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String delete(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, headers, connectTimeout, readTimeout, charset, HttpMethod.DELETE);
    }

    /**
     * HEAD方法提交Http请求，语义同GET方法  <br/>
     * 跟GET方法不同的是，用该方法请求，服务端不返回message body只返回头信息，能节省带宽
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String head(String url, Map params, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, null, connectTimeout, readTimeout, charset, HttpMethod.HEAD);
    }

    /**
     * HEAD方法提交Http请求，语义同GET方法  <br/>
     * 跟GET方法不同的是，用该方法请求，服务端不返回message body只返回头信息，能节省带宽
     *
     * @param url            资源路径（如果url中已经包含参数，则params应该为null）
     * @param params         参数
     * @param headers        请求头参数
     * @param connectTimeout 连接超时时间（单位为ms）
     * @param readTimeout    读取超时时间（单位为ms）
     * @param charset        字符集（一般该为“utf-8”）
     * @return
     */
    public static String head(String url, Map params, Map<String, String> headers, int connectTimeout, int readTimeout, String charset) {
        return invokeUrl(url, params, headers, connectTimeout, readTimeout, charset, HttpMethod.HEAD);
    }

    /**
     * 发送json数据
     *
     * @param url
     * @param param
     * @return
     */
    public static String postJson(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {

        //region 测试第三方触发websocket消息
//        Map params = new HashMap();
//        params.put("message", "hello, b**ches");
//        String str = HttpUtil.post("http://192.168.99.75:8701/send/log-counts-streaming", params, 3000, 3000, "UTF-8");
//        System.out.println(str);
        //endregion


        //region 测试触发redis消息
//        Map<String, String> params = new HashMap<>();
//        params.put("msgTag", "TOTAL_COUNTS");
//        params.put("msgBody", "1");
//        String targetServerURL = "http://192.168.99.75:8703" + "/msg/router";
//        String str = HttpUtil.post(targetServerURL, params, 3000, 3000, "UTF-8");
//        System.out.println("已处理1条日志：" + str);
        //endregion


//        String lts = postJson("http://localhost:8702/logb/stats/app/add",
//                "{\n" +
//                        "  \"appCode\": \"hello004\",\n" +
//                        "  \"logCounts\": 2,\n" +
//                        "  \"startTime\": \"2016-12-03T14:22:07.931Z\",\n" +
//                        "  \"statsRid\": \"hell161201142249\"\n" +
//                        "}");

    }

}

