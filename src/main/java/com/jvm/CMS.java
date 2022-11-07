package com.jvm;

/**
 * 记录CMS的调试过程 启动参数： -Xms20m -Xmx20m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:PrintGcDetails
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\\log
 */
public class CMS {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024 * 1024 * 15];
            System.out.println(b);
        }
    }
}
