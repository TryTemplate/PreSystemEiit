package com.eiit.presystemeiit.netty.utils;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

@Component
public class SocketChannelSet {

    public static Hashtable<String, Hashtable<String, Channel>> socketIdKey = new Hashtable<>();

    /*//un used(hashtable support sync)*/
    public static final String lock = "lock";

    //发布状态修改锁
    public static final String push_eq_lock = "push_eq_lock";
    //撤销状态修改锁
    public static final String undo_eq_lock = "undo_eq_lock";
    //设备下线锁
    public static final String close_eq_lock = "close_eq_lock";

}
