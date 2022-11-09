package com.io.netty.n1;

import io.netty.bootstrap.ServerBootstrap;

/**
 * @author zhaoyubo
 * @PackageName:com.io.netty.n1
 * @ClassName:HelloServer
 * @Description:
 * @date 2022/11/9 18:56
 */
public class HelloServer {
    public static void main(String[] args) {
        new ServerBootstrap().bind(8888);
    }
}
