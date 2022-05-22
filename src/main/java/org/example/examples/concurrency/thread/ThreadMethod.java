package org.example.examples.concurrency.thread;

import static org.example.examples.util.Printer.print;

/**
 * 线程方法演示
 */
public class ThreadMethod {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                print("开始睡眠！");
                Thread.sleep(10000);
                print("恢复运行！");
            } catch (InterruptedException e) {
                print("睡眠中断！");
            }
        });

        thread1.start();

        print("开始中断。");

        thread1.interrupt();

        print("等待线程退出。");
        thread1.join();

        print("结束。");
    }
}
