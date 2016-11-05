package com.runtai.testproject.utils.httputil;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 作者：${王毅} on 2016/9/9 14:50
 * 类说明：网络请求工具类
 */
public class HttpUtils {

    private static final String SERVLET_POST = "POST";
    private static final String SERVLET_GET = "GET";

    private static String prepareParam(Map<String, Object> paramMap) {
        StringBuffer sb = new StringBuffer();
        if (paramMap.isEmpty()) {
            return "";
        } else {
            for (String key : paramMap.keySet()) {
                String value = paramMap.get(key).toString();
                if (sb.length() < 1) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
            return sb.toString().trim();
        }
    }

    /**
     * GET请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static JsonDTO getRequest(String url, Map<String, Object> param) throws IOException {
        String urlStr = url;
        String strparam = prepareParam(param);
        if (null != strparam && !"".equals(strparam)) {
            urlStr += "?" + strparam;
        }
        Log.e("url", urlStr);
        URL urls = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setRequestMethod(SERVLET_GET);
        // 设置连接超时、读取超时的时间，单位为毫秒（ms）
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        //4、获取响应的输入流对象
        InputStreamReader is = new InputStreamReader(conn.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        final StringBuffer strBuffer = new StringBuffer();
        String line = null;
        //5、读取服务器返回信息
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        Log.e("Result", strBuffer.toString());
        is.close();
        conn.disconnect();
        return getJsonDTO(strBuffer.toString());
    }

    /**
     * POST请求
     *
     * @param url
     * @param parament
     * @return String
     * @throws IOException
     */
    public static JsonDTO postRequst(String url, Map<String, Object> parament) throws IOException {
        URL urls = new URL(url);
        Log.e("url", url);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setRequestMethod(SERVLET_POST);
        String paramStr = prepareParam(parament);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(paramStr.toString().getBytes("utf-8"));
        os.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        Log.e("result", result);
        br.close();
        conn.disconnect();
        return getJsonDTO(result);
    }

    /**
     * 将服务器响应字符串转换成JSON数据
     *
     * @param retSrc
     * @return JsonDTO
     */
    private static JsonDTO getJsonDTO(String retSrc) {
        // 生成 JSON 对象
        JsonDTO jsonDto = new JsonDTO();
        JSONObject result;
        try {
            result = new JSONObject(retSrc);
            jsonDto.setData(result.get("data"));
            jsonDto.setLog(result.getString("log"));
            jsonDto.setStatus(result.getInt("status"));
        } catch (JSONException e) {
            return null;
        }
        return jsonDto;
    }


    /**
     * GET请求
     *
     * @param url
     * @return String
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        Log.e("url", url);
        URL urls = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setRequestMethod(SERVLET_GET);
        // 设置连接超时、读取超时的时间，单位为毫秒（ms）
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        //4、获取响应的输入流对象
        InputStreamReader is = new InputStreamReader(conn.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        final StringBuffer strBuffer = new StringBuffer();
        String line;
        //5、读取服务器返回信息
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        Log.e("Result", strBuffer.toString());
        is.close();
        conn.disconnect();
        return strBuffer.toString();
    }

    /**
     * POST请求
     *
     * @param url
     * @return String
     * @throws IOException
     */
    public static String postRequst(String url) throws IOException {
        URL urls = new URL(url);
        Log.e("url", url);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setRequestMethod(SERVLET_POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        Log.e("result", result);
        br.close();
        conn.disconnect();
        return result;
    }

}
