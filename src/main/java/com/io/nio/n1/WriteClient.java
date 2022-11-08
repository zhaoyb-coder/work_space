package com.io.nio.n1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zhaoyubo
 * @PackageName:com.io.nio.n1
 * @ClassName:WriteClient
 * @Description:
 * @date 2022/11/7 13:40
 */
public class WriteClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8888));

        int read = 0;
        while (true) {
            // 接收数据
            ByteBuffer bbf = ByteBuffer.allocate(1024);
            read += sc.read(bbf);
            System.out.println(read);
            bbf.clear();
        }
    }
}
