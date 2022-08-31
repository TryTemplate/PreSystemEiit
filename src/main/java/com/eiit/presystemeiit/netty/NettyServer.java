package com.eiit.presystemeiit.netty;

import com.eiit.presystemeiit.netty.handler.NettyControlHandler;
import com.eiit.presystemeiit.netty.heart.NettyHeartBeatHandler;
import com.eiit.presystemeiit.redis.RedisHelperImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/14 3:49
 * @description default
 */

@Component
public class NettyServer {

    private final Logger LOG = LogManager.getLogger();


    @Value("${base.netty.prot}")
    private Integer prot;

    @Value("${base.netty.bossThreads}")
    private Integer bossThreads;

    @Value("${base.netty.workThreads}")
    private Integer workThreads;

    @Autowired
    private RedisHelperImpl redisHelper;


    public void start() throws InterruptedException {
        Thread th = new Thread(() -> init());
        th.start();
    }

    private void init() {
        //  创建两个线程组 老板处理连接 工人处理响应
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreads);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workThreads);

        try {

            //   创建服务端的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 20480)    //  设置最大设备连接数
                    .option(ChannelOption.TCP_NODELAY, true)
//                    .option(ChannelOption.SO_RCVBUF, 20480)     //   系统接收缓存区大小
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(128, 128, 2048))    //netty接收缓冲区bytebuff大小(单个客户端)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            //解决粘包半包问题, 4字节头文件代表长度, 后读取器头自己中长的内容长度
                            //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(2048, 0, 4, 0, 4));
                            //  解码器
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            //  编码器
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            //========================增加心跳支持 start    ========================
                            //针对客户端，如果在1分钟时没有向服务端发送写心跳(ALL)，则主动断开
                            //如果是读空闲或者写空闲，不处理
                            //超时时间，修改参数 一致即可 单位秒
                            ch.pipeline().addLast(new IdleStateHandler(21, 21, 21));
                            //自定义的空闲检测
                            ch.pipeline().addLast(new NettyHeartBeatHandler(redisHelper));
                            //========================增加心跳支持 end      ========================

                            //  自己的handler
                            ch.pipeline().addLast(new NettyControlHandler(redisHelper));
                        }
                    });

            ChannelFuture cf = bootstrap.bind(prot).sync();
            LOG.info("Netty started on port: {} (TCP) with boss thread {}, work thread {}", prot, bossThreads, workThreads);
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOG.error("netty server error ", e);
        } finally {
            // 6. 收到关闭信号后，优雅关闭server的线程池，保护应用
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            LOG.error("netty server closed ~ ");
        }

    }
}
