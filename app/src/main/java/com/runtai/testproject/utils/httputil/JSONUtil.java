package com.runtai.testproject.utils.httputil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：${王毅} on 2016/9/10 10:54
 * 类说明：json解析辅助类
 */
public class JSONUtil {

    public static String getString(String source) {
        if ("null".equals(source)) return null;
        else return source;
    }

    public static String getString(JSONObject object, String name) {
        try {
            return object.getString(name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
}
