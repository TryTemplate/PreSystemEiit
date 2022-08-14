package com.eiit.presystemeiit.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.eiit.presystemeiit.common.Constants;
import com.eiit.presystemeiit.netty.utils.ChannelIdObj;
import com.eiit.presystemeiit.netty.utils.SocketChannelSet;
import com.eiit.presystemeiit.netty.utils.SocketResultBean;
import com.eiit.presystemeiit.redis.RedisHelper;
import com.eiit.presystemeiit.redis.RedisHelperImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/14 3:55
 * @description default
 */

public class NettyControlHandler  extends ChannelInboundHandlerAdapter {

    private final Logger LOG = LogManager.getLogger();

    private final RedisHelper redisHelper;

    public NettyControlHandler(RedisHelperImpl redisHelper){
        this.redisHelper =  redisHelper;
    }





    /**
     * 当客户端连接服务端之后（打开链接） 获取客户端的channel，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {      //  执行顺序 1

    }


    /**
     * 客户端连接完成会回调方法
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx){                     //  执行顺序 2
        ChannelIdObj obj = new ChannelIdObj(ctx);
        LOG.info(obj.getShort_id() + " connection successful" + " ΘΘ");

        ctx.channel().writeAndFlush("connection complete !");
    }


    /**
     * 读取客户端发送的数据
     *
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ChannelIdObj obj = new ChannelIdObj(ctx);

        String msg_str = msg.toString();
        JSONObject read_obj = JSONObject.parseObject(msg_str);

        Hashtable<String, Channel> ha = SocketChannelSet.socketIdKey.get(obj.getLong_id());
        Enumeration<String> keys = ha.keys();
        String eq = keys.nextElement();

        try {
            String type = read_obj.getString("type");
            System.out.println("type:\t" + type);

            //操作完成后放行,进入下一个handler
            ctx.fireChannelRead(msg);
        } catch (Exception e) {
            LOG.error("equipment {} processing business error msg : {}", eq, msg_str);
            LOG.error("equipment {} processing business error info : \n{}\n{}", eq, e, e.getStackTrace());

            e.printStackTrace();
        }
    }

    /***
     * 读取完成
     * :::: channel中没有可读取的数据时调用 一次读取完成后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {   //    执行顺序 4

        /*  *
        ChannelIdObj obj = new ChannelIdObj(ctx);
        Hashtable<String, Channel> ha = SocketChannelSet.socketIdKey.get(obj.getLong_id());
        Enumeration keys = ha.keys();
        LOG.info("------------- 设备 {} read complete\n\n", keys.nextElement());
        //  应答回复
        ctx.channel().writeAndFlush(JSONObject.toJSON(new ResultBean(Constants.RET_CODE.SUCCESS, "success")).toString());

         */
    }

    /**
     * 客户端异常退出时调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ChannelIdObj obj = new ChannelIdObj(ctx);
        Hashtable<String, Channel> ha = SocketChannelSet.socketIdKey.get(obj.getLong_id());
        Enumeration keys = ha.keys();
        LOG.error("------------- 设备 {} 异常掉线", keys.nextElement());
        ctx.channel().close();
    }

    /**
     * 客户端关闭时调用
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx){             //    执行顺序 8
        ChannelIdObj obj = new ChannelIdObj(ctx);

        Hashtable<String, Channel> ha = SocketChannelSet.socketIdKey.get(obj.getLong_id());
        if (ha!=null) {
            Enumeration keys = ha.keys();
            LOG.error(obj.getShort_id() + "----------- 设备: {} 断开连接, line dropped ΞΞ", keys.nextElement());
        }

        //  移除 业务在线列表
        SocketChannelSet.socketIdKey.remove(obj.getLong_id());
    }

    //  客户端移除netty时调用
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {    //  执行顺序 9

    }



}
