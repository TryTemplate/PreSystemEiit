package com.eiit.presystemeiit.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liujingguang
 * @version V1.0 类说明: 获取请求的公网ip
 * @Title Snippet.java
 * @Package com.pro.huanbao.common.utils
 * @dade 2017年12月11日 下午1:59:05
 */

public class GetClientIpUtils {

    private static final Logger LOG = LogManager.getLogger();

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip.contains(",")) {
            LOG.info("---------- you ip is :\t" + ip);
            return ip.split(",")[0];
        } else {
            LOG.info("---------- you ip is :\t" + ip);
            return ip;
        }
    }

}
