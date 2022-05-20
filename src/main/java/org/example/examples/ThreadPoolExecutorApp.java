package org.example.examples;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class ThreadPoolExecutorApp {

    public static void main(String[] args ) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("test-request-" + counter.getAndIncrement());
                return thread;
            }
        }, new ThreadPoolExecutor.DiscardPolicy());


        executor.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread.sleep(100000);

    }
}
