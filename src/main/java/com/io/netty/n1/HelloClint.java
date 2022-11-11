package com.io.netty.n1;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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
        ChannelFuture cf = new Bootstrap()
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
            .connect(new InetSocketAddress("localhost", 8888));

        // 阻塞当前线程，直到连接建立，如果不加这个方法，下一步获取channel则会是空对象
        cf.sync();
        // 获取Channel
        Channel ch = cf.channel();
        // 向服务器发送数据
        ch.writeAndFlush("hello netty");

        /****************************
         * 不使用sync 第二种方式进行异步
         ***************************/

    }
}
