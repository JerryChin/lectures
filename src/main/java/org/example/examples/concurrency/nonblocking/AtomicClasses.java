package org.example.examples.concurrency.nonblocking;


import org.checkerframework.checker.units.qual.A;
import org.example.examples.util.Printer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static org.example.examples.util.Printer.print;

/**
 * 原子类
 */
public class AtomicClasses {

    public static void main(String[] args) throws InterruptedException {

        AtomicLong counter = new AtomicLong();

        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                counter.incrementAndGet();
                latch.countDown();
            });
        }

        latch.await();

        print("" + counter.get());

        // 其他原子类
        AtomicBoolean ready = new AtomicBoolean(true);
    }
}

