package com.io.netty.n1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zhaoyubo
 * @PackageName:com.io.netty.n1
 * @ClassName:HelloClint
 * @Description:
 * @date 2022/11/10 14:27
 */
public class HelloClint {

    public static void main(String[] args) throws InterruptedException {
        // 客户端启动类
        new Bootstrap()
            // 添加EventLoop
            .group(new NioEventLoopGroup())
            // 添加客户端SocketChannel实现
            .channel(NioSocketChannel.class)
            // 添加处理器
            .handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                // 在连接建立后被调用
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                }
            })
            // 连接服务器
            .connect(new InetSocketAddress("localhost", 8888)).sync().channel()
            // 向服务器发送数据
            .writeAndFlush("hello netty");

    }
}
