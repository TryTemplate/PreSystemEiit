package com.eiit.presystemeiit.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

    /**
     * 是否是手机号码
     *
     * @param phoneNo
     * @return
     */
    public static boolean isMobileNO(String phoneNo) {
        Pattern pattern = Pattern.compile("((^1)\\d{10}$)");
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    public static boolean isNotBlank(Object o) {
        if (o != null && !("").equals(o)) {
            return true;
        } else return false;
    }

    public static boolean isBlank(Object o) {
        if (o == null || ("").equals(o)) {
            return true;
        } else return false;
    }

    public static boolean isBlankPeplace(Object o) {
        if (o == null || ("").equals(o)) {
            return true;
        } else if (isBlank(o.toString().replace(" ", ""))) {
            return true;
        } else return false;
    }

    public static String isNotBlank(String o, String str) {
        if (o != null && !("").equals(o)) {
            return o;
        } else return str;
    }

    /**
     * @param list  元素集合
     * @param split 拼接字符串
     * @param <T>
     * @return
     * @Remark 拼接集合id
     */
    public static <T> String splicingId(List<T> list, String split) {
        if (list == null || list.size() == 0) return null;

        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            T str = list.get(i);
            sbf.append(str);
            if ((i + 1) != list.size()) sbf.append(split);
        }
        return sbf.toString();
    }


    /**
     * 拆分id集合 返回Integer数组
     *
     * @param id_str int类型的字符串
     * @param split  截取依据
     * @param isInit 是否初始化 default false
     * @return
     */
    public static List<Integer> splitToIntegers(String id_str, String split, Boolean isInit) {
        if (isInit == null) isInit = false;

        if (isBlank(id_str) || isBlank(split)) {
            if (isInit) {
                return new ArrayList<>();
            }
            return null;
        }

        String[] strs = id_str.split(split);

        if (strs.length == 0) {
            if (isInit) {
                return new ArrayList<>();
            }
            return null;
        }

        List<Integer> ids = new ArrayList<>();
        for (String _id : strs) {
            ids.add(Integer.parseInt(_id));
        }

        return ids;
    }


    /**
     * 拆分id集合 返回String数组
     *
     * @param id_str String类型的字符串
     * @param split  截取依据
     * @param isInit 是否初始化 default false
     * @return
     */
    public static List<String> splitToStrings(String id_str, String split, Boolean isInit) {
        if (isInit == null) isInit = false;

        if (isBlank(id_str) || isBlank(split)) {
            if (isInit) {
                return new ArrayList<>();
            }
            return null;
        }

        String[] strs = id_str.split(split);

        if (strs.length == 0) if (isInit) {
            return new ArrayList<>();
        }

        return Arrays.asList(strs);
    }


    /**
     * 把参数拼接在一起
     *
     * @param strs
     * @return
     */
    public static String splicingString(String... strs) {
        StringBuffer sbf = new StringBuffer();
        for (String str : strs) {
            sbf.append(str);
        }
        return sbf.toString();
    }

    /**
     * @param str
     * @return
     * @Rmark 替换mac地址 : -> _
     */
    public static String replaceSymbol(String str) {
        if (str == null || ("").equals(str)) {
            return null;
        } else {
            str = str.replace(":", "_");
        }
        return str;
    }

    /**
     * @param str
     * @return
     * @Rmark 反替换redisKey为mac地址 _ -> :
     */
    public static String backReplaceSymbol(String str) {
        if (str == null || ("").equals(str)) {
            return null;
        } else {
            str = str.replace("_", ":");
        }
        return str;
    }

    public static String getString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }


    /**
     * 文件名后缀前添加一个不重复的 时间戳+随机数
     */
    public static String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  //设置时间格式
        final SimpleDateFormat sdfsss = new SimpleDateFormat("SSS");  //设置时间格式
        String nowTimeStr = sdf.format(new Date()); // 当前时间
        nowTimeStr += TokenUtil.getCodeNums(1) + sdfsss.format(new Date()) + TokenUtil.getCodeNums(1);
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }
}
