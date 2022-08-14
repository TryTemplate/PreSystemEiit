package com.eiit.presystemeiit.netty.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Enumeration;
import java.util.Hashtable;

@Data
public class ChannelIdObj {

    public ChannelIdObj(ChannelHandlerContext obj){
        this.short_id = obj.channel().id().asShortText();
        this.long_id = obj.channel().id().asLongText();

        Hashtable<String, Channel> ha = SocketChannelSet.socketIdKey.get(this.long_id);
        if (ha == null) {
            this.eqNo = null;
        } else {
            Enumeration<String> keys = ha.keys();
            this.eqNo = keys.nextElement();
        }
    }

    ChannelIdObj(){}

    private String short_id;
    private String long_id;
    private String eqNo;
}
