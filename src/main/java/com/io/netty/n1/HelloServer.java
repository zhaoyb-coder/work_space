package com.io.netty.n1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author zhaoyubo
 * @PackageName:com.io.netty.n1
 * @ClassName:HelloServer
 * @Description:
 * @date 2022/11/9 18:56
 */
public class HelloServer {
    public static void main(String[] args) {
        // 1、启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
            // 2、放入事件的处理器
            .group(new NioEventLoopGroup())
            // 3、选择服务器的ServerSocketChannel实现，一共有四种实现
            // NioServerSocketChannel -->针对java NIO的具体实现
            // EpollServerSocketChannel -->针对linux epoll的实现
            // KQueueServerSocketChannel -->针对Mac的实现
            // OioServerSocketChannel -->针对java BIO的实现
            .channel(NioServerSocketChannel.class)
            //
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                // channel 代表和客户端进行数据读写的通道的初始化
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    // 添加具体的handler ，客户端传输是string转码byteBuffer，服务端就需要对应的string解码
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        // 读事件
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println(msg);
                        }
                    });
                }
            }).bind(8888);
    }
}
