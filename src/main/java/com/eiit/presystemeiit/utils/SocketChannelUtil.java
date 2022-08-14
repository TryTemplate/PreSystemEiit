package com.eiit.presystemeiit.utils;

import com.eiit.presystemeiit.netty.utils.SocketChannelSet;
import io.netty.channel.Channel;

import java.util.Hashtable;
import java.util.Set;

/**
 * @Ahtuor liujingguang
 * @Date 2022/5/6 0006 18:35
 * @ClassName SocketChannelUtil
 * @description default
 */

public class SocketChannelUtil {

    public static Channel findOnLineSocket(String no){
        Channel channel = null;

        Hashtable<String, Hashtable<String, Channel>> onLineEqSocket = SocketChannelSet.socketIdKey;
//        Hashtable<String, Hashtable<String, Channel>> onLineEqSocket = new Hashtable<>(SocketChannelSet.socketIdKey);

        Set<String> hasKeys = onLineEqSocket.keySet();
        boolean isFind = false;
        for (String hasKey : hasKeys) {
            Hashtable<String, Channel> ha = onLineEqSocket.get(hasKey);
            if (ha==null)
                continue;
            Set<String> haKeys = ha.keySet();
            for (String haKey : haKeys) {
                if (haKey.equals(no)) {
                    channel = ha.get(haKey);
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        return channel;
    }

}
