package org.example.examples.concurrency.nonblocking;


import org.example.examples.concurrency.sync.SynchronizedStatement;

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

        AtomicEvenCounter counter = new AtomicEvenCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                counter.increment();
                latch.countDown();
            });
        }

        latch.await();

        print("" + counter.get());

        // 其他原子类
        AtomicBoolean ready = new AtomicBoolean(true);
    }

    private static class AtomicEvenCounter implements SynchronizedStatement.EvenCounter {
        private final AtomicLong counter = new AtomicLong(0);


        @Override
        public void increment() {
            counter.getAndAdd(2);
        }

        @Override
        public long get() {
            return counter.get();
        }
    }
}

