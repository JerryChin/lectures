package org.example.examples.concurrency.sync;

import java.util.concurrent.*;

import static org.example.examples.util.Printer.print;

public class CountDownLatchClass {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(latch::countDown);
        }

        latch.await();
        print("所有任务完成！");
    }
}
