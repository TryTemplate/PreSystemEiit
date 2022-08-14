package com.eiit.presystemeiit.utils;



import com.eiit.presystemeiit.model.Emp;

import java.util.UUID;

public class TokenUtil {

    /**
     * @author liujingguang
     * @mark 根据用户对象生产token(L 24)
     *
     * @param sua sysUserAdmin
     * @return
     */
    public static String getToken(Emp sua){
        Long id = sua.getId();
        String nm = sua.getName();

        StringBuffer token = new StringBuffer(" ");

        token.append(UUID.randomUUID().toString().substring(24,36));
        token.append(String.valueOf(id.toString().hashCode()).substring(0,2));
        token.append(String.valueOf(nm.toString().hashCode()).substring(0,2));
        token.append(getCode(8));

        return token.toString().replace("-", "0").replace(" ", "");

    }

    /**
     * 获取N位随机字母数字组合
     * @Author liujingguang
     * @param int n
     * @return String code
     */
    public static String getCode(int n) {
        char arr[] = new char[n];
        int i = 0;
        while (i < n) {
            char ch = (char) (int) (Math.random() * 124);
            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                arr[i++] = ch;
            }
        }
        //将数组转为字符串
        return new String(arr);
    }

    //生成N位数字随机数
    public static Long getCodeNums(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }else if (n > 32){
            throw new IllegalArgumentException("随机数位数不大于32");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }
}
