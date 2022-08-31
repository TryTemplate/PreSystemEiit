package com.eiit.presystemeiit.netty.heart;

import com.alibaba.fastjson.JSONObject;
import com.eiit.presystemeiit.netty.utils.ChannelIdObj;
import com.eiit.presystemeiit.netty.utils.SocketChannelSet;
import com.eiit.presystemeiit.redis.RedisHelperImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Hashtable;

/**
 * @Anthor ljg
 * @Date 2022年4月15日16:23:58
 * @Remak 心跳&设备登录时间机制 handler
 */
public class NettyHeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static String log_access_str = "----- socket ----- ";


    private final RedisHelperImpl redisHelper;

    public NettyHeartBeatHandler(RedisHelperImpl redisHelper) {
        this.redisHelper = redisHelper;
    }

    private final Logger LOG = LogManager.getLogger(NettyHeartBeatHandler.class);


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();

        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt; // 强制类型转换

            if (event.state() == IdleState.READER_IDLE) {
                System.out.println(log_access_str + channel.id().asShortText() + " read in to free time ...");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                //  向下写空闲不处理    业务有需求才会往下写数据
                //  System.out.println(channel.id().asShortText() + " wait in to free time ...");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println(log_access_str + channel.id().asShortText() + " over time ...");
                System.out.println(log_access_str + channel.id().asShortText() + " before closing, onlies ：" + SocketChannelSet.socketIdKey.size());
                //关闭无用的channel，以防资源浪费

                channel.writeAndFlush("设备超时");
                channel.close();

                System.out.println(log_access_str + channel.id().asShortText() + " after closing, onlies ：" + SocketChannelSet.socketIdKey.size());
            }
        }
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

        String json = msg.toString();

        JSONObject read_obj;
        try {
            read_obj = JSONObject.parseObject(json);
        } catch (Exception e) {
            LOG.error("------------- 设备 {} 消息格式错误 msg :{}", obj.getEqNo(), json);
            return;
        }

        try {


            Hashtable<String, Channel> socketNoKey = new Hashtable<>();
            socketNoKey.put(ctx.channel().id().asShortText(), ctx.channel());
            SocketChannelSet.socketIdKey.put(obj.getLong_id(), socketNoKey);


            //操作完成后放行,进入下一个handler
            ctx.fireChannelRead(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
