package com.io.nio.n1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author zhaoyubo
 * @PackageName:com.io.nio.n1
 * @ClassName:WriteServer
 * @Description:
 * @date 2022/11/7 13:26
 */
public class WriteServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 开启异步，防止阻塞
        ssc.configureBlocking(false);
        // 开启Selector
        Selector selector = Selector.open();
        // 监听ACCEPT事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        // 监听8888端口
        ssc.bind(new InetSocketAddress("localhost", 8888));

        while (true) {
            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 30000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer bbf = Charset.defaultCharset().encode(sb.toString());
                    // 不能保证一次性写入全部数据
                    while (bbf.hasRemaining()) {
                        int write = sc.write(bbf);
                        System.out.println(write);
                    }

                }

            }

        }
    }
}
