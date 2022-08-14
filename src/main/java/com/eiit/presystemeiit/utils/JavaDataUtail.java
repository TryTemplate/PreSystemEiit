package com.eiit.presystemeiit.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class JavaDataUtail {
    /**
     * 完整的堆栈信息
     *
     * @param e Exception
     * @return Full StackTrace
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }


    /**
     * 根据入参的key设置返回jsonobject keys和values的长度要一直切顺序对应
     *
     * @param dataObj his返回的对象
     * @param keys    返回的json key
     * @param values  his数据返回的键,用户去除his对象的值
     * @return
     */
    public static JSONObject setJSONObjectValue(JSONObject dataObj, String[] keys, String[] values) {

        JSONObject obj = new JSONObject();

        if (keys.length != 0 && keys.length == values.length) {
            for (int i = 0; i < keys.length; i++) {
                obj.put(keys[i], dataObj.getString(values[i]));
            }
        }

        return obj;

    }
}
