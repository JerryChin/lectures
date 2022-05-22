package org.example.examples.concurrency.executor;


import com.google.common.util.concurrent.Uninterruptibles;

import java.time.Duration;
import java.util.concurrent.*;

import static org.example.examples.util.Printer.print;

/**
 * ExecutorService 增加了管理执行器的一些方法，并且支持取消或者等待执行完成。
 */
public class ExecutorServiceClass {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future1 = executorService.submit(() -> {
            print("开始任务!");

            try {
                Thread.sleep(2000);
                print("执行任务完成!");
            } catch (InterruptedException e) {
                print("执行中断");
            }
        });

        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        future1.cancel(true);
    }
}
