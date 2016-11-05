package com.runtai.testproject.utils.httputil;

/**
 * 作者：${王毅} on 2016/9/10 10:57
 * 类说明：json解析辅助类
 */
public class JsonDTO {

    private int status;

    private String log;

    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
