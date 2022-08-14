package com.eiit.presystemeiit.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Ahtuor liujingguang
 * @Date 2022/6/6 0006 16:39
 * @ClassName MD5Util
 * @description default
 */

public class MD5Util {

    public final static String MD5Operation(String s) {
        try {
            byte strTemp[] = s.getBytes();

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(strTemp);

            byte b[] = md.digest();

            BigInteger bigInt = new BigInteger(1, b);

            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            return null;

        }

    }

    //利用java底层代码实现十六进制的转换
    public static String getMD5(String key) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = key.getBytes();// 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");// 使用指定的字节更新摘要
            mdInst.update(btInput);// 获得密文
            byte[] md = mdInst.digest();// 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}